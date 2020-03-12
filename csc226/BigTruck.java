// V00864667 Yvorchuk, Eric

// All helper classes used are taken from the textbook with some 
// modifications to fit the problem.

import java.util.Scanner;

public class BigTruck {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int num_towns = in.nextInt();
    EdgeWeightedGraph G = new EdgeWeightedGraph(num_towns);
    int[] towns = new int[num_towns];
    for (int i = 0; i < num_towns; i++) {
      towns[i] = in.nextInt();
    }
    int paths = in.nextInt();
    Edge[] edges = new Edge[paths];
    for (int i = 0; i < paths; i++) {
      edges[i] = new Edge(in.nextInt() - 1,in.nextInt() - 1,(double)(in.nextInt()));
      G.addEdge(edges[i]);
    }
    BellmanFord findPath = new BellmanFord(G,0, towns);
    if (!findPath.hasPathTo(num_towns - 1)) {
      System.out.println("impossible");
    } else {
      int items = findPath.getItems(towns, num_towns - 1);
      System.out.print((int)findPath.distTo(num_towns - 1));
      System.out.println(" " + items);
    }
  }
}
