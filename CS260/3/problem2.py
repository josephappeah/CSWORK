#!/usr/bin/python
#Sunny Shah 
#CS260 Week 3
#problem 2

import sys
from array import *
Array=[]
for i in range(100):
	Array.append(0)	
		
def Fib_memo(n):
	if n > 100: 
		return n
	if n <= 1:
		return 1
	if Array[n] != 0:
		return Array[n]
	Array[n] = Fib_memo(n-1) + Fib_memo(n-2)
	return Array[n]
	
def main (argv = sys.argv):
	print(Fib_memo(int(sys.argv[1])))
	
if __name__ == "__main__":	
	main()