import java.util.*;

//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//	 	 height function.
//
class BinarySearchTree <K extends Comparable<K>, V>  {

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	// These are package friendly for the TreeView class
	BSTNode<K,V>	root;
	int		count;

	int		findLoops;
	int		insertLoops;

	public BinarySearchTree () {
		root = null;
		count = 0;
		resetFindLoops();
		resetInsertLoops();
	}

	public int getFindLoopCount() {
		return findLoops;
	}

	public int getInsertLoopCount() {
		return insertLoops;
	}

	public void resetFindLoops() {
		findLoops = 0;
	}
	public void resetInsertLoops() {
		insertLoops = 0;
	}

	//
	// Purpose:
	//
	// Insert a new Key:Value Entry into the tree.  If the Key
	// already exists in the tree, update the value stored at
	// that node with the new value.
	//
	// Pre-Conditions:
	// 	the tree is a valid binary search tree
	//
	public void insert (K k, V v) {
		if (root == null) {
			root = new BSTNode<K,V>(k,v);
			count++;
			return;
		}
		BSTNode<K,V> newNode = new BSTNode<K,V>(k,v);
		BSTNode<K,V> curr = root;
		while (true) {
			insertLoops++;
			if (curr.key.compareTo(k) > 0 && curr.left != null) {
				curr = curr.left;
			} else if (curr.key.compareTo(k) < 0 && curr.right != null) {
				curr = curr.right;
			} else if (curr.key.compareTo(k) > 0 && curr.left == null) {
				curr.left = newNode;
				count++;
				return;
			} else if (curr.key.compareTo(k) < 0 && curr.right == null) {
				curr.right = newNode;
				count++;
				return;
			} else {
				curr.value = v;
				return;
			}
		}
	}

	//
	// Purpose:
	//
	// Return the value stored at key.  Throw a KeyNotFoundException
	// if the key isn't in the tree.
	//
	// Pre-conditions:
	//	the tree is a valid binary search tree
	//
	// Returns:
	//	the value stored at key
	//
	// Throws:
	//	KeyNotFoundException if key isn't in the tree
	//
	public V find (K key) throws KeyNotFoundException {
		BSTNode<K,V> curr = root;
		while (true) {
			findLoops++;
			if (curr.key.compareTo(key) == 0) {
				return curr.value;
			} else if (curr.key.compareTo(key) > 0 && curr.left != null) {
				curr = curr.left;
			} else if (curr.key.compareTo(key) < 0 && curr.right != null) {
				curr = curr.right; 
			} else {
				throw new KeyNotFoundException();
			}
		}
	}

	//
	// Purpose:
	//
	// Return the number of nodes in the tree.
	//
	// Returns:
	//	the number of nodes in the tree.
	public int size() {
		return count;
	}

	//
	// Purpose:
	//	Remove all nodes from the tree.
	//
	public void clear() {
		root = null;
		count = 0;
	}

	//
	// Purpose:
	//
	// Return the height of the tree.  We define height
	// as being the number of nodes on the path from the root
	// to the deepest node.
	//
	// This means that a tree with one node has height 1.
	//
	// Examples:
	//	See the assignment PDF and the test program for
	//	examples of height.
	//
	public int height() {
		return getHeight(root);
	}
	
	private int getHeight(BSTNode root) {
		if (root == null)
			return 0;
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a level-order
	// traversal of the tree.
	//
	// Level order is most commonly implemented using a queue of nodes.
	//
	//  From wikipedia (they call it breadth-first), the algorithm for level order is:
	//
	//	levelorder()
	//		q = empty queue
	//		q.enqueue(root)
	//		while not q.empty do
	//			node := q.dequeue()
	//			visit(node)
	//			if node.left != null then
	//			      q.enqueue(node.left)
	//			if node.right != null then
	//			      q.enqueue(node.right)
	//
	// Note that we will use the Java LinkedList as a Queue by using
	// only the removeFirst() and addLast() methods.
	//
	public List<Entry<K,V>> entryList() {
		List<Entry<K, V>> l = new LinkedList<Entry<K,V> >();
		LinkedList<BSTNode<K,V>> nodes = new LinkedList<BSTNode<K,V> >();
		nodes.addLast(root);
		while (!nodes.isEmpty()) {
			BSTNode<K,V> curr = nodes.removeFirst();
			l.add(new Entry<K,V>(curr.key, curr.value));
			if (curr.left != null) {
				nodes.addLast(curr.left);
			} 
			if (curr.right != null) {
				nodes.addLast(curr.right);
			}
		}
		return l;
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a traversal 
	// specified by the parameter which.
	//
	// If which is:
	//	BST_PREORDER	perform a pre-order traversal
	//	BST_POSTORDER	perform a post-order traversal
	//	BST_INORDER	perform an in-order traversal
	//
	public List<Entry<K,V> > entryList (int which) {
		List<Entry<K,V> > l = new LinkedList<Entry<K,V> >();
		if (which == 1) {
			doPreOrder(root, l);
		} else if (which == 2) {
			doPostOrder(root, l);
		} else if (which == 3) {
			doInOrder(root, l);
		}
		return l;
	}
	
	private void doPreOrder(BSTNode<K,V> n, List <Entry<K,V> >l) {
		l.add(new Entry<K,V>(n.key, n.value));
		if (n.left != null) {
			doPreOrder(n.left, l);
		}
		if (n.right != null) {
			doPreOrder(n.right, l);
		}
	}
	
	private void doPostOrder(BSTNode<K,V> n, List <Entry<K,V> > l) {
		if (n.left != null) {
			doPostOrder(n.left, l);
		} 
		if (n.right != null) {
			doPostOrder(n.right, l);
		} 
		l.add(new Entry<K,V>(n.key, n.value));
	}
	
	private void doInOrder(BSTNode<K,V> n, List <Entry<K,V> > l) {
		if (n.left != null) {
			doInOrder(n.left, l);
		}
		l.add(new Entry<K,V>(n.key, n.value));
		if (n.right != null) {
			doInOrder(n.right, l);
		}
	}

	// Your instructor had the following private methods in his solution:
	// private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private int doHeight (BSTNode<K,V> t)
}
