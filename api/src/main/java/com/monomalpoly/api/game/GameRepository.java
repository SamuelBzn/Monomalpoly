package com.monomalpoly.api.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

public interface GameRepository extends CrudRepository<Game, Long> {
	@Query(value = "SELECT game FROM Game game")
	List<Game> findFirst(Pageable limit);

	Game findById(int id);
}
