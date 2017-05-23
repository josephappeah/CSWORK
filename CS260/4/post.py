#!/usr/bin/python
#Sunny Shah CS260 HW4 post.py 
from lexer import *

def eval(input = []):
	value = []
	tree = []
	for element in input:
		if str.isdigit(element):
			value.append(element)
		else:
			second = int(value.pop())
			first = int(value.pop())
			if element == "+":
				value.append(first + second)
			elif element == "-":
				value.append(first - second)
			elif element == "*":
				value.append(first * second)
			elif element == "/":
				value.append(first / second)
	return value
def Pre(input = []):
	 prefix = []
	 for value in input:
		 if str.isdigit(value):
			 prefix.append(value)
		 else:
			 last = prefix.pop()
			 second = prefix.pop()
			 prefix.append(value + ' ' + second + ' ' + last + ' ')
	 return prefix
def In(input = []):
	infix = []
	for value in input:
		if str.isdigit(value):
			infix.append(value)
		else:
			last = infix.pop()
			second = infix.pop()
			infix.append(second + ' ' + value + ' ' + last + ' ')
	return infix
	
	
while get_expression():
	post_in = []
	t = get_next_token()
	while t:
		post_in.append(t)
		t = get_next_token()
	print ''
	
	prefix = Pre(post_in)
	print 'pre: ', prefix[0]
	
	infix = In(post_in)
	print 'in: ', infix[0]
	
	print 'post: ',
	for value in post_in:
		print value,
	print ''
	answer = eval(post_in)	
	print 'eval: ', answer[0]
	
	