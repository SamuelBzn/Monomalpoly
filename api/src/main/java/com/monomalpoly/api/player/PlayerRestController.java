package com.monomalpoly.api.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerRestController {
    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("player/add/")
    public Player addPlayer() {
        Player p = new Player();
        p.setName("Abdoulito");
        p.setBalance(1000);
        p.setProperties(0);
        p.setHouses(0);
        p.setHotels(0);
        playerRepository.save(p);

        return p;
    }
}
