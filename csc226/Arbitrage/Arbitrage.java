// V00864667 Yvorchuk, Eric

// This code is modified from the arbitrage implementation in
// the textbook. Most of the helper classes are taken from the
// textbook. The BellmanFord implementation was based off
// pseudocode from Wikipedia to make detecting negative cycles easier.

// Aforementioned source: https://en.wikipedia.org/wiki/Bellman–Ford_algorithm#Finding_negative_cycles

import java.util.*;
import java.math.*;

public class Arbitrage {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int currencies = in.nextInt();
    while (currencies != 0) {
      ArrayList<String> names = new ArrayList<String>(currencies);
      EdgeWeightedDigraph G = new EdgeWeightedDigraph(currencies);
      for (int i = 0; i < currencies; i++) {
        names.add(in.next());
      }
      int sequences = in.nextInt();
      for (int i = 0; i < sequences; i++) {
        String first = in.next();
        String second = in.next();
        String[] parts = in.next().split(":");
        int first_currency = Integer.parseInt(parts[0]);
        int second_currency = Integer.parseInt(parts[1]);
        double exchange = Math.log(1.0*first_currency/second_currency);
        DirectedEdge e = new DirectedEdge(names.indexOf(first),names.indexOf(second),exchange);
        G.addEdge(e);
      }
      int i;
      String verdict = null;
      for (i = 0; i < G.v(); i++) {
        BellmanFord spt = new BellmanFord(G,i);
        if (spt.hasNegativeCycle()) {
          verdict = "Arbitrage";
          break;
        }
      }
      if (verdict == null) {
        verdict = "Ok";
      }
      System.out.println(verdict);
      currencies = in.nextInt();
    }
  }
}
