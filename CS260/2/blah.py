import random
import timeit
from cell import Cell

def isEmpty( L ) :
	return L == None


def length( L ) :
	rv = 0

	while L is not None :
		rv += 1
		L = L.next
	
	return rv

A = Cell( 1, Cell( 2, Cell( 3 )))
B = Cell( "a", Cell( 'b', Cell( 'c' )))

def getLastNode( L ) :
	'''return None if L is empty'''
	
	p = L
	q = None

	while p is not None :
		q = p
		p = p.next
	
	return q

# to append B onto A, find end of A:

end = getLastNode( A )
end.next = B

setup = 'from __main__ import getLastNode, A'
mytime = timeit.Timer( 'getLastNode(A)', setup )
	# actually time a single call to bar
delta = mytime.timeit( 1 )
print "1 run took: " + str( delta ) + " seconds."
#delta = mytime.timeit( 5 )
#print "5 runs of bar( 1000000 ) took: " + str( delta ) + " seconds."
print ''

