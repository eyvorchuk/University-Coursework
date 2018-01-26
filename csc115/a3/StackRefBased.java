public class StackRefBased<T> implements Stack<T> {
    StackNode<T> top;

    public StackRefBased() {
        top = null;
    }

    public int size() {
        StackNode<T> curr = top;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }


    public boolean isEmpty() {
        return top == null;
    }


    public void push(T data) {
        StackNode<T> newNode = new StackNode<T>(data);
        newNode.next = top;
        top = newNode;
    }


    public T pop() throws StackEmptyException {
        if (isEmpty())
            throw new StackEmptyException();
        StackNode<T> returnedNode = top;
        top = top.next;
        return returnedNode.data;
    }


    public T peek() throws StackEmptyException {
        if (isEmpty())
            throw new StackEmptyException();
        return top.data;
    }


    public void makeEmpty() {
        top = null;
    }


    public String toString() {
        if (isEmpty())
            return "";
        String stackString = top.toString();
        StackNode<T> curr = top.next;
        while (curr != null) {
            stackString += " " + curr.toString();
            curr = curr.next;
        }
        return stackString;
    }
}

