public class BellmanFord {
  private double[] distTo;
  private Edge[] edgeTo;
  private boolean[] onQueue;
  private IntQueue queue;
  private int cost;

  public BellmanFord(EdgeWeightedGraph G, int s, double[] factors) {
    distTo = new double[G.vertices()];
    edgeTo = new Edge[G.vertices()];
    onQueue = new boolean[G.vertices()];
    queue = new IntQueue();
    for (int v = 0; v < G.vertices(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    distTo[s] = 0.0;
    queue.enqueue(s);
    onQueue[s] = true;
    double[] tot_factors = new double[G.vertices()];
    for (int i = 0; i < tot_items.length; i++) {
      tot_factors[i] = factors[i];
    }
    while(!queue.isEmpty()) {
      int v = queue.dequeue();
      onQueue[v] = false;
      relax(G,v, tot_factors,factors);
    }
    factors[G.vertices() - 1] = tot_factors[G.vertices() - 1];
  }

  private void relax(EdgeWeightedGraph G, int v, int[] tot_items, int[] towns) {
    for (Edge e: G.adj(v)) {
      int w = e.other(v);
      if (distTo[w] >= distTo[v] + e.weight()) {
        if (distTo[w] > distTo[v] + e.weight()) {
          tot_items[w] = tot_items[v] + towns[w];
        }
        if (distTo[w] == distTo[v] + e.weight() && tot_items[w] < tot_items[v] + towns[w]) {
          tot_items[w] = tot_items[v] + towns[w];
        }
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if(!onQueue[w]) {
          queue.enqueue(w);
          onQueue[w] = true;
        }
      }
    }
  }

  public int getItems(int[] towns, int destination) {
    return towns[destination];
  }

  public double distTo(int v) {
    return distTo[v];
  }

  public boolean hasPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }
}
