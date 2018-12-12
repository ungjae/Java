package project3;

import cs212lib.QueueException;

/**
 * <b>Title:</b> Project 3<br>
 * <b>Filename:</b> Runway.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * class to simulate a runway/control tower
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */


public class Runway extends Thread {
	/**
	 * arriving flights thread
	 */
	Arrival arrivals;
	/**
	 * departing flights thread
	 */
	Departure departures;
	/**
	 * thread status
	 */
	boolean running = true;
	/**
	 * most recent amount of time this thread slept for
	 */
	static long lastSleep;
	/**
	 * airline/flight object
	 */
	private Airline airplane;
	/**
	 * time an airplane was dequeued and processed by this thread
	 */
	public long enteredRunway;
	/**
	 * time the flight was created/enqueued in its respective queue
	 */
	long enteredQueue;
	/**
	 * length of time a flight waited since instantiation til landing/takeoff
	 */
	long waitTime;
	/**
	 * constructs a runway to simulate landing and takeoff
	 * @param arrivals thread to simulate arrival flights
	 * @param departures thread to simulate departing flights
	 */
	public Runway(Arrival arrivals, Departure departures) {
		// initialize the instance vars for this thread (arrivals, departures)
		this.arrivals = arrivals;
		this.departures = departures;
	}
	/**
	 * runs thread
	 */
	@Override
	public void run() {
		log("Runway is open.");
		
		while (running) {

			// while running & there are planes in the arrival queue
			// get a plane from the arrival queue, log info about the airline
			while(this.running && !arrivals.getQueue().isEmpty()) {
				//	
				try {
					airplane = arrivals.getQueue().dequeue();
					enteredRunway = System.currentTimeMillis();
					enteredQueue = Simulation.timeInSeconds(airplane.getEntered()-Program3.startTime);
					waitTime = Simulation.timeInSeconds(enteredRunway - airplane.getEntered());
					this.log("Flight " + airplane.getFlightID() + " cleared for landing - Entered Queue at Minute " + enteredQueue  + " -  waited for " + waitTime + (waitTime == 1 ? " minute" : " minutes"));
				} catch (QueueException e) {
					this.stopRunning();
				}
				
				// simulate landing by sleeping thread 
				try {
					Runway.sleep(arrivals.getTime());
					
				// keep record of the current 'sleep' time to refer in other threads
					lastSleep = enteredRunway + arrivals.getTime();
				} catch (InterruptedException e) {
					
				}
			}
			//	while running & there are NO arrivals trying to land & there are planes waiting to depart 
			// get a plane from the departure queue and log info
			while(this.running && arrivals.queue.isEmpty() && !departures.queue.isEmpty()) {
				
				try {
					airplane = departures.getQueue().dequeue();
					enteredRunway = System.currentTimeMillis();
					enteredQueue = Simulation.timeInSeconds(airplane.getEntered()-Program3.startTime);
					waitTime = Simulation.timeInSeconds(enteredRunway - airplane.getEntered());
					this.log("Flight " + airplane.getFlightID() + " cleared for takeoff - Entered Queue at Minute " + enteredQueue  + " -  waited for " + waitTime + (waitTime == 1 ? " minute" : " minutes"));
				} catch (QueueException e) {
					e.printStackTrace();
				}

				//	simulate takeoff by sleeping the thread
				try {
					this.sleep(departures.getTime());
					
				// keep record of the current 'sleep' time to refer in other threads
					lastSleep = departures.getTime();
				} catch (InterruptedException e) {
					
				}
			}
		}
		
		log("Runway is now closed");
	}
	
	/**
	 * Use this method to log Runway messages to the console. Do NOT change this.
	 * @param msg The message you want the arrival thread to print.
	 */
	private void log(String msg) {
		System.out.printf("Minute %3d - [   Runway]: %s%n", Simulation.elapsedSimulationTime(), msg);
	}
	/**
	 * stops the thread
	 */
	public void stopRunning() {
		running = false;
	}

	
}
