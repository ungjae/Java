package generics;
/**
 * <p>
 * Title: Stack Full Exception
 * </p>
 * 
 * <p>
 * Description: Exception to throw when Stack is full
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author A. Abreu, Ung Jae Yun
 * @version 0.9
 */

public class StackFullException extends RuntimeException {
	/**
	 * Default StackFullException; displays "Exception : Stack is full"
	 */
	public StackFullException(){
		super("Exception : Stack is full");
	}
	/**
	 * Displays msg parameter as this instance of StackFullException is thrown
	 * @param msg
	 */
	public StackFullException(String msg){
		super(msg);
	}
}
