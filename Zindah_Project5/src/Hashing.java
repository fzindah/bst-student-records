/**
 * A partial implementation of a hash table using chaining (binary search trees)
 *  @author COSC 311, Farha Zindah, Fall '21
 *  @version (12-6-21)
 */

@SuppressWarnings ("unchecked")
public class Hashing <E extends Comparable <E>>{
	private final int SIZE= 37;
	private BST<Pair<E>> [] table;
	
	// constructor 
	public Hashing () {
		table = new BST[SIZE];
	}

	// hash the key using division
	public int hash(int key) {
		return ((key*key) >> 10) % 37;
	}
	
	// add a (key,value) pair into the hash table
	public void put (Pair<E> item) {
		int key = (Integer) item.getFirst();
		key = hash(key) % SIZE; 
		
		if (key < 0) 
			key += SIZE;
		if (table[key] == null) 
			// Create a new BST at table[index]
			table[key] = new BST<Pair<E>> ();
		
		// if item already exists in table, update it
		if (table[key].find(item) != null)
			table[key].find(item).setSecond(item.getSecond());
		else 
		// if key is not already in the table, add it in
		table[key].add(item);	
	}	
	
	// get a address value for a key
	public E get (E key) {
		// Use hash function to change key into hash table value
		int index = hash((Integer)key) % SIZE;
		// if index is negative, add table size
		if (index < 0) 
			index += SIZE;
		// if key is not in table, return null
		if (table[index] == null)
			return null;
			
		// search the BST to find the key
		Pair<E> p= new Pair<>();
		p.setFirst(key);
		// return address associated with key
		return table[index].find(p).getSecond();
	}
	
	// see if key exists
	public boolean find (E key) {
		if (get(key) != null)
			return true;
		return false;
	}
	
	// remove pair from hash table
	public Pair<E> remove(Pair<E> item) {
		int index = hash((Integer) item.getFirst()) % SIZE;
		
		if (index < 0) 
			index += SIZE;
		if (table[index] == null)
			// key is not in table
			return null;
		table[index].delete(item);
		// check if table[index] is empty
		if (table[index].min() == null) {
			table[index] = null;
		}
		return item;
	}
	
	// print out hash table (level by level)
	public void print() {
		print(0,SIZE, false);
	}
	
	// print the hash table using level by level traversal of BSTs
	public void print(int start, int end, boolean quit) {
		for (int i=start;i<end;i++)
			if (table[i] != null) {
				System.out.print(i + ": ");
				if (!quit)
					// if user didn't quit, print table breadth-first with levels on different lines
					table[i].breadthFirst();
				else {
					// print table breadth-first with levels on one line
					table[i].levelTraversal();
					System.out.println();
				}
			}
	}
}
