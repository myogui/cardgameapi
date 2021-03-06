package com.cardgameapi.player;

import java.util.ArrayList;
import java.util.List;

import com.cardgameapi.cards.Card;

import org.springframework.data.gemfire.mapping.annotation.Region;

import lombok.Data;

@Data
@Region("Player")
public class PlayerWithCards extends Player {

    private final List<Card> hand;

    public PlayerWithCards(String name) {
        super(name);
        hand = new ArrayList<Card>();
    }

	public void receiveCards(List<Card> dealtCards) {
        hand.addAll(dealtCards);
	}

	public int getHandValue() {
        return hand.stream().mapToInt(o -> o.getValue()).sum();
	}
}