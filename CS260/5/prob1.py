#!/usr/bin/python
#Sunny Shah CS260 HW5 prob1.py
import random
import time
import timeit
from math import *
def downHeap(heap,i):
	if (left(i) > len(heap)):	
		return None; 
	biggerChild = left(i)
	if (right(i) <= len(heap)-1): 
		if(heap[biggerChild] < heap[right(i)]):
			biggerChild = right(i)
		if(heap[i] < heap[biggerChild]):
			heap[i],heap[biggerChild] = heap[biggerChild],heap[i]
			downHeap(heap,biggerChild)
	
	
def make_heap(H):
	a = [0]
	H = a + H 
	i = len(H)//2 
	for x in xrange(i,0,-1):	
		downHeap(H,x)
		
def left(i):
	return 2*i
def right(i):
	return 2*i+1
	
	
# for x in range(1, 11):
	# l = random.sample(range(1,100),10)
	# x =[65, 53, 52, 21, 60, 2, 75, 79, 31, 64]
	# y = [8,6,5,7,3,1,2,10,12]
# make_heap(l)
file = open("mydata.txt", 'w')
print 'n', '	', 'T(n)'
for x in range(1, 11):
	l = [random.randint(0,1000) for r in xrange(x * 100)]
	start = time.time()
	make_heap(l)
	Totaltime = time.time() - start
	print x*100,'	', Totaltime
	y = x*100
	file.write(str(y)+' ' + str(Totaltime)) 
	file.write('\n')
file.close()