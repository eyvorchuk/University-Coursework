import java.lang.Iterable;

public class EdgeWeightedGraph {
  private final int vertices;
  private int edges;
  private Bag<Edge>[] adj;

  public EdgeWeightedGraph(int v) {
    vertices = v;
    edges = 0;
    adj = (Bag<Edge>[]) new Bag[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new Bag<Edge>();
    }
  }

  public int vertices() {
    return vertices;
  }

  public int edges() {
    return edges;
  }

  public Iterable<Edge> adj(int v) {
    return adj[v];
  }

  public void addEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    adj[v].add(e);
    adj[w].add(e);
    edges++;
  }
}
