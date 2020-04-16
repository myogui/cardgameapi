package com.cardgameapi.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTests {

    @Test
    public void instantiate_WithName_NameIsSet(){
        String expectedName = "Bob Ross";
        Player actual = new Player(expectedName);
        
        assertEquals(expectedName, actual.getName());
    }
}