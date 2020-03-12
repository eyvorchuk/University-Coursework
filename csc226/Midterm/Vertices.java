import java.util.*;

public class Vertices {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int num_vertices = in.nextInt();
    while(num_vertices != - 1) {
      boolean[] weak = new boolean[num_vertices];
      for (int i = 0; i < num_vertices; i++) {
        weak[i] = true;
      }
      int[][] adjMatrix = new int[num_vertices][num_vertices];
      for (int i = 0; i < num_vertices; i++) {
        for (int j = 0; j < num_vertices; j++) {
          adjMatrix[i][j] = in.nextInt();
        }
      }
      for (int i = 0; i < num_vertices; i++) {
        for (int j = 0; j < i; j++) {
          for (int k = 0; k < j; k++) {
            if(adjMatrix[i][j] == 1 && adjMatrix[i][k] == 1 && adjMatrix[j][k] == 1) {
              weak[i] = false;
              weak[j] = false;
              weak[k] = false;
            }
          }
        }
      }
      for (int i = 0; i < num_vertices; i++) {
        if(weak[i]) {
          System.out.print(i + " ");
        }
      }
      System.out.println();
      num_vertices = in.nextInt();
    }
  }
}
