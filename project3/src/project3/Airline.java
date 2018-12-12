package project3;


/**
 * <b>Title:</b> Project 3<br>
 * <b>Filename:</b> Airline.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * class to represent airlines/airplanes
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */


public class Airline {
	/**
	 * unique ID of flight
	 */
	private String flightID;
	/**
	 * time enqueued
	 */
	private long entered;
	/**
	 * time dequeued
	 */
	private long exited;
	/**
	 * constructs an airline object; essentially a 'flight'
	 * @param flightID the unique ID associated with the flight
	 * @param timeEntered time the airline was enqueued
	 */
	public Airline (String flightID, long timeEntered) {
		this.flightID = flightID;
		entered = timeEntered;
	}
	/**
	 * getter for the flight ID
	 * @return <i>String</i> flight ID
	 */
	public String getFlightID() {
		return flightID;
	}
	/**
	 * getter for enqueued time
	 * @return <i>long</i> time the flight was enqueued
	 */
	public long getEntered() {
		return entered;
	}
	/**
	 * setter for enqueued time
	 * @param entered new value for enqueued time
	 */
	public void setEntered(long entered) {
		this.entered = entered;
	}
	/**
	 * setter for exit time
	 * @param exited time the flight was dequeued
	 */
	public void setExited(long exited) {
		this.exited = exited;
	}
}
