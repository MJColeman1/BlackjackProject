package com.skilldistillery.projects.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.card.common.Card;
import com.skilldistillery.card.common.Deck;

public class Dealer {
	private Deck deck = new Deck();
	Hand hand;
	private String name;
	
	public Dealer() {
		hand = new Hand();
	}
	
	public Dealer(String name) {
		this();
		this.name = name;
	}
	
	public Card dealCard() {
			return deck.dealCard();
	}
	
	public List<Card> dealFirstHand() {
		List<Card> cards = new ArrayList<>();
		cards.add(deck.dealCard());
		cards.add(deck.dealCard());
		return cards;
	}

	public Deck getDeck() {
		return deck;
	}
	
	public Hand getHand() {
		return hand;
	}
	
}


