package lab6b;

/**
 * <b>Title:</b> Lab 6b:<br>
 * <b>Filename:</b> OrderedArrayList.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Ordered ArrayList class which sorts the 
 * objects in its arraylist according to their Comparable
 * attributes; (uses compareTo to compare two T's)
 * order allows for faster searching such as binary search
 * which is implemented in the methods below
 * </p>
 *
 * @author Ung Jae Yun
 */


public class OrderedArrayList<T extends Comparable<T>> extends ArrayList<T> {
	/**
	 * default constructor
	 * creates an orderedArrayList with default capacity 100(from superclass)
	 */
	public OrderedArrayList() {
		size = 0;
		data = (T[])new Comparable[CAPACITY];
	}
	/**
	 * overloaded constructor that specifies the capacity
	 * @param capacity the capacity of the arraylist
	 */
	public OrderedArrayList(int capacity) {
		// creates a new orderedArrayList with the default constructor if the capacity value is invalid
		if(capacity <= 0) {
			System.err.println("The list capacity must be positive. "
					+ "Creating a list whose capacity is " + CAPACITY + ".");
			data = (T[])new Comparable[CAPACITY];
		}
		// if valid, instantiate a new orderedArrayList object with given capacity
		else
			data = (T[])new Comparable[capacity];
		size = 0;
	}
	/**
	 * Adds an item to the list while maintaining the natural ordering of the elements.
	 */
	@Override
	public boolean add(T insertItem) {

		if (insertItem == null) return false;

		// creates a bigger data array if full
		if (this.isFull()) {
			//copy its contents to a bigger array
			T[] temp = (T[])new Object[this.getCapacity()*2];
			for (int i = 0; i < this.size; i++) {
				temp[i] = this.data[i];
			}
			data = temp;
		}
		// find the appropriate place to insert with binary search
		int insertIndex = findInsertIndex(insertItem);
		
		size++;
		// shift elements over to make room
		for (int i = size; i > insertIndex; i--) {
			data[i] = data[i-1];
		}
		// insert at appropriate spot
		data[insertIndex] = insertItem;
		
		return true;
		
	}
	/**
	 * helper function to find the appropriate index to insert the insertElement
	 * @param insertElement the object to be inserted
	 * @return <i>int</i> of the index to where insertElement should be inserted
	 */
	int findInsertIndex(T insertElement) {
		int left = 0, right = size, mid = 0;

		while (left <= right) {
			mid = (left + right) / 2;
			if (mid == 0) {
			// refer to index 'size' for indices 0 and 1
				return size;
			
			} else if ((data[mid].compareTo(insertElement) <= 0) && (data[mid+1] == null || data[mid+1].compareTo(insertElement) > 0)) {
			
				return ++mid;
			
			} else if (data[mid].compareTo(insertElement) > 0) {
				
				right = mid-1;
				
			} else {
				
				left = mid+1;
			}
		}
		return size;
	}
	/**
	 * Returns the first index of an item in the list. Uses left-most binary search.
	 */
	@Override
	public int indexOf(T searchItem) {

		// do left-most binary search

		int left = 0, right = size-1, mid;

		while (left <= right) {
			mid = (left+right)/2;

			boolean found = (searchItem.compareTo(data[mid]) == 0);
				
			// if we found a match, there are two cases where it's the left most one:
			// at the very beginning, OR if the element to the left is less than mid
			if (found && mid == 0 || found && data[mid-1].compareTo(searchItem) < 0)
				return mid;
			else if (searchItem.compareTo(data[mid]) > 0)
				left = mid + 1; // search right half
			else 
				right = mid - 1; // search left half
		}
		// if the while loop didn't return and we get here, it means searchItem wasn't in the list
		return -1;
	}


	/**
	 * Returns the last index of an item in this list. Uses right-most binary search.
	 */
	@Override
	public int lastIndexOf(T searchItem) {
		
		int left = 0, right = size-1, mid;

		while (left <= right) {
			mid = (left+right)/2;

			boolean found = (searchItem.compareTo(data[mid]) == 0);

			// if we found it, there are two cases where it's the right most one:
			// it's the last element in the list, OR if the element immediately to the right is greater
			if (found && mid == size-1 || found && searchItem.compareTo(data[mid+1]) < 0)
				return mid;
			else if (searchItem.compareTo(data[mid]) < 0)
				right = mid - 1; // search left half
			else 
				left = mid + 1; // search right half
		}
		return -1;
	}
	/**
	 * @param searchItem the item we are looking for in the arraylist
	 * @return <i>true</i> if the arraylist contains searchItem; else returns <i>false</i>
	 */
	@Override
	public boolean contains(T searchItem) {
		if(indexOf(searchItem) > -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * returns the item at index
	 * @param index the index of the arraylist we are trying to retrieve an object from
	 * @throws IndexOutOfBoundsException when the index is out of range
	 * @return <i>T</i> if found; <i>null</i> if not found
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		// check index validity
		if(index < 0 || index > size-1) {
			return null;
		} else {
			return data[index];
		}
	}
	/**
	 * removes and returns given object from the arraylist if found
	 * @param removeItem the object we want to remove from the arraylist
	 * @return <i>T</i> if found; <i>null</i> if not found
	 */
	@Override
	public T remove(T removeItem) {

		int removeIndex = this.indexOf(removeItem);
		if(removeIndex == -1) {
			return null;
		} else {
			return this.remove(removeIndex);
		}
	}
	/**
	 * removes and returns object at the given index from the arraylist if found
	 * @param index the index of the arraylist where we want to remove its contents from
	 * @return <i>T</i> if found; <i>null</i> if not found
	 */
	@Override
	public T remove(int index) {
		// save object to return later
		T temp = this.get(index);
		
		// if not found, return null
		if (temp == null) {
			return null;
		} else {
			
		// if found, shift all the objects to its right and decrement size
			for(int i = index + 1; i < this.size; i++) {
				data[i] = data[i+1];
			}
			size--;
			return temp;
		}
	}

}
