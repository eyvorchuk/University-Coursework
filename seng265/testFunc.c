#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void func2(char *a, char *b) {
  *a = *b;
}

int main(int argc, char const *argv[]) {
  char c[] = {'0'};
  char *a = &c;
  char *b = "5";
  func2(a,b);
  printf("main: a =%s\n", a);
}
