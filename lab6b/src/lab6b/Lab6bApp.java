package lab6b;

/**
 * <b>Title:</b> Lab 6b:<br>
 * <b>Filename:</b> Lab6bApp.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Driver to test the orderedArrayList class methods on 
 * Packet objects which are loaded from a .dat file
 * also created different methods to print out the contents
 * of the package.dat file
 * </p>
 *
 * @author Ung Jae Yun
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab6bApp {

	// Don't change the code for main. Seriously. Not a single character! :)
	// What you have to implement are the methods that main is calling! (At the bottom)
	public static void main(String[] args) throws FileNotFoundException {

		// read the packets into an OrderedArrayList
		OrderedArrayList<Packet> list = getPackets();
		System.out.println(list + "\n");
		
		// assemble the message into a single string
		String msg = assembleMessage(list);
		System.out.println("The assembled message as a string:\n"+msg+"\n");

		// display the message formatted to no more than 60 chars per line.
		// if a word cannot fit, place the word on the next line. Do not split the word between two lines.
		System.out.println("The formatted message:");
		displayMessage(msg);
		
	}
	/**
	 * Opens the local "package.dat" file and parses the packages (lines) into an OrderedArrayList.
	 * @return an OrderedArrayList of Packets retrieved from the file.
	 * @throws FileNotFoundException if the package.dat file is not found.
	 */
	private static OrderedArrayList<Packet> getPackets() throws FileNotFoundException {
		
		// create a new OrderedArrayList of Packet. This is where we'll add packets as we parse them.
		OrderedArrayList<Packet> list = new OrderedArrayList<>(); 
		
		// open the package.dat file and parse the lines, create packets, and add them to the list
		
		try(Scanner sc = new Scanner(new File("package.dat"))) {
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				int i = Integer.parseInt(line.split("\\t")[0]);
				list.add(new Packet(i, line.split("\\t")[1]));
			}
		}
		
		return list;
	}
	/**
	 * Reconstructs the original message from the packages in the given list.
	 * @param list is the OrderedArrayList<Package> containing the packages.
	 * @return A single String, equivalent to the original message before split into packages.
	 */
	private static String assembleMessage(OrderedArrayList<Packet> list) {
		// the StringBuilder is like a dynamic array in that the object itself can be modified to add, delete
		// reverse, etc, whereas when you '+=' to a String object, it actually creates and returns a new String object
		// that copies a concatenated version of the the original String and the 'appending' String 
		// since the String object is immutable (i.e. copying arrays onto a new bigger array vs dynamic array)
		
		StringBuilder extraCredit = new StringBuilder(); 
		for(int i = 0; i < list.getSize(); i++) {
			extraCredit.append(list.get(i).getMsg());
		}
		return extraCredit.toString();
	}
	/**
	 * Prints the given message to the console, ensuring at most 60 characters per line.
	 * Does not split words between lines.
	 * @param msg the String to print.
	 */
	private static void displayMessage(String msg) {

		int charCount = 0;
		String[] words = msg.split(" ");
		
		for (int i = 0; i < words.length; i++) {
			int length = words[i].length();
			
			if (length + charCount > 60) {
				// print blank line and reset count
				// if addition of words[i] would make
				// the line go past 60 characters
				System.out.println();
				charCount = 0;
			}
				
			// otherwise print words[i] + " " and increment 
			// count by the length of words[i]
			System.out.print(words[i] + " ");
			charCount+= length;
			
		}
	}
}
