package com.skilldistillery.projects.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.card.common.Card;

public class Hand {

	public List<Card> cards = new ArrayList<>();
	
	public Hand() {
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void clearHand() {
		cards.clear();
	}
	
	public void addCards(List<Card> dealt) {
		for (Card card : dealt) {
			cards.add(card);
		}
	}
	
	public List<Card> getCardsInHand() {
		return cards;
	}
	
	public int getValueOfHand() {
		int counter = 0;
		for (Card card : cards) {
			counter += card.getValue();
		}
		return counter;
	}

	public List<Card> getCard() {
		return cards;
	}

	public void setCard(List<Card> card) {
		this.cards = card;
	}
	
	public Card getDealerFirstCard() {
		return cards.get(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hand other = (Hand) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(cards);
		return builder.toString();
	}
	
	
	
}
