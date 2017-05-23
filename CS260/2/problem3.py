#!/usr/bin/python
# Sunny Shah 
# Problem3- function time testing 

import timeit
from cell import Cell 
def Create_list(n):
    for i in range(0,n):
        a = Cell(i)
        a = a.next
	return a

def list_concat( A, B ) :
    if A is None:
        return B
    if B is None:
        return A
	temp = A 
	while temp.next is not None:
		temp = temp.next
	temp.next = B 
	return temp 
	
def list_concat_copy( A, B ) :
 if A is not None: 
	temp_a = A 
	temp_b = B 
	C1 = Cell(temp_a.data)
	temp_C1 = C1

	while temp_a.next is not None:
		temp_a = temp_a.next 
		temp_C1.next = Cell(temp_a.data)
		temp_C1 = temp_C1.next
	
	temp_C1.next = Cell(temp_b.data)
	temp_C1 = temp_C1.next
		
	while temp_b.next is not None: 
		temp_b = temp_b.next 
		temp_C1.next = Cell(temp_b.data)
		temp_C1 = temp_C1.next 

	return temp_C1

file = open("mydata.txt", 'w')

for x in xrange(1000,16000,1000):
	mytime1 = timeit.Timer( 'list_concat(Create_list(x), Create_list(x))', 'from __main__ import list_concat, Create_list,x' )
	delta1 = mytime1.timeit( 1 )
	#print "1 run of list_concat( a, b ) took: " + str( delta1 ) + " seconds."
	mytime2 = timeit.Timer( 'list_concat_copy(Create_list(x), Create_list(x))', 'from __main__ import list_concat_copy, Create_list,x' )
	delta2 = mytime2.timeit( 1 )
	#print "1 run of list_concat( a, b ) took: " + str( delta2 ) + " seconds."
	file.write(str(x)+" "+str(delta1)+" "+str( delta2)) 
	file.write('\n')
file.close()	
