package com.skilldistillery.projects.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.card.common.Card;
import com.skilldistillery.card.common.Deck;

public class BlackjackApp {

	private Deck deck = new Deck();

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		Scanner kb = new Scanner(System.in);
		int totalValue = 0;
		int input = 0;
		while (true) {
			try {
				input = kb.nextInt();
			} catch (InputMismatchException e) {
				System.out.print("That's not a number, please enter a valid number: ");
				input = kb.nextInt();
			}
			if (input > 52) {
				try {
					input = kb.nextInt();
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Error. Cannot draw more than 52 cards. Try again.");
					System.out.print("How many cards do you want to draw? ");
				}
			}
			app.deck.shuffle();
			for (int i = 0; i < input; i++) {
				Card dealt = app.deck.dealCard();
				System.out.println(dealt);
				totalValue = totalValue + dealt.getRank();
			}
			System.out.println("The total value of your cards is " + totalValue);
		}
	}

}
