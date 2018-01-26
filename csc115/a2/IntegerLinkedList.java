public class IntegerLinkedList implements IntegerList {
	IntegerNode head;
	IntegerNode tail;
	
	public IntegerLinkedList() {
		head = null;
		tail = null;
	}

	/*
	 * PURPOSE:
	 *   Add the element x to the front of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addFront(9) returns, then l is {9,1,2,3}.
	 * If l is {} and l.addFront(3) returns, then l is {3}.
	 */
	public void addFront (int x) {
		if (size() == 0) {
			head = new IntegerNode(x);
			tail = head;
			return;
		} else {
			IntegerNode newNode = new IntegerNode(x, head);
			head = newNode;
			head.next.prev = head;
		}
	}


	/*
	 * PURPOSE:
	 *   Add the element x to the back of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addBack(9) returns, then l is {1,2,3,9}.
	 * If l is {} and l.addBack(9) returns, then l is {9}.
	 */
	public void addBack (int x) {
		if (size() == 0) {
			addFront(x);
		} else {
			IntegerNode newNode = new IntegerNode(x);
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
	}

	/*
	 * PURPOSE:
	 *   Add the element x at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for addAt are
	 *   0, 1, 2, 3.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos <= l.size()
	 *
	 * Examples:
	 *
	 * If l is {} and l.addAt(9,0) returns, then l is {9}.
	 * If l is {1} and l.addAt(9,0) returns, then l is {9,1}.
	 * If l is {1,2} and l.addAt(9,1) returns, then l is {1,9,2}
	 * If l is {1,2} and l.addAt(9,2) returns, then l is {1,2,9}
	 */
	public void addAt (int x, int pos) {
		if (pos == size()) {
			addBack(x);
		} else if (pos == 0) {
			addFront(x);
		} else {
			IntegerNode newNode = new IntegerNode(x);
			IntegerNode curr = head;
			IntegerNode previous = null;
			for (int i = 0; i < pos; i++) {
				previous = curr;
				curr = curr.next;
			}
			newNode.next = curr;
			newNode.prev = previous;
			curr.prev = newNode;
			previous.next = newNode;
		}
	}

	/*
	 * PURPOSE:
	 *	Return the number of elements in the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {7,13,22} l.size() returns 3
	 *	If l is {} l.size() returns 0
	 */
	public int size() {
		int count = 0;
		IntegerNode curr = head;
		while (curr != null) {
			count++;
			curr = curr.next;
		}
		return count;
	}

	/*
	 * PURPOSE:
	 *   Return the element at position pos in the list.
	 *
	 * PRECONDITIONS:
	 *	pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *	If l is {67,12,13} then l.get(0) returns 67
	 *	If l is	{67,12,13} then l.get(2) returns 13
	 *	If l is {92} then the result of l.get(2) is undefined.
	 *
	 */
	public int get (int pos) {
		IntegerNode curr = head;
		for (int i = 0; i < pos; i++) {
			curr = curr.next;
		}
		return curr.getValue();
	}

	/*
	 * PURPOSE:
	 *   Remove all elements from the list.  After calling this
	 *   method on a list l, l.size() will return 0
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13} then after l.clear(), l is {}
	 *	If l is {} then after l.clear(), l is {}
	 *
	 */
	public void clear() {
		head = null;
		tail = null;
	}

	/*
	 * PURPOSE:
	 *   Remove all instances of value from the list.
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13,12} then after l.remove(12), l is {67,13}
	 *	If l is {1,2,3} then after l.remove(2), l is {1,3}
	 *	If l is {1,2,3} then after l.remove(99), l is {1,2,3}
	 */
	public void remove (int value) {
		IntegerNode curr = head;
		IntegerNode previous = null;
		for (int i = 0; i < size() - 1; i++) {
			if (curr.getValue() == value) {
				removeAt(i);
				i--;
			}
			previous = curr;
			curr = curr.next;
		}
		if (curr.getValue() == value) {
			removeAt(size() - 1);
		}
	}

	/*
	 * PURPOSE:
	 *   Remove the element at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for removeAt are
	 *   0, 1, 2.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *
	 * If l is {1} and l.removeAt(0) returns, then l is {}.
	 * If l is {1,2,3} and l.removeAt(1) returns, then l is {1,3}
	 * If l is {1,2,3} and l.removeAt(2) returns, then l is {1,2}
	 */
	public void removeAt (int pos) {
		if (pos == 0 && size() != 1) {
			head.next.prev = null;
			head = head.next;
		} else if (size() == 1) {
			clear();
		} else if (pos == size() - 1) {
			tail.prev.next = null;
			tail = tail.prev;
		} else {
			IntegerNode curr = head;
			IntegerNode previous = null;
			for (int i = 0; i < pos; i++) {
				previous = curr;
				curr = curr.next;
			}
			curr.next.prev = previous;
			previous.next = curr.next;
		}
	}

	/*
	 * PURPOSE:
	 *	Return a string representation of the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {1,2,3,4} then l.toString() returns "{1,2,3,4}"
	 *	If l is {} then l.toString() returns "{}"
	 *
	 */
	public String toString() {
		if (head == null) 
			return "{}";
		IntegerNode curr = head;
		String list = "{" + curr.getValue();
		curr = curr.next;
		for (int i = 1; i < size(); i++) {
			list += "," + curr.getValue();
			curr = curr.next;
		}
		list += "}";
		return list;
	}
}
