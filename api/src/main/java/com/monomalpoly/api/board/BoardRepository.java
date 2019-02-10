package com.monomalpoly.api.board;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

public interface BoardRepository extends CrudRepository<Board, Long> {
	@Query(value = "SELECT board FROM Board board")
	List<Board> findFirst(Pageable limit);

	Board findById(int id);
}
