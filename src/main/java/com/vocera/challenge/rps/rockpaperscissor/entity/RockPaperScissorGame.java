package com.vocera.challenge.rps.rockpaperscissor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class RockPaperScissorGame {

	@Id
	@SequenceGenerator(name = "game_id_seq", sequenceName = "game_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
	private long id;
	private String token;
	private int playerScore;
	private int computerScore;
	private boolean gameInProgress;

	public RockPaperScissorGame() {
		this.playerScore = 0;
		this.computerScore = 0;
		this.token = "";
		this.gameInProgress = true;
	}
	
	public RockPaperScissorGame(String token) {
		this.playerScore = 0;
		this.computerScore = 0;
		this.token = token;
		this.gameInProgress = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public int getComputerScore() {
		return computerScore;
	}

	public void setComputerScore(int computerScore) {
		this.computerScore = computerScore;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isGameInProgress() {
		return gameInProgress;
	}

	public void setGameInProgress(boolean gameInProgress) {
		this.gameInProgress = gameInProgress;
	}

}
