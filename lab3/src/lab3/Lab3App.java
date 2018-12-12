package lab3;

import java.util.InputMismatchException;
import java.io.*;
import java.util.Scanner;

/**
 * <b>Title:</b> Lab 3:<br>
 * <b>Filename:</b> Lab3App.java<br>
 * <b>Date Written:</b> October 14, 2018<br>
 * <b>Due Date:</b> October 14, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Driver for the "Date" class, which has a try-catch block 
 * to catch all invalid values for the "Date" constructor variables
 * </p>
 *
 * @author Ung Jae Yun
 */

public class Lab3App {
	
	public static void main(String[] args) throws InputMismatchException, DateException, FileNotFoundException {
		
		Scanner scan = new Scanner(System.in);
		
		// create a new Date object to start
		// able to use setters to an existing date object
		// which would raise exceptions right away 
		// instead of waiting to create a new date object and 
		// check for exceptions then
		
		Date aDate = new Date(); 
		boolean done = false;
		int n = 0, count = 0;
		
		PrintWriter out = new PrintWriter(new File("validates.txt")); // create 'validates.txt' to write our dates to
			 

		while(!done) { // input how many dates to write into the file
			try {
				System.out.print("Enter a number of valid dates to write to 'validates.txt': ");
				n = scan.nextInt();
				done = true;
					
			} catch (InputMismatchException ime) {
				System.out.println(ime + "\n Enter an integer: ");
				scan.nextLine();
			}
		}
		

		
		while(count < n) {
			
			done = false;
			
			while(!done) {
				
				try {
					System.out.print("Enter the year as an integer: ");
					aDate.setYear(scan.nextInt()); // tries to set year with the given input
					
					done = true;
					
				} catch (InputMismatchException ime) { // exception thrown if input not an int
					
					System.out.println(ime);
					scan.nextLine();
			
				} catch (DateException de) { // exception thrown if invalid according to setter
					
					System.out.println("Date Exception: " + de.getMessage());
					scan.nextLine();
					
				}
			}
			
			done = false; //reset done to false so the next while loop can loop
			
			while(!done) {
				
				try {
					System.out.print("Enter the month as an integer: ");
					aDate.setMonth(scan.nextInt()); // tries to set month with the given input
					
					done = true;
					
				} catch (InputMismatchException ime) { // exception thrown if input not an int
					
					System.out.println(ime);
					scan.nextLine();
			
				} catch (DateException de) { // exception thrown if invalid according to setter
					
					System.out.println("Date Exception: " + de.getMessage());
					scan.nextLine();
				}
			}
			
			done = false; //reset done to false so the next while loop can loop
			
			while (!done) {
				
				try {
					System.out.print("Enter the day as an integer: ");
					aDate.setDay(scan.nextInt()); // tries to set day with the given input
					
					done = true;
					
				} catch (InputMismatchException ime) { // exception thrown if input not an int
					
					System.out.println(ime);
					scan.nextLine();
			
				} catch (DateException de) { // exception thrown if invalid according to setter
					
					System.out.println("Date Exception: " + de.getMessage());
					scan.nextLine();
					
				} 
				
				// need to set year and month first in order to account for leap year's days
			}
			System.out.println(aDate);
			out.println(aDate);
			count++;
	}
		out.close();
		scan.close();
	}
		
}