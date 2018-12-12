package blackJack;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/**
 * <p>
 * Title: The Hand Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a BlackJack Hand.
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author A. Abreu edited by Ung Jae Yun
 */
public class Hand{
	/**
	 * LinkList of BlackJack Cards
	 */
	private LinkedList<BlackJackCard> hand = new LinkedList<>();
	/**
	 * count of cards in hand
	 */
	private int count;
	/**
	 * Default constructor
	 */
	public Hand(){
		this.hand = new LinkedList<>();
		
	}
	/**
	 * Constructs hand dealt from given deck
	 * @param deck Deck object to deal cards from
	 */
	public Hand(Deck deck) {
		for(int i = count; i > 0; i--) {
			Card temp = deck.deal();
			this.hand.add(new BlackJackCard(temp));
		}
	}
	/**
	 * Returns the number of cards in the hand
	 * @return the number of cards in the hand
	 */
	public int getCount(){
		return this.count;
	}
	/**
	 * Returns the hand as LinkedList of BlackJack cards
	 * @return the hand as a LinkedList of BlackJack Cards
	 */
	public LinkedList<BlackJackCard> getHand() {
		return this.hand;
	}	
	/**
	 * Returns <i>true</i> if this rank is in the hand
	 * @param rank - the rank to search for
	 * @return Returns <i>true</i> if this rank is in the hand,<br> 
	 * <i>false</i> otherwise
	 */
	public boolean hasRank(String rank) {
		ListIterator<BlackJackCard> li = hand.listIterator();
		while (li.hasNext()) {
			Card temp = li.next();
			if (temp.getRankAsString().equalsIgnoreCase(rank))
				return true;
		}
		return false;
	}
	/**
	 * Returns a string representation of the hand
	 */
	public String toString(){
		return hand.toString();
	}
	/**
	 * Finds and returns all cards of the specified rank
	 * @param rank - the rank to search for
	 * @return all of the cards of that rank as a LinkedList of BlackJack Cards
	 */
	public LinkedList<Card> findRank(int rank){
		LinkedList<Card> rankList = new LinkedList<>();
		ListIterator<BlackJackCard> li = hand.listIterator();
		while(li.hasNext()) {
			if(li.next().getRank() == rank) {
				rankList.add(new Card(li.next().getRank(), li.next().getSuit()));
			}
			li.next();
		}
		return rankList;
	}
	/**
	 * Adds a Card to the hand, the hand is sorted by rank
	 * @param card - a BlackJack Card
	 */
	public void insertByRank(BlackJackCard card){
		this.hand.add(card);
		Collections.sort(this.hand);
		this.count++;
		
	}
	
	public void insert(BlackJackCard card) {
		this.hand.add(card);
		this.count++;
	}
	/**
	 * Adds a LinkList of Cards to the hand, the hand is sorted by rank
	 * @param otherHand LinkedList of BlackJack Cards
	 */
	public void insertHand(Collection<? extends BlackJackCard> otherHand) {

		this.hand.addAll(otherHand);
		count += otherHand.size();
		Collections.sort(this.hand);
	}
	public void deleteHand(Collection<? extends BlackJackCard> otherHand) {
		this.hand.removeAll(otherHand);
		count -= otherHand.size();
		Collections.sort(this.hand);
		
	}
	/**
	 * Determines if the hand is empty
	 * @return - Returns <i>true</i> if the hand is empty, <i>false</i> otherwise
	 */
	public boolean isEmpty() {
		if(this.hand.size() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * Returns the value of current hand
	 * @return the total of card values considering A as either 1 or 11 conditionally
	 */
	public int evaluate() {
		int total = 0;
		int aCount = 0;
		for(int i = 0; i < this.count; i++) {
			total += this.hand.get(i).getValue();
			if(this.hand.get(i).getRank() == 12)
				aCount++;
		}
		if (total > 21 && this.hasRank("a")) {
			if(aCount > 1 && 21-total >= 0 && 21-total < 21-total-10)
				return total;
			return total - 10*aCount;
		}
		return total;
	}
	/**
	 * Always considers A as 11 for players' advantage
	 * @return the total value of the dealer's hand
	 */
	public int dealerEvaluate() {
		int total = 0;
		for(int i = 0; i < this.count; i++) {
			total += this.hand.get(i).getValue();
		}
		return total;
	}
	/**
	 * Returns the card at the specified position in this list.
	 * @param index the index of the list
	 * @return Card at the specified index
	 */
	public BlackJackCard getCardAt(int index){
		return hand.get(index);
	}
	/**
	 * Returns a list of cards of a specified rank
	 * @param rank - the rank to search for 
	 * @return the cards as LinkedList of Black Jack Cards
	 */
	public LinkedList<BlackJackCard> getCards(int rank) {
		ListIterator<BlackJackCard> li = hand.listIterator();
		LinkedList<BlackJackCard> rankList = new LinkedList<>();
		while(li.hasNext()) {
			BlackJackCard temp = li.next();
			if(temp.getRank() == rank) {
				rankList.add(temp);
			}
		}
		return rankList;
	}
}


