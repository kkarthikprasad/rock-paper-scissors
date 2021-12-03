package com.vocera.challenge.rps.rockpaperscissor.model;

public class ReadyResponse {

	private String message = "READY";
	private String token;

	public ReadyResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getMessage() {
		return message;
	}

}
