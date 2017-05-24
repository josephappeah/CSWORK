#!/usr/bin/python

import base64
import binascii
import logging
from itertools import izip
import sys
import struct
import argparse
import string

import numpy as np

def solve(c1,c2):
    e_np = c1 + c2
    e = "".join([chr(item) for item in e_np])
    
def main():
    c1f = 'ct1.hex'
    c2f = 'ct3.hex'
    
    dtf = '/usr/share/dict/american-english'
    
    with open(dtf) as f:
        dt = f.readlines()
        
    with open(c1f) as f:
        c1 = f.readlines()
        
    with open(c2f) as f:
        c2 = f.readlines()
        
    dt = [x.strip('\n').lower() for x in dt]
    c1 = [x.strip('\n').lower() for x in c1]
    c2 = [x.strip('\n').lower() for x in c2]
    
    c1 = np.array([int(x,0) - 97 for x in c1])
    c2 = np.array([int(x,0) - 97 for x in c2])
    
    e = np.mod(c1 + c2,26)
    test = ' the '.lower().strip()
    test_ints = np.mod(np.array([ord(x) - 97 for x in test]),26)
    for i in range(len(e)-len(test)):
        d = e
        d[i:i + len(test)] =  np.mod(d[i:i + len(test)] + test_ints,26)
        print "Current Location + ", i
        for x in d[i:i+len(test)]:
            print(str(chr(97+x)) + " ")
        #print()
        #print()

if __name__ == "__main__":
    main()
