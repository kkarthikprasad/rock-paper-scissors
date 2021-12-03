package com.vocera.challenge.rps.rockpaperscissor.mapper;

import com.vocera.challenge.rps.rockpaperscissor.entity.RockPaperScissorGame;
import com.vocera.challenge.rps.rockpaperscissor.model.GameResponse;

public class GameToGameResponseMapper {

	public static GameResponse copyFrom(RockPaperScissorGame game) {
		GameResponse response = new GameResponse(game.getToken(), game.getPlayerScore(), game.getComputerScore());
		return response;

	}
}
