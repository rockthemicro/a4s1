import nltk
import re
import time
import csv
import math

TITLE = 0
TEXT = 1
CTEXT = 2
VOCAB = 3
NR_TERMS = 4
TOKS = 5
SENT_SCORES = 6
TITLE_TOKS = 7
TOP3 = 8
INIT_SENT = 9


def add_to_top3(id_x, sc_x, top3):
    [[_, sc_a], [_, sc_b], [_, sc_c]] = top3

    if sc_x > sc_a:
        top3[2] = top3[1]
        top3[1] = top3[0]
        top3[0] = [id_x, sc_x]

    elif sc_x > sc_b:
        top3[2] = top3[1]
        top3[1] = [id_x, sc_x]

    elif sc_x > sc_c:
        top3[2] = [id_x, sc_x]


def rearrange_top3(top3):
    for i in range(2):
        for j in range(i + 1, 3):
            if top3[i][0] > top3[j][0]:
                tmp = top3[i]
                top3[i] = top3[j]
                top3[j] = tmp


def print_article_info(id, articles):
    print('Titlul este: ')
    print(articles[id][TITLE])
    print('')

    print('Rezumatul este: ')
    print(articles[id][TEXT])
    print('')

    print('Textul este: ')
    print(articles[id][CTEXT])
    print('')

    print('Propozitiile alese pentru rezumat sunt: ')
    for i in range(0, articles[id][TOP3].__len__()):
        print(articles[id][TOP3][i][2])
    print('')

def main():
    start = time.time()
    stop_words = set(nltk.corpus.stopwords.words('english'))

    articles = []
    where_word_appears = {}
    with open("dataset_tema3.csv") as file:
        csv_reader = csv.reader(file, delimiter=',')
        line_id = 0
        almost_useless_idx = 0
        for row in csv_reader:
            almost_useless_idx += 1
            if almost_useless_idx == 1:
                continue

            text = row[2].decode('utf-8')

            # spargem textul in propozitii
            sent = nltk.sent_tokenize(text)
            if len(sent) == 0 or (len(sent) == 1 and len(sent[0]) == 0):
                continue

            articles.append([row[0].decode('utf-8'), row[1].decode('utf-8'), text, {}, 0, [], [], {}, [], []])
            articles[line_id][INIT_SENT] = nltk.sent_tokenize(text)

            # eliminam numerele
            sent = map(lambda x: re.sub(r'\d+', '', x), sent)

            # vom face o matrice de tokeni (liniile matricei sunt propozitii)
            tokens = articles[line_id][TOKS]
            idx = 0
            lemmatizer = nltk.stem.WordNetLemmatizer()
            vocabulary = articles[line_id][VOCAB]
            for x in sent:
                tokens.append(nltk.word_tokenize(x))  # tokenizam o propozitie
                tokens[idx] = nltk.pos_tag(tokens[idx]) # adaugam la fiecare cuvant o categorie sintactica
                # eliminam stop words si cuvintele de lungime 1 si non-substantivele, si lematizam (obtinem radacina)
                tokens[idx] = [lemmatizer.lemmatize(i[0]) for i in tokens[idx] if not i in stop_words and len(i[0]) > 1 and i[1][:2] == 'NN']
                for token in tokens[idx]:

                    # calculam frecventa cuvintelor in articolul curent (vocabulary tine de articolul curent)
                    if token not in vocabulary:
                        vocabulary[token] = 1
                    else:
                        vocabulary[token] = vocabulary[token] + 1

                    articles[line_id][NR_TERMS] += 1

                    # memoram pt toate cuvintele indicele articolului in care apare
                    if token in where_word_appears:
                        if line_id not in where_word_appears[token]:
                            where_word_appears[token].append(line_id)
                    else:
                        where_word_appears[token] = [line_id]

                #print(tokens[idx])

                idx += 1

            title_tokens = nltk.pos_tag(nltk.word_tokenize(re.sub(r'\d+', '', articles[line_id][TITLE])))
            title_tokens = [lemmatizer.lemmatize(i[0]) for i in title_tokens if not i in stop_words and len(i[0]) > 1 and i[1][:2] == 'NN']
            # plasam o valoare dummy in dictionarul de cuvinte al titlului
            for tok in title_tokens:
                articles[line_id][TITLE_TOKS][tok] = 0

            if title_tokens.__len__() == 0:
                articles[line_id][TITLE_TOKS]['aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'] = 0

            line_id += 1

        nr_articles = line_id

        for article_id in range(nr_articles):
            max_sent_score = 0.0

            # pentru fiecare propozitie (sparta in tokens)
            for tokens in articles[article_id][TOKS]:
                sent_score = 0.0
                words_in_title = 0

                # pentru fiecare token al propozitiei
                for token in tokens:
                    tf = float(articles[article_id][VOCAB][token]) / float(articles[article_id][NR_TERMS])
                    idf = math.log(float(nr_articles) / float(where_word_appears[token].__len__()))
                    idf = 1.0 if idf == 0.0 else idf
                    sent_score += (tf * idf)

                    # numaram cate cuvinte regasim in titlu
                    if token in articles[article_id][TITLE_TOKS]:
                        words_in_title += 1

                # adaugam extra punctaj pt cuvinte care se regasesc in titlu
                sent_score += (float(words_in_title) / float(articles[article_id][TITLE_TOKS].__len__()))

                articles[article_id][SENT_SCORES].append(sent_score)
                if sent_score > max_sent_score:
                    max_sent_score = sent_score

            nr_sentences = articles[article_id][SENT_SCORES].__len__()
            onethird_nr_sentences = nr_sentences / 3
            twothirds_nr_sentences = (2 * nr_sentences) / 3

            for i in range(onethird_nr_sentences):
                articles[article_id][SENT_SCORES][i] *= 0.3

            for i in range(onethird_nr_sentences, twothirds_nr_sentences):
                articles[article_id][SENT_SCORES][i] *= 0.5

            for i in range(twothirds_nr_sentences, nr_sentences):
                articles[article_id][SENT_SCORES][i] *= 0.7

            # pentru fiecare scor de propozitie normalizam scorul impartindu-l la cel mai mare scor
            # obtinut de o propozitie in articolul curent
            max_sent_score = 1 if max_sent_score == 0.0 else max_sent_score
            top3 = [[0, 0.0], [0, 0.0], [0, 0.0]]

            for i in range(articles[article_id][SENT_SCORES].__len__()):
                articles[article_id][SENT_SCORES][i] /= max_sent_score
                add_to_top3(i, articles[article_id][SENT_SCORES][i], top3)

            rearrange_top3(top3)
            articles[article_id][TOP3] = top3
            for top in top3:
                top.append(articles[article_id][INIT_SENT][top[0]])

    print(time.time() - start)
    print_article_info(0, articles)


if __name__ == '__main__':
    main()


'''
impart in propozitii si pastrez si propozitiile initiale si apoi le procesez
remove number, semne de punctuatie
remove stopwords
pastrez substantive
lematizare
calculez tf, idf, tf-idf (tf * idx)
calculez scor/propozitii
updatez scoruri in fct titlu (valoare aditionala adaugata la fiecare propozitie, egala cu nrcuv_care_apar_intitlu / nrcuv_titlu)
aplic o pondere fiecarei propoztii
'''