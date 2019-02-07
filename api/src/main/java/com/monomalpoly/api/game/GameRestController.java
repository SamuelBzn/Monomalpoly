package com.monomalpoly.api.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;

@RestController
public class GameRestController {
    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("game/new/{nbUsers}/{state}")
    public Game addGame(@PathVariable int nbUsers, @PathVariable String state) {
        Game g = new Game();
        g.setNbUsers(nbUsers);
        g.setCountNbUsers(nbUsers);
        g.setState(state);
        gameRepository.save(g);

        return g;
    }

    @RequestMapping("game/countNbUsers")
    public int getCountNbUsers() {
        Game g = gameRepository.findById(1);

        return g.getCountNbUsers();
    }

    @RequestMapping("game/decreaseCountNbUsers")
    public int decreaseCountNbUsers() {
        Game g = gameRepository.findById(1);
        g.setCountNbUsers(g.getCountNbUsers() - 1);
        gameRepository.save(g);
        return g.getCountNbUsers();
    }

    @RequestMapping("game/editState/{state}")
    public Game editState(@PathVariable String state) {
        Game g = gameRepository.findById(1);
        g.setState(state);
        gameRepository.save(g);
        return g;
    }

    @RequestMapping("game")
    public List<Game> games() {
        List<Game> l = new ArrayList<Game>();
        for(Game g : gameRepository.findAll())
            l.add(g);
        if(l.isEmpty()) {
            Game g = new Game();
            g.setNbUsers(-1);
            g.setCountNbUsers(-1);
            g.setState("null");
            l.add(g)
        }
        return l;
    }
}
