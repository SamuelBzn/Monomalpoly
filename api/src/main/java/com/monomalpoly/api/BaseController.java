package com.monomalpoly.api;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.game.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class BaseController {
    @Autowired
    private GameRepository gameRepository;

    public Game getLastGame() {
        List<Game> games = gameRepository.findFirst(PageRequest.of(0, 1));
        return games.get(0);
    }
}


