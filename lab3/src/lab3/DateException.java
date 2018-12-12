package lab3;

/**
 * <b>Title:</b> Lab 3:<br>
 * <b>Filename:</b> DateException.java<br>
 * <b>Date Written:</b> October 14, 2018<br>
 * <b>Due Date:</b> October 14, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Exception specifically for Date class; extends Exception class
 * </p>
 *
 * @author Ung Jae Yun
 */

public class DateException extends Exception{
	public DateException() {
		super("Invalid value for Date");
	}
	public DateException(String message) {
		super(message);
	}
}