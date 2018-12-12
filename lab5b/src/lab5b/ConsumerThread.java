package lab5b;

import cs212lib.Queue;
import cs212lib.QueueException;
import java.util.Random;

/**
 * <b>Title:</b> Lab 5b:<br>
 * <b>Filename:</b> ConsumerThread.java<br>
 * <b>Date Written:</b> November 4, 2018<br>
 * <b>Due Date:</b> November 4, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * takes images from the queue and populates
 * panels/windows that display the images as long as 
 * the queue is not empty
 * </p>
 *
 * @author Ung Jae Yun
 */

public class ConsumerThread extends Thread {
	/**
	 * queue from which to retrieve the images
	 */
	private Queue<String> queue;
	/**
	 * producerThread that enqueues to the image queue;
	 * necessary to know whether the producer thread is finished or not
	 */
	private ProducerThread producer;
	/**
	 * count of items processed
	 */
	private int count;
	/**
	 * picture panel
	 */
	private PictPanel panel;
	/**
	 * indicates whether a thread is alive
	 */
	private boolean running;
	/**
	 * constructor to create a thread with access to the params below
	 * @param queue queue of images
	 * @param producer thread that enqueues the images to the queue
	 * @param panel picture panel
	 */
	public ConsumerThread(Queue<String> queue, ProducerThread producer, PictPanel panel) {
		this.queue = queue;
		this.producer = producer;
		this.panel = panel;
	}
	/**
	 * override default run of a thread
	 */
	public void run() {	

		// only runs when the producer thread is active or the queue is filled
		while(this.producer.isAlive() || !this.queue.isEmpty()) {
			
				// waits until queue is not empty
				while(this.queue.isEmpty()){}
				
				if (!this.queue.isEmpty())
				{
					try {
						// takes image from the queue and 'draws'
						// the image via panels to the screen
						String str = this.queue.dequeue();
						this.panel.drawPict(str);
						
						// prints which image the thread has printed
						log("processed " + str);
						
					} catch (QueueException e1) {
						// waits if the queue is empty until filled
						while(this.queue.isEmpty()) {};
					}
					
					// increment 'count' to keep count
					count++;
					
					try {
						// pauses the thread for a random amount of time 
						// between 1000 - 5000 milliseconds
						sleep(new Random().nextInt(4001) + 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
		}
		// print the amount of items processed when the thread has finished 
		log("processed " + count + " items.");
	/**
	 * print method with current thread name labeled []:	
	 */
	}
	private void log(String msg) {
		String s = String.format("[%s]: %s", Thread.currentThread().getName(), msg);
		System.out.println(s);
	}
}
