#!/usr/bin/env python
# coding: utf-8

# # Laboratorul 3: Satisfacerea parțială a restricțiilor
#  - Tudor Berariu
#  - Andrei Olaru

# In[1]:


from copy import copy, deepcopy
from itertools import combinations


# ## O problemă de satisfacere a restricțiilor
# 
# O **problemă de satisfacere a restricțiilor** este definită de:
#  - o mulțime discretă de **variabile**
#  - câte un **domeniu de valori** pentru fiecare variabilă
#  - un set de **constrângeri** impuse asupra unor grupuri de variabile
#  
# Vom reprezenta în Python cele de mai sus astfel:
#  - fiecare variabilă va fi reprezentată printr-un șir de caractere
# 
# ```
# Vars = ["A", "B", "C"]
# ```
#  - mulțimea domeniilor va fi un dicționar având câte o intrare pentru fiecare variabilă:
#     + cheie va fi numele variabilei
#     + valoare va fi domeniul de valori al acelei variabile
# 
# ```
# Domains = {"A": [1, 2, 3], "B": [1, 5, 9], "C": [-2, -1]}
# ```
#  - o constrângere va fi reprezentată printr-un tuplu format din:
#     + lista de variabile implicată în constrângere
#     + o funcție anonimă care întoarce `True` sau `False`
# 
# ```
# Constraints = [(["A", "B", "C"], lambda a, b, c: a + b + c == 0)]
# ```
# 
# Vom reprezenta o **soluție** printr-un dicționar ce indică o valoare pentru fiecare variabilă (e.g. `{"A": 1, "B": 1, "C" -2}`) și vom defini **costul** ca fiind egal cu numărul de constrângeri încălcate de acea soluție.

# In[2]:
from typing import List, Any, Tuple, Callable

VarsA = ["A", "B", "C", "D", "E"]
DomainsA = {v: [i for i in range(10)] for v in VarsA}
ConstraintsA: List[Tuple[List[str], Callable[[Any, Any], bool]]] = [(list(p), lambda x,y: x != y) for p in combinations(VarsA, 2)] # toate valorile diferite
ConstraintsA.append((["A","B"], lambda a, b: a + b == 10))
ConstraintsA.append((["B","D"], lambda b, d: b + d == 6))
ConstraintsA.append((["C"], lambda c: c < 5))
ConstraintsA.append((["A"], lambda a: a > 5))
ConstraintsA.append((["A","B","C","D","E"], lambda a, b, c, d, e: a + b + c + d + e == 30))
MathProblem = {"Vars": VarsA, "Domains": DomainsA, "Constraints": ConstraintsA}


# In[3]:


VarsC = ["France", "Germany", "Loux", "Belgium", "Netherlands"]
DomainsC = {v: ["blue", "red", "yellow", "green"] for v in VarsC}
ConstraintsC = []
for (a, b) in [("France", "Germany"), ("France", "Belgium"), ("France", "Loux"),
               ("Belgium", "Netherlands"), ("Belgium", "Loux"), ("Belgium", "Germany"),
               ("Loux", "Germany"), ("Netherlands", "Germany")]:
    ConstraintsC.append(([a, b], lambda a, b: a != b))
ColoringProblem = {"Vars": VarsC, "Domains": DomainsC, "Constraints": ConstraintsC}


# ## Cerința 1
# 
# Implementați funcția `get_constraints` care primește o variabilă `var` și o listă de constrângeri `constraints` și întoarce doar acele constrângeri care implică variabila dată.
# ```
# Constraints = [(["A", "B"], lambda a,b: a<b), (["A"], lambda a: a<5)]
# get_constraints("B", Constraints)
# ==> [(["A", "B"], lambda a,b: a<b)]
# get_constraints("A", Constraints)
# ==> [(["A", "B"], lambda a,b: a<b), (["A"], lambda a: a<5)]
# ```

# In[4]:


def get_constraints(var, constraints):
    # TODO
    result = []
    for constraint in constraints:
        vars = constraint[0]
        if var in vars:
            result.append(constraint)
    
    return result

get_constraints("France", ConstraintsC) # => [(['France', 'Germany'], ...), (['France', 'Belgium'], ...), (['France', 'Loux'], ...)]


# ## Cerința 2
# 
# Implementați funcția `fixed_constraints` care primește o soluție parțială `solution` și un set de constrângeri `constraints` și întoarce doar acele constrângeri care pot fi evaluate (i.e. variabilele implicate se regăsesc în soluția parțială).

# In[5]:


def fixed_constraints(solution, constraints):
    # TODO
    result = []
    tmp_constraints = []
    vars_in_solution = []
    
    for pair in solution:
        pair_constraints = get_constraints(pair, constraints)
        
        if pair_constraints != []:
            for c in pair_constraints:
                if c not in tmp_constraints:
                    tmp_constraints.append(c)
    
    
        vars_in_solution.append(pair)
 
    if c not in tmp_constraints:
                    tmp_constraints.append(c)
    
    for constraint in tmp_constraints:
        vars = constraint[0]
        can_be_evaluated = True
        
        for var in vars:
            if var not in vars_in_solution:
                can_be_evaluated = False
                break
            
        if can_be_evaluated == True:
            result.append(constraint)

    return result

print(fixed_constraints({"France": "blue", "Belgium": "green"}, ConstraintsC)) # => [(['France', 'Belgium'], ...)]
print(fixed_constraints({"A": "1", "C": "2"}, ConstraintsA)) # => [(['A', 'C'], ...), (['C'], ...), (['A'], ...)]


# ## Cerința 3
# 
# Implementați funcția `check_constraint` care primește o constrângere `constraint` și o soluție parțială `solution` și întoarce `True` dacă soluția respectă constrângerea și `False` altfel.
# 
# **Hint:** utilizați `apply` în Python 2 sau notația cu steluță în Python 3 (vezi [aici](http://www.diveintopython3.net/porting-code-to-python-3-with-2to3.html#apply)).

# In[6]:


def check_constraint(solution, constraint):
    # TODO
    vals = constraint[0]
    lambda_func = constraint[1]
    mylist = []
    
    for val in vals:
        mylist.append(solution[val])
    
    #return apply(lambda_func, tuple(mylist))
    return lambda_func(*mylist)

print(check_constraint({"France": "blue", "Belgium": "green"}, ConstraintsC[1]))  # => True
print(check_constraint({"France": "blue", "Belgium": "blue"}, ConstraintsC[1]))  # => False


# ## Cerința 4: PCSP
# 
# Completați ceea ce lipsește în algoritmul PCSP.
# 
# * `vars` -- variabilele care mai rămân de verificat
# * `domains` -- domeniile pentru variabilele care mai rămân de verificat, cu valorile care mai rămân de verificat pentru fiecare variabilă
# * `constrains` -- lista de constrângeri
# * `acceptable_cost` -- costul pentru care se consideră ca soluția este satisfăcătoare
# * `solution` -- soluția parțială, conținând valori pentru variabilele verificate până acum
# * `cost` -- costul soluției parțiale (`solution`) -- numărul de constrângeri nesatisfăcute
# 
# Valoare întoarsă de funcție este `True` dacă a fost găsită o soluție completă satisfăcătoare (vezi costul acceptabil), și `False` altfel.
# 
# Se folosesc două variabile globale:
# * `best_cost` -- cel mai bun (mic) cost întâlnit până acum în explorare, pentru o soluție completă
# * `best_solution` -- soluția corespunzătoare celui mai bun cost

# In[7]:


def PCSP(vars, domains, constraints, acceptable_cost, solution, cost):
    global best_solution
    global best_cost
    if not vars:
        # Dacă nu mai sunt variabile, am ajuns la o soluție mai bună
        print("New best: " + str(cost) + " - " + str(solution))
        
        # TODO: salvați soluția nou-descoperită
        best_solution = solution
        best_cost = cost
        
        # TODO: dacă este suficient de bună, funcția întoarce True
        if cost <= acceptable_cost:
            return True
        else:
            return False
        
    elif not domains[vars[0]]:
        # Dacă nu mai sunt valori în domeniu, am terminat căutarea
        return False
    elif cost == best_cost:
        # Dacă am ajuns deja la un cost identic cu cel al celei mai bune soluții, nu mergem mai departe
        return False
    else:
        # TODO: Luăm prima variabilă și prima valoare din domeniu
        var = vars[0]
        val = domains[var].pop(0)

        # TODO: Construim noua soluție
        new_solution = deepcopy(solution)
        new_solution[var] = val

        # TODO: Obținem lista constrângerilor ce pot fi evaluate acum
        fixed_cons = fixed_constraints(new_solution, constraints)
        
        # TODO:  Calculăm costul noii soluții parțiale (fiecare constrângere încălcată = 1)
        new_cost = 0
        for cons in fixed_cons:
            if not check_constraint(new_solution, cons):
                new_cost = new_cost + 1

        # Verificăm dacă noul cost este mai mic decât cel mai bun cost
        res = False
        if new_cost < best_cost:
            # TODO:
            # Dacă noul cost este mai mic decât cel mai bun cunoscut, rezolvăm pentru restul variabilelor
            new_vars = vars[1:]
            
            # Dacă apelul recursiv întoarce True, a fost găsită o soluție suficient de bună, deci întoarcem True
            res = PCSP(new_vars, deepcopy(domains), constraints, acceptable_cost, new_solution, new_cost)
            
        # Verificăm pentru restul valorilor
        # TODO:
        if not res:
            return PCSP(vars, deepcopy(domains), constraints, acceptable_cost, solution, cost)
        else:
            return True


# Un wrapper care să instanțieze variabilele globale
def run_pcsp(problem, acceptable_cost):
    global best_solution
    global best_cost

    [vars, domains, constraints] = [problem[e] for e in ["Vars", "Domains", "Constraints"]]
    
    best_solution = {}
    best_cost = len(constraints)

    PCSP(vars, deepcopy(domains), constraints, acceptable_cost, {}, 0)
    
    print("Best found: " + str(best_cost) + " - " + str(best_solution))

# Rulăm măgăria
run_pcsp(MathProblem, 1)
run_pcsp(ColoringProblem, 1)


# Expected for numbers:
# 
# ```
# New best:  15  -  {'A': 0, 'C': 0, 'B': 0, 'E': 0, 'D': 0}
# New best:  11  -  {'A': 0, 'C': 0, 'B': 0, 'E': 1, 'D': 0}
# New best:  9  -  {'A': 0, 'C': 0, 'B': 0, 'E': 1, 'D': 1}
# New best:  8  -  {'A': 0, 'C': 0, 'B': 0, 'E': 2, 'D': 1}
# New best:  6  -  {'A': 0, 'C': 0, 'B': 0, 'E': 1, 'D': 6}
# New best:  5  -  {'A': 0, 'C': 1, 'B': 0, 'E': 1, 'D': 6}
# New best:  4  -  {'A': 0, 'C': 1, 'B': 0, 'E': 2, 'D': 6}
# New best:  3  -  {'A': 0, 'C': 2, 'B': 1, 'E': 3, 'D': 5}
# New best:  2  -  {'A': 4, 'C': 1, 'B': 6, 'E': 2, 'D': 0}
# New best:  1  -  {'A': 6, 'C': 0, 'B': 4, 'E': 1, 'D': 2}
# Best found: 1  -  {'A': 6, 'C': 0, 'B': 4, 'E': 1, 'D': 2}
# ```
# 
# Expected for country colors:
# 
# ```
# New best:  8  -  {'Loux': 'blue', 'Belgium': 'blue', 'Netherlands': 'blue', 'Germany': 'blue', 'France': 'blue'}
# New best:  6  -  {'Loux': 'blue', 'Belgium': 'blue', 'Netherlands': 'red', 'Germany': 'blue', 'France': 'blue'}
# New best:  4  -  {'Loux': 'blue', 'Belgium': 'red', 'Netherlands': 'blue', 'Germany': 'blue', 'France': 'blue'}
# New best:  3  -  {'Loux': 'blue', 'Belgium': 'red', 'Netherlands': 'yellow', 'Germany': 'blue', 'France': 'blue'}
# New best:  2  -  {'Loux': 'red', 'Belgium': 'red', 'Netherlands': 'yellow', 'Germany': 'blue', 'France': 'blue'}
# New best:  1  -  {'Loux': 'red', 'Belgium': 'yellow', 'Netherlands': 'red', 'Germany': 'blue', 'France': 'blue'}
# Best found: 1  -  {'Loux': 'red', 'Belgium': 'yellow', 'Netherlands': 'red', 'Germany': 'blue', 'France': 'blue'}
# ```
