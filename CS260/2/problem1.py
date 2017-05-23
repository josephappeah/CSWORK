#!/usr/bin/python
# Sunny Shah 
# Problem1- Write a function called list_concat( A, B ) 
# that takes two Cell lists and returns a list that is the concatenation of both of them

import sys
from cell import Cell 

def list_concat( A, B ) :
	temp = A 
	while temp.next is not None:
		temp = temp.next
	temp.next = B 
	return temp 

	