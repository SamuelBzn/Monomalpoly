package com.monomalpoly.api.player;

import com.monomalpoly.api.BaseController;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.game.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;

@RestController
public class PlayerRestController extends BaseController {
    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("player/add/{name}")
    public Player addPlayer(@PathVariable String name) {
        Player p = new Player();
        Game game = getLastGame();

        p.setName(name);
        p.setBalance(1000);
        p.setGame(game);
        playerRepository.save(p);

        game.getPlayers().add(p);
        gameRepository.save(game);

        return p;
    }

    @RequestMapping("player")
    public List<Player> players() {
        List<Player> l = new ArrayList<Player>();
        for(Player p : playerRepository.findAll())
            l.add(p);
        return l;
    }
}
