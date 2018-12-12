package lab5a;

/**
 * <b>Title:</b> Lab 5a:<br>
 * <b>Filename:</b> Lab5aApp.java<br>
 * <b>Date Written:</b> October 28, 2018<br>
 * <b>Due Date:</b> October 28, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Driver to test out Queue functions such as:
 * enqueue, dequeue, front, rear, makeEmpty, toString and getSize
 * also included function fullCheck()
 * </p>
 *
 * @author Ung Jae Yun
 */

public class Lab5aApp {
	
	public static void main(String[] args) throws QueueException {
		
			System.out.println();
			
			// create queue1 with default constructor and print 
			Queue queue1 = new Queue();
			System.out.println(queue1);
			
			System.out.println();
			
			// create queue2 with capacity 3 and print 
			Queue queue2 = new Queue(3);
			System.out.println(queue2);
			
			System.out.println();
			
			// enqueue and print into queue2 until full			
			while(true) {
				try {
					queue2.enqueue(new Integer(4)); 
					System.out.println(queue2);
				} catch(QueueException qe) {
					System.out.println(qe);
					break;
				}
			}
			
			System.out.println();
			
			// call fullCheck() method which utilizes isEmpty() and isFull()
			// to check whether the queue is empty/full or displays information otherwise
			System.out.println(queue1.fullCheck());
			System.out.println(queue2.fullCheck());
			queue2.dequeue(); // dequeue queue2 to test out when neither empty nor full
			System.out.println("After dequeueing: " + queue2.fullCheck());
			
			System.out.println();
			
			// test front() and rear() methods on queue2
			queue2.enqueue(new Integer(9));
			System.out.println(queue2);
			System.out.println("Front of queue 2: " + queue2.front());
			System.out.println("Rear of queue 2: " + queue2.rear());
			
			System.out.println();
			
			// dequeue queue2 until empty			
			while(true) {
				try {
					queue2.dequeue();
					System.out.println(queue2);
				} catch (QueueException qe) {
					System.out.println(qe+"\n");
					break;
				}
			}
			
			//test front() and rear() methods on an empty queue
			try {
			System.out.println("Front of queue 2: " + queue2.front());
			} catch (QueueException qe) {
				System.out.println(qe);
			}
			
			try {
			System.out.println("Rear of queue 2: " + queue2.rear()); //how to driver
			} catch (QueueException qe) {
				System.out.println(qe);
			}
			
			System.out.println();
			
			// test out the makeEmpty() method
			queue2.enqueue(new Integer(7));
			System.out.println(queue2);
			queue2.makeEmpty();
			System.out.println(queue2);

	}

}
