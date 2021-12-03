package com.vocera.challenge.rps.rockpaperscissor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vocera.challenge.rps.rockpaperscissor.model.GameResponse;
import com.vocera.challenge.rps.rockpaperscissor.model.ReadyResponse;
import com.vocera.challenge.rps.rockpaperscissor.service.RockPaperScissorGameService;

@RestController
@CrossOrigin(value = "*")
public class RockPaperScissorController {

	@Autowired
	private RockPaperScissorGameService gameService;

	@GetMapping("/start")
	public ResponseEntity<ReadyResponse> readyGame() {
		return new ResponseEntity<>(gameService.startGame(), HttpStatus.OK);
	}

	@GetMapping("/v1/{token}/{move}")
	public ResponseEntity<GameResponse> playGameV1(@PathVariable(name = "token", required = true) String token,
			@PathVariable(name = "move", required = true) String move) {

		return new ResponseEntity<>(gameService.play(token, move, false), HttpStatus.OK);
	}

	@GetMapping("/v2/{token}/{move}")
	public ResponseEntity<GameResponse> playGameV2(@PathVariable(name = "token", required = true) String token,
			@PathVariable(name = "move", required = true) String move) {

		return new ResponseEntity<>(gameService.play(token, move, true), HttpStatus.OK);
	}

}
