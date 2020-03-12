import java.lang.Iterable;
import java.util.Iterator;

public class Bag<Edge> implements Iterable<Edge> {
  private Node first;

  private class Node {
    Edge item;
    Node next;
  }

  public void add(Edge item) {
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
  }

  public Iterator<Edge> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Edge> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {

    }

    public Edge next() {
      Edge item = current.item;
      current = current.next;
      return item;
    }
  }
}
