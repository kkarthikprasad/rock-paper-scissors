package com.vocera.challenge.rps.rockpaperscissor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vocera.challenge.rps.rockpaperscissor.entity.RockPaperScissorGame;

@Repository
public interface RockPaperScissorGameRepository extends JpaRepository<RockPaperScissorGame, Long> {

	public Optional<RockPaperScissorGame> findByTokenAndGameInProgress(String token, boolean isGameInProgress);
	
}
