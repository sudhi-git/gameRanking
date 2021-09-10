package com.sudhi.gameranking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudhi.gameranking.dto.PlayerDto;
import com.sudhi.gameranking.dto.TeamDto;
import com.sudhi.gameranking.model.Players;
import com.sudhi.gameranking.model.Team;
import com.sudhi.gameranking.model.TeamDao;

@Controller
public class GameRankingController {

	@Autowired
	TeamDao teamDao;
	ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "/gameranking/team")
	public ResponseEntity<String> createTeam(@RequestBody TeamDto teamDto) {
		Team existingTeam = teamDao.findByTeamName(teamDto.getTeamName());
		if (existingTeam != null) {
			return new ResponseEntity<>("Team already exists", HttpStatus.BAD_REQUEST);
		} else {
			List<Players> playersDb = new ArrayList<>();
			Team team = new Team();
			team.setOwner(teamDto.getOwner());
			team.setTeamName(teamDto.getTeamName());
			for(PlayerDto player : teamDto.getPlayers()) {
				Players players = new Players();
				players.setAge(player.getAge());
				players.setFirstName(player.getFirstName());
				players.setLastName(player.getLastName());
				playersDb.add(players);
			}
			team.setPlayers(playersDb);
			teamDao.save(team);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	
	@GetMapping(value = "/gameranking/teamplayers")
	public ResponseEntity<String> getTeam(){
		List<Team> team = teamDao.findAll();
		String responseString;
		try {
			responseString = mapper.writeValueAsString(team);
			return new ResponseEntity<>(responseString, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
