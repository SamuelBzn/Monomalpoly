package com.monomalpoly.api.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

public interface GameRepository extends CrudRepository<Game, Long> {
	public Game findById(int id);
}
