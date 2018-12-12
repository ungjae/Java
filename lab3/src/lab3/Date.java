package lab3;


/**
 * <b>Title:</b> Lab 3:<br>
 * <b>Filename:</b> Date.java<br>
 * <b>Date Written:</b> October 14, 2018<br>
 * <b>Due Date:</b> October 14, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Date class, for which takes in values for month, day and year and produces a date object
 * </p>
 *
 * @author Ung Jae Yun
 */

public class Date {
	
	private int dMonth; // variable to store the month
	private int dDay; // variable to store the day
	private int dYear; // variable to store the year
	private boolean leap = false; // leap year set to false by default

	/**
	 * default constructor - sets dMonth=1, dDay=1, and dYear=1900
	 */
	public Date() {
		dMonth = 1;
		dDay = 1;
		dYear = 1900;
	}

	/**
	 * parameterized constructor - sets dMonth, dDay, and dYear to user
	 * specified values
	 * 
	 * @param month
	 *            value to be stored in dMonth
	 * @param day
	 *            value to be stored in dDay
	 * @param year
	 *            value to be stored in dYear
	 * @throws DateException 
	 */
	public Date(int month, int day, int year) throws DateException {
		setYear(year);
		setMonth(month);
		setDay(day);

	}

	/**
	 * setDate - stores month, day, and year in dMonth, dDay, and dYear
	 * respectively be calling each of the setMethods defined
	 * 
	 * @param month
	 *            value to be stored in dMonth
	 * @param day
	 *            value to be stored in dDay
	 * @param year
	 *            value to be stored in dYear
	 * @throws DateException 
	 */
	public void setDate(int month, int day, int year) throws DateException {
		setMonth(month);
		setDay(day);
		setYear(year);
	}

	/**
	 * setMonth - stores month in dMonth
	 * 
	 * @param month
	 *            the value to be stored in dMonth
	 */
	public void setMonth(int month) throws DateException {
		if (month >= 1 && month <= 12)
			dMonth = month;
		else
			throw new DateException("Invalid Month: month out of range");

	}

	/**
	 * setDay - stores day in dDay
	 * 
	 * @param day
	 *            the value to be stored in dDay
	 * @throws DateException 
	 */
	public void setDay(int day) throws DateException {
		if (leap == true && dMonth == 2 && day >= 1 && day <= 29) {
			dDay = day;
		} else if (leap == false && dMonth == 2 && day >= 1 && day <= 28) {
			dDay = day;
		} else if (getMonth() % 2 == 1 && day >= 1 && day <= 31 && getMonth() < 8) {
			dDay = day;
		} else if (getMonth() % 2 == 0 && day >= 1 && day <= 30 && getMonth() < 8) {
			dDay = day;
		} else if (getMonth() % 2 == 0 && day >= 1 && day <= 31 && getMonth() > 7) {
			dDay = day;
		} else if (getMonth() % 2 == 1 && day >= 1 && day <= 30 && getMonth() > 7) {
			dDay = day;
		} else {
			throw new DateException("Invalid Day: Day out of range");
		}
	}

	/**
	 * setYear - stores year in dYear and checks if it is a leap year
	 * 
	 * @param year
	 *            the value to be stored in dYear
	 * @throws DateException 
	 */
	public void setYear(int year) throws DateException {
		if (year >= 1752 && year <= 2018) {
			dYear = year;
			leapCheck(); // checks whether the year is a leap year and sets to true if so
		} else {
			throw new DateException("Invalid Year: Year out of range");
		}
	}

	/**
	 * getMonth - accessor for dMonth
	 * 
	 * @return returns the value stored in dMonth
	 */
	public int getMonth() {
		return dMonth;
	}

	/**
	 * getDay - accessor for dDay
	 * 
	 * @return returns the value stored in dDay
	 */
	public int getDay() {
		return dDay;
	}

	/**
	 * getYear - accessor for dYear
	 * 
	 * @return returns the value stored in dYear
	 */
	public int getYear() {
		return dYear;
	}

	/**
	 * toString - returns the month, day, and year in the format: mm-dd-yyyy;
	 * leading zeros are NOT contained within the string
	 * 
	 * @return a String containing the date in month-day-year format
	 */
	public String toString() {
		return (dMonth + "-" + dDay + "-" + dYear);
	}
	
	/**
	 * leapCheck - tells you whether the Date is a leap year
	 * set to false by default
	 * 
	 * steps according to https://support.microsoft.com/en-us/help/214019/method-to-determine-whether-a-year-is-a-leap-year
	 * 
	 * To determine whether a year is a leap year, follow these steps: 
	 * 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
	 * 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
	 * 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
	 * 4. The year is a leap year (it has 366 days).
	 * 5. The year is not a leap year (it has 365 days).
	 * 
	 * @return true for leap year, false otherwise
	 */
	private void leapCheck() {
		if (dYear % 4 == 0) {
			if (dYear % 100 == 0) {
				if (dYear % 400 == 0) {
					leap = true;
				} else {
					leap = false;
				}
			} else {
				leap = true;
			}
		} else {
			leap = false;
		}
	}
	
}