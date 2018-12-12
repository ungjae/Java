package blackJack;

/**
 * <p>
 * Title: The Dealer Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a BlackJack Dealer
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author A. Abreu edited by Ung Jae Yun
 */

public class Dealer extends Player{

	/**
	 * constructor extended from super class that takes a name
	 */
	public Dealer(String name) {
		super(name);
	}

	/**
	 * Returns the string representation of the hand
	 * @return the string representation of the hand 
	 */
	public String showHand() {
		String str = "";
		for(int i = 0; i < this.getHand().getCount(); i++)
			str += this.getHand().getCardAt(i).toString() + " ";
		return str;
	}
	/**
	 * hidden version of the hand
	 * @return dealer's hand with the first card hidden
	 */
	public String showHidden() {
		String str = "[  Dealer  | Wins: " + this.getPoints() + " ] ";
		for(int i = 1; i < this.getHand().getCount(); i++)
			str += this.getHand().getCardAt(i).toString() + " ";
		return str;
	}
	
	/**
	 * returns 1 if blackjack 0 if not -1 if bust
	 */
	public int hasBlackJack() {
		int i = 0;
		if (this.hand.dealerEvaluate() == 21)
			i = 1;
		else if (this.hand.dealerEvaluate() > 21) 
			i = -1;
		return i;
	}

}
