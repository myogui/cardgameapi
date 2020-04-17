package com.cardgameapi.cards;

import java.util.ArrayList;
import java.util.List;

import com.cardgameapi.game.InvalidArgumentException;

import org.springframework.data.gemfire.mapping.annotation.Region;

import lombok.Data;

@Data
@Region("Cards")
public class Deck {
    
    protected List<Card> unDealtCards = new ArrayList<Card>();

    protected Deck(List<Card> cardsForDeck) {
        this.unDealtCards = cardsForDeck;
	}

	public synchronized List<Card> dealCards(int nbOfCards){
        if(nbOfCards <= 0){
            throw new InvalidArgumentException("nbOfCards");
        }

        List<Card> dealtCards = new ArrayList<Card>();

        while(dealtCards.size() < nbOfCards && !unDealtCards.isEmpty()){
            Card cardToDeal = unDealtCards.iterator().next();
            dealtCards.add(cardToDeal);
            unDealtCards.remove(cardToDeal);
        }        

        return dealtCards;
    }
}