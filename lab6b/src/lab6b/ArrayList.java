package lab6b;

public abstract class ArrayList<T> implements ArrayListADT<T> {
	/**
	 * The list - generic array of items
	 */
	protected T[] data;
	/**
	 * The number of items in the list
	 */
	protected int size;
	/**
	 * default maximum capacity
	 */
	protected final int CAPACITY = 100;
	
	/**
     * default constructor - creates a list that can store 100 items;<br>
     * the size of the list is initialized to 0
     */
    public ArrayList() {
          size = 0;
          data = (T[])new Object[CAPACITY];
    }

    /**
     * parameterized constructor - allows the user to specify the maximum capacity of the list.<br>
     * The list created can store at most <i>capacity</i> items; the size of the list is initialized to 0.
     * @param capacity indicates the maximum capacity of the list as specified by the user
     */
    public ArrayList(int capacity){
          if(capacity <= 0) {
                System.err.println("The list capacity must be positive. "
                     + "Creating a list whose capacity is " + CAPACITY + ".");
                data = (T[])new Object[CAPACITY];
          }
          else
        	  data = (T[])new Object[capacity];
          size = 0;
    }
    /**
     * empty method - determines whether or not the list is empty
     * @return <i>true</i> if the list is empty; <i>false</i> otherwise
     */	
	public boolean isEmpty() {
		return size == 0;
	}
    /**
     * full method - determines whether or not the list is full
     * @return <i>true</i> if the list is full; <i>false</i> otherwise
     */
	public boolean isFull() {	
		return size == data.length;
	}
    /**
     * getSize method - returns the number of items in the list
     * @return the number of items in the list
     */
	public int getSize() {
		return size;
	}
    /**
     * getCapacity method - returns the maximum size of the list
     * @return the maximum size of the list
     */
	public int getCapacity() {
		return data.length;
	}
    /**
     * clear method - makes the list empty and sets the size to 0
     */
	public void clear() {
			size = 0;
			data = (T[])new Object[data.length];
			System.gc();
	}
    /**
     * toString method - returns the state of the object as a String
     * @return a String containing the items in the list
     */	
	public String toString() {
		String str = "The list contains " + size + " items.\n";
		if(!isEmpty()) {
			for(int i=0; i < size; i++)
				str += data[i] + "\n";
		}
		return str;
	}
	
    /**
     * add method - adds an item to this list
     * @param insertItem is a reference to the item to be added
     * @return <i>true</i> if item was added, <i>false</i> otherwise
     */
    public abstract boolean add(T insertItem);

    /**
     * Returns the index of the first occurrence of the specified item (key) in this list
     * @param searchItem is a reference to an item whose key-field has been initialized
     * @return the location of the first occurrence of the specified item in this list; 
     * if searchItem is not found, -1 is returned
     */
    public abstract int indexOf(T searchItem);

    /**
     * Returns the index of the last occurrence of the specified item (key) in this list
     * @param searchItem is a reference to an item whose key-field has been initialized
     * @return the location of the last occurrence of the specified item in this list; 
     * if searchItem is not found, -1 is returned
     */
    public abstract int lastIndexOf(T searchItem);
    
    /**
     * contains method - determines whether or not the searchItem (key)is in this list
     * @param searchItem is a reference to an item whose key-field has been initialized
     * @return <i>true</i> if the item is in this list, <i>false</i> otherwise 
     */
    public abstract boolean contains(T searchItem);
    
    /**
     * get method - returns the item at the specified location in this list
     * @param index is the index of the item in this list
     * @return the item, if it is in this list, otherwise null is returned
     */
    public abstract T get(int index);

    /**
     * Removes the first occurrence of the specified item from this list, if it is present
     * @param removeItem is a reference to an item whose key-field has been initialized
     * @return the item, if it is in this list, otherwise null is returned
     */
    public abstract T remove(T removeItem);

    /**
     * Removes the item at the specified location from this list, if it is present
     * @param index is the index of the item in this list
     * @return the item, if it is in this list, otherwise null is returned
     */
    public abstract T remove(int index);
}
