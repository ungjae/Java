package lab5a;
/**
 * <b>Title:</b> Lab 5a:<br>
 * <b>Filename:</b> QueueException.java<br>
 * <b>Date Written:</b> October 28, 2018<br>
 * <b>Due Date:</b> October 28, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Exceptions thrown when user tries to access a Queue
 * that is full or empty
 * </p>
 *
 * @author Ung Jae Yun
 */
public class QueueException extends Exception {
	/**
	 * default QueueException; displays "Queue Exception: Something's wrong..."
	 * 
	 */
	public QueueException() {
		super("Queue Exception: Something's wrong...");
	}
	/**
	 * Displays 'msg' string when throwing this Queue Exception
	 * @param msg message to be printed when this instance of Queue Exception is thrown
	 */
	public QueueException(String msg) {
		super(msg);
	}
}


