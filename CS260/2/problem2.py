#!/usr/bin/python
# Sunny Shah 
# Problem2- Write a function called list_concat_copy( A, B ) 
#  that takes two lists and returns a list that is the concatenation of 
#  both of them, but without re-using the data. That is, make sure that future edits to A and B don't modify the concatenated list.

import sys
from cell import Cell


def list_concat_copy( A, B ) :
 if A != None:
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

	return C1 

	
	
