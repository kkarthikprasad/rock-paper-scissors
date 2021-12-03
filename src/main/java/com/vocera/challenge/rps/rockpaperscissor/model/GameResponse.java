package com.vocera.challenge.rps.rockpaperscissor.model;

public class GameResponse {

	private String token;
	private int playerScore;
	private int serverScore;

	public GameResponse(String token, int playerScore, int serverScore) {
		this.token = token;
		this.setPlayerScore(playerScore);
		this.setServerScore(serverScore);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public int getServerScore() {
		return serverScore;
	}

	public void setServerScore(int serverScore) {
		this.serverScore = serverScore;
	}

}
