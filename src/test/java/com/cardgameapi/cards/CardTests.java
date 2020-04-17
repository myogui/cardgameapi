package com.cardgameapi.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cardgameapi.game.InvalidArgumentException;

import org.junit.jupiter.api.Test;

public class CardTests {

    @Test
    public void Card_ValidValue_CardInstantiated(){
        // arrange
        Suit expectedSuit = Suit.CLUBS;
        Byte expectedValue = 1;

        // act
        Card actual = new Card(expectedSuit, expectedValue);

        // assert
        assertEquals(expectedSuit, actual.getSuit());
        assertEquals(expectedValue, actual.getValue());
    }

    @Test
    public void Card_InvalidValue_ThrowInvalidArgumentException(){
        // arrange
        Suit expectedSuit = Suit.CLUBS;
        Byte underLimitValue = 0;
        Byte overtLimitValue = 14;

        // act

        // assert
        assertThrows(InvalidArgumentException.class, () -> new Card(expectedSuit, underLimitValue));
        assertThrows(InvalidArgumentException.class, () -> new Card(expectedSuit, overtLimitValue));
    }
}