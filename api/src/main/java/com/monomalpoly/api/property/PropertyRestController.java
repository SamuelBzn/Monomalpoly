package com.monomalpoly.api.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.game.GameRepository;
import com.monomalpoly.api.property.Property;
import com.monomalpoly.api.property.PropertyRepository;
import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.player.Player;

import com.monomalpoly.api.BaseController;
import com.monomalpoly.api.JSONResponse;

import java.util.HashMap;

@RestController
public class PropertyRestController extends BaseController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("property")
    public List<Property> properties() {
        List<Property> l = new ArrayList<Property>();
        for(Property p : propertyRepository.findAll())
            l.add(p);
        return l;
    }

    @RequestMapping("/buy/property")
    public HashMap<String, Object> buyProperty() {
        Game game = getLastGame();
        Player current = game.getCurrentPlayer();
        List<Card> cards = game.getBoard().getCards();

        Property property = (Property)cards.get(current.getPosition());

        property.setUser(current);
        propertyRepository.save(property);
        current.getProperties().add(property);

        current.removeToBalance(property.getLandCost());
        playerRepository.save(current);

        return JSONResponse.builder()
            .with("message", "Vous avez acheté cette propriété.")
            .build();
    }

    @RequestMapping("/buy/property/house")
    public HashMap<String, Object> buyHouse() {
        Game game = getLastGame();
        Player current = game.getCurrentPlayer();
        List<Card> cards = game.getBoard().getCards();

        Property property = (Property)cards.get(current.getPosition());

        property.addToHouses(1);
        propertyRepository.save(property);

        return JSONResponse.builder()
            .with("message", "Vous avez acheté une maison pour cette propriété.")
            .build();
    }

    @RequestMapping("/buy/refuse")
    public HashMap<String, Object> refuse() {
        return JSONResponse.builder()
            .with("message", "Vous n'avez rien acheté")
            .build();
    }
}
