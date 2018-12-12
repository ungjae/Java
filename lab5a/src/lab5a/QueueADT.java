package lab5a;

public interface QueueADT<T> {
	void enqueue(T d) throws QueueException;
	T dequeue() throws QueueException;
	T front() throws QueueException;
	T rear() throws QueueException;
	boolean isEmpty();
	boolean isFull();
	int getSize();
}