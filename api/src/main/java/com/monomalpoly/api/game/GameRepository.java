package com.monomalpoly.api.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
	public Game findById(int id);
}
