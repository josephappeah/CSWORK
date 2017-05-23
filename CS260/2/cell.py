#!/usr/bin/python
# Cell - module for a "cons" cell - a node in a linked list
# 
# 2/06
#

import sys

class Cell:

	def __init__( self, data, next=None ):
		self.data = data
		self.next = next

	def __str__( self ):
		return str( self.data )

	def echo( self ):
		print self.__str__()



def main( argv=sys.argv ) :
	"Just a test driver - NOT part of the class"

	c = Cell( 13 )
	print "c is holding: " + c.__str__()
	d = Cell( 12, c )
	print "d is holding: " + d.__str__()

if __name__ == "__main__" :
	# then this was NOT included in another file, so, run the test driver
	main()

