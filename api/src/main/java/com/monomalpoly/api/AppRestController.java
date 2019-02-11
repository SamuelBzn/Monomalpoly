package com.monomalpoly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monomalpoly.api.dice.*;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.property.PropertyRepository;
import com.monomalpoly.api.game.*;

import java.util.Map;
import java.util.HashMap;

@RestController
public class AppRestController extends BaseController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @RequestMapping("reset")
    public HashMap<String, String> reset() {
        playerRepository.deleteAll();
        propertyRepository.deleteAll();
        gameRepository.deleteAll();

        HashMap<String, String> response = new HashMap<String, String>();

        response.put("response", "base vidée sans erreur");

        return response;
    }

    @RequestMapping("whoStart")
    public Player whoStart() {
    	HashMap<Player, Integer> rollDice = new HashMap<Player, Integer>();

    	for(Player p : playerRepository.findAll()) {
            Dice d = Dice.draw();
            rollDice.put(p, (int)d.getValue());
    	}

    	Player pMax = null;
    	int max = 0;

    	for(Map.Entry<Player, Integer> entry : rollDice.entrySet()) {
    		if(entry.getValue() > max) {
    			pMax = entry.getKey();
    			max = entry.getValue();
    		}
    	}

        Game g = getLastGame();
        g.setCurrentPlayer(pMax);
        gameRepository.save(g);

    	return pMax;
    }

    @RequestMapping("winner")
    public HashMap<String, String> whoWin(){
        HashMap<String, String> response = new HashMap<String, String>();

        int betterCapital = 0;
        Player betterPlayer;
        for(Player p : playerRepository.findAll()){
            if (p.getCapital() > betterCapital){
                betterCapital = p.getCapital();
                betterPlayer = p;
            }
        }
        response.put("response", "Le vainqueur actuel est : " + p.getName());

        return response;
    }
}
