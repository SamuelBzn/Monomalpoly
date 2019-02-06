package com.monomalpoly.api.property;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

public interface PropertyRepository extends CrudRepository<Property, Long> {
	
}
