package com.monomalpoly.api.property;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.monomalpoly.api.player.Player;

public interface PropertyRepository extends CrudRepository<Property, Long> {
	@Query(value = "SELECT property FROM Property property ORDER BY id DESC")
	List<Property> findFirst(Pageable limit);
}
