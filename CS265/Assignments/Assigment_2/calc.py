#!/usr/bin/python

import sys

postfix=[]

f = sys.stdin
l = f.readline()

def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        return False
def eval(postfix):
	stack = []
	try:
		for char in postfix:
			if is_number(char):
				stack.append( char )
			elif  char == '+' or char == '-' or char == '*' or char == '/' or char == '^':
				y = float( stack.pop() )
				x = float( stack.pop() )
				if char == '/':
					result = x / y
				elif char == '*':
					result = x * y
				elif char == '/':
					result = x / y
				elif char == '+':
					result = x + y
				elif char == '-':
					result = x - y
				elif char == '^':
					result = x ** y 
				stack.append( str(result) )
				
		if len(stack) > 1:
			return False
		else:
			return stack.pop()
	except IndexError:
			return False


while l:
	l = l.strip( ' \t\n' )
	for i in l.split(' '):
			postfix.append( i )
	if eval(postfix) != False:
		print eval(postfix)
	else: 
		print '-E-'
	postfix = []
	
	l = f.readline()