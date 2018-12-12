package lab6b;

public interface ArrayListADT<T> {
    
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
    
    /**
     * empty method - determines whether or not this list is empty
     * @return <i>true</i> if this list is empty; <i>false</i> otherwise
     */
    public boolean isEmpty();

    /**
     * full method - determines whether or not this list is full
     * @return <i>true</i> if this list is full; <i>false</i> otherwise
     */
    public boolean isFull();

    /**
     * getSize method - returns the number of items in this list
     * @return the number of items in this list
     */
    public int getSize();

    /**
     * getCapacity method - returns the maximum size of this list
     * @return the maximum size of this list
     */
    public int getCapacity();

    /**
     * clear method - makes this list empty and sets numItems to 0
     */
    public void clear();

    /**
     * toString method - returns the state of the object as a String
     * @return a String containing the items in this list
     */
    public String toString();
}