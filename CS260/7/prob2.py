#!/usr/bin/python
#Sunny Shah CS260 HW7 prob2.py 
import sys

graph = {1: [3, 2, 7],2: [1],3: [1, 5, 6, 8],4: [5, 7],5: [3, 4, 6],6: [3, 5, 7],7: [1, 4, 6], 8: [3]}

def BFS(graph, start):
	queue = []
	order = []
	queue.append(start)
	while(queue):
		node = queue.pop(0)
		if node not in order:
			order.append(node)
		for child in graph.get(node):
			if child not in order:
				order.append(child)
				queue.append(child)
	return order

def DFS(graph, start):
	queue = []
	order = []
	queue.append(start)
	order.append(start)
	while(queue):
		node = queue[len(queue) - 1]
		children = graph.get(node)
		child = children[0]
		if child in order:
			for element in children:
				if element not in order:
					child = element
					break
				else:
					child = None
		if (child == None):
			queue.pop()
		else:
			order.append(child)
			queue.append(child)
	return order


first = 1
print 'Graph', graph
print 'BFS:', BFS(graph, first)
print 'DFS:', DFS(graph, first)
