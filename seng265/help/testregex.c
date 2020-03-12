#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>

int main(int argc, char const *argv[]) {
  regex_t re;
  regmatch_t match[4];
  char* pattern = "(.*)([[:<:]]bbb[[:>:]])(.*)";
  if(regcomp(&re, pattern, REG_EXTENDED|REG_ICASE)) {
    fprintf(stderr, "Failed to compile\n");
    exit(1);
  }
  char* test = "aaa bbb ccc";
  regexec(&re, test, 4, match, 0);
  char matchtext[100];
  strncpy(matchtext, test + match[0].rm_so, match[0].rm_eo - match[0].rm_so);
  matchtext[match[0].rm_eo - match[0].rm_so] = '\0';
  printf("%s\n", matchtext);
  return 0;
}
