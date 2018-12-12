package project3;

import cs212lib.Queue;
import cs212lib.QueueException;

/**
 * <b>Title:</b> Project 3<br>
 * <b>Filename:</b> Arrival.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * class to simulate arriving queue of flights
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */


public class Arrival extends Thread {
	/**
	 * queue of arriving flights
	 */
	Queue<Airline> queue;
	/**
	 * airline/flight arriving
	 */
	Airline airplane;
	/**
	 * time it takes to land a plane
	 */
	int landingTime;
	/**
	 * thread status
	 */
	boolean running = true;
	/**
	 * constructs a new arrival object based on its landing time
	 * @param landingTime the time it takes to land an airplane
	 */
	public Arrival(int landingTime) {
		this.landingTime = landingTime;
		this.queue = new Queue<Airline>();
	}
	/**
	 * runs the thread
	 */
	@Override
	public void run() {
		while (running) {
			
			// create a random time til next arrival using Simulation.timeTillNext()
			int timeNext = Simulation.timeTillNext(Simulation.MEAN_LANDING_TIME);
			long timeNextInSeconds = Simulation.timeInSeconds(timeNext);
			
			try {

				this.log("Next arriving flight in " + timeNextInSeconds + (timeNextInSeconds == 1 ? " minute" : " minutes"));
				
			// does not 'sleep' if the time til next flight would 
			// go past remaining simulation time
				if(timeNext > (Program3.simulationTimeInMillis - Simulation.elapsedSimulationTime()*1000)) {
					this.stopRunning();
				}
				
			// sleep for random seconds to simulate waiting for flight
				Arrival.sleep(timeNext);
			} catch (InterruptedException e) {
				this.stopRunning();
			}
			
			// create a new random airline and place it in the departure queue
			// only if this thread is not interrupted/stopped and remaining simulation time
			// is greater than the sum of Runway's current landing/takeoff 'sleep' time
			// and this arrival's most recent 'sleep'/til-next-flight time
			if(this.running && Runway.lastSleep + timeNext < Program3.simulationTimeInMillis + Program3.startTime) {
				try {
					airplane = Simulation.generateRandomAirline();
					queue.enqueue(airplane);
					this.log("Added Flight " + airplane.getFlightID() + " to the arrival queue");
				} catch (QueueException e) {}
			}
		}
	}
	
	/**
	 * Use this method to log Arrival messages to the console. Do NOT change this.
	 * @param msg The message you want the arrival thread to print.
	 */
	private void log(String msg) {
		System.out.printf("Minute %3d - [  Arrival]: %s%n", Simulation.elapsedSimulationTime(), msg);
	}
	/**
	 * stops the thread
	 */
	public void stopRunning() {
		running = false;
	}
	/**
	 * getter for the arrival queue
	 * @return <i>Queue<Airline></i> queue of arrival airlines
	 */
	public Queue<Airline> getQueue() {
		return queue;
	}
	/**
	 * getter for arrival landing time
	 * @return <i>int</i> time it takes to land a flight
	 */
	public int getTime() {
		return landingTime;
	}
}
