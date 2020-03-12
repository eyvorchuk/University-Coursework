public class BellmanFord {
  private double[] distTo;

  public BellmanFord(int[][] edges, double[][] weights, int s) {
    distTo = new double[edges.length];
    distTo[s] = 1.0;
    for (int i = 1; i < edges.length; i++) {
      boolean relaxed = false;
      for (int v = 0; v < edges.length; v++) {
        if (relax(edges,weights,v)) {
          relaxed = true;
        }
      }
      if (!relaxed) {
        break;
      }
    }
    System.out.printf("%.4f\n",distTo[edges.length - 1]);
  }

  private boolean relax(int[][] edges, double[][] weights,int v) {
    boolean relaxed = false;
    for (int i = 0; i < edges.length; i++) {
      if (edges[v][i] == 1 && distTo[i] < distTo[v]*weights[v][i]) {
        relaxed = true;
        distTo[i] = distTo[v]*weights[v][i];
      }
    }
    return relaxed;
  }
}
