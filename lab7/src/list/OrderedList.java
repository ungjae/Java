package list;

/**
* <b>Title:</b> Lab 7:<br>
* <b>Filename:</b> OrderedList.java<br>
* <b>Date Written:</b> November 25, 2018<br>
* <b>Due Date:</b> November 25, 2018<br>
*
* <p>
* <b>Description:</b><br>
* implements an ordered version of the list class
* using linked lists
* </p>
*
* @author Ung Jae Yun
*/

public class OrderedList<T extends Comparable<T>> extends List<T> {

	/**
	 * adds an item in order
	 * @param item object to add to list 
	 */
	@Override
	public void add(T item) {
		Node<T> trav = head;
		// iterate until data is less than item
		while(trav.next != null && trav.next.data.compareTo(item) < 0)
			trav = trav.next;
		// head and tail points to new node with item if empty
		if (isEmpty())
			head.next = tail.next = new Node<T>(item);
		else {
			// places new node with item in list
			trav.next = new Node<T>(item, trav.next);
			// points tail to added item if added at the end 
			if(tail.next.next != null)
				tail.next = trav.next;
		}
		size++;
	}

	/**
	 * removes an item in list and keeps order
	 * @param item object to remove from list
	 */
	@Override
	public void remove(T item) {
		Node<T> trav = head;
		while(trav.next != null){
			// if next iterator's data is the item
			if(trav.next.data.compareTo(item) == 0){
				// if the item to remove is at the tail, move tail up one index
				if(trav.next == tail.next) {
					tail.next = trav;
					// if empty afterwards, reset tail
					if(tail.next == head)
						tail.next = null;
				}
				// re-link pointer so that node before item points to node after item
				trav.next = trav.next.next;
				size--;
				return;
			}
			// exits method if the iterator is greater than item we're looking for
			else if(trav.next.data.compareTo(item) > 0)
				return;
			else
				trav = trav.next;
		}
	}

	/**
	 * searches for the item and returns its index
	 * @param item the object to look for
	 * @return <i>int</i> index of the item in list; -1 if not found
	 */
	@Override
	public int search(T item) {
		int index = 0;
		Node<T> trav = head.next;
		while(trav != null){
			// return index if found
			if(trav.data.compareTo(item) == 0)
				return index;
			// return -1 if iterator becomes greater than item
			else if(trav.data.compareTo(item) > 0)
				return -1;
			else{
				index++;
				trav = trav.next;
			}
		}
		return -1;
	}

}
