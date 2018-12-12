package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* <b>Title:</b> Lab 7:<br>
* <b>Filename:</b> List.java<br>
* <b>Date Written:</b> November 25, 2018<br>
* <b>Due Date:</b> November 25, 2018<br>
*
* <p>
* <b>Description:</b><br>
* General abstract list class that lays out the general concept and 
* implements methods that do not depend on whether or not the list
* is ordered or not
* </p>
*
* @author Ung Jae Yun
*/

public abstract class List<T extends Comparable<T>> implements ListADT<T> {

	protected class Node<E extends Comparable<E>>{
		protected E data;
		protected Node<E> next;
		/**
		 * default constructor
		 */
		public Node(){
			data = null;
			next = null;
		}
		/**
		 * parameterized constructor with the item
		 * @param item
		 */
		public Node(E item){
			data = item;
			next = null;
		}
		/**
		 * parameterized constructor with the item and a reference to the next Node
		 * @param item
		 * @param n
		 */
		public Node(E item, Node<E> n){
			data = item;
			next = n;
		}
	}

	class ListIterator<E extends Comparable<E>> implements Iterator<E> {
		/**
		 * singly linked list used to store the contents of this list
		 */
		private Node<E> list;
		/**
		 *  count the number of items in this list
		 */
		private int count;
		/**
		 * current the index of current item in the list
		 */	
		private int current;
		/**
		 * Parameterized constructor for the ListIterator class.
		 * @param d the list
		 * @param c the number of items in the list
		 */	
		public ListIterator(Node<E> head, int c){
			list = head.next;
			count = c;
			current = 0;
		}
		
		/**
		 * Returns <i>true</i> if there is another item in the list.
		 * @return <i>true</i> if there is another item in the list
		 */
		public boolean hasNext() {
			return current < count;
		}
		
		/**
		 * Returns the next item in the list and advances to the next item.
		 * @return the next item in the list
		 */
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E data = list.data;
			list = list.next;
			current++;
			return data;
		}
		/**
		 * This is a stub method. It does nothing
		 */		
		public void remove() {
			/* does nothing
			 	add code if you need to delete while traversing
				http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html 
			*/
		}
	}
	/**
	 * front of the list
	 */
	protected Node<T> head = new Node<>();
	/**
	 * rear/end of the list
	 */
	protected Node<T> tail = new Node<>();
	/**
	 * size of the list
	 */
	protected int size;

	/**
	 * abstract add method; implement in child class
	 */
	@Override
	abstract public void add(T item);

	/**
	 * @return the first element of the list
	 */
	@Override
	public T first() throws ListEmptyException {
		if (size == 0) {
			throw new ListEmptyException();
		} else {
			return this.head.next.data;
		}
	}
		
	/**
	 * @return the last element of the list
	 */
	@Override
	public T last() throws ListEmptyException {
		if (size == 0) {
			throw new ListEmptyException();
		} else {
			return this.tail.next.data;
		}
	}
	
	/**
	 * removes and returns the first item of the list
	 * @return <i>T</i> the element removed
	 */
	@Override
	public T removeFirst() throws ListEmptyException {
		T temp = null;
		if (size == 0) {
			throw new ListEmptyException();
		
		} else {
			temp = this.head.next.data;
			this.head.next = this.head.next.next;
			size--;
		}
		return temp;
	}

	/**
	 * removes and returns the last element of the list
	 * @return <i>T</i> the element removed
	 */
	@Override
	public T removeLast() throws ListEmptyException {
		T temp = null;
		if (size == 0) {
			throw new ListEmptyException();
		
		} else {
			temp = this.tail.next.data;
			Node<T> trav = head.next;
			while (trav.next != null) {
				trav = trav.next;
			}
			this.tail.next = trav;
			this.tail.next.next = null;
		}
		return temp;
	}

	/**
	 * removes and returns element at location
	 * @param the index/location of element to remove
	 * @return <i>T</i> the element removed
	 */
	@Override
	public T removeAtPosition(int location) throws IndexOutOfBoundsException {
		T data = null;
		if (location < 0 || location > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> trav = head;
		for (int i = 0; i < location; i++) {
			if (i == location - 1) {
				data = trav.next.data;
				trav.next = trav.next.next;
			}
		}
		return data;
	}

	/**
	 * abstract remove method to be implemented in child class
	 */
	@Override
	abstract public void remove(T item);

	/**
	 * replaces item 1 with item 2
	 * @param <i>item1</i> element in the list we want to replace with <i>item2</i>
	 */
	@Override
	public void set(T item1, T item2) throws ListEmptyException {
		if (size == 0) {
			throw new ListEmptyException();
		}
		Node<T> trav = head;
		while(trav.next.data != item1) {
			if (trav.next.data == null) {
				return;
			}
			trav = trav.next;
		}
		trav.next.data = item2;
	}

	/**
	 * places item at location in list
	 * @param item the element we want to add/replace
	 * @param location the index where we want to place item
	 * @return original element at location that we removed
	 */
	@Override
	public T set(T item, int location) throws IndexOutOfBoundsException {
		T data = null;
		if (location < 0 || location > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> trav = head.next;
		for (int i = 0; i < location; i++) {
			if (i == location - 1) {
				data = trav.next.data;
				trav.next.data = item;
			}
		}
		return data;
	}

	/**
	 * searches for item in list
	 * @param item the object we're looking for
	 */
	@Override
	abstract public int search(T item);

	/**
	 * lets the user know if the item is in the list
	 * @param item the object we're looking to see if the list contains
	 * @return <i>true</i> if item is found, <i>false</i> if not
	 */
	@Override
	public boolean contains(T item) throws ListEmptyException {
		if(isEmpty())
			throw new ListEmptyException();
		return search(item) != -1;
	}

	/**
	 * @return <i>true</i> if the list is empty <i>false</i> if not
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @return <i>int</i> size of list
	 */
	@Override
	public int getSize(){
		return size;
	}

	/**
	 * clears list
	 */
	@Override
	public void clear() {
		size = 0;
		head.next = tail.next = null;
		System.gc();
	}

	/**
	 * iterates through list
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListIterator<T>(head, size);
	}

	/**
	 * overloaded toString
	 * @return <i>String</i> representation of the list
	 */
	public String toString(){
		String str = "[";
		Iterator<T> iter = iterator();
		while(iter.hasNext())
			str += iter.next() + (iter.hasNext() ? ", " : "]");
		return str;
	}
}
