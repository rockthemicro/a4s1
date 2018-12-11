from collections import namedtuple
Factor = namedtuple("Factor", ["vars", "values"])

class Vertex:
    def __init__(self, key):
        self.id = key
        self.connectedTo = []
        self.parents = []
        self.cond_prob = []
        self.factor = None

    def addNeighbor(self, nbr):
        if nbr not in self.connectedTo:
            self.connectedTo.append(nbr)

    def addParent(self, parent):
        if parent not in self.parents:
            self.parents.append(parent)

    def __str__(self):
        return str(self.id) + ' connectedTo: ' + str([x for x in self.connectedTo])

    def getConnections(self):
        return self.connectedTo

    def getId(self):
        return self.id

    def getParents(self):
        return self.parents

    def getCondProb(self):
        return self.cond_prob

    def isConnectedTo(self, key):
        return key in self.connectedTo

    def setFactor(self, factor):
        self.factor = factor

    def getFactor(self):
        return self.factor


class Graph:
    def __init__(self):
        self.vertList = {}
        self.numVertices = 0

    def addVertex(self, key):
        if key in self.vertList:
            return

        self.numVertices = self.numVertices + 1

        newVertex = Vertex(key)
        self.vertList[key] = newVertex

        return newVertex

    def getVertex(self, n):
        if n in self.vertList:
            return self.vertList[n]
        else:
            return None

    def __contains__(self, n):
        return n in self.vertList

    def addEdge(self, f, t):

        # ma asigur mai intai ca exista cele 2 noduri intre care vreau sa trag arc
        if f not in self.vertList:
            nv = self.addVertex(f)
        if t not in self.vertList:
            nv = self.addVertex(t)

        # duc arc de la f la t (in lista connectedTo a nodului cu cheia f adaug nodul cu cheia t)
        self.vertList[f].addNeighbor(t)

    def getVertices(self):
        return self.vertList.keys()

    def __iter__(self):
        return iter(self.vertList.values())

    def __str__(self):
        string_graph = ''
        for vertex in self.vertList:
            string_graph = string_graph + vertex + "(parents: " + str(self.vertList[vertex].getParents())\
                           + " ----- children: " + str(self.vertList[vertex].getConnections()) + ");" + " "

        return string_graph


def increment_indexes(indexes):
    ind_len = len(indexes)
    index = ind_len - 1

    if 0 not in indexes:
        return

    while True:
        if indexes[index] == 0:
            indexes[index] = 1
            return

        # else, if it is 1
        indexes[index] = 0
        index -= 1


def build_graph_from_input(G, N, lines):
    for i in range(1, N + 1):
        curr_line = lines[i]
        curr_line_parts = curr_line.split(";")

        var_name = curr_line_parts[0].split(" ")[0]

        G.addVertex(var_name)

        nr_parents = 0
        parents = []

        var_parents = curr_line_parts[1].split(" ")
        for var_parent in var_parents:
            if var_parent == "":
                continue

            nr_parents += 1
            parents = parents + [var_parent]

            G.addEdge(var_parent, var_name)
            G.getVertex(var_name).addParent(var_parent)

        conditional_probabilities = curr_line_parts[2].split(" ")
        for cond_prob in conditional_probabilities:
            if cond_prob == "":
                continue

            G.getVertex(var_name).getCondProb().append(float(cond_prob))

        # building the Factor of the vertex
        probabilities = G.getVertex(var_name).getCondProb()
        probabilities_negated = [1 - x for x in probabilities]
        probabilities_whole = probabilities_negated + probabilities

        vars = [var_name] + parents
        values = {}

        nr_vars_in_factor = nr_parents + 1
        indexes = [0 for _ in range(nr_vars_in_factor)]

        for prob in probabilities_whole:
            values[tuple(indexes)] = prob
            increment_indexes(indexes)

        G.getVertex(var_name).setFactor(Factor(vars=vars, values=values))


def build_unoriented_graph_from_oriented(G):
    U = Graph()

    for vertex in G.getVertices():
        oldVertex = G.getVertex(vertex)
        connections = oldVertex.getConnections()

        for connection in connections:
            U.addEdge(vertex, connection)
            U.addEdge(connection, vertex)

    return U


def moralize_graph(U, G):
    for vertex in G.getVertices():
        for parent1 in G.getVertex(vertex).getParents():
            for parent2 in G.getVertex(vertex).getParents():
                if parent1 == parent2:
                    continue

                U.addEdge(parent1, parent2)
                U.addEdge(parent2, parent1)

    return U


def has_no_cords(H, cycle):
    for i in range(len(cycle) - 2):
        for j in range(i + 2, len(cycle)):
            if H.getVertex(cycle[i]).isConnectedTo(cycle[j]):
                return False

    return True


def dfs(H, vertex, visited, parent, path):
    vertices = H.getVertex(vertex).getConnections()

    for neighbour in vertices:
        if neighbour == parent:
            continue

        # if we have a cycle
        if visited[neighbour] == True:
            if neighbour not in path:
                continue

            # obtain the index of the neighbour in the path so far
            neighbour_index = path.index(neighbour)

            # if we have a bigger than 3 cycle and there are no cords, we return False
            if neighbour_index + 1 > 3 and has_no_cords(H, path[:neighbour_index + 1]):
                return False

            continue

        visited[neighbour] = True
        ret = dfs(H, neighbour, visited, vertex, [neighbour] + path)

        if ret == False:
            return False

    return True


def graph_is_cordal(H):
    visited = {}
    vertices = H.getVertices()

    for vertex in vertices:
        visited[vertex] = False

    for vertex in vertices:
        if visited[vertex] == False:
            visited[vertex] = True
            ret = dfs(H, vertex, visited, "", [vertex])

            if ret == False:  # if we find a bigger than 3 cycle, with no cords, dfs returns False
                return False

    return True


def build_cordal_graph(H):
    #if graph_is_cordal(H):
    #    return H

    tmp_vertices = H.getVertices()
    done = False

    # eliminate vertices that don't need any extra edges
    while not done:
        new_tmp_vertices = []
        done = True

        for vertex in tmp_vertices:
            connectedTo = H.getVertex(vertex).getConnections()
            can_delete = True

            for neighbour1 in connectedTo:
                if neighbour1 not in tmp_vertices:
                    continue

                for neighbour2 in connectedTo:
                    if neighbour2 not in tmp_vertices:
                        continue

                    if neighbour1 == neighbour2:
                        continue

                    if H.getVertex(neighbour1).isConnectedTo(neighbour2) == False:
                        can_delete = False

            if can_delete == False:
                new_tmp_vertices.append(vertex)
            else:
                done = False

        tmp_vertices = new_tmp_vertices

    # eliminate 1 vertex at a time and add edges between its parents
    # we'll eliminate the one that needs the least amount of extra edges
    done = False

    while not done:
        done = True
        edges_needed = {}
        minimum_edges_needed = -1
        minimum_edges_vertex = ""

        for tmp_vertex in tmp_vertices:
            connectedTo = H.getVertex(tmp_vertex).getConnections()
            edge_count = 0


            for neighbour1 in range(len(connectedTo) - 1):
                if connectedTo[neighbour1] not in tmp_vertices:
                    continue

                for neighbour2 in range(neighbour1 + 1, len(connectedTo)):
                    if connectedTo[neighbour2] not in tmp_vertices:
                        continue

                    if H.getVertex(connectedTo[neighbour1]).isConnectedTo(connectedTo[neighbour2]) == False:
                        edge_count += 1

            edges_needed[tmp_vertex] = edge_count

            if edge_count == 0:
                continue
            else:
                done = False

            if minimum_edges_needed == -1:
                minimum_edges_needed = edge_count
                minimum_edges_vertex = tmp_vertex

            if minimum_edges_needed > edge_count:
                minimum_edges_needed = edge_count
                minimum_edges_vertex = tmp_vertex

        if done:
            break

        connectedTo = H.getVertex(minimum_edges_vertex).getConnections()

        for neighbour1 in range(len(connectedTo) - 1):
            if connectedTo[neighbour1] not in tmp_vertices:
                continue

            for neighbour2 in range(neighbour1 + 1, len(connectedTo)):
                if connectedTo[neighbour2] not in tmp_vertices:
                    continue

                if H.getVertex(connectedTo[neighbour1]).isConnectedTo(connectedTo[neighbour2]) == False:
                    H.addEdge(connectedTo[neighbour1], connectedTo[neighbour2])
                    H.addEdge(connectedTo[neighbour2], connectedTo[neighbour1])

        tmp_vertices.remove(minimum_edges_vertex)

    return H


def BronKerbosch1(H_star, R, P, X, result):
    if len(P) == 0 and len(X) == 0:
        result.append(R)

    while len(P) > 0:
        vertex = P[0]

        P2 = []
        X2 = []

        neighbours = H_star.getVertex(vertex).getConnections()

        for tmp_vertex in P:
            if tmp_vertex in neighbours:
                P2.append(tmp_vertex)

        for tmp_vertex in X:
            if tmp_vertex in neighbours:
                X2.append(tmp_vertex)

        BronKerbosch1(H_star, R + [vertex], P2, X2, result)

        P = P[1:]
        X = X + [vertex]


def build_graph_with_BronKerbosch1(H_star):
    C = Graph()

    vertices = H_star.getVertices()
    R = []
    P = list(vertices)
    X = []
    result = []

    BronKerbosch1(H_star, R, P, X, result)

    for i in range(len(result) - 1):
        clique_name_i = ""
        for vertex in result[i]:
            clique_name_i += vertex

        for j in range(i + 1, len(result)):
            clique_name_j = ""
            for vertex in result[j]:
                clique_name_j += vertex

            for vertex in result[i]:
                if vertex in result[j]:
                    C.addEdge(clique_name_i, clique_name_j)
                    C.addEdge(clique_name_j, clique_name_i)

                    break;

    return C


# akin to Prim's algorithm
def build_minimum_tree(C):
    T = Graph()

    vertices = list(C.getVertices())
    vertices_len = len(vertices)

    T.addVertex(vertices[0])
    T_vertices_len = 1

    while T_vertices_len < vertices_len:
        T_vertices = list(T.getVertices())

        for vertex in vertices:
            # if we already added it in the minimum coverage tree, we don't care about it
            if vertex in T_vertices:
                continue

            # if i have a vertex in my new tree that connects to an unadded vertex in the old tree, i add it
            for T_vertex in T_vertices:
                if C.getVertex(T_vertex).isConnectedTo(vertex):
                    T.addEdge(vertex, T_vertex)
                    T.addEdge(T_vertex, vertex)

                    break

        T_vertices_len += 1


    return T


def multiply(phi1, phi2):
    assert isinstance(phi1, Factor) and isinstance(phi2, Factor)
    vars1, vals1 = phi1
    vars2, vals2 = phi2
    common_vars = list(filter(lambda x: x in vars1, vars2))
    vars3 = vars1 + list(filter(lambda x: x not in vars1, vars2))

    idxs = {}
    for var in common_vars:
        idxs[var] = (vars1.index(var), vars2.index(var))
    vals = {}
    for vals1, p1 in phi1.values.items():
        for vals2, p2 in phi2.values.items():
            ok = True
            for var in common_vars:
                if not vals1[idxs[var][0]] == vals2[idxs[var][1]]:
                    # ma intereseaza sa iau doar perechile de (valori, probabilitate) care au aceleasi valori
                    # pentru toate variabilele comune
                    ok = False
            if ok:
                new_values = vals1
                for i in range(len(vals2)):
                    if vars2[i] not in common_vars:
                        new_values = new_values + tuple([vals2[i]])  # operatii pe tupluri, adaug o noua variabila
                vals[tuple(new_values)] = p1 * p2

    return Factor(vars3, vals)


def attach_factors(T, G):
    for vertex in G.getVertices():
        vertex_factor = G.getVertex(vertex).getFactor()

        for T_vertex in T.getVertices():
            vars = vertex_factor.vars
            all_vars_are_included = True

            for var in vars:
                if var not in T_vertex:
                    all_vars_are_included = False
                    break

            if all_vars_are_included:

                if T.getVertex(T_vertex).getFactor() == None:
                    T.getVertex(T_vertex).setFactor(vertex_factor)

                else:
                    T.getVertex(T_vertex).setFactor(multiply(vertex_factor, T.getVertex(T_vertex).getFactor()))

                break


def main():
    print('python main function: ', __name__)
    input_file = open("bn1", "r")
    lines = input_file.readlines()

    first_line = lines[0]
    first_line_parts = first_line.split(" ")
    N = int(first_line_parts[0])
    M = int(first_line_parts[1])

    G = Graph()
    build_graph_from_input(G, N, lines)

    U = build_unoriented_graph_from_oriented(G)

    # H is equal to U after moralize_graph, but we'll keep the notations from the homework description
    H = moralize_graph(U, G)

    # H_star is equal to H (and U), but we'll keep the notations
    H_star = build_cordal_graph(H)

    C = build_graph_with_BronKerbosch1(H_star)

    T = build_minimum_tree(C)

    attach_factors(T, G)

    clique_vertices = T.getVertices()
    clique_vertices = list(clique_vertices)
    for i in range(len(clique_vertices) - 1):
        new_output = "Vertex " + clique_vertices[i] + " has the factor: " + str(T.getVertex(clique_vertices[i]).getFactor())
        print(new_output)

        new_output = "Vertex " + clique_vertices[i] + " has the following connections: "

        for j in range(i + 1, len(clique_vertices)):
            if T.getVertex(clique_vertices[i]).isConnectedTo(clique_vertices[j]):
                new_output += clique_vertices[j] + "  "

        print(new_output)
        print()


if __name__ == '__main__':
    main()
