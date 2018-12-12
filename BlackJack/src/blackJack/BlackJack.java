package blackJack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import blackJack.Deck;
import blackJack.Player;
import blackJack.Hand;

/**
 * <p>
 * Title: The BlackJack Class
 * </p>
 * 
 * <p>
 * Description: Defines the properties and behaviors of a BlackJack Game
 * (contains the game logic)
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * 
 * @author A. Abreu edited by Ung Jae Yun
 */

public class BlackJack {
	/**
	 * array of players in the game
	 */
	Player[] players;
	/**
	 * number of players in the game
	 */
	int numPlayers;
	/**
	 * deck of cards to deal from
	 */
	Deck deck;
	/**
	 * array of each player's hand's scores
	 */
	int[] points;
	/**
	 * int used for pushgame
	 */
	int i=1;

	/**
	 * default constructor
	 */
	public BlackJack() {
		this.getInfo();
		this.deck = new Deck();
		this.deck.shuffle();
		
		dealCards();
		
	}
	/**
	 * overloaded constructor that makes a new blackjack class with given array of players
	 * @param players the array of players to play the game
	 */
	public BlackJack(Player[] players) {
		this.players = players;
		this.deck = new Deck();
		this.deck.shuffle();
		this.numPlayers = players.length;
		this.points = new int[numPlayers];
		dealCards();
		//automatically calls pushgame since that is this constructor's main purpose
		pushGame();
	}
	/**
	 * deals 2 cards to each player
	 */
	private void dealCards() {
		for (int i = 0; i < numPlayers; i++) {
			players[i].addCard(new BlackJackCard (this.deck.deal()));
			players[i].addCard(new BlackJackCard (this.deck.deal()));
			
		}
	}
	/**
	 * retrieves information on number of players and their names
	 */
	private void getInfo() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.print("How many players are there? ");
			    numPlayers = Integer.parseInt(sc.nextLine());
			    if (numPlayers <= 0 || numPlayers > 26)
			    		System.out.println("Invalid amount of players");
			    else
			    	break;
			} catch (NumberFormatException e) {
				System.out.println("Input is not a valid integer");
				System.out.print("How many players are there? ");
				numPlayers = Integer.parseInt(sc.nextLine());
			}
		}
		
		numPlayers++;
		points = new int[numPlayers];
		players = new Player[numPlayers];
		players[0] = new Dealer("Dealer");
		
		for(int i = 1; i < numPlayers; i++) {
			System.out.print("What is the name of Player " + i + " ? ");
			Scanner nameScan = new Scanner(System.in);
			players[i] = new Player(nameScan.nextLine());
		}
	}
	/**
	 * actual gameplay
	 * asks the user if s/he wants to continue after every round
	 */
	public void playGame() {
		String border = "***************************************************************";
		int i = 1;
		showDeal();
		if(blackCheck())
			i = numPlayers+1;
		boolean flag = false;
		
		
		while(this.deck.getSize() > numPlayers*2) {
			System.out.println(border);
			
			// asks each player their move
			if (i > 0 && i < numPlayers) {
				askHit(i);
				// add points if player has blackjack
				if(players[i].hasBlackJack() > 0) {
					System.out.println(players[i].getName() + " has BLACKJACK!!!!!");
					flag = true;
					players[i].addPoints(1);
				}
			} else if(i == numPlayers) {
				// dealer goes last after all the players
				System.out.println(players[0]);
				dealerAuto((Dealer) players[0]);
				
			}
			i ++;
			
			// after dealer is done playing, figure out who won
			if (i > numPlayers) {
				
				roundUp();
				
				// ask user if s/he wants to play more
				if(!askPlay())
					return;
				newRound();
				showDeal();
				i = 1;
				
				// if the new dealt hand contains a blackjack, come back around to this if statement
				if (blackCheck())
					i = numPlayers + 1;
				
			}
			
			// makes a new deal/round if a blackjack is found in middle of play
			if (flag) {
				
				newRound();
				if(!askPlay())
					return;
				showDeal();
				
				i = 1;
				if (blackCheck())
					i = numPlayers + 1;
				flag = false;
			}
			
			
			
		}
		return;
	}
	
	/**
	 * creates a new round with new deck and hands
	 */
	private void newRound() {
		this.deck = new Deck();
		this.deck.shuffle();
		for (int i = 0; i < numPlayers; i++) 
			players[i].emptyHand();
			
		dealCards();

	}
	/**
	 * asks the user if s/he wants to play again
	 * @return <i>true</i> if s/he wants to play again, else <i>false</i>
	 */
	private boolean askPlay() {
		String border = "***************************************************************";
		System.out.print(border + "\nAnother Round? ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		while(!str.equalsIgnoreCase("y") && !str.equalsIgnoreCase("n") && !str.equalsIgnoreCase("yes") && !str.equalsIgnoreCase("no")) {
			System.out.print("Not a valid response. Would you like to continue? ");
			str = sc.nextLine();
		}
		System.out.println(border);
		boolean play = (str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes") ? true : false);
		return play;
	}
	/**
	 * game logic for when pushing
	 */
	public void pushGame() {
		System.out.println("PUSH GAME!");
		String border = "***************************************************************";
		
		
		newRound();

		showDeal();
		if(blackCheck())
			roundUp();
		
		while(this.deck.getSize() > numPlayers*2) {
			System.out.println(border);
			if(this.i == 0) {
				System.out.println(players[0]);
				dealerAuto((Dealer) players[0]);
				roundUp();
				return;

			} else if(this.i==-1) {
				return;
			} else if(this.i == -2) {
				return;
			} else {
			
			
				askHit(this.i);
			}
			if(this.i != -1) {
				this.i = (++this.i) % numPlayers;
			}
			
		}
		return;
	}
		
	
	/**
	 * displays all the cards that are dealt with one dealer's card hidden
 	 */
	private void showDeal() {
		System.out.println(((Dealer) players[0]).showHidden());
		for (int j = 1; j < numPlayers; j++) {
			System.out.println(players[j]);
		}

	}
	/**
	 * deals a card to player
	 * @param player the player to deal the card to
	 */
	private void hitPlayer(Player player) {
		player.addCard(new BlackJackCard (this.deck.deal()));
		System.out.println(player);
	}
	/**
	 * autoPilot for dealer
	 * @param dealer the dealer object
	 */
	private void dealerAuto(Dealer dealer) {
		Arrays.sort(points);
		int maxScore = points[points.length-1];
		int minScore = points[0];
		while (dealer.getHand().dealerEvaluate() < 17) {
			dealer.addCard(new BlackJackCard (this.deck.deal()));
			System.out.println("Dealer hit!\n" + dealer);
		} 
		if (dealer.getHand().dealerEvaluate() >= 17 && dealer.getHand().dealerEvaluate() < 21) {
			int choice = (int) (Math.random() * 2);
			if (dealer.getHand().dealerEvaluate() > maxScore) {
				System.out.println("Dealer stands");
			} else if (dealer.getHand().dealerEvaluate() < minScore) {
				dealer.addCard(new BlackJackCard (this.deck.deal()));
				System.out.println("Dealer hit!\n" + dealer);
			} else if (choice % 2 == 0 && dealer.getHand().dealerEvaluate() < maxScore) {
			
				dealer.addCard(new BlackJackCard (this.deck.deal()));
				System.out.println("Dealer hit!\n" + dealer);
			} else {
				System.out.println("Dealer stands");
			}
		} 
		if (dealer.getHand().dealerEvaluate() > 21) {
			System.out.println("Dealer bust!");
		}
	}
	/**
	 * checks to see if there's a blackjack in the hands dealt
	 * @return <i>true</i> if blackJack found, <i>false</i> if not
	 */
	private boolean blackCheck() {
		for(int i = 1; i < numPlayers; i++) {
			if (players[i].getHand().evaluate() == 21) {
				return true;
			}
		}
		return false;
	}
	/**
	 * sums up the end of the round and shows who won
	 */
	private void roundUp() {
		int score = players[0].getHand().dealerEvaluate();
		int winner = 0;
		int push = 0;
		Player[] pushPlayers;
		points[0] = (score > 21 ? 0 : score);
		for (int k = 1; k < numPlayers; k++) {
			score = players[k].getHand().evaluate();
			points[k] = (score > 21 ? 0 : score);
			if (points[k] == 21) {
				if(players[0].blackJack) {
					push = 2;
					pushPlayers = new Player[push];
					pushPlayers[0] = players[0];
					pushPlayers[1] = players[k];
					new BlackJack(pushPlayers);
				}
				System.out.println(players[k].getName() + " has BLACK JACK!!!!!");
				players[k].addPoints(1);
				return;
			}		
			
		}
		for (int l = 1; l < numPlayers; l++) {
			if (points[l] > points[0] && players[l].hasBlackJack() == 0) {
				players[l].addPoints(1);
				winner++;
				System.out.println(players[l].getName() + " wins!");
			} else if (points[l] == points[0] && points[l] != 0) {
				push++;
			}
		}
		if (push > 0 && winner == 0) {
			push++;
			pushPlayers = new Player[push];
			pushPlayers[0] = players[0];
			for (int i = 1, j = 1; i < push && j < players.length; j++) {
				if(players[j].getHand().evaluate() == points[0] && points[0] != 0) {
					pushPlayers[i] = players[j];
					i++;
				}
				
			}
			new BlackJack(pushPlayers);
			return;
		}
		if (winner == 0) {
			if(points[0] != 0) {
				System.out.println("Dealer wins!");
				players[0].addPoints(1);
			} else {
				System.out.println("Nobody wins :(");
			}
		} 
			
	}
	/**
	 * asks the player if they want to hit
	 * @param i the index of the player in array players[]
	 */
	private void askHit(int i) {
		Scanner sc = new Scanner(System.in);
		String ask = "";
		while(!ask.equalsIgnoreCase("yes") && !ask.equalsIgnoreCase("y") && !ask.equalsIgnoreCase("no") && !ask.equalsIgnoreCase("n")) {
			System.out.print(players[i].getName() + ", do you want to hit (Y/N)? ");
			ask = sc.nextLine();
		}
		boolean hit = (ask.equalsIgnoreCase("yes") || ask.equalsIgnoreCase("y") ? true : false);
		while (hit) {
		
			hitPlayer(players[i]);
			
			if (players[i].hasBlackJack() > 0) {
				return;
			
			} else if (players[i].hasBlackJack() < 0) {
				System.out.println(players[i].getName() + " has bust.");
				return;
			}
			ask = "";
			while(!ask.equalsIgnoreCase("yes") && !ask.equalsIgnoreCase("y") && !ask.equalsIgnoreCase("no") && !ask.equalsIgnoreCase("n")) {
				System.out.print(players[i].getName() + ", do you want to hit (Y/N)? ");
				ask = sc.nextLine();
			}
			
			hit = (ask.equalsIgnoreCase("yes") || ask.equalsIgnoreCase("y") ? true : false);
		}
		if (!hit) {
			System.out.println(players[i]);
		}
	}
		
}
