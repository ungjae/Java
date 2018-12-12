package project4;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * <b>Title:</b> Project 4<br>
 * <b>Filename:</b> Hand.java<br>
 * <b>Date Written:</b> December 3, 2018<br>
 * <b>Due Date:</b> December 9, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * Defines the properties and behaviors of a GoFish Hand
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */
public class Hand{
	/**
	 * LinkList of GoFish Cards
	 */
	private LinkedList<GoFishCard> hand;
	/**
	 * count of cards in hand
	 */
	private int count;
	/**
	 * Default constructor
	 */
	public Hand(){
		hand = new LinkedList<GoFishCard>();
		
	}
	/**
	 * Returns the number of cards in the hand
	 * @return the number of cards in the hand
	 */
	public int getCount(){
		return this.count;
	}
	/**
	 * Returns the hand as LinkedList of GoFish cards
	 * @return the hand as a LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> getHand() {
		return this.hand;
	}	
	/**
	 * Returns <i>true</i> if this rank is in the hand
	 * @param rank - the rank to search for
	 * @return Returns <i>true</i> if this rank is in the hand,<br> 
	 * <i>false</i> otherwise
	 */
	public boolean hasRank(int rank) {
		ListIterator<GoFishCard> li = hand.listIterator();
		while (li.hasNext()) {
			GoFishCard temp = li.next();
			if (temp.getRank() == rank)
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
	 * @return all of the cards of that rank as a LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> findRank(int rank){
		LinkedList<GoFishCard> rankList = new LinkedList<>();
		ListIterator<GoFishCard> li = hand.listIterator();
		while(li.hasNext()) {
			GoFishCard temp = li.next();
			if(temp.getRank() == rank) {
				rankList.add(new GoFishCard(li.next().getRank(), li.next().getSuit()));
			}
			li.next();
		}
		return rankList;
	}
	/**
	 * Adds a Card to the hand, the hand is sorted by rank
	 * @param card - a GoFish Card
	 */
	public void insertByRank(GoFishCard card){
		this.hand.addLast(card);
		Collections.sort(this.hand);
		this.count++;
		
	}
	/**
	 * Adds a LinkList of Cards to the hand, the hand is sorted by rank
	 * @param otherHand LinkedList of GoFish Cards
	 */
	public void insertHand(Collection<? extends GoFishCard> otherHand) {

		this.hand.addAll(otherHand);
		count += otherHand.size();
		Collections.sort(this.hand);
	}
	/**
	 * removes a collection of cards from this hand
	 * @param otherHand the collection of cards to remove from this hand
	 */
	public void deleteHand(Collection<? extends GoFishCard> otherHand) {
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
	 * Returns 1 if a book (all 4 cards of a particular suit) is 
	 * in the hand and removes the book from the hand
	 * @return the number of books (all 4 cards of a particular suit) in the hand
	 */
	public int evaluate() {
		int bookCount = 0;
		ListIterator<GoFishCard> li = hand.listIterator();
		LinkedList<GoFishCard> book = new LinkedList<>();
		int rank = hand.getFirst().getRank();
		// since the list is sorted, count consecutive ranks until 4 in a row
		while(li.hasNext()) {
			GoFishCard temp = li.next();
			if(temp.getRank() == rank) {
				bookCount++;
				book.add(temp);
			} else {
				rank = temp.getRank();
				bookCount = 1;
				book.clear();
				book.add(temp);
			}
			if(bookCount == 4) {
				this.deleteHand(book);
				
				// design tweak since 10 is the only card rank with two characters
				if(rank == 8) {
					System.out.println("+------------------------------+\n| SCORE!! " + book + " |\n+------------------------------+");
					return 1;
				}
					
				System.out.println("+--------------------------+\n| SCORE!! " + book + " |\n+--------------------------+");
				return 1;
			}
		}
		return 0;
	}
	/**
	 * Counts the number of cards of a particular rank in the hand
	 * @param rank - the rank to count
	 * @return the number of cards of that rank
	 */
	public int countRank(int rank){
		ListIterator<GoFishCard> li = hand.listIterator();
		int count = 0;
		while(li.hasNext()) {
			GoFishCard temp = li.next();
			if(temp.getRank() == rank)
				count++;
		}
		return count;
	}
	/**
	 * Returns the card at the specified position in this list.
	 * @param index the index of the list
	 * @return GoFishCard at the specified index
	 */
	public GoFishCard getCardAt(int index){
		return hand.get(index);
	}
	/**
	 * Returns a list of cards of a specified rank and removes from player
	 * @param rank - the rank to search for 
	 * @return the cards as LinkedList of GoFish Cards
	 */
	public LinkedList<GoFishCard> getCards(int rank) {
		ListIterator<GoFishCard> li = hand.listIterator();
		LinkedList<GoFishCard> rankList = new LinkedList<>();
		while(li.hasNext()) {
			GoFishCard temp = li.next();
			if(temp.getRank() == rank) {
				rankList.add(temp);
			}
		}
		this.deleteHand(rankList);
		return rankList;
	}
}


