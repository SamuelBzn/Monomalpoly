package com.monomalpoly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monomalpoly.api.dice.*;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.game.GameRepository;

import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.dice.ForwardService;
import com.monomalpoly.api.board.Board;
import com.monomalpoly.api.property.Property;
import com.monomalpoly.api.property.PropertyRepository;
import com.monomalpoly.api.chance.Chance;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

@RestController
public class AppRestController extends BaseController {

    public static final int HOUSEPRICE = 100;
    public static final int HOSTELPRICE = 300;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ResetService resetService;

    @Autowired
    private ForwardService forwardService;

    @RequestMapping("reset")
    public HashMap<String, Object> reset() {
        resetService.reset();

        return JSONResponse.builder()
            .with("response", "base vidée sans erreur")
            .build();
    }

    @RequestMapping("whoStart")
    public Player whoStart() {
    	HashMap<Player, Integer> rollDice = new HashMap<Player, Integer>();

    	for (Player p : playerRepository.findAll()) {
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
    public HashMap<String, Object> whoWin(){
        int betterCapital = 0;
        Player betterPlayer = null;

        for(Player p : playerRepository.findAll()){
            if (p.getCapital() > betterCapital){
                betterCapital = p.getCapital();
                betterPlayer = p;
            }
        }

        return JSONResponse.builder()
            .with("message", "Le vainqueur actuel est : " + betterPlayer.getName())
            .build();
    }

    @RequestMapping("consultAccount")
    public HashMap<String, Object> consultAccount(){
        Game g = getLastGame();
        Player p = g.getCurrentPlayer();

        return JSONResponse.builder()
            .with("message", "Votre compte est de " + p.getBalance() + " et votre capital est de " + p.getCapital())
            .build();
    }

    @RequestMapping("force/goToJail")
    public HashMap<String, Object> goToJail() {
        Game game = getLastGame();

        Property goToJail = (Property)game.getCards().get(30);

        goToJail.setUser(game.getCurrentPlayer());
        propertyRepository.save(goToJail);

        game.getCurrentPlayer().setPosition(30);

        forwardService.forward(
            game.getCurrentPlayer(),
            new Dice(0, false, "")
        );

        game.setNextCurrentPlayer();

        playerRepository.save(game.getCurrentPlayer());
        gameRepository.save(game);

        return JSONResponse.builder()
            .with("message", "success")
            .build();
    }

}
