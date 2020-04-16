package com.cardgameapi.cards;

import java.util.List;

import org.springframework.data.gemfire.mapping.annotation.Region;

import lombok.Data;

@Data
@Region("Cards")
public class GameDeck extends Deck {

    protected GameDeck(List<Card> cardsForDeck) {
        super(cardsForDeck);
    }

    public void addDeckToGameDeck(){
        StandardDeckFactory deckFactory = new StandardDeckFactory();
        unDealtCards.addAll(deckFactory.getStandardDeck().unDealtCards);
    }

}