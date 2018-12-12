package list;


/**
* <b>Title:</b> Lab 7:<br>
* <b>Filename:</b> ListEmptyException.java<br>
* <b>Date Written:</b> November 25, 2018<br>
* <b>Due Date:</b> November 25, 2018<br>
*
* <p>
* <b>Description:</b><br>
* Exception thrown when list is empty
* </p>
*
* @author Ung Jae Yun
*/

public class ListEmptyException extends Exception {

	public ListEmptyException() {
		super("List Empty Exception...");
	}

	public ListEmptyException(String message) {
		super(message);
	}
}