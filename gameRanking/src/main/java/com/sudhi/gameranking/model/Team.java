package com.sudhi.gameranking.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.eclipse.persistence.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Entity implementation class for Entity: Team
 *
 */
@Entity
public class Team implements Serializable {

	   
	@Id
	@UuidGenerator(name = "TeamUuid")
	@GeneratedValue(generator = "TeamUuid")
	private String teamId;
	private String teamName;
	private String owner;
	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Players> players;
	private static final long serialVersionUID = 1L;

	public Team() {
		super();
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

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

	public List<Players> getPlayers() {
		return players;
	}

	public void setPlayers(List<Players> players) {
		this.players = players;
	}
   
}
