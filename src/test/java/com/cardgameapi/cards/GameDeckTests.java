package com.cardgameapi.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GameDeckTests {

    private final StandardDeckFactory standardDeckFactory = new StandardDeckFactory();

    @Test
    public void addDeckToGameDeck_WhenCalled_DeckAdded(){
        Byte numberOfCards = 104;
        GameDeck actual = standardDeckFactory.getStandardGameDeck();

        actual.addDeckToGameDeck();
        List<Card> actualCards = actual.dealCards(numberOfCards);

        assertTrue(noCardsAreLeft(actual));
        assertEquals(104, actualCards.size());
    }

    private boolean noCardsAreLeft(GameDeck deck){
        return deck.dealCards((byte) 1).isEmpty();
    }

}