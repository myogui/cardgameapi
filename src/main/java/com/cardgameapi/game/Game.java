package com.cardgameapi.game;

import java.util.concurrent.atomic.AtomicLong;

import com.cardgameapi.cards.GameDeck;
import com.cardgameapi.cards.StandardDeckFactory;

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
	private GameDeck gameDeck;

	@PersistenceConstructor
	public Game(){
		this.id = COUNTER.incrementAndGet();
		StandardDeckFactory deckFactory = new StandardDeckFactory();
		this.gameDeck = deckFactory.getStandardGameDeck();
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
}