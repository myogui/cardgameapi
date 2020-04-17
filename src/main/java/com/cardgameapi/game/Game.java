package com.cardgameapi.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.cardgameapi.cards.GameDeck;
import com.cardgameapi.cards.StandardDeckFactory;
import com.cardgameapi.player.PlayerWithCards;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import lombok.Data;

@Data
@Region("Game")
public class Game {

	private static AtomicLong COUNTER = new AtomicLong(0L);
	
	@Id
	private Long id;
	private final GameDeck gameDeck;
	private final List<PlayerWithCards> players;

	@PersistenceConstructor
	public Game(){
		this.id = COUNTER.incrementAndGet();
		StandardDeckFactory deckFactory = new StandardDeckFactory();
		this.gameDeck = deckFactory.getStandardGameDeck();
		players = new ArrayList<PlayerWithCards>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GameDeck getGameDeck(){
		return gameDeck;
	}

	public void addPlayerToGame(String name){
		if(playerExists(name)){
			throw new InvalidArgumentException("name");
		}

		players.add(new PlayerWithCards(name));
	}

	public void removePlayerFromGame(String name){
		if(!playerExists(name)){
			throw new InvalidArgumentException("name");
		}

		PlayerWithCards playerToRemove = 
			players.stream().filter(o -> o.getName().equals(name)).findFirst().get();
		players.remove(playerToRemove);
	}

	public List<String> getPlayersName(){
		return players.stream().map(o -> o.getName()).collect(Collectors.toList());
	}

	private boolean playerExists(String name){
		return players.stream().anyMatch(o -> o.getName().equals(name));
	}
}