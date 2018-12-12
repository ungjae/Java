package project4;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * <b>Title:</b> Project 4<br>
 * <b>Filename:</b> GoFish.java<br>
 * <b>Date Written:</b> December 3, 2018<br>
 * <b>Due Date:</b> December 9, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * GoFish game class 
 * includes game logic and methods to use within game
 * 
 * </p>
 *
 * @author Ung Jae Yun
 */

public class GoFish {
	/**
	 * autoplaying computer player
	 */
	Player computer = new Player("Computer");
	/**
	 * user as player
	 */
	Player player;
	/**
	 * array of players
	 */
	Player[] players = {computer, player};
	/**
	 * deck of cards for this game
	 */
	Deck deck;

	/**
	 * default constructor 
	 * gets player name and deals cards from shuffled deck
	 */
	public GoFish() {
		this.getNames();

		this.deck = new Deck();
		this.deck.shuffle();
		
		dealCards();
		
	}
	/**
	 * deals 7 cards to each player
	 */
	private void dealCards() {
		for(int j = 0; j < 7; j++) {
			computer.addCard((GoFishCard) this.deck.deal());
			player.addCard((GoFishCard) this.deck.deal());
		}
	}
	/**
	 * gets name from user and creates a player object 
	 */
	private void getNames() {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is your name? ");
		player = new Player(sc.nextLine());
		
		players[0] = this.computer;
		players[1] = this.player;
	}
	/**
	 * game logic
	 */
	public void playGame() {
		String border = "***************************************************************";
		
		int i = 0;
		
		// play until player's hand, computer's hand or the deck is empty
		while ((computer.getTotalCards() > 0) && (player.getTotalCards() > 0) && (this.deck.getSize() > 0)) {

			System.out.println(border);
			if(i == 0) {
				// i = 0 == computer

				System.out.println(computer);
				this.autoPlay(computer, player);
				
				// ends game if hand is empty
				if(computer.getTotalCards() == 0) {
					System.out.println(computer.getName() + " says \"My hand is empty!\"");
					break;
				}
				System.out.println(computer);
				
				// hands over turn to the other player
				i = 1;
			} else {
				
				System.out.println(player);
				// get input from user through getRank() and play on
				this.manualPlay(player, computer, this.getRank(player));	
				//this.autoPlay(player, computer); // in case of speedy simulation
				
				//ends game if hand is empty
				if(player.getTotalCards() == 0) {
					System.out.println(player.getName() + " says \"My hand is empty!\"");
					break;
				}
				System.out.println(player);
				
				// hands over turn to the other player
				i = 0;
			}
			// checks to see if the other player's hand is empty
			if (players[i].getTotalCards() == 0) {
				System.out.println(border);
				System.out.println(players[i].getName() + " says \"My hand is empty!\"");
			}
		}
			
		
		System.out.println(border);
		
		// display if an empty deck is the reason for stopping
		if(this.deck.isEmpty()) {
			System.out.println("The Deck is empty!\n");
		}
		
		// show both players' hands at the end of game
		this.displayHands();
		System.out.println(border);
		
		// display score
		this.gameResults();
		
		
	}
	/**
	 * outputs the winner or draw
	 */
	public void gameResults() {

		displayScore();
		String win = (computer.getPoints() > player.getPoints() ? "Computer" : player.getName());
		if(computer.getPoints() == player.getPoints()) 
			win = "DRAW!";
		System.out.println("   " + (win == "DRAW!" ? "DRAW!" : win + " won !!"));
	}
	/**
	 * displays scoreboard 
	 */
	private void displayScore() {
		StringBuilder str = new StringBuilder();
		str.append("\n      Scores!\n-------------------\n");
		str.append("  " + String.format("%-10s", computer.getName()));
		str.append("|" + String.format("%3s", computer.getPoints()) + "\n-------------------\n");
		str.append("  " + String.format("%-10s", player.getName()));
		str.append("|" + String.format("%3s", player.getPoints()) + "\n-------------------\n");
		
		System.out.println(str);
	}
	/**
	 * displays computer's hand and player's hand
	 */
	private void displayHands() {
		System.out.println(computer);
		System.out.println(player);
	}
	/**
	 * autopilot for any given player
	 * @param autoPlayer the player you want to auto pilot
	 * @param otherPlayer the player the above player is playing against
	 */
	public void autoPlay(Player autoPlayer, Player otherPlayer) {
		int randIndex = new Random().nextInt(autoPlayer.getTotalCards());
		GoFishCard find = autoPlayer.getCardAt(randIndex);
		
		System.out.println(otherPlayer.getName() + ", do you have any cards with rank: " + find.getRankAsString() + "?");
		this.manualPlay(autoPlayer, otherPlayer, find.getRank());
	}
	/**
	 * player asks the other if s/he has any cards of a particular rank
	 * transfers over all the relevant cards to player if s/he does; else draws a card
	 * @param currentPlayer the player asking for cards
	 * @param otherPlayer the player being asked for cards
	 * @param rank the rank of cards that currentPlayer wants 
	 */
	public void manualPlay(Player currentPlayer, Player otherPlayer, int rank) {
		if(otherPlayer.hasRank(rank)) {
			System.out.println(otherPlayer.getName() + " says \"Yes!!\"");
			LinkedList<GoFishCard> temp = otherPlayer.getCards(rank);
			System.out.println(currentPlayer.getName() + " got " + temp + " from " + otherPlayer.getName());
			currentPlayer.addCards(temp);
		} else {
			System.out.println(otherPlayer.getName() + " says \"No, Go Fish!!\"");
			GoFishCard temp = (GoFishCard) this.deck.deal();
			System.out.println(currentPlayer.getName() + " drew [" + temp + "]");
			currentPlayer.addCard(temp);
		}
	}
	/**
	 * asks user what rank s/he wants to ask the other player for
	 */
	public int getRank(Player player) {
		String rank = "";
		
		
		// verifies that the player has a card with the same rank
		while(true) {
			System.out.print("Computer do you have any cards with rank: ");
			Scanner str = new Scanner(System.in);
			rank = str.nextLine();
			if(player.hasRank(GoFishCard.convertToRank(rank))) {
				break;
			}
			System.out.println("You must choose a rank that you have in your hand. Try again.");
			
		}
		
		return GoFishCard.convertToRank(rank);
	}
}
