#!/usr/bin/env python
# coding: utf-8

# # Prelucrarea limbajului natural: Analiza sintactică
# 
#  - Andrei Olaru
# 
# Scopul acestui laborator îl reprezintă familiarizarea cu analiza sintactică a limbajului natural, prin identificarea structurii propoziției și rolului fiecărui cuvânt.
# 

# ## Gramatica
# 
# Se consideră următoarea gramatică (similară cu cea din curs):
# 
# $
# S \rightarrow \mathit{NP} \; \mathit{VP} \\
# S \rightarrow \mathit{Aux} \; \mathit{NP} \; \mathit{VP} \\
# S \rightarrow \mathit{VP} \\
# \mathit{NP} \rightarrow \mathit{Pronoun} \\
# \mathit{NP} \rightarrow \mathit{Proper-Noun} \\
# \mathit{NP} \rightarrow \mathit{Det} \; \mathit{Nominal} \\
# \mathit{Nominal} \rightarrow \mathit{Noun} \\
# \mathit{Nominal} \rightarrow \mathit{Nominal} \; \mathit{Noun} \\
# \mathit{Nominal} \rightarrow \mathit{Nominal} \; \mathit{PP} \\
# \mathit{VP} \rightarrow \mathit{Verb} \\
# \mathit{VP} \rightarrow \mathit{Verb} \; \mathit{NP} \\
# \mathit{VP} \rightarrow \mathit{Verb} \; \mathit{NP} \; \mathit{PP} \\
# \mathit{VP} \rightarrow \mathit{Verb} \; \mathit{PP} \\
# \mathit{VP} \rightarrow \mathit{VP} \; \mathit{PP} \\
# \mathit{PP} \rightarrow \mathit{Preposition} \; \mathit{NP} \\
# \\
# \mathit{Det} \rightarrow \mathit{that} \;|\; \mathit{this} \;|\; \mathit{a} \\
# \mathit{Noun} \rightarrow \mathit{book} \;|\; \mathit{flight} \;|\; \mathit{meal} \;|\; \mathit{money} \\
# \mathit{Verb} \rightarrow \mathit{book} \;|\; \mathit{include} \;|\; \mathit{prefer} \\
# \mathit{Pronoun} \rightarrow \mathit{I} \;|\; \mathit{she} \;|\; \mathit{me} \;|\; \mathit{you} \\
# \mathit{Proper-Noun} \rightarrow \mathit{Huston} \;|\; \mathit{TWA} \\
# \mathit{Aux} \rightarrow \mathit{does} \;|\; \mathit{do} \\
# \mathit{Preposition} \rightarrow \mathit{from} \;|\; \mathit{to} \;|\; \mathit{on} \;|\; \mathit{near} \;|\; \mathit{through} \;|\; \mathit{that} \;|\; \mathit{with} \\
# $
# 
# Vom reprezenta gramatica astfel:

# In[2]:

from copy import deepcopy


S, NP, VP, Aux, Pronoun, ProperNoun, Det, Nominal, Noun, PP, Verb, Preposition =     "S NP VP Aux Pronoun ProperNoun Det Nominal Noun PP Verb Preposition".split(" ")

G = [
    (S, NP, VP),
    (S, Aux, NP, VP),
    (S, VP),
    (NP, Pronoun),
    (NP, ProperNoun),
    (NP, Det, Nominal),
    (NP, Pronoun, Nominal),
    (Nominal, Noun),
    (Nominal, Nominal, Noun),
    (Nominal, Nominal, PP),
    (VP, Verb),
    (VP, Verb, NP),
    (VP, Verb, NP, PP),
    (VP, Verb, PP),
    (PP, Preposition, NP),
]
Lexicon = {
    Det: ["that", "this", "a", "an", "the"],
    Noun: ["book", "flight", "meal", "money", "elephant", "pajamas", "man"],
    Verb: ["book", "include", "includes", "prefer", "shot", "hit", "saw"],
    Pronoun: ["I", "she", "me", "you", "my", "we"],
    ProperNoun: ["Huston", "TWA"],
    Aux: ["does", "do"],
    Preposition: ["from", "to", "on", "near", "through", "that", "with", "in"],
}


# In[3]:


# reprezentăm un nod ca un tuplu cu eticheta pe prima poziție și nodurile copii pe celalalte poziții.
# Frunzele pot să nu mai fie conținute ca tupluri, ci pot fi reprezentate direct prin eticheta lor.
# Nodurile N cu o singură frunză F vor fi reprezentate ca "N: F"
def print_tree(node, indent = ""):
    if isinstance(node, tuple):
        label, children = node[0], node[1:]
    else:
        label, children = node, []
    if len(children) == 1 and not isinstance(children[0], tuple): #shorten
        label, children = label + ": " + children[0], []
    print(indent, label)
    for c in children:
        print_tree(c, indent + "   ")
        
print_tree((S, (NP, (Pronoun, "I")), (VP, (Verb, "am"), (NP, (Det, "a"), (Nominal, (Noun, "human"))))))


# **Cerință:** Analizați sintactic următoarele propoziții, folosind gramatica dată, afișând arborele. Pentru cazurile de ambiguitate, se vor afișa toți arborii de derivare găsiți:

# In[5]:


Sentences = [
    "Book that flight",
    "The flight includes a meal",                
    "I prefer a flight that includes a meal",
    "I shot an elephant in my pajamas",
    "I hit a man with an umbrella",
]


def rule_matches(rule, words):
    all_match = True
    start = 0
    end = len(words)

    for i in rule[1:]:
        se_afla = False

        if start >= len(words):
            all_match = False
            break

        for j in range(start, end):
            if i == words[j][0]:
                se_afla = True
                start = j + 1
                end = j + 2
                break

        if not se_afla:
            all_match = False
            break

    if all_match:
        for i in range(start - len(rule) + 1, start):

            if i == start-len(rule)+1:
                words[i] = (rule[0], words[i])

            else:
                words[start - len(rule) + 1] += (words[i],)

        i = start - len(rule) + 2

        while(i < start):
            words.remove(words[i])
            start -= 1

        return True

    else:
        return False


possible_trees = []


def build_parse_trees2(words, index):
    if(index < len(words)):
        for i in Lexicon:
            if words[index] in Lexicon[i]:
                new_words = deepcopy(words)
                new_words[index] = (i, new_words[index])
                build_parse_trees2(new_words, index + 1)

    elif(index > len(words)-1):
        if(words[0][0] == 'S' and len(words) == 1):

            if words not in possible_trees:
                possible_trees.append(words)

        else:
            for i in G:
                new_words = deepcopy(words)

                if(rule_matches(i, new_words)):
                    build_parse_trees2(new_words, index)


def build_parse_trees(sentence):
    words = sentence.split(' ')
    words[0] = words[0].lower() if words[0] not in ProperNoun and words[0] != 'I' else words[0]
    build_parse_trees2(words, 0)


def parse_sentences():
    for sentence in Sentences:
        build_parse_trees(sentence)
        for actual_parse_tree in possible_trees:
            print(actual_parse_tree)

        del possible_trees[:]

parse_sentences()
