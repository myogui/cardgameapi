package com.cardgameapi.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.cardgameapi.cards.Card;
import com.cardgameapi.cards.Suit;

import org.junit.jupiter.api.Test;

public class PlayerWithCardsTests {

    @Test
    public void receivesCards_WhenCalled_CardsAddedToHand(){
        Card expectedCard1 = new Card(Suit.CLUBS, (byte) 1);
        Card expectedCard2 = new Card(Suit.HEARTS, (byte) 1);
        List<Card> dealtCards = new ArrayList<Card>();
        dealtCards.add(expectedCard1);
        dealtCards.add(expectedCard2);
        PlayerWithCards actual = new PlayerWithCards("Bob Ross");

        actual.receiveCards(dealtCards);
        
        assertEquals(2, actual.getHandValue());
    }
}
