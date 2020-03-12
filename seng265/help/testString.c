#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[]) {
  char* test = (char *)malloc(sizeof(char)*strlen("testify"));
  strncpy(test, "testify", strlen("testify"));
  test[strlen(test) - 1] = '\0';
  printf("%s\n", test);
  char* copy_test = (char*)malloc(sizeof(char)*strlen(test));
  strncpy(copy_test, test, strlen(test));
  strncpy(test, copy_test + 1, strlen(test + 1));
  test[strlen(test) - 1] = '\0';
  printf("%s\n", test);
  return 0;
}
