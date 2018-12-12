package project3;

/**
 * <b>Title:</b> Project 3<br>
 * <b>Filename:</b> Simulation.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * utility class with helper functions to
 * use in other classes
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */



import java.util.Random;
public class Simulation {
	
	// can tweak the values of these to see how the simulation gets affected
	public static final int TAKEOFF_TIME = 4000;
	public static final int LANDING_TIME = 5000;
	public static final int MEAN_LANDING_TIME = 7000;
	public static final int MEAN_TAKEOFF_TIME = 7000;
	
	// used to generate random names of airlines
	public static String[] AIRLINES = {"AA","AI","AF","AZ","KL","BA","BW","DL","FL",
			"BA","AC","ET","GH","LH","JM","KE","TW","UA"};
	public static final Random rand = new Random();
	
	/**
	 * Calculates time till next event
	 * @param meanEventTime in milliseconds
	 * @return timeTillNext - time before next event occurs
	 */
	public static int timeTillNext(int meanEventTime){
		Random random = new Random();
		double randomDouble = random.nextDouble();
		int timeTillNext = (int)Math.round (-meanEventTime * Math.log (1 - randomDouble));
		return timeTillNext;
	}
	
	/**
	 * Generates a random Airline with randomID
	 * @return a new random Airline object
	 */
	public static Airline generateRandomAirline() {
		// pick a random airline name from the name array and add some random number [1-100] to it
		String flightID = AIRLINES[rand.nextInt(AIRLINES.length)] + rand.nextInt(100)+1;
		return new Airline(flightID, System.currentTimeMillis());
	}
	
	/**
	 * Converts millisecs to seconds. Useful for displaying simulation time.
	 * Since 1 min simTime = 1 second, use this wherever you want to print 'minutes' to the console.
	 * @param millisecs The time in millisec to convert to regular seconds or simulated minutes.
	 * @return 
	 */
	public static long timeInSeconds(long millisecs) {
		return millisecs / 1000;
	}
	
	/**
	 * Returns the elapsed time from when this simulation started.
	 * @return The amount of simulated 'minutes' eg. seconds that has passed since simulation started.
	 */
	public static int elapsedSimulationTime() {
		return (int) timeInSeconds(System.currentTimeMillis() - Program3.startTime);
	}
	
	/**
	 * Converts milliseconds to minutes
	 * @param millisecs
	 * @return timeInMinutes
	 */
	public static long timeInMinutes(long millisecs){
		return millisecs / 60000;
	}
	
	/**
	 * Converts minutes to milliseconds
	 * @param timeInMinutes
	 * @return timeInMilisecs
	 */
	public static long timeInMilisecs(long timeInMinutes){
		return timeInMinutes * 60000;
	}
}
