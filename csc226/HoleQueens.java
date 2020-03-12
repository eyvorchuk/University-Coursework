// V00864667 Yvorchuk, Eric

// This code is based on the solution given in class.
import java.util.Scanner;

public class HoleQueens {
  static int count;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    while (n != 0) {
      count = 0;
      int num_holes = in.nextInt();
      boolean[][] holes = new boolean[n][n];
      boolean[] a = new boolean[256];
      boolean[] b = new boolean[256];
      boolean[] c = new boolean[256];
      for (int i = 0; i < 256; i++) {
        a[i] = b[i] = c[i] = true;
      }
      for (int i = 0; i < num_holes; i++) {
        holes[in.nextInt()][in.nextInt()] = true;
      }
      gen(0,holes,a,b,c,n);
      System.out.println(count);
      n = in.nextInt();
    }
  }

  public static void gen(int col, boolean[][] holes,boolean[] a, boolean[] b, boolean[] c, int n) {
    for (int row = 0; row < n; row++) {
      if (a[row] && b[row+col] && c[row-col+n] && !holes[row][col]) {
        a[row] = b[row+col] = c[row-col+n] = false;
        if (col == n - 1) {
          count++;
        } else {
          gen(col+1,holes,a,b,c,n);
        }
        a[row] = b[row+col] = c[row-col+n] = true;
      }
    }
  }
}
