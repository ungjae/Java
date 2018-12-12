package blackJack;

import java.util.LinkedList;
/**
 * <p>
 * Title: The Player Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a BlackJack Player.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author A. Abreu edited by Ung Jae Yun
 */
public class Player {
	private String name;
	protected Hand hand = new Hand();
	private int points;
	public boolean blackJack = false;
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
	 * Returns all of the cards of the specified rank as a LinkedList
	 * @param rank - the rank to search for
	 * @return - the cards of the specified rank as a LinkedList
	 */
	public LinkedList<BlackJackCard> getCards(int rank) {
		return this.hand.getCards(rank);
	}
	/**
	 * Sets the hand
	 * @param hand - hand to set to
	 */
	public void setHand(Hand hand){
		this.hand = hand;
	}
	/**
	 * getter for the hand
	 * @return player's hand
	 */
	public Hand getHand() {
		return this.hand;
	}
	/**
	 * Returns the number of books the player has
	 * @return the number of books the player has
	 */
	public int getPoints() {
		return this.points;
	}
	/**
	 * adds to the score 
	 * @param score value of score 
	 */
	public void addPoints(int score) {
		this.points += score;
	}
	/**
	 * Returns the string representation of the player
	 * @return the string representation of the player
	 */
	public String toString(){
		return "[  " + String.format("%-8s", this.name) + "| Wins: " + this.getPoints() + " ] " + this.showHand();
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
	public boolean hasRank(String rank){
		return this.hand.hasRank(rank);
	}
	/**
	 * Adds a card to the hand
	 * @param card - the card to add
	 */
	public void addCard(BlackJackCard card){
		this.hand.insert(card);
		if (this.hand.evaluate() == 21) 
			this.blackJack = true;
	}
	/**
	 * returns 1 if blackjack 0 if not -1 if bust
	 */
	public int hasBlackJack() {
		int i = 0;
		if (this.hand.evaluate() == 21)
			i = 1;
		else if (this.hand.evaluate() > 21) 
			i = -1;
		return i;
	}
	/**
	 * Adds a LinkList of Cards to the hand
	 * @param otherHand - the LinkedList of cards
	 */
	public void addCards(LinkedList<BlackJackCard> otherHand) {
		this.hand.insertHand(otherHand);
	}
	/**
	 * returns the card at a specified index in the hand
	 * @param index - the position of the card
	 * @return - the card at that position
	 */
	public BlackJackCard getCardAt(int index){
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
	 * Returns the cards of a specified rank as a Linkedlist
	 * @param rank - the rank to search for
	 * @return - the Linked List of cards
	 */
	public LinkedList<BlackJackCard> getCard(int rank){
		return this.hand.getCards(rank);
	}
	
	public int compareTo(Player other) {
		return this.getPoints() - other.getPoints();
	}
	public void emptyHand() {
		this.hand = new Hand();
		this.blackJack = false;
	}
}
