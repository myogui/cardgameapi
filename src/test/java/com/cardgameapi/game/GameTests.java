package com.cardgameapi.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameTests {

    @Test
    public void newGame_WhenCalled_CreatesAGameDeck(){
        // arrange 

        // act
        Game actual = new Game();

        // assert
        assertEquals(52, actual.getGameDeck().dealCards(52).size());
        assertEquals(0, actual.getGameDeck().dealCards(1).size());
    }
}
