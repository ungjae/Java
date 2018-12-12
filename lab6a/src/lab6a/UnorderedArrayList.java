package lab6a;

/**
 * <b>Title:</b> Lab 6a:<br>
 * <b>Filename:</b> UnorderedArrayList.java<br>
 * <b>Date Written:</b> November 11, 2018<br>
 * <b>Due Date:</b> November 11, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * object class which represents a list not sorted/ordered
 * in any fashion; extends from ArrayList
 * includes methods that will modify the object
 * without any sorting
 * </p>
 *
 * @author Ung Jae Yun
 */

public class UnorderedArrayList<T> extends ArrayList<T>{
	/**
	 * default constructor; creates using ArrayList default constructor
	 */
	public UnorderedArrayList() {
		super();
	}
	/**
	 * parameterized constructor with capacity i
	 * @param i the capacity of the unorderedArrayList
	 */
	public UnorderedArrayList(int i) {
		super(i);
	}
	/**
	 * provides the first occurrence of searchItem
	 * @param searchItem the object we are looking for
	 * @return the first index number of the searchItem or <i>-1</i> if not found
	 */
	public int indexOf(T searchItem) {
		int indexOf = -1;
		if (this.isEmpty()) {
			return indexOf;
		} else {
			
			for (int i = 0; i < this.size; i++) {
				if (this.data[i].equals(searchItem)) {
					return indexOf;
				}
			}
		}
		return indexOf;
	}
	/**
	 * provides the last occurrence of searchItem
	 * @param searchItem the object we are looking for
	 * @return the last index number of the searchItem or <i>-1</i> if not found
	 */
	public int lastIndexOf(T searchItem) {
		int lastIndex = -1;
		if (this.isEmpty()) {
			return lastIndex;
		} else {
			for (int i = size-1; i >= 0; i--) {
				if (data[i].equals(searchItem)) {
					return lastIndex;
				}
			}
		}
		return lastIndex;
	}
	/**
	 * method to see whether the unorderedArrayList contains an item
	 * @param searchItem the object we want to look for 
	 * @return <i>true</i> if searchItem is found <i>false</i> if not found
	 */
	public boolean contains(T searchItem) {
		if(this.indexOf(searchItem) == -1) {
		return false;
		} else { 
			return true;
		}
	}
	/**
	 * method to remove a particular item from the unorderedArrayList
	 * @param removeItem the object we want to remove
	 * @return <i>T</i> the object that has been remove <i>null</i> if the object is not found
	 */
	public T remove(T removeItem) {
		int index = this.indexOf(removeItem);
		T temp;
		if(index == -1) {
			return null;
		} else {
			temp = data[index];
			for (int i = index; i < size-1; i++) {
				data[i] = data[i+1];
			}		
		}
		size--;
		return temp;
	}
	/**
	 * method to add an object to the array
	 * @param insertItem the object to add
	 * @return <i>true</i> if the object has been added <i>false</i> if the unorderedArrayList is full
	 */
	public boolean add(T insertItem) {
		if(this.isFull()) {
			return false;
		} else {
			data[size] = insertItem;
			size++;
			return true;
		}
	}
	/**
	 * method to return the object at an index
	 * @param index the index to which to look
	 * @return <i>T</i> the object if present <i>null</i> if not present
	 */
	public T get(int index) {
		if(index < 0 || index > size-1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if(data[index] == null) {
			return null;
		} else {
			return data[index];
		}
	}
	/**
	 * method to remove the object at an index
	 * @param index the index to which to look and remove from
	 * @return <i>T</i> the object if present <i>null</i> if not present
	 */
	public T remove(int index) {
		if(this.data[index] == null) {
			return null;
		}
		return remove(data[index]);
	}
}
