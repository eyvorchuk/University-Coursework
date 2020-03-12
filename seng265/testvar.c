#include "stdio.h"
#include "string.h"

/*void func1(char* a,char* b) {
  printf("%s\n", b);
}*/

int main(int argc, char const *argv[]) {
  char* tester = "This is a string";
  strncpy(tester, "This string", 20);
  printf("%s\n", tester);
}
