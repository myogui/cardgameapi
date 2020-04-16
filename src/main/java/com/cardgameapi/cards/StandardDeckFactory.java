package com.cardgameapi.cards;

import java.util.ArrayList;
import java.util.List;

public class StandardDeckFactory {

    public Deck getStandardDeck(){
        return new Deck(getStandardCardsForDeck());
    }
    
    public GameDeck getStandardGameDeck(){
        return new GameDeck(getStandardCardsForDeck());
    }

    private List<Card> getStandardCardsForDeck(){
        List<Card> cardsForDeck = new ArrayList<Card>(); 

        for (Suit suit : Suit.values()) { 
            for(Byte value = 1; value <= 13; value++){
                Card newCard = new Card(suit, value);
                cardsForDeck.add(newCard);
            }
        }

        return cardsForDeck;
    }

}