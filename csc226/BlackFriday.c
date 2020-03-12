# include <stdlib.h>
# include <stdio.h>

int main(int argc, char const *argv[]) {
  int n;
  scanf("%d",&n);
  int freqs[6];
  int group[n];
  for (int i = 0; i < 6; i++) {
    freqs[i] = 0;
  }
  for (int i = 0; i < n; i++) {
    int curr;
    scanf("%d",&curr);
    freqs[curr - 1]++;
    group[i] = curr;
  }
  int unique = 0;
  for (int i = 5; i >= 0; i--) {
    if (freqs[i] == 1) {
      unique = i + 1;
      break;
    }
  }
  if (!unique) {
    printf("none");
  } else {
    for (int i = 0; i < n; i++) {
      if (group[i] == unique) {
        printf("%d",i+1);
        break;
      }
    }
  }
  return 0;
}
