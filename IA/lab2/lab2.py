#!/usr/bin/env python
# coding: utf-8

# ## Inteligență Artificială - Laboratorul 2 : Căutare nedeterministă
#   - Andrei Olaru <cs@andreiolaru.ro>
#   - Tudor Berariu <tudor.berariu@gmail.com>

# ### Scopul laboratorului
# 
# Familiarizarea cu probleme mai avansate de căutare în spațiul stărilor, nedeterminism, introducere în planificare, și lucrul cu arbori ȘI-SAU.

# ### Problema
# 
# Rezolvăm problema aspiratorului nedeterminist.
# 
# #### Problema aspiratorului determinist
# 
# Avem un aspirator care trebuie să realizeze un plan pentru aspirarea într-un spațiu (unidimensional). Aspiratorul poate realiza operațiile Dreapta, Stânga, Curăță.
# 
# #### Problema aspiratorului nedeterminist
# 
# Aspiratorul nedeterminist are următoarea comportare:
# * atunci când curăță o celulă murdară, celula va fi ulterior curată și este posibil ca și celula din dreapta ei să devină curată
# * atunci când curăță o celulă curată, celula poate rămâne curată sau poate deveni murdară.

# ### Setup
# 
# Vom lucra inițial într-un de 2 celule (coordonate 0, 0 și 1, 0) iar apoi putem extinde la 3, 4 sau 5 celule. Mediul este inițial murdar. Se pornește din 0, 0.
# 
# Ne vom referi cu termenii **stare / state** la starea (coordonatele) aspiratorului, și cu **mediu / env(ironment)** la starea mediului.

# In[7]:


# Dimensiunea mediului
width = 2
height = 1

# Inițial, întreg spațiul este murdar.
env = [[1 for x in range(width)] for y in range(height)]

start = (0, 0)
#env[start[1]][start[0]] = 0


# #### Mișcări
# 
# Avem la dispoziție 3 mișcări. Efectul lor asupra stării aspiratorului și asupra mediului este descris în cele două arrayuri effectD/N, dar nu este necesar să intrăm în detaliile lor.

# In[8]:


moves = ['Left', 'Right', 'Clean']

# efect is a tuple of:
#  delta-x
#  delta-y
#  cleanness of current cell if current cell was clean
#  cleanness of cell to the right if current cell was clean
#  cleanness of current cell if current cell was dirty
#  cleanness of cell to the right if current cell was dirty

# deterministic effects:
effectD = {}
effectD['Left'] = [(-1, 0, -1, -1, -1, -1)]
effectD['Right'] = [(+1, 0, -1, -1, -1, -1)]
effectD['Clean'] = [(0, 0, 0, -1, 0, -1)]

# non-deterministic effects:
effectN = {}
effectN['Left'] = effectD['Left']
effectN['Right'] = effectD['Right']
effectN['Clean'] = [(0, 0, 0, -1, 0, -1), (0, 0, 1, -1, 0, 0)]


# #### Funcții utile
# 
# * `is_good` -- verifică dacă un tuplu de coordonate este valid. Nu ar trebui să fie necesar să o folosiți explicit
# * `env_clean` -- verifică dacă mediul este complet curat
# * `compute_effectD` și `compute_effectN` -- pornind de la o stare și un mediu, se calculează efectul mișcării date și se întoarce o listă de posibile efecte (poate fi nulă), ca tupluri (stare, mediu). Valorile întoarse sunt instanțe **noi**
#  * vedeți și exemplele de la sfârșitul celulei.

# In[9]:


import operator
from functools import reduce

# Întoarce adevărat dacă celula este o celul în interiorul spațiului.
def is_good(state):
    return state[0] >= 0 and state[0] < width and state[1] >= 0 and state[1] < height

# Întoarce adevărat dacă toate celulele din mediu sunt curate.
def env_clean(env):    return 0 == len(list(filter(lambda x: x > 0, reduce(operator.add, env, []))))

# Întoarce o listă de tupluri (stare-nouă, mediu-nou), conținând ca singur element efectul
#    realizării mutării deterministe specificate. Dacă mutarea nu poate fi realizată, lista este nulă.
# Mediul întors este o copie (instanță nouă) a parametrului dat.
def compute_effectD(state, env, move):
    return compute_effects(state, env, move, effectD)
    
# Întoarce o listă de tupluri (stare-nouă, mediu-nou), conținând efectele realizării mutării nedeterministe specificate.
# Lista poate conține zero (dacă mutarea nu este posibilă), unul sau mai multe elemente distincte.
# Mediul întors este o copie (instanță nouă) a parametrului dat.
def compute_effectN(state, env, move):
    return compute_effects(state, env, move, effectN)
    
def compute_effects(state, env, move, effects):
    effects = [compute_effect(state, env, effect) for effect in effects[move]]
    effects = list(filter(lambda e: e is not None, effects))
    if len(effects) == 2 and effects[0] == effects[1]:
        return effects[:1]
    return effects
    
def compute_effect(state, env, effect):
    new_env = [line[:] for line in env]
    (x, y) = state
    new_state = tuple([state[idx] + effect[idx] for idx in range(2)])
    if not is_good(new_state):
        return None
    
    for d in range(2):
        clean_effect = effect[2 + d + env[y][x] * 2]
        if clean_effect >= 0 and is_good((x + d, y)):
            new_env[y][x + d] = clean_effect
    return (new_state, new_env)
        

printX = 1
print(env_clean(env))
print([compute_effectD((printX, 0), env, m) for m in  moves])
print(compute_effectD((printX, 0), env, 'Clean'))
print(compute_effectN((printX, 0), env, 'Clean'))


# #### Afișare arbore
# 
# Funcțiile `printTree` și `printNode` presupun că nodurile sunt structurate ca o lista de 6 elemente:
# * tipul care este fie acțiunea aleasă (din părinte), pentru nodurile ȘI, sau `"OR"`, pentru nodurile SAU
# * starea curentă (într-un nod și va fi aceeași cu cea din părinte, pentru că încă nu știm ce efect se va aplica)
# * starea mediului (aceeași observație ca mai sus)
# * lista de copii -- copii vor fi dați ca noduri; practic, un nod va conține în reprezentare întreg subarborele său
# * etichetă -- etichetele pot fi alese oricum, valorile recomandate fiind `None`, `LOOP` și `SUCCESS`
# * calea din rădăcina arborelui până la nodul curent (inclusiv), dată, de exemplu, ca tupluri (stare, mediu)

# In[10]:


TYPE = 0
STATE = 1
ENV = 2
CHILDREN = 3
TAG = 4
PATH = 5

#%matplotlib inline
#import matplotlib.pyplot as pyplot
#import networkx as nx

counter = 0
labels = {}
nodes = []
edges = []


# reprezentăm un nod din arbore ca o listă
# [move, state, environment, children, tag(None/SUCCESS/LOOP), path]
# formată din mutarea realizată în nodul părinte, stare în urma mutării, starea mediului în urma mutării,
#   lista de copii ai nodului (tot noduri), etichetă, reprezentare a căii din rădăcină până în nod


# afișează un arbore format din noduri definite ca mai sus (se dă rădăcina arborelui, care conține și copiii, etc)
# parametrul onlyOR indică dacă arborele este format doar din noduri SAU (altfel, este interpretat ca arbore ȘI-SAU)
def printTree(root, onlyOR = True):
    #G=nx.Graph()
    
    printTreeEx(root, 0, onlyOR, None)
    
    #G.add_nodes_from(nodes)
    #G.add_edges_from(edges)
    #nx.draw(G)
    #pyplot.show() # display
    
def printTreeEx(node, indent, onlyOR, parent):
    global counter
    line = ""
    for i in range(indent):
        line += "   "
    if node[TYPE] == "OR":
        line += "|  "
        line += str(node[STATE]) + " : " + str(node[ENV])
    else:
        line += ". " + node[TYPE] + " -> "
        if onlyOR:
            line += str(node[STATE]) + " : " + str(node[ENV])
    if node[TAG] is not None:
        line += " " + node[TAG]
    print(line)
    counter += 1
    nodes.append(counter)
    if parent is not None:
        edges.append((parent, counter))
    labels[counter] = line
    for child in node[CHILDREN]:
        printTreeEx(child, indent + 1, onlyOR, node)
        
def printNode(node):
    tag = ""
    if node[TAG] is not None:
        tag = node[TAG]
    print(str(node[TYPE]) + " : " + str(node[STATE]) + " : " + str(node[ENV]) + " (" + str(len(node[CHILDREN])) + ") [" + tag + "]")
    



# ### Task 1
# 
# Implementați funcția `makeTree` pentru a parcurge **complet** stările problemei, pornind de la starea dată pentru aspirator și mediu. Funcția trebuie să întoarcă arborele ȘI-SAU corespunzător.

# In[33]:


import Queue

# Întoarce un arbore al căutării în spațiul env, pornind din starea start
def makeTree(start, env):
    
    root = ["OR", start, env, [], None, [(start, env)]]
    frontiera = Queue.Queue()
    teritoriu = []
    
    frontiera.put(root)
    
    #TODO
    while not frontiera.empty():
        nod_curent = frontiera.get()
        teritoriu.append((nod_curent[STATE], nod_curent[ENV]))
        
        if env_clean(nod_curent[ENV]):
            nod_curent[TAG] = SUCCESS
            continue
            
        for nextMove in moves:
            nod_si = [nextMove, nod_curent[STATE], nod_curent[ENV], [], None, nod_curent[PATH]]
            effects = compute_effectN(nod_curent[STATE], nod_curent[ENV], nextMove)
            
            if len(effects) == 0:
                continue
                
            for effect in effects:
                path_nod_sau = list(nod_curent[PATH])
                path_nod_sau.append((effect[0], effect[1]))
                nod_sau = ["OR", effect[0], effect[1], [], None, path_nod_sau]
                
                if effect in nod_curent[PATH]:
                    nod_sau[TAG] = "LOOP"
                elif env_clean(effect[1]):
                    nod_sau[TAG] = "SUCCESS"
                else:
                    nod_sau[TAG] = "None"
                    
                nod_si[CHILDREN].append(nod_sau)
                
                if nod_sau[TAG] == "None":
                    frontiera.put(nod_sau)
                
            nod_curent[CHILDREN].append(nod_si)
    
    return root
        
tree = makeTree(start, env)
#print(tree)
printTree(tree, False)
        


# ### Task 2
# 
# Implementați funcția `makePlan`, care bazat pe un arbore ȘI-SAU întoarce reprezentarea textuală a unui plan care rezolvă problema.

# In[35]:


# Întoarce un plan de acțiuni care, conform arborelui ȘI-SAU dat, duc la realizarea scopului. Planul este textual.
# Exemplu: "Clean; if env is [0, 0] then [DONE]; if env is [0, 1] then [Right; Clean]"
def makePlan(node):
    result = ""
    # TODO
    if node[TAG] == 'SUCCESS':
        return '[DONE]'
    for child in node[CHILDREN]:
        for i in range(len(child[CHILDREN])):
            temp = makePlan(child[CHILDREN][i])
            if temp != None:
                if len(child[CHILDREN]) == 1:
                    result += child[TYPE] + "|" + temp
                else:
                    result += child[TYPE] + "; " + "if env is " + str(child[CHILDREN][i][ENV]) + " then " + temp
     
    return result
    
makePlan(tree)


# In[ ]:




