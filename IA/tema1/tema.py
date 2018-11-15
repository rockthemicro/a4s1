# -*- coding: utf-8 -*-

import yaml
import sys
from copy import copy

with open("test_input.yml", 'r') as stream:
    data_loaded = yaml.load(stream)

sevenHundredHours: int = 7 * 60
twentyFourHundredHours: int = 24 * 60


def build_domain(delta: int, add_flag: bool = False) -> list:
    domain = []

    for i in range(7):
        domain.append([])

        start = sevenHundredHours
        end = twentyFourHundredHours

        while True:

            interval_end = start + delta

            # nu vrem sa intram in dimineata cu activitatea :)
            if interval_end > end:
                break

            if add_flag:
                # True means this slot is free
                domain[i].append([start, interval_end, True])
            else:
                domain[i].append((start, interval_end))

            start = interval_end

    return domain


def get_empty_domain() -> list:
    domain = []
    for i in range(7):
        domain.append([])

    return domain


def interval_is_free(interval, slots):
    start = interval[0]
    end = interval[1]

    index = int((start - sevenHundredHours) / 5)

    while True:
        if start == end:
            return True

        if not slots[index][2]:
            return False

        index += 1
        start += 5


def occupy_interval(interval, slots):
    start = interval[0]
    end = interval[1]

    index = int((start - sevenHundredHours) / 5)

    while True:
        if start == end:
            return

        slots[index][2] = False

        index += 1
        start += 5


def free_interval(interval, slots):
    start = interval[0]
    end = interval[1]

    index = int((start - sevenHundredHours) / 5)

    while True:
        if start == end:
            return

        slots[index][2] = True

        index += 1
        start += 5


def get_index_of(time):
    index = int((time - sevenHundredHours) / 5)
    return index


c_missing_instance_week = data_loaded['costs']['c_missing_instance_week']
c_missing_instance_day = data_loaded['costs']['c_missing_instance_day']
c_relative = data_loaded['costs']['c_relative']
c_preferred_interval = data_loaded['costs']['c_preferred_interval']
c_excluded_interval = data_loaded['costs']['c_excluded_interval']
c_activity_distance = data_loaded['costs']['c_activity_distance']

print(c_missing_instance_week)
print(c_missing_instance_day)
print(c_relative)
print(c_preferred_interval)
print(c_excluded_interval)
print(c_activity_distance)

exact_activities = []  # all activities with scheduling_type == 'exact_interval'
instances_activities = []  # all activities with scheduling_type == 'nr_instances'
relative_activities = []  # all activities with scheduling_type == 'relative'
activities = []  # exact_activities + instances_activities + relative_activities

intervals = {}  # dictionary containing a domain of interval values for each day of the week for all variables
constraints = {}  # dictionary containing a list of cost/restriction functions for all variables that have them

instances_position = {}  # used by the same type of instance variables to coordinate between them
instances_of_activity = {}  # for ex, Breakfast -> [Breakfast_w0_d0, Breakfast_w1_d0, ...]


def build_exact_interval_activity(exact_interval_activity):
    name = exact_interval_activity['name']
    day = exact_interval_activity['interval']['day'] - 1
    start = exact_interval_activity['interval']['start']
    end = exact_interval_activity['interval']['end']

    # activities.append(name)
    exact_activities.append(name)

    curr_domain = get_empty_domain()
    curr_domain[day].append((start, end))

    intervals[name] = curr_domain
    constraints[name] = []

    instances_of_activity[name] = [name]


def get_cost_relative_restriction(day, start, end, solution, relative_to_instances, relative_within):
    cost = sys.maxsize
    for relative_instance in relative_to_instances:
        if relative_instance in solution:
            [relative_day, (relative_start, relative_end)] = solution[relative_instance]
            if day != relative_day:
                continue

            if relative_within < 0:
                interval_start = relative_start + relative_within
                interval_end = relative_start

            else:
                interval_start = relative_end
                interval_end = relative_end + relative_within

            new_cost = sys.maxsize
            if start <= interval_start:
                new_cost = c_relative * abs(start - interval_start)
            elif start > interval_end:
                new_cost = c_relative * abs(start - interval_end)

            if new_cost < cost:
                cost = new_cost

    if cost != sys.maxsize:
        return cost
    else:
        return None


def create_restrictions(tmp_names, generic_activity):
    curr_constraints = []
    relative_within = 1

    if 'before' in generic_activity:
        relativity = 'before'
        relative_within = -1

    elif 'after' in generic_activity:
        relativity = 'after'

    else:
        relativity = None

    if relativity is not None:
        relative_to = generic_activity[relativity]['activity_type']
        relative_to_instances = instances_of_activity[relative_to]

        relative_within *= generic_activity[relativity]['relative_within']['value']
        if generic_activity[relativity]['relative_within']['unit'] == 'hour':
            relative_within *= 60

        def f1(day,
               start,
               end,
               solution,
               relative_to_instances2=relative_to_instances,
               relative_within2=relative_within):
            return get_cost_relative_restriction(day, start, end, solution, relative_to_instances2, relative_within2)

        restriction_func = f1
        curr_constraints.append(restriction_func)

    for name in tmp_names:
        constraints[name] = curr_constraints


def build_generic_activity(generic_activity):
    name = generic_activity['name']
    tmp_names = []  # names of all variables created in this function

    if generic_activity['duration']['unit'] == 'minute':
        duration = generic_activity['duration']['value']
    else:
        duration = generic_activity['duration']['value'] * 60

    if (
            'instances_per_week' in generic_activity or
            'instances_per_day' in generic_activity or
            generic_activity['scheduling_type'] == 'relative'
    ):
        instances_week = 7
        instances_day = 1

        if 'instances_per_week' in generic_activity:
            instances_week = generic_activity['instances_per_week']

        if 'instances_per_day' in generic_activity:
            instances_day = generic_activity['instances_per_day']

        # TODO declare a global starting_cost, which grows by
        # TODO instances_week * c_missing_instance_week + instances_day * c_missing_instance_day
        # TODO for every nr_instances or relative variable you add, and then, in pcsp, when you add
        # TODO such a variable to the 'solution' dictionary, you decrease that global starting_cost accordingly
        positioning = []
        for i in range(instances_week):
            positioning.append(-1)

            # used for each set of alike variables, that should be instantiated on the same day
            for j in range(instances_day):
                # build a new instance name, and let one know that this variable is pointing to
                # the i'th element in 'positioning', which tells us what day has been chosen
                # by the instances with common days
                new_name = name + '_w' + str(i) + '_d' + str(j)
                instances_position[new_name] = (i, positioning)

                # activities.append(new_name)
                if generic_activity['scheduling_type'] == 'nr_instances':
                    instances_activities.append(new_name)
                else:
                    relative_activities.append(new_name)

                new_domain = build_domain(duration)
                intervals[new_name] = new_domain

                tmp_names.append(new_name)

    else:
        # activities.append(name)
        if generic_activity['scheduling_type'] == 'nr_instances':
            instances_activities.append(name)
        else:
            relative_activities.append(name)

        intervals[name] = build_domain(duration)

        tmp_names.append(name)

    instances_of_activity[name] = tmp_names
    create_restrictions(tmp_names, generic_activity)


global_domain = build_domain(5, True)

activities_list = data_loaded['activity_list']
for activity in activities_list:
    print(activity)

    activity = activity['activity']
    if activity['scheduling_type'] == 'exact_interval':
        build_exact_interval_activity(activity)
    else:
        build_generic_activity(activity)

print(intervals)

activities = exact_activities + instances_activities + relative_activities
print(activities)

best_cost = sys.maxsize
best_solution = {}


def pcsp(variables, domains, cost, solution, acceptable_cost):
    global best_cost
    global best_solution
    global activities_list

    # we've completed a new round of complete assignments; it's time to see if it paid off
    if not variables:
        new_cost = 0

        for variable in solution:
            if variable in constraints:
                (var_day, (var_start, var_end)) = solution[variable]
                constr = constraints[variable]

                for constr_function in constr:
                    new_cost += constr_function(var_day, var_start, var_end, solution)

        if new_cost < best_cost:
            best_cost = new_cost
            best_solution = solution

            print('New best found (' + str(new_cost) + '): ' + str(solution))

        if new_cost <= acceptable_cost:
            return True
        else:
            return False

    var = variables[0]
    domain = domains[var]

    is_first_instance = False
    var_index = -1

    if var in instances_position:
        (var_index, positioning) = instances_position[var]
        chosen_day = positioning[var_index]

        # choosing a day from the week, which has not been chosen by instances of the same type
        # of activity as the current one
        if chosen_day == -1:
            is_first_instance = True
            indexes_to_remove = [False for _ in range(7)]

            # we mark for removal those days that are already chosen by other instances that mustn't be in the
            # same day with us
            for i in range(len(positioning)):
                if i != var_index and positioning[i] != -1:
                    indexes_to_remove[i] = True

            # TODO seteaza in indexes_to_remove pe true si zilele care NU au instante de care depinde
            # TODO variabila curenta in cazul in care variabila curenta e de tipul relative
            # TODO relative_to = instances_of_activity[var]

            empty_domain = get_empty_domain()
            for i in range(7):
                if not indexes_to_remove[i]:
                    empty_domain[i] = domain[i]

            domain = empty_domain

        else:
            empty_domain = get_empty_domain()
            empty_domain[chosen_day] = domain[chosen_day]
            domain = empty_domain

    ret = False
    # if we have multiple instances of the current variable, we have to specify in 'positioning' the day we
    # have chosen for the current variable, so that the other instances know if they have to choose this day
    # as well, or if they have to choose a different one
    if is_first_instance:
        for i in range(7):
            day_domain = domain[i]
            instances_position[var][1][var_index] = i

            for interval in day_domain:
                if interval_is_free(interval, global_domain[i]):
                    occupy_interval(interval, global_domain[i])

                    new_solution = copy(solution)
                    new_solution[var] = (i, interval)
                    ret = ret or pcsp(variables[1:], domains, cost, new_solution, acceptable_cost)

                    free_interval(interval, global_domain[i])

        instances_position[var][1][var_index] = -1

    else:
        for i in range(7):
            day_domain = domain[i]

            for interval in day_domain:
                if interval_is_free(interval, global_domain[i]):
                    occupy_interval(interval, global_domain[i])

                    new_solution = copy(solution)
                    new_solution[var] = (i, interval)
                    ret = ret or pcsp(variables[1:], domains, cost, new_solution, acceptable_cost)

                    free_interval(interval, global_domain[i])

    return ret


pcsp(activities, intervals, 0, {}, 0)


# global_domain[1][get_index_of(8 * 60 + 10)][2] = False
# print(global_domain)
# print(interval_is_free((8 * 60 + 15, 9 * 60 + 15), global_domain[1]))
