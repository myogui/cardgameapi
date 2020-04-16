package com.cardgameapi.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class GameServiceTests {

    @Mock
    GameRepo repoMock = Mockito.mock(GameRepo.class);

    @Test
    public void getAll_WhenCalled_CallsRepoFindAllOnce(){
        // arrange 
        when(repoMock.findAll()).thenReturn(new ArrayList<Game>());
        GameService service = new GameService(repoMock);

        // act
        service.getAll();

        // assert
        verify(repoMock, times(1)).findAll();
    }

    @Test
    public void getOne_WhenCalled_CallsRepoFindByIdOnce(){
        // arrange 
        when(repoMock.findById(anyLong())).thenReturn(Optional.of(new Game()));
        GameService service = new GameService(repoMock);

        // act
        service.getOne(1L);

        // assert
        verify(repoMock, times(1)).findById(anyLong());
    }

    @Test
    public void createGame_WhenCalled_CallsRepoSaveOnce(){
        // arrange 
        when(repoMock.save(any(Game.class))).thenReturn(new Game());
        GameService service = new GameService(repoMock);

        // act
        service.createGame(new Game());

        // assert
        verify(repoMock, times(1)).save(any(Game.class));
    }

    @Test
    public void delete_WhenGameExists_CallsRepoDeleteOnce(){
        // arrange 
        doNothing().when(repoMock).delete(any(Game.class));
        when(repoMock.findById(anyLong())).thenReturn(Optional.of(new Game()));
        GameService service = new GameService(repoMock);

        // act
        service.deleteGame(1L);

        // assert
        verify(repoMock, times(1)).delete(any(Game.class));
    }

    @Test
    public void delete_WhenGameDoesntExists_ThrowsGameNotFoundException(){
        // arrange 
        Game fixture = new Game();
        fixture.setId(1L);

        doNothing().when(repoMock).delete(fixture);
        when(repoMock.findById(anyLong())).thenReturn(Optional.empty());
        GameService service = new GameService(repoMock);

        // act

        // assert
		assertThrows(GameNotFoundException.class, () -> service.deleteGame(1L));
    }

    @Test
    public void addDeckToGame_WhenGameExists_DeckAddedGameSaved(){
        // arrange 
        when(repoMock.save(any(Game.class))).thenReturn(new Game());
        when(repoMock.findById(anyLong())).thenReturn(Optional.of(new Game()));
        GameService service = new GameService(repoMock);

        // act
        Game updatedGame = service.addDeckToGame(1L);

        // assert
        verify(repoMock, times(1)).save(any(Game.class));
        assertEquals(104, updatedGame.getGameDeck().dealCards(104).size());
        assertEquals(0, updatedGame.getGameDeck().dealCards(1).size());
    }

    @Test
    public void addDeckToGame_WhenGameDoesntExists_ThrowsGameNotFoundException(){
        // arrange 
        when(repoMock.findById(anyLong())).thenReturn(Optional.empty());
        GameService service = new GameService(repoMock);

        // act

        // assert
		assertThrows(GameNotFoundException.class, () -> service.addDeckToGame(1L));
    }

}