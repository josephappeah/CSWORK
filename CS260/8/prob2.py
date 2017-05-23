#!/usr/bin/python

a__author__ = 'ryan'

inf = float("inf")

# weightedAdjMatrix = [[0, 28, inf, 33, inf],
#                      [28, 0, 10, inf, 44],
#                      [inf, 10, 0, 50, inf],
#                      [33, inf, 50, 0, 30],
#                      [inf, 44, inf, 30, 0]]

#parse input
count = 0
a = list()

with open('example.input') as fp:
    for line in fp:
        count = count + 1
        u = line.split()
        a.append(u)

adj = list()
dist = list()
pred = list()

for x in range(1, count+1):
    adj.append([float("inf")] * count) #dist initializes to infinity
    pred.append([None] * count) #pred initializes to None

for i in a:
    key = int(i[0])
    for b in i[1:]:
        g = b.split(',')
        adj[key][int(g[0])] = int(g[1])
        adj[int(g[0])][key] = int(g[1])
    for i in range(len(adj)):
        adj[i][i] = 0

# Floyd-Warshall algorithm finds for a graph the shortest path from any vertex to any vertex.
def floyd(adj):
    # P = [[inf for x in range(5)] for x in range(5)]
    for i in range(len(adj)):
        for j in range(len(adj)):
            pred[i][j] = i
    for k in range(len(adj)):
        for i in range(len(adj)):
            for j in range(len(adj)):
                if adj[i][k] + adj[k][j] < adj[i][j]:
                    adj[i][j] = adj[i][k] + adj[k][j]
                    pred[i][j] = k
    for i in range(len(adj)):
        pred[i][i] = '-'
    return pred

print "predecessor matrix"
print 'N', '\t',
for y in range(len(adj)):
    print y - 0, '\t',

print
t = 0
for i in floyd(adj):
    print t,
    for j in i:
        print '%3s' % (j),
    t += 1
    print

print
print

print "distance matrix"
print 'N', '\t',
for y in range(len(adj)):
    print y - 0, '\t',

print
t = 0
for i in adj:
    print t,
    for j in i:
        print '%3s' % (j),
    t += 1
    print