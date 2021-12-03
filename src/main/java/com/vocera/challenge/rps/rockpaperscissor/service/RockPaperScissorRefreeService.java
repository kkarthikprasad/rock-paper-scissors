package com.vocera.challenge.rps.rockpaperscissor.service;

import com.vocera.challenge.rps.rockpaperscissor.entity.GameMove;
import com.vocera.challenge.rps.rockpaperscissor.model.GameResult;

public class RockPaperScissorRefreeService {

	public static GameResult findWinner(GameMove playerMove, GameMove computerMove) {
		if (playerMove.equals(computerMove)) {
			return GameResult.GAME_TIED;
		}

		if (GameMove.PAPER.equals(playerMove)) {
			return GameMove.SCISSORS.equals(computerMove) ? GameResult.SERVER_WON : GameResult.PLAYER_WON;
		} else if (GameMove.ROCK.equals(playerMove)) {
			return GameMove.PAPER.equals(computerMove) ? GameResult.SERVER_WON : GameResult.PLAYER_WON;
		} else {
			return GameMove.ROCK.equals(computerMove) ? GameResult.SERVER_WON : GameResult.PLAYER_WON;
		}
	}

	public static GameMove getRandomServerMove() {
		int randomMove = (int)(Math.random()*3);
		GameMove computerMove = GameMove.values()[randomMove];
		return computerMove;
	}

	public static GameMove getStrategicMove(GameMove playerMove) {
		if (GameMove.PAPER.equals(playerMove)) {
			return GameMove.SCISSORS;
		} else if (GameMove.SCISSORS.equals(playerMove)) {
			return GameMove.ROCK;
		} else {
			return GameMove.PAPER;
		}
	}

}
