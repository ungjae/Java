package project4;

/**
 * <b>Title:</b> Project 4<br>
 * <b>Filename:</b> GoFishCard.java<br>
 * <b>Date Written:</b> December 3, 2018<br>
 * <b>Due Date:</b> December 9, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Defines the properties and behaviors of a single GoFish card.
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */

public class GoFishCard extends Card implements Comparable<GoFishCard>{

	/**
	 * Default Constructor
	 */
	public GoFishCard(){
		super();
	}
	/**
	 * Constructor 
	 * @param n a number between 0 and 51 inclusive
	 */
	public GoFishCard(int n){
		super(n);
	}
	/**
	 * Constructor 
	 * @param r - a rank (0 to 12) representing the Card's rank 
	 * @param s - a suit (0 to 3) representing the Card's suit 
	 */
	public GoFishCard(int r, int s){
		super(r,s);
	}
	/**
	 * @param otherCard - a GoFishCard
	 * @return -1 if this card's rank is lower than than other's,<br>
	 * 	0 if they are the same, <br>1 if this card's rank is higher than the other's
	 */
	public int compareTo(GoFishCard otherCard) {
		return compareByRank((Card) otherCard);
	}
	/**
	 * @param otherCard - a GoFishCard
	 * @return Returns <i>true</i> if this card's rank is the same as otherCard's,<br>
	 *  <i>false</i> otherwise
	 */
	public boolean equals(GoFishCard otherCard) {
		return (getRank() == otherCard.getRank());
	}
	/**
	 * @param otherCard an Object
	 * @return Returns <i>true</i> if this card's rank is the same as otherCard's,<br> 
	 * <i>false</i> otherwise
	 */	
	public boolean equals(Object otherCard) {
		return (getRank() == ((GoFishCard)otherCard).getRank() 
				&& getSuit() == ((GoFishCard)otherCard).getSuit());
	}
	/**
	 * A static method that converts a string to a card's equivalent rank
	 * @param str - "2", "3", "4", "5", "6", "7", "8", "9", "10", "J","Q", "K", "A"
	 * @return the equivalent rank (0 - 12)
	 */
	public static int convertToRank(String str){
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J",
				"Q", "K", "A" };
		for(int i=0; i<ranks.length; i++)
			if(ranks[i].equalsIgnoreCase(str))
				return i;
		return -1;
	}
}