package list;


/**
* <b>Title:</b> Lab 7:<br>
* <b>Filename:</b> UnorderedList.java<br>
* <b>Date Written:</b> November 25, 2018<br>
* <b>Due Date:</b> November 25, 2018<br>
*
* <p>
* <b>Description:</b><br>
* implements an unordered version of the list class
* using linked lists
* </p>
*
* @author Ung Jae Yun
*/

public class UnorderedList<T extends Comparable<T>> extends List<T> {

	/**
	 * adds an item to the end of list
	 * @param item the object to add
	 */
	@Override
	public void add(T item) {
		// head and tail point to new node with item if empty
		if (isEmpty()) {
			head.next = tail.next = new Node<T>(item);
		} else {
		// if not empty, insert at end
			tail.next = tail.next.next = new Node<T>(item);
		}
		size++;
	}

	/**
	 * removes an item in list
	 */
	@Override
	public void remove(T item) {	
		Node<T> trav = head;
		while(trav.next != null){
			// if item found in list
			if(trav.next.data.compareTo(item) == 0){
				// tail points to new node with item if at the end
				if(trav.next == tail.next) {
					tail.next = trav;
					if(tail.next == head)
						tail.next = null;
				}
				
				// else point node before item to node after item
				trav.next = trav.next.next;
				size--;
				return;
			}
			else{
				trav = trav.next;
			}
		}
	}

	/**
	 * returns index of the item we're looking for
	 * @param item the object we're looking for
	 */
	@Override
	public int search(T item) {
		int index = 0;
		Node<T> trav = head.next;
		while(trav != null){
			// if item found, return index
			if(trav.data.compareTo(item) == 0){
				return index;
			}
			else{
				index++;
				trav = trav.next;
			}
		}
		// return -1 if not found
		return -1;
	}

}
