#!/usr/bin/python

import sys



postfix=[]
stack=[]
f = sys.stdin
l = f.readline()
	
def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        pass
 
    try:
        import unicodedata
        unicodedata.numeric(s)
        return True
    except (TypeError, ValueError):
        pass
	return False
def check(stack):
	if len(stack) < 2:
		print 'e'
	

			
def evaluation( postfix ):
	stack = []

	for char in postfix:
		if is_number(char):
			stack.append( char )
		elif  char == '+' or char == '-' or char == '*' or char == '/' or char == '^':
			if char == '/':
				check(stack)
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x / y
			elif char == '*':
				check(stack)
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x * y
			elif char == '/':
				check(stack)
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x / y
			elif char == '+':
				if len(stack) < 2:
					continue
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x + y
			elif char == '-':
				result = x - y
			stack.append( str(result) )
	return stack.pop()
	
while l :
	l = l.strip( ' \t\n' )

	for i in l.split(' '):
			postfix.append( i )
	result = evaluation(postfix)
	print result
	l = f.readline()
f.close()	

def evaluation( postfix ):
	for char in postfix:
		if is_number(char):
			stack.append( char )
		elif  char == '+' or char == '-' or char == '*' or char == '/' or char == '^' :
			
			if char == '/':
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x / y
			elif char == '*':
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x * y
			elif char == '/':
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x / y
			elif char == '+':
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x + y
			elif char == '-':
				y = float( stack.pop() )
				x = float( stack.pop() )
				result = x - y
			elif char == '^':
				y = float(stack.pop())
				x = float(stack.pop())
				result = x ** y
			stack.append( str(result) )
	return stack.pop()
	 
	
	 			
	

	 
		



				


