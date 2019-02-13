package com.monomalpoly.api.game;

import com.monomalpoly.api.BaseController;
import com.monomalpoly.api.ResetService;
import com.monomalpoly.api.board.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@RestController
public class GameRestController extends BaseController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ResetService resetService;

    @RequestMapping("game/new/{nbUsers}/{state}")
    public Game addGame(@PathVariable int nbUsers, @PathVariable String state) {
        resetService.reset();

        Game g = new Game();
        g.setNbUsers(nbUsers);
        g.setCountNbUsers(nbUsers);
        g.setState(state);

        boardService.createBoard(g);

        gameRepository.save(g);

        return g;
    }

    @RequestMapping("game/countNbUsers")
    public HashMap<String, Integer> getCountNbUsers() {
        HashMap<String, Integer> response = new HashMap<String, Integer>();

        response.put("value", getLastGame().getCountNbUsers());

        return response;
    }

    @RequestMapping("game/decreaseCountNbUsers")
    public HashMap<String, Integer> decreaseCountNbUsers() {
        Game g = getLastGame();
        g.setCountNbUsers(g.getCountNbUsers() - 1);
        gameRepository.save(g);

        HashMap<String, Integer> response = new HashMap<String, Integer>();

        response.put("value", g.getCountNbUsers());

        return response;
    }

    @RequestMapping("game/editState/{state}")
    public Game editState(@PathVariable String state) {
        Game g = getLastGame();
        g.setState(state);
        gameRepository.save(g);
        return g;
    }

    @RequestMapping("game")
    public Game games() {
        List<Game> games = gameRepository.findFirst(PageRequest.of(0, 1));

        if (games.size() > 0) {
            return games.get(0);
        } else {
            Game g = new Game();
            g.setNbUsers(-1);
            g.setCountNbUsers(-1);
            g.setState("null");

            return g;
        }
    }
}
