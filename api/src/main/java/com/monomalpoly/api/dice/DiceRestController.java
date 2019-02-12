package com.monomalpoly.api.dice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;

import com.monomalpoly.api.game.GameRepository;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.player.PlayerRepository;

import com.monomalpoly.api.BaseController;

import java.util.HashMap;

@RestController
public class DiceRestController extends BaseController {
    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/dice")
    public HashMap<String, String> dice() {
        Dice d = Dice.draw();

        Player current = getLastGame().getCurrentPlayer();
        String action  = current.forward(d);

        playerRepository.save(current);

        return json("message", action);
    }
}
