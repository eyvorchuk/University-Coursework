#!/usr/bin/env python3

from sys import stdin
line = stdin.read().strip().split(" ")
words = []
for word in line:
    if word not in words:
        words.append(word)
    else:
        print("no")
        exit()
print("yes")
