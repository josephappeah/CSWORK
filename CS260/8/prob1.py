#!/usr/bin/python
#Sunny Shah CS260 HW8 prob1.py 
import sys

edges = []
count = 0
while (True):
	input = raw_input()
	count += 1
	input = input.split()
	first = int(input[0])
	index = 1
	while(index < len(input)):
		adjacent = input[index].split(",")
		second = int(adjacent[0])
		weight = int(adjacent[1])
		edges.append((first, second, weight))
		index = index + 1
	if (len(input) == 1):
		break;

inf   = float("inf")
graph = [[inf for x in range(count)] for x in range(count)]
path = [[None for x in range(count)] for x in range(count)]


def floyd(graph, path):
	for first, second, weight in edges:
		graph[first][second] = weight
		graph[second][first] = weight

		path[first][second] = first
		path[second][first] = second

	#print 'Adjacency matrix:'
	#prin(graph)
	for k in range(count):
		for i in range(count):
			for j in range(count):
				if ( i == j):
					graph[i][j] = 0
				else:	
					if (graph[i][j] > graph[i][k] + graph[k][j]):
						graph[i][j] = graph[i][k] + graph[k][j]
						path[i][j] = path[k][j]
	for i in range(count) :
	  for j in range(count):
		if (path[i][j] == None):
			path[i][j] ='-'
		if (path[j][i] == None):
			path[j][i] ='-'


def prin(out):
	for i in range(count) :
	  for j in range(count):
	    print ' ', out[i][j],
	  print


floyd(graph, path)

print 'Distance matrix'
prin(graph)

print
print 
print 'Predecessor matrix'
prin(path)


