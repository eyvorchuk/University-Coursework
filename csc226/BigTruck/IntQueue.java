public class IntQueue {
    private Node first;
    private Node last;
    private int n;

    public IntQueue() {
        first = null;
        last = null;
        n = 0;
    }

    public void enqueue(int v) {
        Node oldlast = last;
        last = new Node(v, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    public int dequeue() {
        int remove = first.value;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return remove;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }
}
