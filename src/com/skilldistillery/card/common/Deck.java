package com.skilldistillery.card.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> deck;
	
	public Deck() {
		deck = new ArrayList<>();
		for (Suit suit: Suit.values()) {
			for (Rank rank: Rank.values()) {
				Card card = new Card(rank, suit);
				deck.add(card);
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public int checkDeckSize() {
		return deck.size();
	}
	
	public Card dealCard() {
		return deck.remove(0);
	}
}
