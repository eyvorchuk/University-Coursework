import java.util.Scanner;
import java.lang.Math;

public class Winnings {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    while (n != 0) {
      int[][] maximizer = new int[n][n];
      int[][] copy_max = new int[n][n];
      int[][] minimizer = new int[n][n];
      int[][] copy_min = new int[n][n];
      int[][] original = new int[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          maximizer[i][j] = in.nextInt();
          copy_max[i][j] = choices[i][j];
          original[i][j] = choices[i][j];
        }
      }
      int turns = in.nextInt();
      int i;
      for (i = 2; i <= turns; i *= 2) {
        for (int j = 0; j < n; j++) {
          for (int k = 0; k < n; k++) {
            int maximum = choices[j][0] + choices[0][k];
            for (int l = 1; l < n; l++) {
              if (choices[j][l] + choices[l][k] > maximum) {
                maximum = choices[j][l] + choices[l][k];
              }
            }
            copy_choices[j][k] = maximum;
          }
        }
        for (int j = 0; j < n; j++) {
          for (int k = 0; k < n; k++) {
            choices[j][k] = copy_choices[j][k];
          }
        }
      }
      for (i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          System.out.print(choices[i][j] + " ");
        }
        System.out.println();
      }
      n = in.nextInt();
    }
  }
}
