package com.monomalpoly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.property.PropertyRepository;
import com.monomalpoly.api.game.GameRepository;

import java.util.HashMap;

@RestController
public class AppRestController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("reset")
    public HashMap<String, String> reset() {
        playerRepository.deleteAll();
        propertyRepository.deleteAll();
        gameRepository.deleteAll();

        HashMap<String, String> response = new HashMap<String, String>();

        response.put("response", "base vidée sans erreur");

        return response;
    }
}
