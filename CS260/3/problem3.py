#!/usr/bin/python
#Sunny Shah 
#CS260 Week 3
#problem 3

import problem1
import problem2
import timeit

file = open("mydata.txt", 'w')

for x in range(1,41):
	setupStr = 'from problem1 import fib'
	setupStr += '; from __main__ import x '
	mytime1 = timeit.Timer( 'fib(x)', setupStr  )
	delta1 = mytime1.timeit( 1 )
	#print str( delta )
	setupStr2 = 'from problem2 import Fib_memo'
	setupStr2 += '; from __main__ import x '	
	mytime2 = timeit.Timer( 'Fib_memo(x)', setupStr2  )
	delta2 = mytime2.timeit(1)
	#print str(delta2) 
	file.write(str(x)+" "+str(delta1)+" "+str( delta2)) 
	file.write('\n')
file.close()
