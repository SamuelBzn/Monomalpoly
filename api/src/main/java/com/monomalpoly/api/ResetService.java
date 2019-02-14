package com.monomalpoly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monomalpoly.api.game.GameRepository;
import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.board.BoardRepository;
import com.monomalpoly.api.chance.ChanceRepository;
import com.monomalpoly.api.property.PropertyRepository;

@Service
public class ResetService {
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ChanceRepository chanceRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public void reset() {
		boardRepository.deleteAll();
		gameRepository.deleteAll();
		chanceRepository.deleteAll();
		propertyRepository.deleteAll();
		playerRepository.deleteAll();
	}
}
