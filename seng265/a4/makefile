
# Spring 2018, assignment #4)
#

CC=gcc
CFLAGS=-c -Wall -g -std=c99 -D_POSIX_C_SOURCE=2008091 -DDEBUG

all: kwic4

kwic4: kwic4.o linkedlist.o
	$(CC) kwic4.o linkedlist.o -o kwic4

kwic4.o: kwic4.c linkedlist.h
	$(CC) $(CFLAGS) kwic4.c

linkedlist.o: linkedlist.c linkedlist.h
	$(CC) $(CFLAGS) linkedlist.c

clean:
	rm -rf *.o kwic4 
