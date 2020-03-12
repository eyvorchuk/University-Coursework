#include <ctype.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {
  char test[] = "testing upper case";
  for (int i = 0; i < strlen(test); i++) {
    putchar(toupper(test[i]));
  }
}
