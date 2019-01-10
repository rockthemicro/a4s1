#!/usr/bin/env python
# coding: utf-8

# # Prelucrarea Limbajului Natural: Analiza Sentimentelor
#  - Tudor Berariu
#  - Andrei Olaru
# 
# Scopul acestui laborator îl reprezintă rezolvarea unei probleme ce implică analiza unor documente în limbaj natural și învățarea unui algoritm simplu de clasificare: **Naive Bayes**.
# 
# ## Analiza Sentimentelor
# 
# O serie de probleme de inteligență artificială presupun asocierea unei clase unui document în limbaj natural. Exemple de astfel de probleme sunt: **clasificarea** email-urilor în *spam* sau *ham* sau a recenziilor unor filme în *pozitive* sau *negative*. În laboratorul de astăzi vom aborda problema din urmă.
# 
# Folosind setul de date de aici: http://www.cs.cornell.edu/people/pabo/movie-review-data/ (2000 de recenzii de film), vom construi un model care să discrimineze între recenziile pozitive și recenziile negative.

# ## Algoritmul Naive Bayes
# 
# ### Clasificare
# 
# Având un set de date $\langle \mathbf{X}, \mathbf{T} \rangle$ compus din $N$ exemple $\mathbf{x}^{(i)}$, $1 \le i \le N$, descrise prin $k$ atribute $(x^{(i)}_1, x^{(i)}_2, \ldots, x^{(i)}_k)$ și etichetate cu o clasă $t^{(i)} \in \mathcal{C}$, se cere construirea unui clasificator care să eticheteze exemple noi.
# 
# ### Naive Bayes
# 
# **Naive Bayes** reprezintă o *metodă statistică inductivă* de clasificare, bazată pe Teorema lui Bayes pentru exprimarea relației dintre probabilitatea *a priori* și probabilitatea *posterioară* ale unei ipoteze.
# 
# $$P(c \vert \mathbf{x}) = \frac{P(\mathbf{x} \vert c) \cdot P(c)}{P(\mathbf{x})}$$
# 
#  - $P(c)$ reprezintă probabilitatea *a priori* a clasei $c$
#  - $P(c \vert \mathbf{x})$ reprezintă probabilitatea *a posteriori* (după observarea lui $\mathbf{x}$)
#  - $P(\mathbf{x} \vert c)$ reprezitnă probabilitatea ca $\mathbf{x}$ să aparțină clasei $c$ (*verosimilitatea*)
#  
# Un clasificator **Naive Bayes** funcționează pe principiul verosimilității maxime (eng. *maximum likelihood*), deci alege clasa $c$ pentru care probabilitatea $P(c \vert x)$ este maximă:
# 
# $$c_{MAP} = \underset{c \in \mathcal{C}}{\arg\max} P(c \vert \mathbf{x}) = \underset{c \in \mathcal{C}}{\arg\max} \frac{P(\mathbf{x} \vert c) \cdot P(c)}{P(x)} = \underset{c \in \mathcal{C}}{\arg\max} P(\mathbf{x} \vert c) \cdot P(c)$$
# 
# Cum fiecare exemplu $\mathbf{x}$ este descris prin $K$ atribute:
# 
# $$c_{MAP} = \underset{c \in \mathcal{C}}{\arg\max} P(x_1, x_2, \ldots x_K \vert c) \cdot P(c)$$
# 
# Algoritmul **Naive Bayes** face o presupunere simplificatoare, și anume, că atributele unui exemplu sunt *condițional independente* odată ce clasa este cunoscută:
# 
# $$P(\mathbf{x} \vert c) = \displaystyle\prod_i P(x_i \vert c)$$
# 
# Astfel clasa pe care o prezice un clasificator **Naive Bayes** este:
# 
# $$c_{NB} = \underset{c \in \mathcal{C}}{\arg\max} P(c) \cdot \displaystyle \prod_{i}^{K} P(x_i \vert c)$$
# 
# 

# ## Clasificarea documentelor
# 
# Pentru clasificare documentele vor fi reprezentate prin vectori binari de lungimea vocabularului (eng. *bag of words*). Practic fiecare document va avea 1 pe pozițiile corspunzătoare cuvintelor pe care le conține și 0 pe toate celelalte poziții. Dimensiunea unui exemplu $\mathbf{x}$ este, deci, numărul de cuvinte diferite din setul de date.
# 
# ### Estimarea parametrilor modelului Naive Bayes
# 
# Probabilitatea _a priori_ pentru o clasă $c \in \mathcal{C}$:
# 
# $$P(c) = \frac{\#\text{ docs in class }c}{\#\text{ total docs}}$$
# 
# $P(x_i \vert c)$ va reprezenta probabilitatea de a apărea cuvântul $x_i$ într-un document din clasa $c$ și o vom estima cu raportul dintre numărul de apariții ale cuvântului $x_i$ în documentele din clasa $c$ și numărul total de cuvinte ale acelor documente:
# 
# $$P(x_i \vert c) = \frac{\#\text{ aparitii ale lui } x_i \text{ in documente din clasa } c}{\#\text{ numar total de cuvinte in documentele din clasa } c}$$
# 
# Deoarece este posibil ca un cuvant _rar_ ce apare într-un exemplu de test să nu se găsească deloc într-una din clase, se poate întâmpla ca un astfel de _accident_ să anuleze complet o probabilitate. Dacă un singur factor al unui produs este zero, atunci produsul devine zero. De aceea vom folosi netezire Laplace (eng. _Laplace smoothing_):
# 
# $$P(x_i \vert c) = \frac{\#\text{ aparitii ale lui } x_i \text{ in documente din clasa } c + \alpha}{\#\text{ numar total de cuvinte in documentele din clasa } c + \vert Voc \vert \cdot \alpha}$$

# ## Setul de date
# 
#  1. Descărcați setul de date **polarity dataset v2.0** de aici http://www.cs.cornell.edu/people/pabo/movie-review-data/
#  2. Dezarhivați fișierul **review_polarity.tar.gz** și rearhivați directorul review_polarity ca zip.
#  3. Plasați / încărcați **review_polarity.zip** în directorul de lucru.

# In[18]:


import zipfile

zipFile = zipfile.ZipFile("review_polarity.zip")

pos_files = [f for f in zipFile.namelist() if '/pos/cv' in f]
neg_files = [f for f in zipFile.namelist() if '/neg/cv' in f]

pos_files.sort()
neg_files.sort()

print("Recenzii pozitive: " + str(len(pos_files)) + "; Recenzii negative: " + str(len(neg_files)))

# Raspunsul asteptat: "Recenzii pozitive: 1000; Recenzii negative: 1000"
assert(len(pos_files) == 1000 and len(neg_files) == 1000)


# ### Setul de antrenare și setul de testare
# 
# Vom folosi 80% din datele din fiecare clasă pentru antrenare și 20% pentru testare.

# In[19]:


tr_pos_no = int(.8 * len(pos_files))
tr_neg_no = int(.8 * len(neg_files))

from random import shuffle
# shuffle(pos_files)
# shuffle(neg_files)

pos_train = pos_files[:tr_pos_no] # Recenzii pozitive pentru antrenare
pos_test  = pos_files[tr_pos_no:] # Recenzii pozitive pentru testare
neg_train = neg_files[:tr_neg_no] # Recenzii negative pentru antrenare
neg_test  = neg_files[tr_neg_no:] # Recenzii negative pentru testare


# ## Construirea vocabularului și calculul parametrilor
# 
# Funcția `parse_document` primește calea către unul dinte fișierele aflate în arhivă și întoarce cuvintele din acest fișier (exceptând cuvintele cu o singură literă și pe cele din lista `STOP_WORDS`. Implementați funcția `count_words` astfel încât să întoarcă un dicționar cu o intrare pentru fiecare cuvânt care să conțină un tuplu cu două valori: numărul de apariții ale acelui cuvânt în rencezii pozitive și numărul de apariții în recenzii negative. În afara acelui dicționar se vor întoarce și numărul total de cuvinte din recenziile pozitive și numărul total de cuvinte din recenziile negative.

# In[28]:


STOP_WORDS = []
#STOP_WORDS = [line.strip() for line in open("Lab12-stop_words")]

import re

def parse_document(path):
    for word in re.findall(r"[-\w']+", zipFile.read(path).decode("utf-8")):
        if len(word) > 1 and word not in STOP_WORDS:
            yield word

def count_words():
    vocabulary = {}
    pos_words_no = 0
    neg_words_no = 0
    
    # ------------------------------------------------------
    # <TODO 1> numrati aparitiile in documente pozitive si
    # in documente negative ale fiecarui cuvant, precum si numarul total
    # de cuvinte din fiecare tip de recenzie
    i = 0
    for neg_file in neg_train:
        for word in parse_document(neg_file):
            if word in vocabulary:
                vocabulary[word][1] += 1
            else:
                vocabulary[word] = [0, 0]
            
            neg_words_no += 1
    
    for pos_file in pos_train:
        for word in parse_document(pos_file):
            if word in vocabulary:
                vocabulary[word][0] += 1
            else:
                vocabulary[word] = [0, 0]
            
            pos_words_no += 1
    # ------------------------------------------------------
    
    return (vocabulary, pos_words_no, neg_words_no)

# -- VERIFICARE --
training_result_words = count_words()

(voc, p_no, n_no) = training_result_words
print("Vocabularul are ", len(voc), " cuvinte.")
print(p_no, " cuvinte in recenziile pozitive si ", n_no, " cuvinte in recenziile negative")
print("Cuvantul 'beautiful' are ", voc.get("beautiful", (0, 0)), " aparitii.")
print("Cuvantul 'awful' are ", voc.get("awful", (0, 0)), " aparitii.")

# Daca se comentează liniile care reordonează aleator listele cu exemplele pozitive și negative,
# rezultatul așteptat este:
#
# Vocabularul are  44895  cuvinte.
# 526267  cuvinte in recenziile pozitive si  469812  cuvinte in recenziile negative
# Cuvantul 'beautiful' are  (165, 75)  aparitii.
# Cuvantul 'awful' are  (16, 89)  aparitii.


# ### Predicția sentimentului unei recenzii noi
# 
# Implementați funcția `predict` care primește parametrii `params` (vocabularul, numărul total de cuvinte din recenziile pozitive și numărul total de cuvinte din recenziile negative) și `path` (calea către o recenzie din cadrul arhivei) și întoarce clasa mai probabilă și logaritmul acelei probabilități. Al treilea argument (opțional) al funcției `predict` este coeficientul pentru netezire Laplace.
# 
# Așa cum a fost explicat anterior, clasa pe care o prezice un clasificator **Naive Bayes** este dată de următoarea expresie:
# 
# $$c_{NB} = \underset{c \in \mathcal{C}}{\arg\max} P(c) \cdot \displaystyle \prod_{i}^{K} P(x_i \vert c)$$
# 
# Pentru a evita lucrul cu numere foarte mici ce pot rezulta din produsul multor valori subunitare, vom logaritma expresiile date:
# 
# $$c_{NB} = \underset{c \in \mathcal{C}}{\arg\max} \log(P(c)) + \displaystyle\sum_{i}^{K} \log(P(x_i \vert c))$$
# 
# Pentru calculul probabilitatilor, vedeti sectiunea "Estimarea parametrilor modelului Naive Bayes", mai sus. În cod, `log_pos` și `log_neg` trebuie însumate cu logaritmul pentru fiecare exemplu -- $ \log(P(c)) $ este deja adunat.
# 

# In[31]:


from math import log

def predict(params, path, alpha = 1):
    (vocabulary, pos_words_no, neg_words_no) = params
    log_pos = log(0.5)
    log_neg = log(0.5)
    
    # ----------------------------------------------------------------------
    # <TODO 2> Calculul logaritmilor probabilităților
    i = 0
    for word in parse_document(path):
        if word in vocabulary:
            log_pos += log((vocabulary[word][0] + alpha) / (pos_words_no + alpha))
            log_neg += log((vocabulary[word][1] + alpha) / (neg_words_no + alpha))
    # ----------------------------------------------------------------------
    
    if log_pos > log_neg:
        return "pos", log_pos
    else:
        return "neg", log_neg

# -- VERIFICARE --
print(zipFile.read(pos_test[14]).decode("utf-8"))
(xxxxx1, xxxxx2) = predict(training_result_words, pos_test[14])

# Daca se comentează liniile care reordonează aleator listele cu exemplele pozitive și negative,
# rezultatul așteptat este:
#
# ('pos', -1790.27088356391) pentru un film cu Hugh Grant și Julia Roberts (o mizerie siropoasă)
#
# Recenzia este clasificată corect ca fiind pozitivă.


# ## 3. Evaluarea modelului
# 
# Pentru a evalua modelul vom calcula acuratețea acestuia și matricea de confuzie, folosind datele de test (`pos_test` și `neg_test`).
# 
# [Vedeți aici despre matricea de confuzie](https://en.wikipedia.org/wiki/Confusion_matrix)

# In[22]:


def evaluate(params, prediction_func):
    conf_matrix = {}
    conf_matrix["pos"] = {"pos": 0, "neg": 0}
    conf_matrix["neg"] = {"pos": 0, "neg": 0}
    right = 0
    wrong = 0
    # ----------------------------------------------------------------------
    # <TODO 3> : Calcularea acurateței și a matricei de confuzie
    for pos_file in pos_test:
        (res, acc) = prediction_func(params, pos_file)
        if res == "pos":
            conf_matrix["pos"]["pos"] += 1
            right += 1
        else:
            conf_matrix["pos"]["neg"] += 1
            wrong += 1

    for neg_file in neg_test:
        (res, acc) = prediction_func(params, neg_file)
        if res == "neg":
            conf_matrix["neg"]["neg"] += 1
            right += 1
        else:
            conf_matrix["neg"]["pos"] += 1
            wrong += 1

    accuracy = float(right / float((right + wrong)))
    #------------------------------------------------------------
    
    return accuracy, conf_matrix
# -----------------------------------------------------------

def print_confusion_matrix(cm):
    print("    | ", "{0:^10}".format("pos"), " | ", "{0:^10}".format("neg"))
    print("{0:-^3}".format(""), "+", "{0:-^12}".format(""), "+", "{0:-^12}".format("-", fill="-"))
    print("pos | ", "{0:^10}".format(cm["pos"]["pos"]), " | ", "{0:^10}".format(cm["pos"]["neg"]))
    print("neg | ", "{0:^10}".format(cm["neg"]["pos"]), " | ", "{0:^10}".format(cm["neg"]["neg"]))


# -- VERIFICARE --
(acc_words, cm_words) = evaluate(training_result_words, predict)
print("Acuratetea pe setul de date de test: ", acc_words * 100, "%. Matricea de confuzie:")
print_confusion_matrix(cm_words)

# Daca se comentează liniile care reordonează aleator listele cu exemplele pozitive și negative,
# rezultatul așteptat este:
#
# Acuratetea pe setul de date de test:  80.5 %. Matricea de confuzie:
#     |     pos      |     neg    
# --- + ------------ + ------------
# pos |     155      |      45    
# neg |      33      |     167


# ## 4. Un model mai bun? Să folosim bigrame? Da!
# 
# Implementați funcția `count_bigrams`, similară cu `count_words`, doar că de data aceasta dicționarul va conține bigramele din text. Funcția va întoarce tot trei elemente: dicționarul cu aparițiile în recenzii pozitive și în recenzii negative ale bigramelor, numărul total de bigrame din recenziile pozitive și numărul total de bigrame din recenziile negative.
# 
# Salvați o bigramă prin concatenarea primului cuvânt, semnului ":" și a celui de-al doilea cuvânt. De exemplu: `"texas:ranger"`.

# In[23]:


def count_bigrams():
    bigrams = {}
    pos_bigrams_no = 0
    neg_bigrams_no = 0

    # ----------------------------------------------------------------------
    # <TODO 4>: Numarati bigramele
    for neg_file in neg_train:
        words = list(parse_document(neg_file))
        words_len = len(words)
        for i in range(words_len - 1):
            word = words[i] + ":" + words[i+1]
            if word in bigrams:
                bigrams[word][1] += 1
            else:
                bigrams[word] = [0, 0]

            neg_bigrams_no += 1

    for pos_file in pos_train:
        words = list(parse_document(pos_file))
        words_len = len(words)
        for i in range(words_len - 1):
            word = words[i] + ":" + words[i+1]
            if word in bigrams:
                bigrams[word][0] += 1
            else:
                bigrams[word] = [0, 0]

            pos_bigrams_no += 1

    #-----------------------------------------------
    
    return bigrams, pos_bigrams_no, neg_bigrams_no

# -- VERIFICARE --
training_result_bigrams = count_bigrams()

(big, pos_b, neg_b) = training_result_bigrams
print("Tabelul are ", len(big), " bigrame.")
print(pos_b, " bigrame in recenziile pozitive si ", neg_b, " bigrame in recenziile negative")
print("Bigrama 'beautiful actress' are ", big.get("beautiful:actress", (0, 0)), " aparitii.")
print("Bigrama 'awful movie' are ", big.get("awful:movie", (0, 0)), " aparitii.")

# Daca se comentează liniile care reordonează aleator listele cu exemplele pozitive și negative,
# rezultatul așteptat este:
#
# Tabelul are  428997  bigrame.
# 525467  bigrame in recenziile pozitive si  469012  bigrame in recenziile negative
# Bigrama 'beautiful actress' are  (2, 0)  aparitii.
# Bigrama 'awful movie' are  (1, 4)  aparitii.


# ### Funcția de predicție folosind bigrame
# 
# Implementați funcția `predict2` care să calculeze logaritmul probabilității fiecărei clase pe baza bigramelor din text. Trebuie să calculați `log_pos` și `log_neg`.

# In[24]:


def predict2(params, path, alpha = 1):
    (bigrams, pos_bigrams_no, neg_bigrams_no) = params
    log_pos = log(0.5)
    log_neg = log(0.5)
    
    # ----------------------------------------------------------------------
    # <TODO 5> Calculul logaritmilor probabilităților folosind bigramele
    words = list(parse_document(path))
    for i in range(len(words) - 1):
        bigram = words[i] + ":" + words[i + 1]
        if bigram in bigrams:
            log_pos += log((bigrams[bigram][0] + alpha) / (pos_bigrams_no + alpha))
            log_neg += log((bigrams[bigram][1] + alpha) / (neg_bigrams_no + alpha))
    # ----------------------------------------------------------------------
    
    if log_pos > log_neg:
        return "pos", log_pos
    else:
        return "neg", log_neg
    
# -- VERIFICARE --
print(zipFile.read(pos_test[14]).decode("utf-8"))
predict2(training_result_bigrams, pos_test[14])

# Daca se comentează liniile care reordonează aleator listele cu exemplele pozitive și negative,
# rezultatul așteptat este:
#
# ('pos', -3034.428732037113) pentru același film cu Hugh Grant


# In[25]:


# -- VERIFICARE --
(acc_bigrams, cm_bigrams) = evaluate(training_result_bigrams, predict2)
print("Acuratetea pe setul de date de test, cu bigrame: ", acc_bigrams * 100, "%. Matricea de confuzie:")
print_confusion_matrix(cm_bigrams)

# Daca se comentează liniile care reordonează aleator listele cu exemplele pozitive și negative,
# rezultatul așteptat este:
#
# Acuratetea pe setul de date de test:  84.5 %. Matricea de confuzie:
#     |     pos      |     neg    
# --- + ------------ + ------------
# pos |     161      |      39    
# neg |      23      |     177   


# ## La final...
# 
#  1. Decomentați liniile care reordonează aleator listele cu exemplele pozitive și cele negative (secțiunea "Setul de antrenare și setul de testare"). Rulați de mai multe ori. Este întotdeauna mai bun modelul cu bigrame? Acuratețea variază mult de la o rulare la alta?
#  2. Încercați să eliminați cuvintele de legătură (linia cu `STOP_WORDS`, din secțiunea "Construirea vocabularului..."). Ce impact are asupra performanței celor două modele?

# In[26]:


print("Acuratetea pe setul de date de test, cu cuvinte simple: ", acc_words * 100, "%. Matricea de confuzie:")
print_confusion_matrix(cm_words)

print("\n\nAcuratetea pe setul de date de test, cu bigrame: ", acc_bigrams * 100, "%. Matricea de confuzie:")
print_confusion_matrix(cm_bigrams)


# In[ ]:




