import java.lang.Iterable;
import java.util.Iterator;

public class Bag<DirectedEdge> implements Iterable<DirectedEdge> {
  private Node first;

  private class Node {
    DirectedEdge item;
    Node next;
  }

  public void add(DirectedEdge item) {
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
  }

  public Iterator<DirectedEdge> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<DirectedEdge> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {

    }

    public DirectedEdge next() {
      DirectedEdge item = current.item;
      current = current.next;
      return item;
    }
  }
}
