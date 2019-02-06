package bimbamboum;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	// ArrayList<Personnage> findAllByUserId(int id);
	// Personnage findByName(String name);
	// Personnage findById(int id);

	// @Query(value = "SELECT c FROM Combat c WHERE c.p1 = ?1 OR c.p2 = ?1")
 	// ArrayList<Combat> findFightsById(Personnage p);
}
