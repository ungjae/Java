package lab5a;

/**
 * <b>Title:</b> Lab 5a:<br>
 * <b>Filename:</b> Queue.java<br>
 * <b>Date Written:</b> October 28, 2018<br>
 * <b>Due Date:</b> October 28, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Queue data structure; First in First Out
 * Structure where you can only exit from the front and enter from the rear
 * in the form of a traditional line
 * </p>
 *
 * @author Ung Jae Yun
 */

public class Queue<T> implements QueueADT<T> {
	/**
	 * The array into which the elements are enqueued and dequeued
	 */
	private T queue[];
	/**
	 * Index of the queue array from which to dequeue
	 */
	private int front;
	/**
	 * Index of the queue array from which to enqueue
	 */
	private int rear;
	/**
	 * Current number of elements in the queue
	 */
	private int size;
	/**
	 * Total number of available elements that can be queued/stored; default at 100
	 */
	private int capacity = 100;
	/**
	 * Constructs a new array of objects/elements with default parameters
	 */
	public Queue() {
		queue = (T[])new Object[this.capacity];
		front = 0;
		rear = 0;
		size = 0;
	}
	/**
	 * Constructs a new array of objects/elements with given capacity
	 * @param capacity the maximum amount of items that can be stored in queue
	 */
	public Queue(int capacity) {
		this.capacity = capacity;
		queue = (T[])new Object[this.capacity];
		front = 0;
		rear = 0;
		size = 0;
	}
	/**
	 * Places the element at the end/rear of the queue
	 * @throws QueueException when the queue is full
	 */
	public void enqueue(T element) throws QueueException {
		if (isFull()) {
			throw new QueueException("Queue is Full!");
		}
		this.queue[this.rear] = element;
		this.rear = (this.rear + 1) % this.queue.length;
		this.size++;		
	}
	/**
	 * Takes off element at the front of the queue
	 * @return element taken off from the front of queue
	 * @throws QueueException when the queue is empty
	 */
	public T dequeue() throws QueueException {
		if (isEmpty()) {
			throw new QueueException("Queue is Empty!");
		}
		T temp = this.queue[front];
		this.front = (this.front + 1) % this.queue.length;
		this.size--;
		return temp;
	}
	/**
	 * Displays what is at the front of the queue unless empty
	 * @return the element at the front of queue
	 * @throws QueueException when the queue is empty
	 */
	public T front() throws QueueException {
		if (isEmpty()) {
			throw new QueueException("Queue is Empty");
		}
		return this.queue[this.front];
	}
	/**
	 * Displays what is at the rear of the queue unless empty
	 * @return the element at the end of the queue
	 * @throws QueueException when the queue is empty
	 */
	public T rear() throws QueueException {
		if (isEmpty()) {
			throw new QueueException("Queue is Empty");
		}
		return this.queue[(this.rear - 1 + capacity) % this.capacity];
	}
	/**
	 * Empties the entire queue and resets front and rear
	 */
	public void makeEmpty() {
		queue = (T[])new Object[this.capacity];
		front = 0;
		rear = 0;
		size = 0;
	}
	/**
	 * @return <i>true</i> if the queue is empty
	 */
	public boolean isEmpty() {
		return (this.size == 0);
	}
	/**
	 * @return <i>true</i> if the queue is full
	 */
	public boolean isFull() {
		return (this.size == this.capacity);
	}
	/**
	 * @return the current size of the queue
	 */
	public int getSize() {
		return this.size;
	}
	/**
	 * Checks whether the queue is empty/full
	 * @return <i>String</i> of size/capacity if neither empty nor full
	 */
	public String fullCheck() {
		String str = "";
		if (isEmpty()) {
			str = "Queue is Empty!";
		} else if (isFull()) {
			str = "Queue is Full!";
		} else {
			String typeName = queue[0].getClass().getName();
			str = "Queue contains " + size + " " + typeName.split("\\.")[typeName.split("\\.").length-1] + "'s; able to store " + capacity + " in total.";
		}
		
		return str;
	}
	/**
	 * toString override
	 * @return <i>String</i> of number of items and items themselves
	 * @throws QueueException when the queue is empty
	 */
	public String toString() {
		String str = "";
		if (isEmpty()) {
			str = "Queue is empty! Maximum number of items that can be stored is " + this.queue.length;
		} else {
			String contents = "";
			for(int i = 0; i < size; i++) {
				contents += this.queue[(i + front) % this.queue.length] + " ";
			}
			str = "The number of items in the queue is: " + (this.size) + "\nThe queue contains the following: " + contents;
		}
		
		return str;
	}

}
