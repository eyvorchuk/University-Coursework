#!/usr/bin/env python3

def function_a():
    print("Inside function_a")
    return function_b

def function_b():
    print("Inside function_b")

def function_c(p):
    print("Inside function_c")
    p()

def main():
    m = function_a()
    m()
    function_c(m)

if __name__ == '__main__':
    main()
