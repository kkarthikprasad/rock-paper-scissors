package com.vocera.challenge.rps.rockpaperscissor.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vocera.challenge.rps.rockpaperscissor.entity.GameMove;
import com.vocera.challenge.rps.rockpaperscissor.entity.RockPaperScissorGame;
import com.vocera.challenge.rps.rockpaperscissor.exception.InvalidTokenException;
import com.vocera.challenge.rps.rockpaperscissor.mapper.GameToGameResponseMapper;
import com.vocera.challenge.rps.rockpaperscissor.model.GameResponse;
import com.vocera.challenge.rps.rockpaperscissor.model.GameResult;
import com.vocera.challenge.rps.rockpaperscissor.model.ReadyResponse;
import com.vocera.challenge.rps.rockpaperscissor.repository.RockPaperScissorGameRepository;

@Service
public class RockPaperScissorGameService {

	@Autowired
	private RockPaperScissorGameRepository gameRepository;

	public ReadyResponse startGame() {
		String token = UUID.randomUUID().toString();
		RockPaperScissorGame newGame = new RockPaperScissorGame(token);
		gameRepository.save(newGame);
		return new ReadyResponse(token);
	}

	public GameResponse play(String token, String playerMove, boolean useStrategy) {
		Optional<RockPaperScissorGame> onGoingGameDataContainer = gameRepository.findByTokenAndGameInProgress(token,
				true);
		if (!onGoingGameDataContainer.isPresent()) {
			// handling both invalid token and game already finished instance in a single
			// logic but can be separated if needed
			throw new InvalidTokenException("Invalid token passed / Game is already closed");
		}
		GameMove playerMoveEnum = GameMove.valueOf(playerMove.toUpperCase());
		GameMove computerMove = useStrategy ? RockPaperScissorRefreeService.getStrategicMove(playerMoveEnum)
				: RockPaperScissorRefreeService.getRandomServerMove();
		GameResult winner = RockPaperScissorRefreeService.findWinner(playerMoveEnum, computerMove);

		GameResponse response = updateGameStatsAndBuildResponse(onGoingGameDataContainer, winner);
		return response;
	}

	private GameResponse updateGameStatsAndBuildResponse(Optional<RockPaperScissorGame> onGoingGameDataContainer,
			GameResult winner) {
		RockPaperScissorGame onGoingGame = onGoingGameDataContainer.get();
		if (GameResult.SERVER_WON.equals(winner)) {
			onGoingGame.setComputerScore(onGoingGame.getComputerScore() + 1);
			if (onGoingGame.getComputerScore() == 3) {
				onGoingGame.setGameInProgress(false);
			}
		} else if (GameResult.PLAYER_WON.equals(winner)) {
			onGoingGame.setPlayerScore(onGoingGame.getPlayerScore() + 1);
			if (onGoingGame.getPlayerScore() == 3) {
				onGoingGame.setGameInProgress(false);
			}
		}

		GameResponse response = GameToGameResponseMapper.copyFrom(onGoingGame);
		gameRepository.save(onGoingGame);
		return response;
	}

}
