#!/usr/bin/python

import sys


d = {}


if len(sys.argv) > 1 :
 
	f = file(sys.argv[1], 'r')

	for l in f:

		l.strip( '\t\n' )
		d[l.split(" ",1)[0]] = l.split(" ",1)[1]
else :
	f = sys.stdin
	l = f.readline()
	while l :
		l = l.strip( '\t\n' )
		d[l.split(" ",1)[0]] = l.split(" ",1)[1]
		l = f.readline()
	print '\n'
		

list = d.keys() 
list.sort(key=int)

for key in list: 
	print key, d[key]

