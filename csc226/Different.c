#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char const *argv[]) {
  long m;
  long n;
  while (scanf("%lu",&m) == 1) {
    scanf("%lu",&n);
    printf("%lu\n",labs(m-n));
  }
  return 0;
}
