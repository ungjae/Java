package project3;

import cs212lib.Queue;
import cs212lib.QueueException;

/**
 * <b>Title:</b> Project 3<br>
 * <b>Filename:</b> Departure.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * class to simulate a queue of departing flights
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */


public class Departure extends Thread {
	/**
	 * queue of departing flights
	 */
	Queue<Airline> queue;
	/**
	 * airline/flight departing
	 */
	Airline airplane;
	/**
	 * time it takes for a plane to take off
	 */
	int takeoffTime;
	/**
	 * thread status
	 */
	boolean running = true;
	/**
	 * constructs a new departure thread
	 * @param takeoffTime time it takes for a plane to take off
	 */
	public Departure(int takeoffTime) {
		// initialize the instance vars for this thread (queue, takeoffTime)
		this.takeoffTime = takeoffTime;
		this.queue = new Queue<Airline>();
	}
	/**
	 * runs thread
	 */
	@Override
	public void run() {
		while (running) {
			// create a random time til next departure using Simulation.timeTillNext()
			int timeNext = Simulation.timeTillNext(Simulation.MEAN_TAKEOFF_TIME);
			long timeNextInSeconds = Simulation.timeInSeconds(timeNext);
			
			try {
				
				this.log("Next departing flight in " + timeNextInSeconds + (timeNextInSeconds == 1 ? " minute" : " minutes"));
				
			// does not 'sleep' if the time til next flight would 
			// go past remaining simulation time
				if(timeNext > (Program3.simulationTimeInMillis - Simulation.elapsedSimulationTime()*1000)) {
					this.stopRunning();
				}
				
			// sleep for random seconds to simulate waiting for flight
				Departure.sleep(timeNext);
			} catch (InterruptedException e) {
				this.stopRunning();
			}
			
			// create a new random airline and place it in the arrival queue
			// only if this thread is not interrupted/stopped and remaining simulation time
			// is greater than the sum of Runway's current landing/takeoff 'sleep' time
			// and this arrival's most recent 'sleep'/til-next-flight time
			if(this.running && Runway.lastSleep + timeNext < (Program3.simulationTimeInMillis + Program3.startTime)) {
				try {
					airplane = Simulation.generateRandomAirline();
					queue.enqueue(airplane);
					this.log("Added Flight " + airplane.getFlightID() + " to the departure queue");
				} catch (QueueException e) {}	
			}
		}
	}

	/**
	 * Use this method to log Departure messages to the console. Do NOT change this.
	 * @param msg The message you want the arrival thread to print.
	 */
	private void log(String msg) {
		System.out.printf("Minute %3d - [Departure]: %s%n", Simulation.elapsedSimulationTime(), msg);
	}
	/**
	 * getter for departure queue
	 * @return <i>Queue<Airline></i> queue of departing flights
	 */
	public Queue<Airline> getQueue() {
		return queue;
	}
	/**
	 * getter for departing flight take off time
	 * @return <i>int</i> time it takes for a flight to takeoff
	 */
	public int getTime() {
		return takeoffTime;
	}
	/**
	 * stops the thread
	 */
	public void stopRunning() {
		running = false;
	}
}
