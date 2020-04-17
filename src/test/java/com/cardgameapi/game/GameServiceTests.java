package com.cardgameapi.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class GameServiceTests {

    @Mock
    GameRepo repoMock = Mockito.mock(GameRepo.class);
    String expectedPlayerName = "Bob Ross";

    @Test
    public void createGame_WhenCalled_CallsRepoSaveOnce(){
        // arrange 
        when(repoMock.save(any(Game.class))).thenReturn(new Game());
        GameService service = new GameService(repoMock);

        // act
        service.createGame();

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
        doNothing().when(repoMock).delete(fixture);
        when(repoMock.findById(anyLong())).thenReturn(Optional.empty());
        GameService service = new GameService(repoMock);

        // act

        // assert
		assertThrows(GameNotFoundException.class, () -> service.deleteGame(fixture.getId()));
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

    @Test
    public void addPlayer_WhenNewPlayer_PlayerAddedRepoSaveCalledOnce()
    {
        // arrange 
        when(repoMock.save(any(Game.class))).thenReturn(new Game());
        when(repoMock.findById(anyLong())).thenReturn(Optional.of(new Game()));
        GameService service = new GameService(repoMock);

        // act
        Game updatedGame = service.addPlayerToGame(1L, expectedPlayerName);

        // assert
        assertTrue(updatedGame.getPlayersName().stream().anyMatch(o -> o.equals(expectedPlayerName)));
        verify(repoMock, times(1)).save(any(Game.class));
    }

    @Test
    public void addPlayer_WhenPlayerExists_ThrowsInvalidArgumentException()
    {
        // arrange 
        Game fixture = new Game();
        fixture.addPlayerToGame(expectedPlayerName);
        when(repoMock.findById(fixture.getId())).thenReturn(Optional.of(fixture));
        GameService service = new GameService(repoMock);

        // act

        // assert
        assertThrows(InvalidArgumentException.class, () -> service.addPlayerToGame(fixture.getId(), expectedPlayerName));
    }

    @Test
    public void addPlayer_WhenInvalidPlayerName_ThrowsInvalidArgumentException()
    {
        // arrange 
        when(repoMock.save(any(Game.class))).thenReturn(new Game());
        when(repoMock.findById(anyLong())).thenReturn(Optional.of(new Game()));
        GameService service = new GameService(repoMock);

        // act

        // assert
        assertThrows(InvalidArgumentException.class, () -> service.addPlayerToGame(1L, ""));
    }

    @Test
    public void removePlayer_WhenPlayerExists_PlayerRemovedRepoSaveCalledOnce()
    {
        // arrange 
        Game fixture = new Game();
        fixture.addPlayerToGame(expectedPlayerName);
        when(repoMock.save(any(Game.class))).thenReturn(new Game());
        when(repoMock.findById(fixture.getId())).thenReturn(Optional.of(fixture));
        GameService service = new GameService(repoMock);

        // act
        Game updatedGame = service.removePlayerFromGame(fixture.getId(), expectedPlayerName);

        // assert
        assertFalse(updatedGame.getPlayersName().stream().anyMatch(o -> o.equals(expectedPlayerName)));
        verify(repoMock, times(1)).save(any(Game.class));
    }

    @Test
    public void getPlayersName_WhenCalled_CallsRepoFindByIdOnceReturnsList(){
        // arrange 
        Game fixture = new Game();
        fixture.addPlayerToGame(expectedPlayerName);
        when(repoMock.findById(fixture.getId())).thenReturn(Optional.of(fixture));
        GameService service = new GameService(repoMock);

        // act
        List<String> actual = service.getPlayersName(fixture.getId());

        // assert
        verify(repoMock, times(1)).findById(fixture.getId());
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).equals(expectedPlayerName));
    }

}