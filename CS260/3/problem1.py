#!/usr/bin/python
#Sunny Shah 
#CS260 Week 3
#problem 1 


import sys 

def fib(x):
	if x == 0:
		return 1
	elif x == 1:
		return 1
	else:
		return fib(x-1) + fib(x -2)

def main (argv = sys.argv):
	print fib(int(sys.argv[1]))

if __name__ == "__main__":	
	main()
	
