package lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * <b>Title:</b> Lab 2:<br>
 * <b>Filename:</b> InheritanceApp.java<br>
 * <b>Date Written:</b> October 7, 2018<br>
 * <b>Due Date:</b> October 7, 2018<br>
 * <p>
 * <b>Description:</b><br>
 * Portray Inheritance through "extends" and "implements"<br/>
 * </p>
 * <p>
 * The Person object is created, which has variables for name and ID
 * an abstract Employee class is extended from Person, from which Faculty and Staff
 * are extended. We also implement accessor and	mutator	methods
 *</p>
 *@author Ung Jae Yun
 */

public class InheritanceApp {
	
	public static void main(String[] args) throws IOException {
		
//		Faculty faculty = new Faculty("Dave Watson", "N00543210","B3038",2000);
//		Staff staff = new Staff("Mary Smith", "N00975310","C3078",49,35);
//		Student student = new Student("John Doe", "N00123456", 2.54);
//
//		System.out.println(faculty);
//		System.out.println(student);
//		System.out.println(staff);
//		
//		// A polymorphic reference
//		Person[] person = new Person[] {faculty, staff, student};
//
//		// Late or dynamic binding
//		for(int i=0; i < person.length; i++)
//			System.out.println(person[i]);
		Scanner scan;
		URL url;
			url = new URL("http://venus.cs.qc.cuny.edu/~aabreu/cs212/project1/Sudoku1.txt");
			scan = new Scanner(url.openStream());
			while (scan.hasNext()) {
				for(int i=0;i<9;i++) {
				   	for(int j=0;j<9;j++) {
				   		System.out.println(Integer.toString(scan.nextInt()));
				   	}
				}
			}
			
	}
}
