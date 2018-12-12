package list;

import java.util.Iterator;


/**
* <b>Title:</b> Lab 7:<br>
* <b>Filename:</b> ListADT.java<br>
* <b>Date Written:</b> November 25, 2018<br>
* <b>Due Date:</b> November 25, 2018<br>
*
* <p>
* <b>Description:</b><br>
* Interface that lays out the most basic/general 
* methods and concepts of lists
* </p>
*
* @author Ung Jae Yun
*/

public interface ListADT<T extends Comparable<T>> {

	/**
	 * Adds an item to the list
	 * 
	 * @param item
	 *            the item to be added
	 */
	void add(T item);

	/**
	 * Returns the first item in the list
	 * 
	 * @return the first item
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	T first() throws ListEmptyException;

	/**
	 * Returns the last item in the list
	 * 
	 * @return the last item
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	T last() throws ListEmptyException;

	/**
	 * Removes and returns the first item in the list
	 * 
	 * @return the first item
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	T removeFirst() throws ListEmptyException;

	/**
	 * Removes and returns the last item in the list
	 * 
	 * @return the last item
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	T removeLast() throws ListEmptyException;

	/**
	 * Removes and returns an item stored at a specified location in the list
	 * 
	 * @param location
	 *            the location of the item to removed from the list
	 * @return the item stored at that location
	 * @throws IndexOutOfBoundsException
	 *             if location &lt; 0 or &gt;= size()
	 */
	T removeAtPosition(int location) throws IndexOutOfBoundsException;

	/**
	 * Removes first occurrence of an item from the list
	 * 
	 * @param item
	 *            the item to removed from the list
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	void remove(T item) throws ListEmptyException;

	/**
	 * Replaces all occurrences of the item stored in the list
	 * 
	 * @param item1
	 *            the old item
	 * @param item2
	 *            the new item
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	void set(T item1, T item2) throws ListEmptyException;

	/**
	 * Replaces the item stored at the given location and returns the old item
	 * 
	 * @param item
	 *            the new item
	 * @param location
	 *            the location
	 * @return the old item
	 * @throws IndexOutOfBoundsException
	 *             if location &lt; 0 or &gt;= size()
	 */
	T set(T item, int location) throws IndexOutOfBoundsException;

	/**
	 * Searches for an item in the list and returns its location or -1 if the
	 * item is not in the list
	 * 
	 * @param item
	 *            the item to search for
	 * @return the location of the item
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	int search(T item) throws ListEmptyException;

	/**
	 * Searches for an item in the list and returns true if it can be found
	 * 
	 * @param item
	 *            the item to search for
	 * @return <i>true</i> if an item is in the list or <i>false</i> otherwise
	 * @throws ListEmptyException
	 *             if the list is empty
	 */
	boolean contains(T item) throws ListEmptyException;

	/**
	 * Returns the number of items in the list
	 * 
	 * @return the number of items in the list
	 */

	/**
	 * Returns <i>true</i> if the list is empty, or <i>false</i> otherwise
	 * 
	 * @return <i>true</i> if the list is empty, or <i>false</i> otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns the number of items in the list
	 * 
	 * @return the number of items in the list
	 */
	int getSize();

	/**
	 * Removes all of the items in this list.
	 */
	void clear();
	
	/**
	 * Returns an iterator over the items in this list in proper sequence.
	 */
	Iterator<T>	iterator();	
}
