package com.sudhi.gameranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = { GameRankingPkgMarker.class })
public class GameRankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameRankingApplication.class, args);
	}

}
