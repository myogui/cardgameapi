package com.cardgameapi.game;

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

}