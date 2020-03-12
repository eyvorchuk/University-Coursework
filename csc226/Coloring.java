// V00864667 Yvorchuk, Eric

import java.util.Scanner;

public class Coloring {
  static int[][] edges;
  static int vertices;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    vertices = in.nextInt();
    in.nextLine();
    edges = new int[vertices][vertices];
    int[] colors = new int[vertices];
    for (int i = 0; i < vertices; i++) {
      String[] line = in.nextLine().split(" ");
      for (int j = 0; j < line.length; j++) {
        int other = Integer.parseInt(line[j]);
        edges[i][other] = edges[other][i] = 1;
      }
    }
    boolean allEdges = true;
    nestedLoop:
    for (int i = 0; i < vertices; i++) {
      for (int j = i; j < vertices; j++) {
        if (i != j && edges[i][j] == 0) {
          allEdges = false;
          break nestedLoop;
        }
      }
    }
    if (allEdges) {
      System.out.println(vertices);
      return;
    }
    for (int i = vertices - 2; i >= 1; i--) {
      if (!hasSolution(0,colors,i)) {
        System.out.println(i + 1);
        return;
      }
    }
  }

  public static boolean hasSolution(int vertex, int[] colors, int num_colors) {
    for (int i = 1; i <= num_colors; i++) {
      colors[vertex] = i;
      boolean unable = false;
      for (int j = 0; j < vertices; j++) {
        if (edges[vertex][j] == 1 && colors[vertex] == colors[j]) {
          unable = true;
          break;
        }
      }
      if (!unable) {
        if (vertex + 1 == vertices) {
          colors[vertex] = 0;
          return true;
        }
        if (hasSolution(vertex + 1, colors, num_colors)) {
          colors[vertex] = 0;
          return true;
        }
      }
    }
    colors[vertex] = 0;
    return false;
  }
}
