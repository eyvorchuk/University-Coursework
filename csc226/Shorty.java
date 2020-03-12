import java.util.Scanner;

public class Shorty {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int intersections = in.nextInt();
    int corridors = in.nextInt();
    while (intersections != 0 && corridors != 0) {
      int[][] edges = new int[intersections][intersections];
      double[][] weights = new double[intersections][intersections];
      for (int i = 0; i < corridors; i++) {
        int first = in.nextInt();
        int second = in.nextInt();
        double weight = in.nextDouble();
        edges[first][second] = edges[second][first] = 1;
        weights[first][second] = weights[second][first] = weight;
      }
      BellmanFord traversal = new BellmanFord(edges,weights,0);
      intersections = in.nextInt();
      corridors = in.nextInt();
    }
  }
}
