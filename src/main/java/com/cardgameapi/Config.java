package com.cardgameapi;

import com.cardgameapi.game.GameRepo;
import com.cardgameapi.game.GameService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("package com.cardgameapi")
public class Config {
    
    @Bean
    public GameService gameService(GameRepo gameRepo){
        return new GameService(gameRepo);
    }
}