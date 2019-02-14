package com.monomalpoly.api.dice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;

import com.monomalpoly.api.game.GameRepository;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.player.PlayerRepository;

import com.monomalpoly.api.BaseController;
import com.monomalpoly.api.JSONResponse;

import java.util.HashMap;

@RestController
public class DiceRestController extends BaseController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/dice")
    public HashMap<String, Object> dice() {
        Dice d = Dice.draw();

        Player current = getLastGame().getCurrentPlayer();
        String action  = current.forward(d);

        playerRepository.save(current);
        gameRepository.save(current.getGame());

        return JSONResponse.builder()
            .with("message", action)
            .with("isDouble", d.getIsDouble())
            .build();
    }
}
