package com.cardgameapi.cards;

public enum Suit {

    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private String value;

    Suit(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
