package com.cardgameapi.cards;

import com.cardgameapi.game.InvalidArgumentException;

public class Card {

    private final Suit suit;
    private final Byte value;

    public Card(Suit suit, Byte value){
        if(value < 1 || value > 13){
            throw new InvalidArgumentException("value");
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