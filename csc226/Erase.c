# include <stdio.h>
# include <string.h>
# include <stdlib.h>

int main(int argc, char const *argv[]) {
  int n;
  scanf("%d",&n);
  char *first = (char *) malloc(sizeof(char)*1000);
  char *second = (char *) malloc(sizeof(char)*1000);
  scanf("%s",first);
  scanf("%s",second);
  if (!n % 2) {
    if (!strcmp(first,second)) {
      printf("Deletion succeeded");
    } else {
      printf("Deletion failed");
    }
  } else {
    for (int i = 0; i < strlen(first); i++) {
      if (first[i] == second[i]) {
        printf("Deletion failed");
        return 0;
      }
    }
    printf("Deletion succeeded");
  }
  free(first);
  free(second);
  return 0;
}
