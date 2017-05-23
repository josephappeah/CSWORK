#!/usr/bin/python

import sys

d = {}

f = file(sys.argv[1], 'r')
 

lines = f.readlines()

for l in lines:

	l.strip( '\t\n' )
	d[l.split(" ",1)[0]] = l.split(" ",1)[1]

list = d.keys() 
list.sort(key=int)

for key in list: 
	print key, d[key]
	