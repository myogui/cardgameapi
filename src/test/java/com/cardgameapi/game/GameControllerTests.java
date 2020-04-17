package com.cardgameapi.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class GameControllerTests {

    @Mock
    GameService serviceMock = Mockito.mock(GameService.class);

    @Test
    public void createGame_WhenCalled_CallsServiceCreateGameOnceReturnsId(){
        // arrange 
        Game fixture = new Game();
        when(serviceMock.createGame()).thenReturn(fixture);
        GameController controller = new GameController(serviceMock);

        // act
        Long actual = controller.createGame();

        // assert
        verify(serviceMock, times(1)).createGame();
        assertEquals(fixture.getId(), actual);
    }

    @Test
    public void addDeck_WhenCalled_CallsServiceAddDeckOnce(){
        // arrange 
        when(serviceMock.addDeckToGame(anyLong())).thenReturn(new Game());
        GameController controller = new GameController(serviceMock);

        // act
        controller.addDeckToGame(1L);

        // assert
        verify(serviceMock, times(1)).addDeckToGame(anyLong());
    }

    @Test
    public void delete_WhenCalled_CallsServiceDeleteOnce(){
        // arrange 
        doNothing().when(serviceMock).deleteGame(anyLong());
        GameController controller = new GameController(serviceMock);

        // act
        controller.deleteGame(1L);

        // assert
        verify(serviceMock, times(1)).deleteGame(anyLong());
    }

    @Test
    public void addPlayerToGame_WhenCalled_CallsServiceAddPlayerOnce(){
        // arrange 
        when(serviceMock.addPlayerToGame(anyLong(), anyString())).thenReturn(new Game());
        GameController controller = new GameController(serviceMock);

        // act
        controller.addPlayerToGame(1L, "Bob Ross");

        // assert
        verify(serviceMock, times(1)).addPlayerToGame(anyLong(), anyString());
    }

    @Test
    public void removePlayerFromGame_WhenCalled_CallsServiceRemovePlayerOnce(){
        // arrange 
        when(serviceMock.removePlayerFromGame(anyLong(), anyString())).thenReturn(new Game());
        GameController controller = new GameController(serviceMock);

        // act
        controller.removePlayerFromGame(1L, "Bob Ross");

        // assert
        verify(serviceMock, times(1)).removePlayerFromGame(anyLong(), anyString());
    }

    @Test
    public void getPlayersName_WhenCalled_CallsServiceGetPlayersNameOnce(){
        // arrange 
        when(serviceMock.getPlayersName(anyLong())).thenReturn(new ArrayList<String>());
        GameController controller = new GameController(serviceMock);

        // act
        controller.getPlayersName(1L);

        // assert
        verify(serviceMock, times(1)).getPlayersName(anyLong());
    }
}