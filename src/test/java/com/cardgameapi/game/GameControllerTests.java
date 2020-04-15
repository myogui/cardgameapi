package com.cardgameapi.game;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    public void getAll_WhenCalled_CallsServiceGetAllOnce(){
        // arrange 
        when(serviceMock.getAll()).thenReturn(new ArrayList<Game>());
        GameController controller = new GameController(serviceMock);

        // act
        controller.getAll();

        // assert
        verify(serviceMock, times(1)).getAll();
    }

    @Test
    public void createGame_WhenCalled_CallsServiceCreateGameOnce(){
        // arrange 
        when(serviceMock.createGame(any(Game.class))).thenReturn(new Game());
        GameController controller = new GameController(serviceMock);

        // act
        controller.createGame(new Game());

        // assert
        verify(serviceMock, times(1)).createGame(any(Game.class));
    }

    @Test
    public void getOne_WhenCalled_CallsServiceGetOneOnce(){
        // arrange 
        when(serviceMock.getOne(anyLong())).thenReturn(new Game());
        GameController controller = new GameController(serviceMock);

        // act
        controller.getOne(1L);

        // assert
        verify(serviceMock, times(1)).getOne(anyLong());
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
}