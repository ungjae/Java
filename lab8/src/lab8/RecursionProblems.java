package lab8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * <b>Title:</b> Lab 8:<br>
 * <b>Filename:</b> RecursionProblems.java<br>
 * <b>Date Written:</b> December 9, 2018<br>
 * <b>Due Date:</b> December 9, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * class containing various recursive methods to demonstrate recursion
 * </p>
 *
 * @author Ung Jae Yun
 */

public class RecursionProblems {
	
	// use this table for problem 5
	private static final char[] table =
		{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	
	public static void main(String[] args) {
		// test your recursive methods here!
		
		LinkedList<String> list = new LinkedList<>();
		list.add("one");
		list.add("two");
		list.add("three");
		
		System.out.println(pow(3,3));
		displayReversed(list);
		
		System.out.println();
		System.out.println(sum(3));
		System.out.println(convert(7,2));
		
		hanoi(3, "one", "two", "three");
	}
	
	/**
	 * Recursive method that calculates the sum of the integers between 1 and n.
	 * @param num the number to sum up to from 1
	 * @return the sum of the integers between 1 and num
	 */
	public static int sum(int num) {
		int result;
		if(num == 1)
			result = 1;
		else
			result = num + sum(num - 1);
		return result;
	}

	/**
	 * function to print out a series of instructions to move a tower of size n to target
	 * @param n size of the tower
	 * @param start_pole the pole where the tower starts
	 * @param temp_pole temporary/auxiliary pole to utilize
	 * @param target_pole the pole we want to move the tower to
	 */
	/*
	 * the recursion below can be illustrated most straightforward by drawing a 
	 * max heap binary tree by the order of n that has hanoi(n) as the root and 
	 * each child, hanoi(n - depth) 'in-order'; left leg is the first recursive call, 
	 * and right leg the second recursive call, the parent printing an instruction
	 * to move it's (n - depth)th disc from its start to target pole. 
	 * Afterwards, one can trace the recursion by tracing the tree in-order
	 * 
	 * looking closer, the recursion can be seen in this pattern:
	 * move n-1 stack from start to temp, move disk n from start to target, 
	 * move n-1 stack from temp to target, which moves the n-2 stack
	 * from temp to start, moves disk n-1 from temp to target, move n-2 stack
	 * from start to target, which moves the n-3 stack from start to temp and so on
	 */
	public static void hanoi(int n, String start_pole, String temp_pole, String target_pole) {
		if (n == 1) {
			System.out.println("Move Disc " + n + " from " + start_pole + " to " + target_pole);
			return;
		}
		hanoi(n-1, start_pole, target_pole, temp_pole);
		System.out.println("Move Disc " + n + " from " + start_pole + " to " + target_pole);
		hanoi(n-1, temp_pole, start_pole, target_pole);
	}
	
	/**
	 * returns a number that is x to the power of y
	 * @param x base number
	 * @param y exponent
	 * @return x to the power of y
	 */
	public static int pow(int x, int y) {
		if (y == 0)
			return 1;
		if (y == 1)
			return x;
		return x * pow(x, y-1);
	}
	
	/**
	 * displays a LinkedList from tail to head
	 * @param list the list printing from
	 */
	public static <T> void displayReversed(LinkedList<T> list) {
		
		if(list.isEmpty()) {
			System.out.println("Nothing in the list");
			return;
		}
		displayReversed(list, 0);
	}
	/**
	 * helper method for the displayReversed(LinkedList<T> list) 
	 * that actually displays the linked list in reverse order
	 * @param list the list to reverse print from
	 * @param i current index
	 */
	private static <T> void displayReversed(LinkedList<T> list, int i) {
		// base case: last index
		if(i == list.size()-1) {
			System.out.println(list.get(i));
			return;
		}
			
		// recursive step
		displayReversed(list, i+1);
		System.out.println(list.get(i));
	}
	/**
	 * a function to convert a decimal to a different base number
	 * @param n the decimal value to convert
	 * @param b the base of the converted number
	 * @return <i>String</i> of n base b
	 */
	public static String convert(int n, int b) {
		if(b > 16 || b < 2)
			return "Unable to change to base " + b;
		//base case
		if(n == 0)
			return "";

		return convert(n/b, b) + table[n%b];
		
	}
}

