package generics;
/**
 * <p>
 * Title: Stack Empty Exception
 * </p>
 * 
 * <p>
 * Description: Exception to throw when Stack is empty
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author A. Abreu, Ung Jae Yun
 * @version 0.9
 */

public class StackEmptyException extends Exception {
	/**
	 * Default StackEmptyException; displays "Exception : Stack is empty"
	 */
	public StackEmptyException(){
		super("Exception : Stack is empty");
	}
	/**
	 * Displays msg parameter as this instance of StackEmptyException is thrown
	 * @param msg
	 */
	public StackEmptyException(String msg){
		super(msg);
	}
}
