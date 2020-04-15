package com.cardgameapi.game;

import java.util.List;
import java.util.Optional;

public class GameService {

    private final GameRepo gameRepo;
    
    public GameService(GameRepo gameRepo){        
		this.gameRepo = gameRepo;
    }

	public List<Game> getAll() {
	  return (List<Game>) gameRepo.findAll();
	}

	public Game getOne(Long id) {
		return gameRepo.findById(id)
		  .orElseThrow(() -> new GameNotFoundException(id));
	}
  
	public Game createGame(Game newGame) {
	  return gameRepo.save(newGame);
	}

	public void deleteGame(Long id){
		Optional<Game> foundGame = gameRepo.findById(id);
		if(!foundGame.isPresent()){
			throw new GameNotFoundException(id);
		}

		gameRepo.delete(foundGame.get());
	}

}