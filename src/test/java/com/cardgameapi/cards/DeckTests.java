package com.cardgameapi.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.cardgameapi.game.InvalidArgumentException;

import org.junit.jupiter.api.Test;

public class DeckTests {

    private final StandardDeckFactory standardDeckFactory = new StandardDeckFactory();

    @Test
    public void dealCards_MaxNumberCards_ReturnsFullDeck(){
        // arrange
        Deck deck = standardDeckFactory.getStandardDeck();
        Byte numberOfCards = 52;

        // act
        List<Card> dealtCards = deck.dealCards(numberOfCards);

        // assert
        assertEquals(52, dealtCards.size());
    }

    @Test
    public void dealCards_NegativeNumberCards_ThrowsInvalidArgumentException(){
        // arrange
        Byte invalidNumberOfCards = 0;
        Deck deck = standardDeckFactory.getStandardDeck();        

        // act
        
        // assert
        assertThrows(InvalidArgumentException.class, () -> deck.dealCards(invalidNumberOfCards));
    }

    @Test
    public void dealCards_MoreCardsThanUnDealt_ReturnsFullDeck(){
        // arrange
        Deck deck = standardDeckFactory.getStandardDeck();
        Byte numberOfCards = 53;

        // act
        List<Card> dealtCards = deck.dealCards(numberOfCards);

        // assert
        assertEquals(52, dealtCards.size());
    }

    @Test
    public void dealCards_Deal1Card_ReturnsOneCard(){
        // arrange
        Deck deck = standardDeckFactory.getStandardDeck();
        Byte numberOfCards = 1;

        // act
        List<Card> dealtCards = deck.dealCards(numberOfCards);

        // assert
        assertEquals(1, dealtCards.size());
    }
}