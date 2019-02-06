package com.monomalpoly.api.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PlayerRestController {
    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("player/add/{name}")
    public Player addPlayer(@PathVariable String name) {
        Player p = new Player();
        p.setName(name);
        p.setBalance(1000);
        p.setProperties(0);
        p.setHouses(0);
        p.setHotels(0);
        playerRepository.save(p);

        return p;
    }
}
