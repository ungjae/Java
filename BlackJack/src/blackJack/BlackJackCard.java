package blackJack;

import blackJack.Card;

/**
 * <p>
 * Title: The BlackJackCard Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a BlackJack Card
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author A. Abreu edited by Ung Jae Yun
 */

public class BlackJackCard extends Card implements Comparable<BlackJackCard>{
	
	/**
	 * value of a card when playing Black Jack
	 */
	private int value;
	
	/**
	 * default constructor
	 */
	public BlackJackCard() {
		super();
		blackJackValue();
	}
	/**
	 * constructor that takes an integer value
	 */
	public BlackJackCard(int n) {
		super(n);
		blackJackValue();
	}
	/**
	 * constructor that takes a rank and suite
	 */
	public BlackJackCard(int r, int s) {
		super(r, s);
		blackJackValue();
	}
	/**
	 * constructor that takes a card and creates a BlackJackCard from it
	 * 
	 */
	public BlackJackCard(Card card) {
		super(card.getRank(), card.getSuit());
		blackJackValue();
	}
	/**
	 * assigns values to cards for playing blackJack
	 */
	private void blackJackValue() {
		if (this.getRank() < 9) {
			this.value = Integer.parseInt(this.getRankAsString());
		} else if (this.getRank() < 12) {
			this.value = 10;
		} else {
			this.value = 11;
		}
	}
	/**
	 * returns <i>int</i> the difference between to BlackJackCards in terms of value
	 */
	@Override
	public int compareTo(BlackJackCard otherCard) {
		return this.value - otherCard.value;
	}
	/**
	 * getter for the card's rank extended from super class
	 */
	public int getRank() {
		return this.rank;
	}
	/**
	 * getter for this card's BlackJack value
	 */
	public int getValue() {
		return this.value;
	}

}
