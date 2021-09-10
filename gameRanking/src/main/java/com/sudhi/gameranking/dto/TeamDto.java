package com.sudhi.gameranking.dto;

import java.util.List;

public class TeamDto {
	
	private String teamName;
	private String owner;
	private List<PlayerDto> players;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<PlayerDto> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerDto> players) {
		this.players = players;
	}

}
