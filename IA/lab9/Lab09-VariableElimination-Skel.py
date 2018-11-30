#!/usr/bin/env python
# coding: utf-8

# # Inferențe în modele probabilistice: Eliminarea variabilelor
#     Tudor Berariu, 2017
# 
# Laboratorul precedent a prezentat câteva aspecte teoretice despre rețele Bayesiene. În practică, pentru folosirea lor se conturează câteva probleme:
# 
#  - estimarea structurii (a numărului de variabile și a legăturilor directe dintre acestea)
#  - estimarea parametrilor (a tabelelor de distribuții de probabilități condiționate)
#  - inferențe (calculul unor probabilități oarecare având deja structura și parametrii)
#  
# În laboratorul curent ne vom ocupa *doar* de problema **inferenței** folosind **algoritmul de eliminare a variabilelor**.
# 
# ## Inferențe în Rețele Bayesiene
# 
# Una dintre problemele esențiale legate de modelele probabilistice este calculul unor întrebări generale de tipul:
# $$p\left({\bf Y}|{\bf Z}={\bf z}\right)$$
# unde ${\bf Y}$ reprezintă o mulțime de variabile neobservate a căror distribuție de probabilitate este cerută în prezența observațiilor ${\bf Z} = {\bf z}$ (_evidence_).
# 
# ### Calculul probabilităților condiționate
# 
# Rescriind ecuația de mai sus astfel:
# 
# $$p\left({\bf Y}|{\bf Z}={\bf z}\right) = \frac{p\left({\bf Y}, {\bf Z}={\bf z}\right)}{p\left({\bf Z}={\bf z}\right)}$$
# 
# problema inferenței se reduce la estimarea distribuției comune $P\left({\bf Y}, {\bf Z} = {\bf z}\right)$ și apoi o valoare oarecare din acestă distribuție va putea fi calculată astfel:
# 
# $$p\left({\bf Y} = {\bf y} \vert {\bf Z} = {\bf z}\right) = \frac{p\left({\bf Y} = {\bf y}, {\bf Z} = {\bf z}\right)}{\sum_{{\bf y}'}p\left({\bf Y} = {\bf y}', {\bf Z} = {\bf z}\right)}$$
# 
# Folosirea acestei expresii permite următoarea relaxare: găsirea unor valori $\phi\left({\bf Y} = {\bf y}, {\bf Z} = {\bf z}\right) \propto P\left({\bf Y} = {\bf y}, {\bf Z} = {\bf z}\right)$, ceea ce permite lucrul cu valori nenormalizate.
# 
# $$p\left({\bf Y} = {\bf y} \vert {\bf Z} = {\bf z}\right) = \frac{\phi\left({\bf Y} = {\bf y}, {\bf Z} = {\bf z}\right)}{\sum_{{\bf y}'}\phi\left({\bf Y} = {\bf y}', {\bf Z} = {\bf z}\right)}$$
# 
# În cele ce urmează vom descrie un algoritm care lucrează cu astfel de valori, pe care le vom numi factori.
# 
# **Extra:** Algoritmul se aplică și rețelelor Markov (varianta _neorientată_ a rețelelor Bayesiene, unde se lucrează cu valori nenormalizate peste clici de variabile)

# ## Factori
# 
# Un factor va fi o tabelă de valori peste o colecție de variabile. De exemplu, pentru valorile `A`, `B` și `C` un factor $\phi_{ABC}$ ar putea arăta așa:
# 
#     A | B | C | Value
#     --+---+---+------
#     0 | 0 | 0 | 0.1
#     0 | 0 | 1 | 0.9
#     0 | 1 | 0 | 0.8
#     0 | 1 | 1 | 0.2
#     1 | 0 | 0 | 0.7
#     1 | 0 | 1 | 0.4
#     1 | 1 | 0 | 0.5
#     1 | 1 | 1 | 0.5
# 
# unde $\phi_{ABC}\left(A=1, B=0, C=0\right) = 0.7$.
#    
# ### Reprezentarea factorilor
# 
# Un factor va fi reprezentat printr-un tuplu cu nume `(vars, values)` unde `vars` este o listă cu numele variabilelor din factorul respectiv, iar `values` este un dicționar ale cărui chei sunt tupluri de valori ale variabilelor, iar valorile dicționarului reprezintă o valoare numerică.
# 
# De exemplu, factorul $\phi_{ABC}$ va fi reprezentat astfel:
# 
# ```(vars=["A", "B", "C"],
#     values={(0, 0, 0): .1, (0, 0, 1): .9, (0, 1, 0): .8, (0, 1, 1): .2,
#             (1, 0, 0): .7, (1, 0, 1): .4, (1, 1, 0): .5, (1, 1, 1): .5
#     }
# )```
# 

# In[1]:


from collections import namedtuple
Factor = namedtuple("Factor", ["vars", "values"])

def print_factor(phi, indent="\t"):
    line = " | ".join(phi.vars + ["ϕ(" + ",".join(phi.vars) + ")"])
    sep = "".join(["+" if c == "|" else "-" for c in list(line)])
    print(indent + sep)
    print(indent + line)
    print(indent +sep)
    for values, p in phi.values.items():
        print(indent + " | ".join([str(v) for v in values] + [str(p)]))
    print(indent + sep)

    
# Examples

phi_ABC = Factor(vars=["A", "B", "C"],
                 values={(0, 0, 0): .1, (0, 0, 1): .9, (0, 1, 0): .8, (0, 1, 1): .2,
                         (1, 0, 0): .7, (1, 0, 1): .4, (1, 1, 0): .5, (1, 1, 1): .5})
phi_AB = Factor(vars=["A", "B"], values={(0, 0): .1, (0, 1): .9, (1, 0): .8, (1, 1): .2})
phi_BC = Factor(vars=["B", "C"], values={(0, 0): .2, (0, 1): .8, (1, 0): .5, (1, 1): .5})
phi_A = Factor(vars=["A"], values={(0,): .4, (1,): .6})
phi_C = Factor(vars=["C"], values={(0,): .6, (1,): .8})

print_factor(phi_ABC)
print("ϕ(A=1, B=0, C=0) = " + str(phi_ABC.values[(1, 0, 0)]))


# ## Multiplicarea a doi factori
# 
# Doi factori $\phi_1$ și $\phi_2$ se pot multiplica, obținându-se un nou factor ale cărui valori sunt produse ale valorilor din $\phi_1$ și $\phi_2$. Dacă $\phi_1$ este un factor peste ${\bf X} \cup {\bf Y}$, iar $\phi_2$ este un factor peste ${\bf Y} \cup {\bf Z}$ (${\bf Y}$ reprezintă mulțimea variabilelor comune celor doi factori), atunci:
# 
# $$\phi\left(X_1, \ldots\ X_{N_X}, Y_1, \ldots Y_{N_Y}, Z_1, \ldots Z_{N_Z}\right) = \phi_1\left(X_1, \ldots\ X_{N_X}, Y_1, \ldots Y_{N_Y}\right) \cdot \phi_2\left(Y_1, \ldots Y_{N_Y}, Z_1, \ldots Z_{N_Z}\right)$$
# 
# De exemplu, fie factorii $\phi_{AB}$ și $\phi_{BC}$:
# 
#     --+---+----------          --+---+----------
#     A | B | ϕ(A,B)             B | C | ϕ(B,C)
#     --+---+----------          --+---+----------
#     0 | 0 | 0.100000   <--     0 | 0 | 0.200000
#     0 | 1 | 0.900000   !!!     0 | 1 | 0.800000   <--
#     1 | 0 | 0.800000           1 | 0 | 0.500000
#     1 | 1 | 0.200000           1 | 1 | 0.500000   !!!
#     --+---+----------          --+---+----------
# 
# Factorul nou se creează combinând toate perechile de intrări din $\phi_{AB}$ și $\phi_{BC}$ pentru care valorile variabilelor comune (în acest caz, $B$) sunt identice.
# 
# 	--+---+---+---------
# 	A | B | C | ϕ(A,B,C)
# 	--+---+---+---------
# 	0 | 0 | 0 | 0.020000
# 	0 | 0 | 1 | 0.080000   <--
# 	0 | 1 | 0 | 0.450000
# 	0 | 1 | 1 | 0.450000   !!!
# 	1 | 0 | 0 | 0.160000
# 	1 | 0 | 1 | 0.640000
# 	1 | 1 | 0 | 0.100000
# 	1 | 1 | 1 | 0.100000
# 	--+---+---+---------
# 
# 
# **Cerința 1** : Implementați funcția `multiply` care primește doi factori și întoarce rezultatul înmulțirii celor doi.

# In[2]:


# Multiplicarea a doi factori:

def multiply(phi1, phi2):
    assert isinstance(phi1, Factor) and isinstance(phi2, Factor)
    # Cerinta 1 :
    return None


# In[3]:


print_factor(phi_AB)
print("*")
print_factor(phi_BC)
print("=")
print_factor(multiply(phi_AB, phi_BC))


# In[ ]:


## Tests for multiply

from itertools import permutations
from operator import mul
from functools import reduce
import sys
from copy import deepcopy

def _check_factor(_phi, all_vars, control):
    assert sorted(_phi.vars) == sorted(all_vars),         "Wrong variables: " + ','.join(_phi.vars) + " instead of " + ','.join(all_vars)
    assert len(_phi.values) == 2 ** len(all_vars),         "Wrong number of entries in phi.values: " + str(len(_phi.values))
    n = len(all_vars)
    if n > 0:
        for j in range(n + 1):
            vals = [0] * (n - j) + [1] * j
            keys = set([p for p in permutations(vals)])
            p = reduce(mul, [_phi.values[k] for k in keys])
            assert abs(p - control[j]) < 1e-9,                 "Values for " + str(keys) + " are wrong!"
    else:
        assert abs(_phi.values[()] - control[0]) < 1e-9


def _test_multiply(name1, name2, all_vars, control, verbose=False):
    _phi = eval("multiply(deepcopy(phi_"+name1+"), deepcopy(phi_"+name2+"))")
    if verbose:
        print("Result of ϕ_"+name+" * ϕ_"+name2+":")
        print_factor(_phi)
    sys.stdout.write("Testing  ϕ_"+name1+" * ϕ_"+name2+" ... ")
    _check_factor(_phi, all_vars, control)
    print("OK!!")

_test_multiply("AB", "BC", ["A", "B", "C"], [.02, .00576, .0288, .1], verbose=False)
_test_multiply("A", "BC", ["A", "B", "C"], [.08, .00768, .0288, .3])
_test_multiply("A", "AB", ["A", "B"], [.04, .1728, .12])
_test_multiply("BC", "A", ["C", "A", "B"], [.08, .00768, .0288, .3])
_test_multiply("ABC", "BC", ["C", "A", "B"], [.02, .04032, .008, .25])
_test_multiply("C", "A", ["C", "A"], [.24, .1152, .48])
_test_multiply("A", "C", ["C", "A"], [.24, .1152, .48])
_test_multiply("C", "C", ["C"], [.36, .64])

print("\nMultiply seems ok!\n")


# ## Eliminarea unei variabile dintr-un factor prin însumare
# 
# O variabilă $X_i$ se poate elimina dintr-un factor $\phi$ prin însumarea tuturor valorilor în care celelalte variabile au aceleași valori. Rezultatul este un nou factor $\tau$ al cărui context este dat de toate celelate variabile din $\phi$ în afara lui $X_i$.
# 
# Notație: $$\tau \leftarrow \sum_{X_i} \phi$$
# 
# $$\tau\left(X_1, \ldots X_{i-1}, X_{i+1}, \ldots, X_N\right) = \sum_{x} \phi \left(X_1, \ldots X_{i-1}, X_i= x, X_{i+1}, \ldots, X_N\right)$$
# 
# Prin eliminarea lui $B$ din factorul:
# ```
# --+---+---+----------
# A | B | C | ϕ(A,B,C)
# --+---+---+----------
# 0 | 0 | 0 | 0.100000    !!!
# 0 | 0 | 1 | 0.900000
# 0 | 1 | 0 | 0.800000    !!!
# 0 | 1 | 1 | 0.200000
# 1 | 0 | 0 | 0.700000    <--
# 1 | 0 | 1 | 0.400000
# 1 | 1 | 0 | 0.500000    <--
# 1 | 1 | 1 | 0.500000
# --+---+---+----------
# ```
# 
# se obține:
# 
# ```
# --+---+----------
# A | C | ϕ(A,C)
# --+---+----------
# 0 | 0 | 0.900000    !!!
# 0 | 1 | 1.100000
# 1 | 0 | 1.200000    <---
# 1 | 1 | 0.900000
# --+---+----------
# ```
# 
# **Cerința 2** : Implementați funcția `sum_out` care primește o variabilă `var` și un factor `phi` și întoarce un factor nou obținut prin eliminarea prin însumare a variabilei `var`.

# In[ ]:


def sum_out(var, phi):
    assert isinstance(phi, Factor) and var in phi.vars
    # Cerinta 2:
    return None


# In[ ]:


# Un exemplu

print("Însumând B afară din")
print_factor(phi_ABC)
print("=")
print_factor(sum_out("B", phi_ABC))


# In[ ]:


## Tests for sum_out

def _test_sum_out(var, name, left_vars, control, verbose=False):
    import sys
    from itertools import permutations
    from operator import mul
    from functools import reduce
    _phi = eval("sum_out('"+var+"', phi_"+name+")")
    if verbose:
        print_factor(_phi)
    sys.stdout.write("Testing  sum_"+var+" ϕ_"+name+" ... ")
    _check_factor(_phi, left_vars, control)
    print("OK!!")

_test_sum_out("A", "ABC", ["C", "B"], [.8, 1.69, .7], verbose=False)
_test_sum_out("B", "ABC", ["A", "C"], [.9, 1.32, .9], verbose=False)
_test_sum_out("C", "C", [], [1.4], verbose=False)
_test_sum_out("A", "A", [], [1.], verbose=False)
_test_sum_out("B", "BC", ["C"], [.7, 1.3], verbose=False)

print("\nSummations seems ok!\n")


# ## Eliminarea unei variabile dintr-o mulțime de factori
# 
# Dându-se o mulțime de factori $\Phi$, eliminați variabila $X$. Operația se face prin înlocuirea tuturor factorilor care conțin variabila $X$ cu unul obținut prin (1) factorizare și apoi (2) însumare.
# 
# `prod_sum(`$\Phi$ `, ` $X$ `)`
#  - $\Phi_{X} \leftarrow \left\lbrace \phi \in \Phi \,:\, X \in \phi \right\rbrace$
#  - $\omega \leftarrow \prod_{\phi \in \Phi_{X}} \phi$
#  - $\tau \leftarrow \sum_{X} \omega$
#  - `return` $\Phi \setminus \Phi_{X} \cup \left\lbrace \tau \right\rbrace$
# 
# **Cerința 3** : Implementați funcția `prod_sum` care primește o variabilă `var` și o listă de factori și întoarce noua listă de factori obținută prin eliminarea variabilei `var`. Dacă `verbose` este `True`, atunci afișați factorul nou construit (e util mai târziu pentru a urmări pașii algoritmului).

# In[ ]:


def prod_sum(var, Phi, verbose=False):
    assert isinstance(var, str) and all([isinstance(phi, Factor) for phi in Phi])
    # Cerinta 3:
    return None


# In[ ]:


# Un exemplu
print("Elininând B din :")
print_factor(phi_AB)
print("și")
print_factor(phi_BC)
print("=>")
print_factor(prod_sum("B", [phi_AB, phi_BC])[0])


# In[ ]:


## Test prod_sum

sys.stdout.write("Testing prod-sum (I) ... ")
result = prod_sum("B", [deepcopy(_phi) for _phi in [phi_A, phi_C, phi_ABC, phi_BC]])
assert len(result) == 3
for _phi in result:
    if sorted(_phi.vars) == ["A", "C"]:
        assert abs(_phi.values[(0, 0)] - 0.42) < 1e-9
        assert abs(_phi.values[(0, 1)] * _phi.values[(1, 0)] - 0.3198) < 1e-9
        assert abs(_phi.values[(1, 1)] - 0.57) < 1e-9
    elif sorted(_phi.vars) == ["A"]:
        assert abs(_phi.values[(0,)] - 0.4) < 1e-9
        assert abs(_phi.values[(1,)] - 0.6) < 1e-9
    elif sorted(_phi.vars) == ["C"]:
        assert abs(_phi.values[(0,)] - 0.6) < 1e-9
        assert abs(_phi.values[(1,)] - 0.8) < 1e-9
print("OK!")

sys.stdout.write("Testing prod-sum (II) ... ")
result = prod_sum("A", [deepcopy(_phi) for _phi in [phi_A, phi_C, phi_ABC, phi_BC]])
assert len(result) == 3
for _phi in result:
    if sorted(_phi.vars) == ["B", "C"]:
        assert abs(_phi.values[(0, 0)] - 0.2) < 1e-9 or abs(_phi.values[(0, 0)] - 0.46) < 1e-9
        assert abs(_phi.values[(0, 1)] * _phi.values[(1, 0)] - 0.4) < 1e-9 or                abs(_phi.values[(0, 1)] * _phi.values[(1, 0)] - 0.372) < 1e-9
        assert abs(_phi.values[(1, 1)] - 0.5) < 1e-9 or abs(_phi.values[(1, 1)] - 0.38) < 1e-9
    elif sorted(_phi.vars) == ["C"]:
        assert abs(_phi.values[(0,)] - 0.6) < 1e-9
        assert abs(_phi.values[(1,)] - 0.8) < 1e-9
print("OK!")
print("Prod-Sum seems ok!")


# ## Eliminarea variabilelor
# 
# Dându-se o mulțime de factori $\Phi$ și o mulțime de variabile de eliminat $\bf{Z}$, să se construiască factorul obținut după eliminarea tuturor variabilelor $Z_i$.
# 
# `variable_elimination(` $\Phi$ `,` ${\bf Z}$ `)`
#  - `for` $Z_i \in {\bf Z}$:
#    - $\Phi \leftarrow $ `prod_sum(` $Z_i$ `,` $\Phi$ `)`
#  - `return` $\prod_{\phi \in \Phi} \phi$
#  
# Ordinea în care se iau variabilele din ${\bf Z}$ poate infleunța eficiența algoritmului. (Vezi BONUS.)
# 
# ** Cerința 4 ** : Implementați funcția `variable_elimination`. Aceasta trebuie să întoarcă un singur factor. Folosiți argumentul `verbose` și în apelurile funcției `prod_sum`.

# In[ ]:


def variable_elimination(Phi, Z, verbose=False):
    # Cerinta 4:
    return None


# In[ ]:


## Testing Variable elimination

def _test_variable_elimination(Phi, Vars, left_vars, control, verbose=False):

    
    var_list = '["' + '", "'.join(Vars) + '"]'
    factor_list = '[' + ','.join([("deepcopy(phi_"+name + ")") for name in Phi]) +']'
    name_list = '[' + ','.join([("ϕ_"+name) for name in Phi]) +']'
    _phi = eval("variable_elimination("+factor_list+", "+var_list+")")
    if verbose:
        print_factor(_phi)
    sys.stdout.write("Testing  eliminate_var "+var_list+" from "+name_list+" ... ")
    _check_factor(_phi, left_vars, control)
    print("OK!!")

_test_variable_elimination(["A", "C"], ["C"], ["A"], [0.56, 0.84])
_test_variable_elimination(["ABC", "BC", "AB", "A"], ["C", "B"], ["A"], [0.2096, 0.2808])
_test_variable_elimination(["ABC", "BC", "AB", "A"], ["C", "B", "A"], [], [0.4904])
_test_variable_elimination(["ABC", "AB", "BC", "A"], ["A", "B", "C"], [], [0.4904])
_test_variable_elimination(["ABC"], ["A", "B", "C"], [], [4.1])


# ## Reducerea factorilor conform observațiilor
# 
# **Cerința 5** : Reduceți factorii eliminanând intrările ce nu corespund observațiilor făcute. Implementați funcția `coniditon_factors` care primește o listă de factori `Phi`, un dicțonar de observații și întoarce o listă nouă în care factorii ce conțin variabile din `Z` sunt reduși la liniile conforme cu observațiile făcute.

# In[ ]:


def condition_factors(Phi : list, Z : dict, verbose=False):
    # Cerinta 5
    return None


# In[ ]:


# Un exemplu
print("Aplicand B=0 in factorul")
print_factor(phi_ABC)
print("=>")
print_factor(condition_factors([phi_ABC], {"B": 0})[0])


# In[ ]:


# Teste pentru condition_factors

phi_ABC = Factor(vars=["A", "B", "C"],
                 values={(0, 0, 0): .1, (0, 0, 1): .9, (0, 1, 0): .8, (0, 1, 1): .2,
                         (1, 0, 0): .7, (1, 0, 1): .4, (1, 1, 0): .5, (1, 1, 1): .5})

_phi = condition_factors([phi_ABC], {"B": 0})[0]
assert sorted(_phi.vars) == ["A", "B", "C"]
assert len(_phi.values) == 4 and abs(_phi.values[(0, 0, 0)] - .1) < 1e-7
_phi = condition_factors([phi_ABC], {"B": 0, "A": 1})[0]
assert sorted(_phi.vars) == ["A", "B", "C"] and len(_phi.values) == 2
print("Condition factors seems ok!")


# ## Realizarea inferențelor în Rețele Bayesiene
# 
# $$P\left({\bf Y} \vert {\bf Z} = {\bf z}\right) = \frac{P\left({\bf Y}, {\bf Z} = {\bf z}\right)}{P\left(\bf{Z = z}\right)}$$
# 
# Realizarea inferențelor de tipul generic de mai sus se face în următorii pași:
# 
#  - tabelele cu distribuțiile condiționate sunt transformate în factori
#  - factorii ce conțin variabile din ${\bf Z}$ sunt reduși la liniile care respectă ${\bf Z} = {\bf z}$
#  - fie $\Phi$ mulțimea factorilor astfel obținuți
#  - fie $\phi_{YZ}$ factorul obținut prin eliminarea tuturor celorlalte variabile:
# 
#      * $\phi_{YZ} \leftarrow $ `var_elimination` $\left(\Phi, {\bf X} \setminus \left({\bf Y} \cup {\bf Z}\right)\right)$
#  - atunci $$P({\bf Y} = {\bf y}| {\bf Z}= {\bf z}) = \frac{\phi_{YZ}({\bf Y}={\bf y})}{\sum_{{\bf Y}} \phi_{YZ}}$$
# 

# In[ ]:


from random import shuffle

def query(X : list, Y : list, Z : dict, Phi: list, Other=None, verbose=False):
    """
    X - full list of variables
    Y - query variables
    Z - dictionary with observations
    Phi - the list with all factor
    Ohter - an order over variables in X \ (Y U Z); None to pick a random one
    verbose - display factors as they are created
    """

    if verbose:
        print("\n-------------\nInitial factors:")
        for phi in Phi:
            print_factor(phi)

    Phi = condition_factors(Phi, Z, verbose=verbose)  # Condition factors on Z=z

    if Other is None:
        Other = [x for x in X if (x not in Y and x not in Z)]  # Variables that need to be eliminated
        shuffle(Other)
    else:
        assert sorted(Other) == sorted([x for x in X if (x not in Y and x not in Z)])
    if verbose:
        print("\n-------------\nEliminating variables in the following order: " + ",".join(Other))

    phi = variable_elimination(Phi, Other, verbose=verbose)  # Eliminate other variables then Y and Z
    
    # Normalize factor to represent the conditional probability p(Y|Z=z)
    s = sum(phi.values.values())
    prob = Factor(vars=phi.vars, values={k: v / s for (k, v) in phi.values.items()})
    print("\n-----------------\nProbabilitatea ceruta:")
    print_factor(prob)


# ## Exemplu
# 
# Urmăriți exemplul din PDF-ul atașat.

# In[ ]:


phi_a = Factor(vars=["A"], values={(0,): .7, (1,): .3})
phi_b = Factor(vars=["B"], values={(0,): .5, (1,): .5})
phi_c = Factor(vars=["C"], values={(0,): .4, (1,): .6})

phi_d = Factor(vars=["A", "B", "D"],
               values={(0, 0, 0): .75, (0, 0, 1): .25, (0, 1, 0): .7, (0, 1, 1): .3,
                       (1, 0, 0): .6, (1, 0, 1): .4, (1, 1, 0): .2, (1, 1, 1): .8
                      })
phi_e = Factor(vars=["C", "E"],
               values={(0, 0): .25, (0, 1): .75, (1, 0): .75, (1, 1): .25})

phi_f = Factor(vars=["A", "D", "F"],
               values={(0, 0, 0): .6, (0, 0, 1): .4, (0, 1, 0): .4, (0, 1, 1): .6,
                       (1, 0, 0): .7, (1, 0, 1): .3, (1, 1, 0): .8, (1, 1, 1): .2
                      })
phi_g = Factor(vars=["D", "E", "G"],
               values={(0, 0, 0): .1, (0, 0, 1): .9, (0, 1, 0): .2, (0, 1, 1): .8,
                       (1, 0, 0): .5, (1, 0, 1): .5, (1, 1, 0): .4, (1, 1, 1): .6
                      })

all_vars = ["A", "B", "C", "D", "E", "F", "G"]
Phi = [phi_a, phi_b, phi_c, phi_d, phi_e, phi_f, phi_g]


# In[ ]:


# Algoritmul ar trebui să ajungă la probabilitățile din tabele

# Verificati ca algoritmul "ajunge" corect la valorile din tabele
query(all_vars, ["F"], {"A": 0, "D": 1}, Phi)
query(all_vars, ["G"], {"D": 0, "E": 1}, Phi)


# In[ ]:


# Exemplul din PDF-ul atașat

query(all_vars, ["C", "F"], {"G": 0}, Phi, Other=["E", "B", "A", "D"], verbose=True)

