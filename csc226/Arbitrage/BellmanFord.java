import java.lang.Iterable;

public class BellmanFord {
  private double[] distTo;
  private DirectedEdge[] edgeTo;
  private boolean cycle;

  public BellmanFord(EdgeWeightedDigraph G, int s) {
    distTo = new double[G.v()];
    edgeTo = new DirectedEdge[G.v()];
    cycle = false;
    for (int v = 0; v < G.v(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    distTo[s] = 0.0;
    for (int i = 1; i < G.v(); i++) {
      for (int v = 0; v < G.v(); v++) {
        relax(G,v);
      }
    }
    for (int v = 0; v < G.v(); v++) {
      if (relax(G,v)) {
        cycle = true;
        return;
      }
    }
  }

  private boolean relax(EdgeWeightedDigraph G, int v) {
    boolean relaxed = false;
    for (DirectedEdge e: G.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        relaxed = true;
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
      }
    }
    return relaxed;
  }

  public double distTo(int v) {
    return distTo[v];
  }

  public boolean hasPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }

  public boolean hasNegativeCycle() {
    return cycle;
  }
}
