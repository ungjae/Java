package project4;

import java.util.LinkedList;
import java.util.Random;
/**
 * <b>Title:</b> Project 4<br>
 * <b>Filename:</b> Player.java<br>
 * <b>Date Written:</b> December 3, 2018<br>
 * <b>Due Date:</b> December 9, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Defines the properties and behaviors of a Go Fish Player
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */
public class Player {
	private String name;
	private Hand hand = new Hand();
	private int points;
	/**
	 * Parameterized constructor
	 * @param n - the name of the player
	 */
	public Player(String n){
		this.name = n;
	}
	/**
	 * Returns the player's name
	 * @return - the player's name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets the name
	 * @param name - the new value for name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Sets the hand
	 * @param hand - hand to set to
	 */
	public void setHand(Hand hand){
		this.hand = hand;
	}

	/**
	 * Returns the number of books the player has
	 * @return the number of books the player has
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Returns the string representation of the player
	 * @return the string representation of the player
	 */
	public String toString(){
		return this.name + " [Books: " + this.points + "]\n" + this.showHand();
	}
	/**
	 * Returns the string representation of the hand
	 * @return the string representation of the hand 
	 */
	public String showHand() {
		String str = "";
		for(int i = 0; i < this.hand.getCount(); i++)
			str += this.hand.getCardAt(i).toString() + " ";
		return str;
	}
	/**
	 * Returns <i>true</i> if the player has a specified rank
	 * @param rank - the rank to search for
	 * @return - Returns <i>true</i> if the player has a specified rank, <i>false</i> otherwise
	 */
	public boolean hasRank(int rank){
		return this.hand.hasRank(rank);
	}
	/**
	 * Adds a card to the hand
	 * @param card - the card to add
	 */
	public void addCard(GoFishCard card){
		this.hand.insertByRank(card);
		int i = this.hand.evaluate();
		points+= i;
	}
	/**
	 * Adds a LinkList of Cards to the hand
	 * @param otherHand - the LinkedList of cards
	 */
	public void addCards(LinkedList<GoFishCard> cards) {
		this.hand.insertHand(cards);
		int i = this.hand.evaluate();
		points+= i;
	}
	/**
	 * returns the card at a specified index in the hand
	 * @param index - the position of the card
	 * @return - the card at that position
	 */
	public GoFishCard getCardAt(int index){
		return this.hand.getCardAt(index);
	}
	 /**
	  * Returns the number of cards the player has
	  * @return Return the number of cards the player has
	  */
	public int getTotalCards() {
		return this.hand.getCount();
	}
	/**
	 * Returns the cards of a specified rank as a LinkedList
	 * @param rank - the rank to search for
	 * @return - the Linked List of cards
	 */
	public LinkedList<GoFishCard> getCards(int rank){
		return this.hand.getCards(rank);
	}
	
}
