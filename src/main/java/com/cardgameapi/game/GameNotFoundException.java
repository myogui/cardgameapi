package com.cardgameapi.game;

class GameNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    GameNotFoundException(Long id) {
        super("Could not find game " + id);
    }
}