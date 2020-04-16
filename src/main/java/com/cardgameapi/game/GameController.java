package com.cardgameapi.game;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/games")
	List<Game> getAll() {
		return gameService.getAll();
	}

	@GetMapping("/games/{id}")
	Game getOne(@PathVariable Long id) {
		return gameService.getOne(id);
	}

	@PostMapping("/games")
	Game createGame(@RequestBody Game newGame) {
		return gameService.createGame(newGame);
	}

	@DeleteMapping("/games/{id}")
	void deleteGame(@PathVariable Long id) {
		gameService.deleteGame(id);
	}

	@PostMapping("/games/{id}/deck")
	void addDeckToGame(@PathVariable Long id) {
		gameService.addDeckToGame(id);
	}

	@GetMapping("/games/{id}/player")
	List<String> getPlayersName(@PathVariable Long id){
		return gameService.getPlayersName(id);
	}

	// User form URL Encoded to POST value
	@PostMapping("/games/{id}/player")
	void addPlayerToGame(@PathVariable Long id, @RequestParam String name) {
		gameService.addPlayerToGame(id, name);
	}

	@DeleteMapping("/games/{id}/player")
	void removePlayerFromGame(@PathVariable Long id, @RequestParam String name){
		gameService.removePlayerFromGame(id, name);
	}
}