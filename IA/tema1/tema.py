# -*- coding: utf-8 -*-

import yaml
import sys
import time
from copy import copy

with open("test_input2.yml", 'r') as stream:
    data_loaded = yaml.load(stream)

sevenHundredHours: int = 7 * 60
twentyFourHundredHours: int = 24 * 60


# creates a list of 7 lists, containing all possible intervals of duration 'delta', with 5 min increments
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

            start += 5
            # start = interval_end

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


# returns the intersection of 2 intervals in minutes
def intersection_duration(a1, b1, a2, b2):
    start = max(a1, a2)
    end = min(b1, b2)

    if end < start:
        return 0

    return end - start


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

cost_of_all_missing_instances = 0

exact_activities = []  # all activities with scheduling_type == 'exact_interval'
instances_activities = []  # all activities with scheduling_type == 'nr_instances'
relative_activities = []  # all activities with scheduling_type == 'relative'
activities = []  # exact_activities + instances_activities + relative_activities

intervals = {}  # dictionary containing a domain of interval values for each day of the week for all variables
constraints = {}  # dictionary containing a list of cost/restriction functions for all variables that have them
binary_constraints = {}  # dictionary containing a list of binary cost functions

instances_position = {}  # used by the same type of instance variables to coordinate between them
instances_of_activity = {}  # for ex, Breakfast -> [Breakfast_w0_d0, Breakfast_w1_d0, ...]

relative = {}  # for ex, Medication is relative to Dinner means relative['Medication'] == 'Dinner'

# for ex, Medication is relative to Dinner means depends_on['Medication'] has 'Dinner'
# and if Medication needs to be at a minimum distance from Breakfast means depends_on['Medication'] has Break
is_needed_by = {}


# creates variable, domain and constraint for an exact_interval activity
def build_exact_interval_activity(exact_interval_activity):
    name = exact_interval_activity['name']
    day = exact_interval_activity['interval']['day'] - 1
    start = exact_interval_activity['interval']['start']
    end = exact_interval_activity['interval']['end']

    exact_activities.append(name)

    curr_domain = get_empty_domain()
    curr_domain[day].append((start, end))

    intervals[name] = curr_domain
    constraints[name] = []

    instances_of_activity[name] = [name]

    is_needed_by[name] = []


# given a day, start time, end time and a partial solution, we calculate the cost of breaking a 'relative'
# activity constraint (relative_to_instances and relative_within are always set by default when adding this
# function to a list of functions)
def get_cost_relative_restriction(day, start, _, solution, relative_to_instances, relative_within):
    cost = sys.maxsize
    for relative_instance in relative_to_instances:
        if relative_instance not in solution:
            continue

        [relative_day, (relative_start, relative_end)] = solution[relative_instance]
        if day != relative_day:
            continue

        if relative_within < 0:
            interval_start = relative_start + relative_within
            interval_end = relative_start

        else:
            interval_start = relative_end
            interval_end = relative_end + relative_within

        if start <= interval_start:
            new_cost = c_relative * abs(start - interval_start) * 60
        elif start > interval_end:
            new_cost = c_relative * abs(start - interval_end) * 60
        else:
            cost = 0
            break

        if new_cost < cost:
            cost = new_cost

    if cost != sys.maxsize:
        return cost
    else:
        return 0


# cost of breaking unary restriction 'preferred_intervals'
def get_cost_preferred_intervals(day, start, end, _, preferred_intervals):
    interval_time = end - start
    cost = c_preferred_interval

    for interval in preferred_intervals:
        interval = interval['interval']
        if 'day' in interval and interval['day'] != day:
            continue

        diff = interval_time - intersection_duration(start, end, interval['start'], interval['end'])
        tmp_cost = (diff * c_preferred_interval) / interval_time

        if tmp_cost == 0:
            cost = 0
            break

        elif tmp_cost < cost:
            cost = tmp_cost

    return cost


# cost of breaking unary restriction 'excluded_intervals'
def get_cost_excluded_intervals(day, start, end, _, excluded_intervals):
    interval_time = end - start
    sum = 0

    for interval in excluded_intervals:
        interval = interval['interval']
        if 'day' in interval and interval['day'] != day:
            continue

        sum += intersection_duration(start, end, interval['start'], interval['end'])

    return c_excluded_interval * sum / interval_time


# cost of breaking binary restriction 'minimal_distance_from'
def get_cost_minimal_distance(day, start, end, solution, minimal_distance_from, name):
    cost = 0

    for target in minimal_distance_from:
        value = target['value']
        unit = target['unit']

        if target['activity_type'] == 'self':
            self_instances = instances_of_activity[name]  # dinner_w0_d0, dinner_w1_d0
            for self_instance in self_instances:
                if self_instance not in solution:
                    continue

                (s_day, (s_start, s_end)) = solution[self_instance]
                if s_day == day and s_start == start and s_end == end:
                    continue

                aux_start = start
                aux_end = end

                if unit == 'day' and abs(day - s_day) < value:
                    start += (day * 24 * 60)
                    end += (day * 24 * 60)

                    s_start += (s_day * 24 * 60)
                    s_end += (s_day * 24 * 60)

                    if s_start >= end:
                        cost += (intersection_duration(s_start, s_end, end, end + value * 24 * 60) / 2)
                    elif s_end < start:
                        cost += (intersection_duration(s_start, s_end, start - value * 24 * 60, start) / 2)

                else:
                    if unit == 'hour':
                        value *= 60

                    if s_start >= end:
                        cost += (intersection_duration(s_start, s_end, end, end + value))
                    elif s_end < start:
                        cost += (intersection_duration(s_start, s_end, start - value, start))

                start = aux_start
                end = aux_end

        else:
            target_instances = instances_of_activity[target['activity_type']]
            for target_instance in target_instances:
                if target_instance not in solution:
                    continue

                (s_day, (s_start, s_end)) = solution[target_instance]

                if unit == 'day' and abs(day - s_day) < value:
                    start += (day * 24 * 60)
                    end += (day * 24 * 60)

                    s_start += (s_day * 24 * 60)
                    s_end += (s_day * 24 * 60)

                    if s_start >= end:
                        cost += (intersection_duration(s_start, s_end, end, end + value * 24 * 60))
                    elif s_end <= start:
                        cost += (intersection_duration(s_start, s_end, start - value * 24 * 60, start))

                else:
                    if unit == 'hour':
                        value *= 60

                    if s_start >= end:
                        cost += (intersection_duration(s_start, s_end, end, end + value))
                    elif s_end < start:
                        cost += (intersection_duration(s_start, s_end, start - value, start))

    return cost * c_activity_distance


# creates a list of restrictions for an exact_interval or relative activity
# tmp_names is a list of all the names that a variable can appear under (e.g. if we have a relative Breakfast
# activity, with instances_per_week: 3 and instances_per_day: 2, tmp_names will be [Breakfast_w0_d0, Breakfast_w0_d1,
# Breakfast_w1_d0, Breakfast_w1_d1, Breakfast_w2_d0, Breakfast_w2_d1]
def create_restrictions(tmp_names, generic_activity):
    curr_constraints = []
    curr_binary_constraints = []
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

        curr_constraints.append(f1)
        curr_binary_constraints.append(f1)

    if 'preferred_intervals' in generic_activity:
        preferred_intervals = generic_activity['preferred_intervals']

        def f2(day, start, end, solution, preferred_intervals2=preferred_intervals):
            return get_cost_preferred_intervals(day, start, end, solution, preferred_intervals2)

        curr_constraints.append(f2)

    if 'excluded_intervals' in generic_activity:
        excluded_intervals = generic_activity['excluded_intervals']

        def f3(day, start, end, solution, excluded_intervals2=excluded_intervals):
            return get_cost_excluded_intervals(day, start, end, solution, excluded_intervals2)

        curr_constraints.append(f3)

    if 'minimal_distance_from' in generic_activity:
        minimal_distance_from = generic_activity['minimal_distance_from']
        targets = []
        target_names = []

        for target_activity in minimal_distance_from:
            target_name = target_activity['activity']
            targets.append(target_name)
            target_names.append(target_activity['activity']['activity_type'])

        for tmp_name in tmp_names:
            is_needed_by[tmp_name] = target_names

        def f4(day, start, end, solution, minimal_distance_from2=targets, name2=generic_activity['name']):
            return get_cost_minimal_distance(day, start, end, solution, minimal_distance_from2, name2)

        curr_constraints.append(f4)
        curr_binary_constraints.append(f4)

    for name in tmp_names:
        constraints[name] = curr_constraints
        binary_constraints[name] = curr_binary_constraints


# creates variable, domain and constraint for an exact_interval or relative activity
def build_generic_activity(generic_activity):
    name = generic_activity['name']
    dependencies = []
    tmp_names = []  # names of all variables created in this function

    if generic_activity['duration']['unit'] == 'minute':
        duration = generic_activity['duration']['value']
    else:
        duration = generic_activity['duration']['value'] * 60

    if (
            'instances_per_week' in generic_activity or
            'instances_per_day' in generic_activity or
            generic_activity['scheduling_type'] == 'nr_instances'
    ):
        instances_week = 7
        instances_day = 1

        if 'instances_per_week' in generic_activity:
            instances_week = generic_activity['instances_per_week']

        if 'instances_per_day' in generic_activity:
            instances_day = generic_activity['instances_per_day']

        global cost_of_all_missing_instances
        cost_of_all_missing_instances += (instances_week * c_missing_instance_week +
                                          instances_week * instances_day * c_missing_instance_day)

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
        relative_activities.append(name)

        intervals[name] = build_domain(duration)

        tmp_names.append(name)

    instances_of_activity[name] = tmp_names
    create_restrictions(tmp_names, generic_activity)

    if generic_activity['scheduling_type'] == 'relative':
        if 'before' in generic_activity:
            for tmp_name in tmp_names:
                relative[tmp_name] = generic_activity['before']['activity_type']

            dependencies.append(generic_activity['before']['activity_type'])

        elif 'after' in generic_activity:
            for tmp_name in tmp_names:
                relative[tmp_name] = generic_activity['after']['activity_type']

            dependencies.append(generic_activity['after']['activity_type'])

        for tmp_name in tmp_names:
            is_needed_by[tmp_name] = is_needed_by.get(tmp_name, []) + dependencies


global_domain = build_domain(5, True)

activities_list = data_loaded['activity_list']
for activity in activities_list:
    activity = activity['activity']
    if activity['scheduling_type'] == 'exact_interval':
        build_exact_interval_activity(activity)
    else:
        build_generic_activity(activity)


# heuristic for ordering variables
def order_variables(non_relatives, relatives):
    for i in range(len(non_relatives)):
        for j in range(i + 1, len(non_relatives)):
            dom_i = intervals[non_relatives[i]]
            dom_j = intervals[non_relatives[j]]

            sum_i = 0
            sum_j = 0
            for k in range(7):
                sum_i += len(dom_i[k])
                sum_j += len(dom_j[k])

            if sum_i > sum_j:
                aux = non_relatives[i]
                non_relatives[i] = non_relatives[j]
                non_relatives[j] = aux

    for i in range(len(relatives)):
        for j in range(i + 1, len(relatives)):
            dom_i = intervals[relatives[i]]
            dom_j = intervals[relatives[j]]

            sum_i = 0
            sum_j = 0
            for k in range(7):
                sum_i += len(dom_i[k])
                sum_j += len(dom_j[k])

            if sum_i > sum_j:
                aux = relatives[i]
                relatives[i] = relatives[j]
                relatives[j] = aux


# we group exact_activities and instances_activities together because we always want them to be
# planned before relative_activities
non_relative_activities = exact_activities + instances_activities

# variable ordering step
order_variables(non_relative_activities, relative_activities)
# end of variable ordering

activities = non_relative_activities + relative_activities

best_cost = sys.maxsize
best_solution = {}

cnt = 0


def get_cost_of_all_restrictions(partial_solution):
    new_cost = 0

    for variable in partial_solution:
        if variable in constraints:
            (var_day, (var_start, var_end)) = partial_solution[variable]
            constr = constraints[variable]

            for constr_function in constr:
                new_cost += constr_function(var_day, var_start, var_end, partial_solution)

    return new_cost


def get_cost_of_all_binary_restrictions(partial_solution):
    new_cost = 0

    for variable in partial_solution:
        if variable in binary_constraints:
            (var_day, (var_start, var_end)) = partial_solution[variable]
            constr = binary_constraints[variable]

            for constr_function in constr:
                new_cost += constr_function(var_day, var_start, var_end, partial_solution)

    return new_cost


def revise3(vars, head, domains):
    v_i = domains[head[0]]
    v_k = domains[head[1]]
    v_j = domains[head[2]]

    has_changed = False
    solution_ij = {head[0]: None, head[2]: None}
    solution_ik = {head[0]: None, head[1]: None}
    solution_jk = {head[2]: None, head[1]: None}

    name_i = head[0]
    name_k = head[1]
    name_j = head[2]

    for i1 in range(7):
        day_domain_i = v_i[i1]
        new_day_domain_i = []
        for i2 in range(len(day_domain_i)):
            if not interval_is_free(day_domain_i[i2], global_domain[i1]):
                continue

            occupy_interval(day_domain_i[i2], global_domain[i1])

            for j1 in range(7):
                day_domain_j = v_j[j1]
                new_day_domain_j = []
                for j2 in range(len(day_domain_j)):
                    if not interval_is_free(day_domain_j[j2], global_domain[j1]):
                        continue

                    occupy_interval(day_domain_j[j2], global_domain[j1])

                    solution_ij[name_i] = (i1, day_domain_i[i2])
                    solution_ij[name_j] = (j1, day_domain_j[j2])

                    keep_ij = False

                    cost1 = get_cost_of_all_binary_restrictions(solution_ij)
                    if cost1 != 0:
                        free_interval(day_domain_j[j2], global_domain[j1])
                        continue

                    for k1 in range(7):
                        day_domain_k = v_k[k1]
                        for k2 in range(len(day_domain_k)):
                            if not interval_is_free(day_domain_k[k2], global_domain[k1]):
                                continue

                            occupy_interval(day_domain_k[k2], global_domain[k1])

                            solution_ik[name_i] = (i1, day_domain_i[i2])
                            solution_ik[name_k] = (k1, day_domain_k[k2])

                            cost2 = get_cost_of_all_binary_restrictions(solution_ik)
                            if cost2 != 0:
                                free_interval(day_domain_k[k2], global_domain[k1])
                                continue

                            solution_jk[name_j] = (j1, day_domain_j[j2])
                            solution_jk[name_k] = (k1, day_domain_k[k2])

                            cost3 = get_cost_of_all_binary_restrictions(solution_jk)
                            if cost3 != 0:
                                free_interval(day_domain_k[k2], global_domain[k1])
                                continue

                            keep_ij = True
                            free_interval(day_domain_k[k2], global_domain[k1])
                            break

                        if keep_ij:
                            break

                    if not keep_ij:
                        has_changed = True
                    else:
                        new_day_domain_i.append(day_domain_i[i2])
                        new_day_domain_j.append(day_domain_j[j2])

                    free_interval(day_domain_j[j2], global_domain[j1])

                v_j[j1] = new_day_domain_j

            free_interval(day_domain_i[i2], global_domain[i1])

        v_i[i1] = new_day_domain_i

    return has_changed


# PC-2 algorithm
def apply_path_consistency(vars, domains):
    n = len(vars)
    queue = []

    for i in range(n - 1):
        for j in range(i + 1, n):

            for k in range(n):
                if k != i and k != j:
                    queue.append((vars[i], vars[k], vars[j]))

    while len(queue) > 0:
        head = queue[0]
        queue.pop(0)

        has_changed = revise3(vars, head, domains)

        if has_changed:
            for l in range(n):
                if vars[l] != head[0] and vars[l] != head[2]:
                    queue.append((vars[l], head[0], head[2]))
                    queue.append((vars[l], head[2], head[0]))


def pcsp(variables, domains, cost, solution, acceptable_cost, path):
    global best_cost
    global best_solution
    global activities_list
    global start_time
    global counter

    elapsed_time = time.time() - start_time
    if elapsed_time >= 1:
        counter += 1
        if counter == 60:
            exit(0)

        start_time = time.time()

        print(str(counter) + ' ' + str(cost + get_cost_of_all_restrictions(solution)))

    # new_cost = cost + get_cost_of_all_restrictions(solution)
    new_cost = get_cost_of_all_restrictions(solution)
    if new_cost >= best_cost:
        return False

    # we've completed a new round of complete assignments; it's time to see if it paid off
    if not variables:
        best_cost = new_cost
        best_solution = solution

        print('New best found (' + str(new_cost) + '): ' + str(solution))

        if new_cost <= acceptable_cost:
            return True
        else:
            return False

    var = variables[0]
    domain = domains[var]

    path = path + [var]

    is_first_instance = False
    var_index = -1

    # if we have multiple instances for a certain activity, we need to be careful about when we plan it
    if var in instances_position:
        (var_index, positioning) = instances_position[var]
        chosen_day = positioning[var_index]

        # choosing a day from the week, which has not been chosen by instances of the same type
        # of activity as the current one
        if chosen_day == -1:
            is_first_instance = True
            empty_domain = get_empty_domain()

            # we only choose days later in the week than the latest day we already find planned
            loc_max = -1
            for i in range(len(positioning)):
                if i != var_index and positioning[i] > loc_max:
                    loc_max = positioning[i]

            for i in range(loc_max + 1, 7):
                empty_domain[i] = domain[i]

            domain = empty_domain

            cost -= (c_missing_instance_day + c_missing_instance_week)

        else:
            empty_domain = get_empty_domain()
            empty_domain[chosen_day] = domain[chosen_day]
            domain = empty_domain

            cost -= c_missing_instance_day

    # if it is a relative activity, we don't want to allow days for which the activity that our
    # relative activity is relative to isn't planned
    if var in relative_activities:
        indexes_to_remove = [True for _ in range(7)]
        empty_domain = get_empty_domain()

        relative_to = relative[var]
        relative_to_instances = instances_of_activity[relative_to]

        for inst in relative_to_instances:
            i = solution[inst][0]
            indexes_to_remove[i] = False

        for i in range(7):
            if not indexes_to_remove[i]:
                empty_domain[i] = domain[i]

        domain = empty_domain

    # value ordering step
    costs = get_empty_domain()
    empty_domain = get_empty_domain()
    for i in range(7):
        day_domain = domain[i]
        for interval in day_domain:
            if interval_is_free(interval, global_domain[i]):
                solution[var] = (i, interval)

                cost = get_cost_of_all_restrictions(solution)

                if cost < best_cost:
                    costs[i].append(cost)
                    empty_domain[i].append(interval)

                del solution[var]

        for j1 in range(len(costs[i])):
            for j2 in range(j1 + 1, len(costs[i])):
                if costs[i][j1] > costs[i][j2]:
                    aux = costs[i][j1]
                    costs[i][j1] = costs[i][j2]
                    costs[i][j2] = aux

                    aux = empty_domain[i][j1]
                    empty_domain[i][j1] = empty_domain[i][j2]
                    empty_domain[i][j2] = aux

    domain = empty_domain
    # end of value ordering

    ret = False
    # if we have multiple instances of the current variable, we have to specify in 'positioning' the day we
    # have chosen for the current variable, so that the other instances know if they have to choose this day
    # as well, or if they have to choose a different one
    if is_first_instance:
        for i in range(7):
            day_domain = domain[i]

            if len(day_domain) == 0:
                continue

            instances_position[var][1][var_index] = i

            for interval in day_domain:
                if interval_is_free(interval, global_domain[i]):
                    occupy_interval(interval, global_domain[i])

                    new_solution = copy(solution)
                    new_solution[var] = (i, interval)
                    ret = ret or pcsp(variables[1:], domains, cost, new_solution, acceptable_cost, path)

                    free_interval(interval, global_domain[i])

        instances_position[var][1][var_index] = -1

    else:
        for i in range(7):
            day_domain = domain[i]

            if len(day_domain) == 0:
                continue

            for interval in day_domain:
                if interval_is_free(interval, global_domain[i]):
                    occupy_interval(interval, global_domain[i])

                    new_solution = copy(solution)
                    new_solution[var] = (i, interval)
                    ret = ret or pcsp(variables[1:], domains, cost, new_solution, acceptable_cost, path)

                    free_interval(interval, global_domain[i])

    return ret


# apply_path_consistency(activities, intervals)
# print(intervals)

start_time = time.time()
counter = 0

pcsp(activities, intervals, cost_of_all_missing_instances, {}, 0, [])


# global_domain[1][get_index_of(8 * 60 + 10)][2] = False
# print(global_domain)
# print(interval_is_free((8 * 60 + 15, 9 * 60 + 15), global_domain[1]))
