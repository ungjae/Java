package lab5b;

import java.util.Random;

import cs212lib.Queue;
import cs212lib.QueueException;

/**
 * <b>Title:</b> Lab 5b:<br>
 * <b>Filename:</b> ProducerThread.java<br>
 * <b>Date Written:</b> November 4, 2018<br>
 * <b>Due Date:</b> November 4, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * takes image addresses from the picture array and
 * feeds it to the queue as long as the queue is not full
 * until there are no more pictures in the array
 * </p>
 *
 * @author Ung Jae Yun
 */

public class ProducerThread extends Thread {
	/**
	 * queue to enqueue image addresses to
	 */
	private Queue<String> queue;
	/**
	 * array of strings of image addresses
	 */
	private String[] picture;
	/**
	 * constructor to create a ProducerThread that feeds strings to the queue
	 * @param queue queue to which the ProducerThread will enqueue the string of image addresses
	 * @param picture the array of image addresses
	 */
	public ProducerThread(Queue<String> queue, String[] picture) {
		this.queue = queue;
		this.picture = picture;
	}
	/**
	 * override thread run method
	 */
	public void run() {

		// loops through the picture array
		for (int i = 0; i < picture.length; i++)
		{

			// waits until the queue is not full
			while(this.queue.isFull()) {}
			
			try {
				
				// prints and places the current indexed 
				// image address onto the queue
				this.queue.enqueue(picture[i]);
				log("enqueued " + picture[i]);
				
			} catch (QueueException e) {
				while(this.queue.isFull()) {};
			}
			
			try {
				
				// pause the thread for a random amount of time
				// between 100 - 1000 milliseconds
				sleep(new Random().nextInt(901) + 100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		
		// prints if this thread has finished
		if (!Thread.currentThread().isAlive()) {
			log(" has stopped running");
		}
	/**
	 * print method with current thread name labeled []:	
	 */
	}
	private void log(String msg) {
		String s = String.format("[Producer]: %s", msg);
		System.out.println(s);
	}
	
}
