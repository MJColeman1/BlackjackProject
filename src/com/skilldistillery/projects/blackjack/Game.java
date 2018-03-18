package com.skilldistillery.projects.blackjack;

import java.util.Scanner;

public class Game {

	private Dealer dealer = new Dealer();
	private Player player;
	private int playerCardValue = 0;
	private int dealerCardValue = 0;
	private boolean flag = true;
	private int choice = 0;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Game game = new Game();
		game.welcomeMessage();
		game.gameRules();
		System.out.print("Please enter your name: ");
		game.player = new Player(scanner.next());
		System.out.println("It's time to play " + game.player.getName() + "!\n");
		game.run();

	}

	private void welcomeMessage() {
		System.out.println("*********************************************");
		System.out.println("Welcome to the game of Blackjack, the most widely played casino banking game in the world.\n");
		System.out.println("May the odds forever be in your favor.");
		System.out.println("*********************************************");
	}

	private void gameRules() {
		System.out.println("The rules of the game are simple: ");
		System.out.println("The objective is to beat the dealer in one of the following ways: ");
		System.out.println("1. Get 21 points on the first two cards dealt to you without a dealer blackjack,");
		System.out.println("2. Reach a final score higher than the dealer without exceeding 21,");
		System.out.println("3. Let the dealer draw additional cards until his hand exceeds 21.\n");
		System.out.println(
				"You and the dealer will both be dealt two cards. You will go first, deciding to either \"hit\" or \"stay\""
						+ " based on the value of your cards.\nYou can hit as many times as you want, as long as your total card value doesn't exceed 21.");
		System.out.println(
				"Once you decide to stay, the dealer will take his turn. If you stay at any value below 21, and the dealer's"
						+ " hand exceeds that value while staying at or below 21, the dealer wins the round.");
		System.out.println("Aces are valued at 11, so if you're dealt two Aces, you lose!\n");
		System.out.println(
				"Keep in mind, you will only be able to see one of the dealer's cards during your turn, so choose wisely!\n");
	}

	private void run() {
		player.hand.clearHand();
		dealer.hand.clearHand();
		playRound();
		playerBlackjack();
		System.out.print("Select (1) to HIT or (2) to STAY: ");
		do {
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("The dealer deals another card to you.");
				playerHitAndResults();
				while (player.getHand().getValueOfHand() <= 21) {
					System.out.print("Select (1) to HIT or (2) to STAY: ");
					choice = scanner.nextInt();
					switch (choice) {
					case 1:
						System.out.println("The dealer deals another card to you.");
						playerBlackjack();
						playerHitAndResults();
						break;
					case 2:
						System.out.println();
						System.out.println("The dealer is showing: " + dealer.getHand());
						dealerDecision();
						break;
					}
				}
				if (player.checkIfBust() == true) {
					System.out.println("You busted! The dealer won this round!");
					player.hand.clearHand();
					dealer.hand.clearHand();
					playAgain();
				}
			case 2:
				System.out.println();
				System.out.println("You chose to STAY. The dealer will take his turn.");
				System.out.println("The dealer shows: " + dealer.getHand());
				dealerDecision();
			}

		} while (player.getHand().getValueOfHand() < 22 && dealer.getHand().getValueOfHand() < 22);
	}

	private void playRound() {
		System.out.println("The dealer deals two cards to you.");
		dealer.getDeck().shuffle();
		player.getHand().addCard(dealer.dealCard());
		dealer.getHand().addCard(dealer.dealCard());
		player.getHand().addCard(dealer.dealCard());
		dealer.getHand().addCard(dealer.dealCard());
		System.out.println("You're now showing " + player.getHand());
		playerCardValue = player.getHand().getValueOfHand();
		System.out.println("The current value of your hand is " + playerCardValue);
		System.out.println("*********************************************");
		dealerCardValue = dealer.getHand().getValueOfHand();
		System.out.println("The dealer is showing " + dealer.getHand().getDealerFirstCard());
	}

	private void playerHitAndResults() {
		player.getHand().addCard(dealer.dealCard());
		System.out.println("You're now showing " + player.getHand());
		playerCardValue = player.getHand().getValueOfHand();
		System.out.println("The current value of your hand is " + playerCardValue);
		if (player.hand.getValueOfHand() > 21) {

		}
	}

	private void dealerDecision() {
		if (dealer.getHand().getValueOfHand() == 21 && player.getHand().getValueOfHand() == 21) {
			System.out.println("This round ends in a tie.");
			playAgain();
		}
		if (dealer.getHand().getValueOfHand() == 21 && player.getHand().getValueOfHand() < 21) {
			System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
			System.out.println("You chose to stay and the dealer has blackjack. You lose this round!");
			playAgain();

		}
		if (dealer.getHand().getValueOfHand() > player.getHand().getValueOfHand()) {
			System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
			dealerWins();
			playAgain();
		}
		while (player.getHand().getValueOfHand() <= 21
				&& player.getHand().getValueOfHand() > dealer.getHand().getValueOfHand()) {
			System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
			System.out.println("The dealer draws another card from the deck.");
			dealer.getHand().addCard(dealer.dealCard());
			System.out.println("The dealer is now showing: " + dealer.getHand());
			System.out.println();
			if (dealer.getHand().getValueOfHand() > 21) {
				System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
				System.out.println("The dealer has busted! You win this round!");
				playAgain();
				break;
			}
			if (dealer.getHand().getValueOfHand() > player.getHand().getValueOfHand()) {
				dealerWins();
				playAgain();
				break;
			}

		}
		while (dealer.getHand().getValueOfHand() <= 17
				&& dealer.getHand().getValueOfHand() < player.getHand().getValueOfHand()) {
			System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
			System.out.println("The dealer draws another card from the deck.");
			dealer.getHand().addCard(dealer.dealCard());
			System.out.println("The dealer is now showing: " + dealer.getHand());
			System.out.println();
			if (dealer.getHand().getValueOfHand() > player.getHand().getValueOfHand()) {
				dealerWins();
				playAgain();
				break;
			}
			else if (dealer.getHand().getValueOfHand() == 21 && player.getHand().getValueOfHand() < 21) {
				System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
				System.out.println("You chose to stay and the dealer has blackjack. You lose this round!");
				playAgain();
			}

			if (dealer.getHand().getValueOfHand() > 21) {
				System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
				System.out.println("The dealer has busted! You win this round!");
				playAgain();
				break;
			}
		}
		while (dealer.getHand().getValueOfHand() == player.getHand().getValueOfHand()
				&& dealer.getHand().getValueOfHand() < 21) {
			System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
			System.out.println("The dealer draws another card from the deck.");
			dealer.getHand().addCard(dealer.dealCard());
			System.out.println("The dealer is now showing: " + dealer.getHand());
			System.out.println();

			if (dealer.getHand().getValueOfHand() > 21) {
				System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
				System.out.println("The dealer has busted! You win this round!");
				playAgain();
				break;
			} 
			else if (dealer.getHand().getValueOfHand() == 21 && player.getHand().getValueOfHand() < 21) {
				System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
				System.out.println("You chose to stay and the dealer has blackjack. You lose this round!");
				playAgain();
			}
			else if (dealer.getHand().getValueOfHand() > player.getHand().getValueOfHand()
					&& dealer.getHand().getValueOfHand() < 21) {
				dealerWins();
				playAgain();
				break;
			}

		}
	}

	private void dealerWins() {
		System.out.println("The current value of the dealer's hand is " + dealer.getHand().getValueOfHand());
		System.out.println("You chose to STAY and the dealer has more points than you.");
		System.out.println("You lost this round!");

	}

	private void playAgain() {
		System.out.print("Do you want to play another round? Select (1) to play or (2) to quit: ");
		choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Here we go again " + player.getName() + "!\n");
			run();
			break;
		case 2:
			System.out.println("You are now exiting the game. Thanks for playing!");
			System.exit(0);
			break;
		}
	}

	private void playerBlackjack() {
		if (player.getHand().getValueOfHand() == 21) {
			System.out.println("The current value of your cards is 21. Make sure you don't HIT!");
		}
	}

	private void roundEndsInTie() {
		if (dealer.getHand().getValueOfHand() == 21 && player.getHand().getValueOfHand() == 21) {
			System.out.println("This round ends in a tie.");
			playAgain();
		}
	}
}
