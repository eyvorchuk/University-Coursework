#!/usr/bin/env python3

import sys

def main():
    aaa = []
    for bbb in sys.stdin:
        bbb = bbb.split()
        print(bbb)
        print(len(bbb[-1]))

if __name__ == '__main__':
    main()
