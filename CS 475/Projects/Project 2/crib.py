#/usr/bin/python 
from sys import argv 
import glob 
from  binascii import hexlify, unhexlify 
import re 
import sys 

filenames = glob.glob('/home/sss329/CS475/ciphertexts/*.hex')


c1f = filenames[0]
c2f = filenames[1]
c3f = filenames[2]
c4f = filenames[3]
c5f = filenames[4] 
c6f = filenames[5]

with open(c1f) as f:
	c1 = f.readlines() 
c1 =  [x.strip('\n') for x in c1] 
c1 =  [x[2:] for x in c1]
c1 = "".join(c1) 

print c1 + '\n'

with open(c2f) as f:
	c2 = f.readlines() 
c2 =  [x.strip('\n') for x in c2] 
c2 =  [x[2:] for x in c2]
c2 = "".join(c2) 

print c2
with open(c3f) as f:
	c3 = f.readlines() 
c3 =  [x.strip('\n') for x in c3] 
c3 =  [x[2:] for x in c3]
c3 = "".join(c3) 

print c3 +'\n'
with open(c4f) as f:
	c4 = f.readlines() 
c4 =  [x.strip('\n') for x in c4] 
c4 =  [x[2:] for x in c4]
c4 = "".join(c4) 

print c4 +'\n'
with open(c5f) as f:
	c5 = f.readlines() 
c5 =  [x.strip('\n') for x in c5] 
c5 =  [x[2:] for x in c5]
c5 = "".join(c5) 

print c5 + '\n'
 
with open(c1f) as f:
	c6 = f.readlines() 
c6 =  [x.strip('\n') for x in c6] 
c6 =  [x[2:] for x in c6]
c6 = "".join(c6) 

print c6 + '\n'



def strxor(a,b): 
 if len(a) > len(b): 
   return "".join([chr(ord(x) ^ ord(y)) for (x,y) in zip(a[:len(b)], b)])
 else: 
   return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a, b[:len(a)])])

def main(): 
	a = raw_input("Enter the file C1:>")
	b = raw_input("Enter the file C2:>")

	with open(a) as f:
		cipher1 = f.readlines() 
		cipher1 =  [x.strip('\n') for x in cipher1] 
		cipher1 =  [x[2:] for x in cipher1]
		cipher1 = "".join(cipher1) 

	print cipher1 

	with open(b) as f:
		cipher2 = f.readlines() 
		cipher2 =  [x.strip('\n') for x in cipher2] 
		cipher2 =  [x[2:] for x in cipher2]
		cipher2 = "".join(cipher2) 

	print cipher2
 
	x = strxor(unhexlify(cipher1), unhexlify(cipher2))
	print  a + " xor " + b 
	crib = raw_input("Enter Crib:>")
	print "Crib\n~%s~"%crib 

	for i in range(len(x)):
	 z =x[i:]
	 print "\n[%d]"%i 
	 print "%s"%strxor(z,crib) 

main()
 

   
