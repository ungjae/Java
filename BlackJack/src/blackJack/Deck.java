package blackJack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * <p>
 * Title: The Deck Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a Deck of Cards.
 * </p>
 * 
 * @author A. Abreu edited by Ung Jae Yun
 *
 */
public class Deck {
	
	/**
	 * ArrayList of Cards
	 */
	private ArrayList<Card> cards = new ArrayList<Card>(52);
	
	public Deck() {
		cards.ensureCapacity(52);
		initialize();
	}
	/**
	 * Generates 52 Cards and stores them in the ArrayList
	 */
	public void initialize(){
		for(int i = 0; i < 52; i++)	{
			cards.add(new Card(i));
		}		
	}
	
	/**
	 * Returns a string representation of the Deck
	 * @return String representation of the list of Cards in the Deck
	 */
	public String toString(){
		return "No. of cards: " + cards.size() +"\n" + cards.toString();
	}
	/**
	 * Shuffles the Deck of Cards
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}	

	/**
	 * Returns a card from the Deck
	 * @return returns a Card from the Deck
	 */
	public Card deal() {
		if(!cards.isEmpty())
			return cards.remove(0);
		return null;
	}
	
	/**
	 * Returns <i>true</i> if the Deck is empty
	 * @return Returns <i>true</i> if the Deck is empty, <i>false</i> otherwise
	 */
	public boolean isEmpty(){
		return cards.isEmpty();
	}
	/**
	 * @return size of the deck
	 */
	public int getSize() {
		return this.cards.size();
	}
	/**
	 * removes a collection of cards
	 */
	public void removeAll(Hand Hand) {
		for (int i = 0; i < Hand.getCount(); i++)
			this.cards.remove(Hand.getCardAt(i));
	}
	
}
