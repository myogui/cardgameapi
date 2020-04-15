package com.cardgameapi.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepo extends CrudRepository<Game, Long> {

}