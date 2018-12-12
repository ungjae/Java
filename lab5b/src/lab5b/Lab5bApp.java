package lab5b;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.net.*;
import java.applet.*;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cs212lib.Queue;

/**
 * <b>Title:</b> Lab 5b:<br>
 * <b>Filename:</b> Lab5bApp.java<br>
 * <b>Date Written:</b> November 4, 2018<br>
 * <b>Due Date:</b> November 4, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * App that takes images either from the venus server
 * or the local machine to output into JFrames that 
 * simulates simultaneous multi-threading using Threads;
 * uses ProducerThread to feed into a queue to be taken
 * and output by ConsumerThread(s)
 * </p>
 *
 * @author Ung Jae Yun
 */

public class Lab5bApp {
	/**
	 * number of threads to be assigned when constructed (3-5)
	 */
	private int numThreads;
	/**
	 * queue of strings of image addresses fed from producer to consumer
	 */
	private Queue<String> queue = new Queue();
	/**
	 * group of consumer threads
	 */
	private ThreadGroup tg = new ThreadGroup("consumers");
	/**
	 * array of ConsumerThreads
	 */
	private Thread[] consumer;
	/**
	 * thread that enqueues the images to the queue
	 */
	private ProducerThread producer;
	/**
	 * array of pictpanels
	 */
	private PictPanel[] panel;
	/**
	 * array of JFrames
	 */
	private JFrame[] pictureFrame;
	/**
	 * array of strings of image addresses
	 */
	private String[] picture;
	/**
	 * driver for the lab
	 */
	public static void main(String[] args) {
		
		new Lab5bApp();
				
		System.exit(0);
	}
	/**
	 * default constructor; runs program
	 */
	public Lab5bApp() {

		numThreads = Integer.parseInt(JOptionPane.showInputDialog("Input the desired number of consumer threads (3-5)"));
		
		consumer = new Thread[numThreads];
		panel = new PictPanel[numThreads];
		pictureFrame = new JFrame[numThreads];

		loadImages();
		
		for (int i = 0; i < numThreads; i++)
		{
			// create an instance of JFrame with the title 'Thread' and the number
			pictureFrame[i] = new JFrame("Thread " + i);
			
			pictureFrame[i].setSize(200, 200);

			// Set X,Y coordinate of upper left corner of the frame
			pictureFrame[i].setLocation(80 * i, 80 * i);
			
			// create an instance of PictPanel and store it in the panel array
			panel[i] = new PictPanel(pictureFrame[i]);

			pictureFrame[i].getContentPane().add(panel[i]);
			pictureFrame[i].setVisible(true);
		}

		// create an instance of ProducerThread and run
		producer = new ProducerThread(queue, picture);
		producer.start();
		
		producer.setPriority(Thread.MAX_PRIORITY);

		for (int i = 0; i < numThreads; i++) {
			consumer[i] = new Thread(tg, new ConsumerThread(queue, producer, panel[i]), "thread " + i);
			consumer[i].start();
		}
		// loop while producer is active
		while(producer.isAlive()) {}
		
		// prints when ProducerThread is finished
		JOptionPane.showMessageDialog(null, "Producer Thread has finished");
		
		// loop while any of the consumers are active
		while (tg.activeCount() > 0) {}
		
		// prints when all the consumer threads have finished
		JOptionPane.showMessageDialog(null, "Consumer threads have finished");
		
		// prints when all of the threads have finished
		JOptionPane.showMessageDialog(null, "End of program");		
	}
	
	/**
	 *  Method loads the pictures from URL
	 */	
	public void loadImages() {		
     Document doc;
     String url = "https://venus.cs.qc.cuny.edu/~aabreu/cs212/images/";
     int j = 0;
        
		try {
			System.out.println("Reading URL directory");
			doc = Jsoup.connect(url).get();
			Elements files = doc.getElementsByTag("a");
			
			// initialize the array size for the file names
			picture = new String[files.size()];
			
			for (Element file : files){
				String filename = file.attr("href");
				if(filename.indexOf(".jpg") >= 0 || filename.indexOf(".gif") >= 0){
					System.out.println(filename);
					picture[j++] = url + filename;
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	
	
 
	/**
	 *  Method loads the pictures from a local folder
	 */	
	public void loadPictures() {
		// create a reference to an array of Files (type is File)
		File[] pictures;

		// create a reference String for the image directory
		String directory = "images/";

		// create a reference to the directory
		File imgDirectory = new File(directory);

		// store the directory listing in the File array
		pictures = imgDirectory.listFiles();

		// initialize the array size for the file names
		picture = new String[pictures.length];

		// add each image file to the image file array
		for (int i = 0, j = 0; i < pictures.length; i++) {
			File file = pictures[i];

			// add .jpg and .gif file names to the String array
			// you can add other image types
			if (file.isFile()
					&& (file.getName().toLowerCase().endsWith(".jpg") || 
						file.getName().toLowerCase().endsWith(".gif"))) {
				// display the name of the image file
				System.out.println((j + 1) + ": " + pictures[i].getName());

				// add the name to the array
				picture[j] = pictures[i].getName();

				// increment the number of images
				j++;
			}
		}
	}
	
}
