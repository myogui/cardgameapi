package com.cardgameapi.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StandardDeckFactoryTests {

    @Test
    public void getDeck_WhenCalled_ReturnsCorrectStandardDeck(){
        StandardDeckFactory deckFactory = new StandardDeckFactory();
        List<Card> expectedCards = getExpectedCards();
        Byte numberOfCards = 52;

        Deck actual = deckFactory.getStandardDeck();
        List<Card> actualCards = actual.dealCards(numberOfCards);
        
        assertEquals(0, actual.dealCards((byte) 1).size());
        assertEquals(expectedCards.size(), actualCards.size());
        assertAllCardsArePresent(expectedCards, actualCards);
    }

    @Test
    public void getGameDeck_WhenCalled_ReturnsCorrectStandardDeck(){
        StandardDeckFactory deckFactory = new StandardDeckFactory();
        List<Card> expectedCards = getExpectedCards();
        Byte numberOfCards = 52;

        GameDeck actual = deckFactory.getStandardGameDeck();
        List<Card> actualCards = actual.dealCards(numberOfCards);
        
        assertEquals(0, actual.dealCards((byte) 1).size());
        assertEquals(expectedCards.size(), actualCards.size());
        assertAllCardsArePresent(expectedCards, actualCards);
    }

    private List<Card> getExpectedCards(){
        List<Card> expectedCards = new ArrayList<Card>();        

        for (Suit suit : Suit.values()) { 
            for(Byte value = 1; value <= 13; value++){
                Card newCard = new Card(suit, value);
                expectedCards.add(newCard);
            }
        }

        return expectedCards;
    }

    private void assertAllCardsArePresent(List<Card> expectedCards, List<Card> actualCards) {
        for (Card card : actualCards) {
            boolean actualCardIsPresentInExpected = 
                expectedCards.stream().filter(
                    o -> o.getSuit() == card.getSuit() && o.getValue() == card.getValue()).findFirst().isPresent();
            assertTrue(actualCardIsPresentInExpected); 
        }       
    }

}