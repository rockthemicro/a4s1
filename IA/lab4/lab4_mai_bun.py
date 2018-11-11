#!/usr/bin/env python
# coding: utf-8

# # Algoritmi MCTS. Aplicație: Connect Four
#  - Tudor Berariu
#  - Andrei Olaru

# ## Scopul laboratorului
# 
# Scopul acestui laborator este acela de a implementa un algoritm din familia **MCTS** (_Monte Carlo Tree Search_), și anume **UCT** (_Upper Confidence Bound for Trees_).
# 
# Pentru a testa algoritmul vom folosi jocul _Connect Four_.
# 
# Prima parte a laboratorului construiește funcțiile necesare desfășurarea jocului _Connect Four_, iar cea de-a doua parte urmărește implementarea pas cu pas a algoritmului **UCT**.
# 
# #### Resurse
# 
# https://en.wikipedia.org/wiki/Monte_Carlo_tree_search
# 
# [Curs 3 IA, Slides 20-30](http://cs.curs.pub.ro/2016/mod/resource/view.php?id=2484)

# ## Jocul _Connect Four_
# 
# ### Descrierea jocului
# 
# Jocul _Connect Four_ lucrează cu o matrice verticală de **înălțime 6** și **lățime 7** în care doi jucători dau drumul unor jetoane de culori diferite (un jucător are jetoane roșii, iar celălalt albastre). La fiecare mutare, un jucător alege o coloană din cele 7 și dă drumul jetonului. Acesta _cade_, așezându-se pe prima poziție liberă din acea coloană. Într-o coloană nu se pot așeza mai mult de șase jetoane. Câștigă acel jucător care reușește să așeze *patru* dintre jetoanele lui (de aceeași culoare) într-o linie pe orizontală, verticală sau diagonală.
# 
# ### Reprezentarea stărilor
# 
# Starea jocului va fi reprezentată printr-un tuplu format din două elemente:
#  - o listă ce va conține 7 liste corespunzătoare celor 7 coloane
#    + fiecare listă va avea lungimea 6 și va conține 0 (poziție liberă), 1 (jeton roșu) și 2 (jeton albastru)
#    + poziția 0 din listă corespunde rândului cel mai de jos
#  - indicatorul jucătorului ce trebuie să _mute_: 1 pentru roșu, 2 pentru jucătorul albastru.

# In[199]:


# Dimensiunile matricei
HEIGHT, WIDTH = 6, 7

# Pozițiile din tuplul ce constituie o stare
BOARD, NEXT_PLAYER = 0, 1

# Jucătorii
RED, BLUE = 1, 2
name = ["", "ROȘU", "ALBASTRU", "REMIZĂ"]

# Funcție ce întoarce o stare inițială
def init_state():
    return ([[0 for row in range(HEIGHT)] for col in range(WIDTH)], RED)

# Funcție ce afișează o stare
def print_state(state):
    for row in range(HEIGHT-1, -1, -1):
        ch = " RA"
        l = map(lambda col: ch[state[BOARD][col][row]], range(WIDTH))
        print("|" + "".join(l) + "|")
    print("+" + "".join("-" * WIDTH) + "+")
    print("Urmează: %d - %s" % (state[NEXT_PLAYER], name[state[NEXT_PLAYER]]))

# Se afișează starea inițială a jocului
print("Starea inițială:")
print_state(init_state())


# ### Mutările
# 
# Cum toată informația necesară pentru a descrie o mutare este dată de coloana în care un jucător a ales să își așeze jetonul, o acțiune va fi reprezentată simplu printr-un număr.
# 
# **Cerința 1:** Completați funcția `get_available_actions(state)` care primește o stare și întoarce acțiunile corecte (o listă cu acele coloane care nu sunt _pline_).
# 
# Funcția `apply_action(state, action)` este deja implementată (întoarce o stare nouă, nu o modifică pe cea dată ca argument).

# In[200]:


# Funcție ce întoarce acțiunile valide dintr-o stare dată



def get_available_actions(state):
    # TODO <1>
    result = []
    for i in range(0, WIDTH):
        if state[BOARD][i][HEIGHT - 1] == 0:
            result.append(i)
    return result


from copy import deepcopy
from functools import reduce

# Funcție ce întoarce starea în care se ajunge prin aplicarea unei acțiuni
def apply_action(state, action):
    if action >= len(state[BOARD]) or 0 not in state[BOARD][action]:
        print("Action " + str(action) + " is not valid.")
        return None
    new_board = deepcopy(state[BOARD])
    new_board[action][new_board[action].index(0,0)] = state[NEXT_PLAYER]
    return (new_board, 3 - state[NEXT_PLAYER])


# Se afișează starea la care se ajunge prin aplicarea unor acțiuni
somestate = reduce(apply_action, [3, 4, 3, 2, 2, 6, 3, 3, 3, 3], init_state())
print_state(somestate)
get_available_actions(somestate)


# ### Stările finale
# 
# Pentru a verifica dacă o stare este finală:
#  - se verifică dacă ultimul jucător care a mutat a câștigat
#  - sau dacă matricea este _plină_
#  
# Scriem o funcție `is_final(state)` care va întoarce:
#  - `False` dacă starea nu este finală,
#  - `1` dacă a câștigat Roșu,
#  - `2` dacă a câștigat Albastru și
#  - `3` dacă este remiză.
# 
# Funcția este deja implementată mai jos.

# In[201]:


# Funcție ce verifică dacă o stare este finală
def is_final(state):
    # Verificăm dacă matricea este plină
    
    #print(state[BOARD])
    ok = 1
   # if not any([0 in col for col in state[BOARD]]): return 3
    
    for col in state[BOARD]:
        for line in col:
            if line == 0:
                ok = 0
    
    if ok == 1:
        return 3
    
    # Jucătorul care doar ce a mutat ar putea să fie câștigător
    player = 3 - state[NEXT_PLAYER]
    
    ok = lambda pos: all([state[BOARD][c][r] == player for (r, c) in pos])
    # Verificăm orizontale
    for row in range(HEIGHT):
        for col in range(WIDTH - 4):
            if ok([(row, col + i) for i in range(4)]): return player
    # Verificăm verticale
    for col in range(WIDTH):
        for row in range(HEIGHT - 4):
            if ok([(row + i, col) for i in range(4)]): return player
    # Verificăm diagonale
    for col in range(WIDTH - 4):
        for row in range(HEIGHT - 4):
            if ok([(row + i, col + i) for i in range(4)]): return player
    for col in range(WIDTH-4):
        for row in range(HEIGHT-4):
            if ok([(row + i, col + 4 - i) for i in range(4)]): return player
    return False


# In[202]:


# Afișăm o stare finală oarecare
from random import choice

rand_state = init_state()
while not is_final(rand_state):
    actions = get_available_actions(rand_state)
    if not actions:
        break
    action = choice(get_available_actions(rand_state))
    rand_state = apply_action(rand_state, action)

print_state(rand_state)
print("Învingător: %s" % (name[is_final(rand_state)]))


# In[203]:


# Exemplu: Se afișează starea obținută prin aplicarea unor acțiuni
all_actions = [1, 2, 1, 2, 1, 2, 1, 4]
some_state = reduce(apply_action, all_actions, init_state())
print_state(some_state)
print("Învingător: %s" % (name[is_final(some_state)]))


# ## Algoritmul UCT
# 
# Algoritmii din familia MCTS conțin patru etape importante:
#  - **selecție** - o strategie de alegere a unei acțiuni pentru a exploata
#  - **extindere** - construirea unui nod nou în arbore
#  - **simulare** - desfășurarea unui joc în mod aleator către o stare finală
#  - **propagare înapoi** - actualizarea statisticilor pentru toate nodurile
# 
# 
# ### Reprezentarea unui nod
# 
# Un nod din arborele de stări va fi un dicționar ce conține:
#  - numărul de vizitări `N` -- de câte ori s-au realizat simulări din acel nod sau dintr-un descendent al său.
#  - valoarea estimată `Q` -- o indicație a calității nodului, bazat pe numărul jocurilor câștigate pornind din acel nod.
#  - o referință către nodul părinte
#  - lista copiilor -- un dicționar ce conține pentru fiecare acțiune o legătură către nodul următor
# 
# Exemplu de nod corespunzător unei stări.
# 
#     {'N': 7, 'Q': 2.5, 'parent': None, 'actions': {0: {'N': 3, ...}, 1: {'N': 4, ...}}}
#     
# ### Desfășurarea algoritmului
# 
# 1. Dacă algoritmul pornește cu un arbore gol (fără memorie), atunci se construiește un nod nou.
#    Altfel se alege subarborele corespunzător ultimei acțiuni a adversarului. (`TODO3`)
# 
# 2. Până când se atinge limita bugetului de calcul:
#   1. pornind din rădăcină, se alege succesiv un nod următor până când se atinge o stare finală sau un nod din care nu s-au explorat toate acțiunile posibile (`TODO2` și `TODO4`)
#   2. pentru un nod care nu este final și din care nu s-au explorat toate acțiunile, se construiește un nod-copil pentru una dintre acțiunile neexplorate
#   3. se simulează un joc pornind din nodul nou până într-o stare finală (`TODO5`)
#   4. se evaluează starea finală și se calculează o recompensă (`TODO6`)
#   5. se propagă înapoi acea recompensă, actualizându-se și statisticile (numărul de vizite) pentru fiecare nod până la rădăcină (`TODO7`)

# In[204]:


# Constante

N = 'N'
Q = 'Q'
PARENT = 'parent'
ACTIONS = 'actions'


# ### Afișarea unui arbore

# In[205]:


def print_tree(tree, indent = 0):
    if not tree:
        return
    tab = "".join(" " * indent)
    print("%sN = %d, Q = %f" % (tab, tree[N], tree[Q]))
    for a in tree[ACTIONS]:
        print("%s %d ==> " % (tab, a))
        print_tree(tree[ACTIONS][a], indent + 3)
        
# def number_of_nodes(tree):
#     if not tree:
#         return 0
#     no = 1
#     for child in tree[ACTIONS].values():
#         no += number_of_nodes(child)
#     return no


# ### Algoritm

# In[206]:


# Funcție ce întoarce un nod nou,
# eventual copilul unui nod dat ca argument
def init_node(parent = None):
    return {N: 0, Q: 0, PARENT: parent, ACTIONS: {}}


# In[207]:


from math import sqrt, log

CP = 1.0 / sqrt(2.0)

# Funcție ce alege o acțiune dintr-un nod
def select_action(node, c = CP):
    N_node = node[N]
    ret_val = -1
    # TODO <2>
    # Se caută acțiunea a care maximizează expresia:
    # Q_a / N_a  +  c * sqrt(2 * log(N_node) / N_a)
    

    
    max = -1

    
    for key, value in node[ACTIONS].items():
            result = value[Q] / value[N] + c * sqrt(2 * log(N_node / value[N]))
            if result > max:
                max = result
                ret_val = key
    
    return ret_val # TODO

# Scurtă testare
test_root = {N: 6, Q: 0.75, PARENT: None, ACTIONS: {}}
test_root[ACTIONS][3] = {N: 4, Q: 0.9, PARENT: test_root, ACTIONS: {}}
test_root[ACTIONS][5] = {N: 2, Q: 0.1, PARENT: test_root, ACTIONS: {}}

print(select_action(test_root, CP))  # ==> 5 (0.8942 < 0.9965)
print(select_action(test_root, 0.3)) # ==> 3 (0.50895 > 0.45157)


# In[208]:


# Algoritmul MCTS (UCT)
#  state0 - starea pentru care trebuie aleasă o acțiune
#  budget - numărul de iterații permis
#  tree - un arbore din explorările anterioare
#  opponent_s_action - ultima acțiune a adversarului

  # TODO <3>
    # DACA exista un arbore construit anterior SI
    #   acesta are un copil ce corespunde ultimei actiuni a adversarului,
    # ATUNCI acel copil va deveni nodul de inceput pentru algoritm.
    # ALTFEL, arborele de start este un nod gol.
def mcts(state0, budget, tree, opponent_s_action=None):
    new_tree = init_node()

    if tree is not None and opponent_s_action is not None:
        for action in tree[ACTIONS]:
            if action == opponent_s_action:
                new_tree = tree[ACTIONS][action]

    tree = new_tree

    # ---------------------------------------------------------------

    for x in range(budget):
        # Punctul de start al simularii va fi radacina de start
        state = state0
        node = tree

        # TODO <4>
        # Coboram in arbore pana cand ajungem la o stare finala
        # sau la un nod cu actiuni neexplorate.
        # Variabilele state si node se 'muta' impreuna.
        while not is_final(state):
            explored = 1

            for action in get_available_actions(state):
                if action not in node[ACTIONS]:
                    explored = 0
                    break

            if not explored:
                break

            new_action = select_action(node)

            if new_action:
                node = node[ACTIONS][new_action]
                state = apply_action(state, new_action)
            else:
                break

        # ---------------------------------------------------------------

        # TODO <5>
        # Daca am ajuns intr-un nod care nu este final si din care nu s-au
        # `incercat` toate actiunile, construim un nod nou.
        if not is_final(state):
            available_actions = get_available_actions(state)

            for a_action in available_actions:
                if a_action not in node[ACTIONS]:
                    new_node = init_node(node)
                    node[ACTIONS][a_action] = new_node

                    node = new_node
                    state = apply_action(state, a_action)
                    break
        # ---------------------------------------------------------------

        # TODO <6>
        # Se simuleaza o desfasuare a jocului pana la ajungerea intr-o
        # starea finala. Se evalueaza recompensa in acea stare.
        while not is_final(state):
            available_actions = get_available_actions(state)
            chosen_action = choice(available_actions)

            state = apply_action(state, chosen_action)
            break

        winner = is_final(state)
        if winner == state0[NEXT_PLAYER]:
            reward = 1
        elif winner == (3 - state0[NEXT_PLAYER]):
            reward = 0.0
        elif winner == 3:
            reward = 0.25
        else:
            reward = 0.5
        # ---------------------------------------------------------------

        # TODO <7>
        # Se actualizeaza toate nodurile de la node catre radacina:
        #  - se incrementeaza valoarea N din fiecare nod
        #  - se adauga recompensa la valoarea Q
        while node:
            node[N] += 1
            node[Q] += reward
            node = node[PARENT]

        # ---------------------------------------------------------------

    if tree:
        final_action = select_action(tree, 0.0)
        return final_action, tree[ACTIONS][final_action]
    # Acest cod este aici doar ca sa nu dea erori testele mai jos; in mod normal tree nu trebuie sa fie None
    if get_available_actions(state0):
        return get_available_actions(state0)[0], init_node()
    return 0, None


# In[209]:


# Testare MCTS
(action, tree) = mcts(init_state(), 20, None, None)
print(action)
if tree: print_tree(tree[PARENT])


# ## Evaluarea algoritmului
# 
# Funcția de mai jos opune doi jucători ce folosesc algoritmul UCT pentru a decide asupra acțiunii dintr-o stare.

# In[210]:


def play_games(games_no, budget1, budget2, verbose = False):
    # Efortul de căutare al jucătorilor
    budget = [budget1, budget2]
    
    score = {p: 0 for p in name}
        
    for i in range(games_no):
        # Memoriile inițiale
        memory = [None, None]
        
        # Se desfășoară jocul
        state = init_state()
        last_action = None
    
        while state and not is_final(state):
            p = state[NEXT_PLAYER] - 1
            (action, memory[p]) = mcts(state, budget[p], memory[p], last_action)
            state = apply_action(state, action)
            last_action = action
        
        # Cine a câștigat?
        if(state):
            winner = is_final(state)
            score[name[winner]] += + 1
        
        # Afișăm
        if verbose and state:
            print_state(state)
            if winner == 3: print("Remiză.")
            else: print("A câștigat %s" % name[winner])

    # Afișează scorul final
    print("Scor final: %s." % (str(score)))


# In[211]:


# play_games(N, BR, BA, VERBOSE) - rulează N jocuri, cu bugetele BR pt ROȘU și BA pt ALBASTRU
play_games(5, 2, 30, True) # ne așteptăm să câștige ALBASTRU
#play_games(5, 30, 2, True) # ne așteptăm să câștige ROȘU


# In[ ]:




