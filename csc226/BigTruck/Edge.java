public class Edge {
  private int v;
  private int w;
  private double weight;

  public Edge(int v, int w, double weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public double weight() {
    return weight;
  }

  public int either() {
    return v;
  }

  public int other(int vertex) {
    if (vertex == v) {
      return w;
    }
    return v;
  }
}
