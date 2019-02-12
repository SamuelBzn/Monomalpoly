package com.monomalpoly.api.dice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;

import com.monomalpoly.api.game.GameRepository;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.player.PlayerRepository;

@RestController
public class DiceRestController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/dice")
    public String dice() {
        Dice d = Dice.draw();

        Player current = gameRepository.findFirst(PageRequest.of(0, 1)).get(0).getCurrentPlayer();
        String action  = current.forward(d);

        playerRepository.save(current);

        return action;
    }
}
