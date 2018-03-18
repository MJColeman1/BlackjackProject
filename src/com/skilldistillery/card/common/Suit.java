package com.skilldistillery.card.common;

public enum Suit {

	Hearts("Hearts"), Spades("Spades"), Clubs("Clubs"), Diamonds("Diamonds");
	private String name;

	Suit(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}