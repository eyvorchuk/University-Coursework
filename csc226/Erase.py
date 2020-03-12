#!/usr/bin/env python3
from sys import stdin

n = int(input())
(first,second) = [i.strip() for i in stdin.readlines()]
if (n % 2 == 0):
    if (first == second):
        print("Deletion succeeded")
    else:
        print("Deletion failed")
else:
    for i in range(len(first)):
        if (first[i] == second[i]):
            print("Deletion failed")
            exit()
    print("Deletion succeeded")
