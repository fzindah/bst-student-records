/**
 * A linked data structure implementation of a binary search tree
 *  @author COSC 311 
 *  @author Farha Zindah
 *  @version (11/23/21)
 */

public class BST<E extends Comparable<E>> {
	
	// Class Node is defined as an inner class
	static class Node <E> {
		
		// data stored in the node
		private E data;
		
		// reference to the root of the left and right subtrees
		private Node<E> left;
		private Node<E> right;
		
		/**
         * Construct a node with the given data value
         * @param item - The data value 
         */
		public Node(E item) {
			data = item;
			left = right = null;
		}
		
		/** 
		 * Return a string representing the node
		 * @param  None  
		 * @return a string representing the data stored in the node  	
		 */
		public String toString () {
			return data.toString();
		}
	}
	
	//data member
	private Node<E> root;
	
	
	/**
     * Construct an empty binary search tree 
     * @param none
     */
	public BST () {
		root = null;
	}
	

	/** 
	 * Search for an item in the tree
	 * @param  item  the target value
	 * @return true 
	 */
	public E find (E item) {
		return find (root, item);
	}
	
	/** 
	 * Search for an item in the tree rooted at current
	 * @param  current  the current root
	 * @param  item  the target value
	 * @return item 
	 */
	private E find (Node<E> current , E item) {
		if (current == null)
			return null;
		int result = current.data.compareTo(item);
		if (result == 0)
			return current.data;
		else if (result < 0)
			return find (current.right, item);
		else
			return find (current.left, item);
	}
	
	/** 
	 * Search for an item in the tree rooted at current
	 * @param  current  the current root
	 * @param  item  the target value
	 * @return Node containing item 
	 */
	private Node<E> findNode (Node<E> current , E item) {
		if (current == null)
			return null;
		int result = current.data.compareTo(item);
		if (result == 0)
			return current;
		else if (result < 0)
			return findNode(current.right, item);
		else
			return findNode(current.left, item);
	}
	
	/** 
	 * Insert an item to the tree
	 * @param  item  the value to be inserted 
	 * @return none 
	 */
	public void add(E item) {
		
			root =  add (root, item);
	}
	
	/** 
	 * Insert an item to the tree rooted at current
	 * @param  current  the current root
	 * @param  item  the value to be inserted 
	 * @return reference to the node that was inserted 
	 */
	private Node<E> add (Node <E>current , E item) {
		if (current == null) 
			current = new Node<>(item);
		else {
		int result = current.data.compareTo(item);
		if (result < 0)
			current.right =  add (current.right, item);
		else if (result > 0)
			current.left =  add (current.left, item);	
		}
		return current;
	}
	
	/**
	 * Print binary search tree on one line starting at root, 
	 * using breadth-first traversal
	 */
	public void levelTraversal() {
		// base case
		if (root == null)
			return;

		// create an empty queue
		QueueSLL<Node<E>> queue = new QueueSLL<>();

		queue.offer(root);

		// loop till queue is empty
		while (!queue.empty()) {
			Node<E> curr = queue.poll();

			// If data isn't null, print and add to queue
			System.out.print(curr.data + " ");

			if (curr.left != null)
				queue.offer(curr.left);
			if (curr.right != null)
				queue.offer(curr.right);
		}
	}
	
	/**
	 * Print binary search tree starting at root, 
	 * using breadth-first traversal
	 */
	public void breadthFirst() {
		breadthFirst(root);
	}
	
	/**
	 * Print binary search tree starting at current item
	 * Calls private breadthFirst function
	 * @param data item searched for
	 */
	public void breadthFirst(E data) {
		// find node of item
		Node<E> n = findNode(root, data);
		// print out subtree starting with node
		breadthFirst(n);
	}
	/**
	 * Print binary search tree starting at current node
	 * @param root Node of item searched from
	 */
    private void breadthFirst(Node<E> root)
    {
        // base case
        if (root == null)
            return;

        // create an empty queue
        QueueSLL<Node<E>> queue = new QueueSLL<>();
        
        queue.offer(root);
        
        // loop till queue is empty
        while (true)
        {
        	// nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodes = queue.size();
            if(nodes == 0)
                break;
            
            // Take all nodes of current level out of queue
            // and and add nodes of next level
            while(nodes > 0)
            {
                Node<E> curr = queue.poll();
                System.out.print(curr + " ");

                if(curr.left != null)
                    queue.offer(curr.left);
                if(curr.right != null)
                    queue.offer(curr.right);
                nodes--;
            }
            System.out.println();
        }
    }
    
	/** 
	 * Traverse the tree using preorder traversal 
	 * @param  none
	 * @return none 
	 */

	public void preorder() {
		preorder (root, 1);
	}
	
	/** 
	 * Traverse the tree using preorder traversal 
	 * @param  current the current root
	 * @param  level the level of the current node
	 * @return none 
	 */
	private void preorder (Node<E> current, int level) {
		if (current != null) {
			for (int i = 1; i  < level; i++ )
				System.out.print("\t");
			System.out.println(current);
			preorder(current.left, level+1);
			preorder(current.right, level+1);
		}
	}
	 
	/** 
	 * Return the smallest value in the tree 
	 * @param  none
	 * @return the smallest value 
	 */
	public E min () {
		return min(root);
	}

	/** 
	 * Return the smallest value in the tree  
	 * @param  current the current root
	 * @return the smallest value
	 */
	private E min (Node<E> current) {
		if (current.left == null)
			return current.data;
		else 
			return min(current.left);
	}
	
	/** 
	 * Delete a given item from the tree 
	 * @param  item the item to be deleted
	 * @return none
	 */
	public void delete (E item) {
		root = delete (root,item);
	}
	
	/** 
	 * Delete a given item from the tree 
	 * @param  current the current root
	 * @param  item the item to be deleted
	 * @return a reference to a node 
	 */
	private Node<E> delete(Node<E> current , E item) {
		if (current != null) {
			int result = current.data.compareTo(item);
			if (result < 0)
				current.right =  delete (current.right, item);
			else if (result > 0)
				current.left =  delete (current.left, item);	
			else {    // find it
				if (current.left == null)    // current has 1 child
					current = current.right;
				else if (current.right == null)  
					current = current.left;
				else {   // current has two children
					E replace = min(current.right);
					current.data = replace;
					current.right = delete(current.right, replace);	
				}
			}
		}
		return current;
	}	
}

