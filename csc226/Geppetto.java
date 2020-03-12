import java.util.Scanner;

public class Geppetto {
  static int pizzas;
  static int[][] edges;
  static boolean[] inSequence;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int ingredients = in.nextInt();
    int restrictions = in.nextInt();
    pizzas = 1;
    edges = new int[ingredients][ingredients];
    for (int i = 0; i < restrictions; i++) {
      int first = in.nextInt() - 1;
      int second = in.nextInt() - 1;
      edges[first][second] = edges[second][first] = 1;
    }
    inSequence = new boolean[ingredients];
    for (int i = 0; i < ingredients; i++) {
      computeCount(i);
    }
    System.out.println(pizzas);
  }

  public static void computeCount(int ingredient) {
    inSequence[ingredient] = true;
    boolean allGood = true;
    for (int i = 0; i < inSequence.length; i++) {
      if (inSequence[i] && edges[ingredient][i] == 1) {
        allGood = false;
        break;
      }
    }
    if (allGood) {
      pizzas++;
    }
    for (int i = ingredient + 1; i < edges.length; i++) {
      computeCount(i);
    }
    inSequence[ingredient] = false;
  }
}
