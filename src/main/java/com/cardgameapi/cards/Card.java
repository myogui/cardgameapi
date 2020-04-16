package com.cardgameapi.cards;

public class Card {

    private final Suit suit;
    private final Byte value;

    public Card(Suit suit, Byte value){
        if(value < 1 || value > 13){
            throw new IllegalArgumentException("Value must be between 1 and 13.");
        }

        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit(){
        return suit;
    }

    public Byte getValue(){
        return value;
    }
}