package com.sudhi.gameranking.model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity implementation class for Entity: Players
 *
 */
@Entity

public class Players implements Serializable {

	   
	@Id
	@UuidGenerator(name = "PlayerUuid")
	@GeneratedValue(generator = "PlayerUuid")
	private String playerId;
	private String firstName;
	private String lastName;
	private int age;
	private String teamId;
	@ManyToOne
	@JoinColumn(name = "teamId", referencedColumnName = "teamId", nullable = false, updatable = false, insertable = false)
	@JsonBackReference
	private Team team;
	private static final long serialVersionUID = 1L;

	public Players() {
		super();
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

}
