package com.cardgameapi.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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

    @Test
    public void addPlayer_PlayerDoesntExists_PlayerAdded(){
        Game game = new Game();
        String expectedPlayerName = "Bob Ross";

        game.addPlayerToGame(expectedPlayerName);
        List<String> playersName = game.getPlayersName();

        assertEquals(1, playersName.size());
        assertTrue(playersName.stream().anyMatch(o -> o.equals(expectedPlayerName)));
    }

    @Test
    public void addPlayer_PlayerExists_Throws(){
        Game game = new Game();
        String expectedPlayerName = "Bob Ross";

        game.addPlayerToGame(expectedPlayerName);
        
        assertThrows(InvalidArgumentException.class, () -> game.addPlayerToGame(expectedPlayerName));
    }

    @Test
    public void removePlayer_PlayerExists_PlayerRemoved(){
        Game game = new Game();
        String playerToRemoveName = "Bob Ross";
        String playerToKeepName = "Jean-Luc Picard";
        game.addPlayerToGame(playerToRemoveName);
        game.addPlayerToGame(playerToKeepName);

        game.removePlayerFromGame(playerToRemoveName);
        List<String> playersName = game.getPlayersName();

        assertEquals(1, playersName.size());
        assertFalse(playersName.stream().anyMatch(o -> o.equals(playerToRemoveName)));
        assertTrue(playersName.stream().anyMatch(o -> o.equals(playerToKeepName)));
    }
}
