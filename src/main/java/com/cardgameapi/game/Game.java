package com.cardgameapi.game;

import java.util.concurrent.atomic.AtomicLong;

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

	@PersistenceConstructor
	public Game(){
		this.id = COUNTER.incrementAndGet();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}