import java.lang.Iterable;

public class EdgeWeightedDigraph {
  private final int v;
  private int e;
  private Bag<DirectedEdge>[] adj;

  public EdgeWeightedDigraph(int v) {
    this.v = v;
    e = 0;
    adj = (Bag<DirectedEdge>[]) new Bag[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new Bag<DirectedEdge>();
    }
  }

  public int v() {
    return v;
  }

  public int e() {
    return e;
  }

  public void addEdge(DirectedEdge E) {
    adj[E.from()].add(E);
    e++;
  }

  public Iterable<DirectedEdge> adj(int v) {
    return adj[v];
  }

  public Iterable<DirectedEdge> edges() {
    Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
    for (int i = 0; i < v; i++) {
      for (DirectedEdge e: adj[v]) {
        bag.add(e);
      }
    }
    return bag;
  }
}
