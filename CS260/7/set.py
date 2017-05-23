#!/usr/bin/python
#Sunny Shah CS260 HW7 set.py 
import sys

class Node:
	def __init__(self, data):
		self.parent = self
		self.value = data
		self.childCount = 0

def Initialize(array):
	d = {}
	for element in array:
		d[element] = Node(element)
	return d

def Merge(d, first, second):
	first_Node = d.get(first, None)
	second_Node = d.get(second, None)
	if (first_Node != None and second_Node != None):
		if (first_Node.parent != second_Node):
			parent_First = first_Node.parent
			parent_Second = second_Node.parent
			while (parent_First.parent != parent_First):
				parent_First = parent_First.parent

			while (parent_Second.parent != parent_Second):
				parent_Second = parent_Second.parent

			if (parent_First.childCount > parent_Second.childCount):
				parent_Second.parent = parent_First
				parent_First.childCount = parent_First.childCount + parent_Second.childCount + 1
			else:
				parent_First.parent = parent_Second
				parent_Second.childCount =  parent_First.childCount + parent_Second.childCount + 1


def Find(dictionary, value):
	node = dictionary.get(value, None)
	parents = []
	if (node != None):
		while (node.parent != node):
			parents.append(node)
			node = node.parent
		for element in parents:
			element.parent = node
		return node
