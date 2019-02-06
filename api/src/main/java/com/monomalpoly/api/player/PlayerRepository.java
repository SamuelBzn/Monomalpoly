package com.monomalpoly.api.player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

public interface PlayerRepository extends CrudRepository<Player, Long> {

}
