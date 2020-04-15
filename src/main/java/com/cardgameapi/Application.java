package com.cardgameapi;

import com.cardgameapi.game.Game;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@SpringBootApplication
@ClientCacheApplication(name = "CardGameRestApi", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = Game.class,
	clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}