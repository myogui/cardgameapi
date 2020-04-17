package com.cardgameapi.game;

import java.util.List;
import java.util.Optional;

public class GameService {

    private final GameRepo gameRepo;
    
    public GameService(GameRepo gameRepo){        
		this.gameRepo = gameRepo;
    }
  
	public Game createGame() {
	  return gameRepo.save(new Game());
	}

	public void deleteGame(Long id){
		Game foundGame = findExistingGameOrThrow(id);
		gameRepo.delete(foundGame);
	}

	public Game addDeckToGame(Long id) {
		Game foundGame = findExistingGameOrThrow(id);
		foundGame.getGameDeck().addDeckToGameDeck();
		gameRepo.save(foundGame);
		return foundGame;
	}

	public List<String> getPlayersName(Long id) {
		Game foundGame = findExistingGameOrThrow(id);
		return foundGame.getPlayersName();
	}

	public Game addPlayerToGame(Long id, String playerName){
		Game foundGame = findExistingGameOrThrow(id);
		validatePlayerNameOrThrow(playerName);

		foundGame.addPlayerToGame(playerName);
		gameRepo.save(foundGame);
		return foundGame;
	}

	public Game removePlayerFromGame(Long id, String playerName) {
		Game foundGame = findExistingGameOrThrow(id);
		validatePlayerNameOrThrow(playerName);

		foundGame.removePlayerFromGame(playerName);
		gameRepo.save(foundGame);
		return foundGame;
	}

	private Game findExistingGameOrThrow(Long id){
		Optional<Game> foundGame = gameRepo.findById(id);
		if(!foundGame.isPresent()){
			throw new GameNotFoundException(id);
		}

		return foundGame.get();
	}

	private void validatePlayerNameOrThrow(String playerName) {
		if("".equals(playerName)){
			throw new InvalidArgumentException("name");
		}
	}

}