package project3;

/**
 * <b>Title:</b> Project 3<br>
 * <b>Filename:</b> Program3.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Driver class that runs the threads and 
 * simulates the runway
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */


import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Program3 {
	// instance vars for our threads
	static Arrival arrivals;
	static Departure departures;
	static Runway runway;
	
	// start time gets initialized when we call startSimulation()
	// it's static so other threads can easily reference it. It doesn't need to be synchronized since it never changes.
	static long startTime;
	static long simulationTimeInMillis;
	static long simulationTimeInSeconds = simulationTimeInMillis/1000;
	
	public static void main(String[] args) {
		//	Get the length of time to run the simulation from user (in minutes)
		Scanner sc = new Scanner(System.in);
		System.out.print("How long do you want to run the simulation? (in minutes): ");
		int simTimeInMinutes = sc.nextInt();

		Program3 project = new Program3();
		
		//	start the simulation
		project.startSimulation(simTimeInMinutes);

		// wait until all threads are finished
		while(arrivals.isAlive() || departures.isAlive() || runway.isAlive()) {}

		JOptionPane.showMessageDialog(null, "Simulation Over");
	}
	/**
	 * constructor that create new instances for arrivals, departures and runway
	 */
	public Program3() {
		// when we create a new Program3 instance, we need to initialize our threads (arrivals, departures, & runway)
		arrivals = new Arrival(Simulation.LANDING_TIME);
		departures = new Departure(Simulation.TAKEOFF_TIME);
		runway = new Runway(arrivals, departures);
	}
	
	/**
	 * Starts the Airport simulation and runs it for the specified amount of time.
	 * @param timeInMinsToRunSimulation how long to run the simulation (minutes)
	 */
	public void startSimulation(int timeInMinsToRunSimulation) {
		// initialize startTime to the current time so we know when the simulation started (hint: System.currentTimeMillis())
		startTime = System.currentTimeMillis();
		// convert the time in minutes to run the simulation to milliseconds. (hint: Simulation class may help)
		simulationTimeInMillis = Simulation.timeInMilisecs(timeInMinsToRunSimulation);
		
		//	start the threads (Arrival, Departure, Runway)
		arrivals.start();
		departures.start();
		runway.start();
		
		//	loop for the specified length of time to run the simulation
		while(System.currentTimeMillis() < startTime + simulationTimeInMillis) {}
		
		//	stop the threads (Arrival, Departure, Runway). Call their stopRunning() method to kill their run loops!
		arrivals.stopRunning();
		departures.stopRunning();
		runway.stopRunning();
		
		//	interrupt the threads (Arrival, Departure, Runway). They could be sleeping even if you killed their run loops!
		arrivals.interrupt();
		departures.interrupt();
		runway.interrupt();
	}
}
