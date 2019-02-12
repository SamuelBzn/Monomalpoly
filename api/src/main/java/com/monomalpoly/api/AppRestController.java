package com.monomalpoly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monomalpoly.api.dice.*;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.property.PropertyRepository;
import com.monomalpoly.api.game.*;
import com.monomalpoly.api.board.BoardRepository;
import com.monomalpoly.api.chance.ChanceRepository;
import com.monomalpoly.api.card.*;
import com.monomalpoly.api.board.*;
import com.monomalpoly.api.property.*;
import com.monomalpoly.api.chance.*;

import java.util.Map;
import java.util.HashMap;

@RestController
public class AppRestController extends BaseController {

    public static final int HOUSEPRICE = 100;
    public static final int HOSTELPRICE = 300;


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ChanceRepository chanceRepository;

    @RequestMapping("reset")
    public HashMap<String, String> reset() {
        playerRepository.deleteAll();
        boardRepository.deleteAll();
        gameRepository.deleteAll();
        chanceRepository.deleteAll();
        propertyRepository.deleteAll();

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
        Player betterPlayer = null;

        for(Player p : playerRepository.findAll()){
            if (p.getCapital() > betterCapital){
                betterCapital = p.getCapital();
                betterPlayer = p;
            }
        }
        response.put("response", "Le vainqueur actuel est : " + betterPlayer.getName());

        return response;
    }

    @RequestMapping("consultAccount")
    public HashMap<String, String> consultAccount(){
    HashMap<String, String> response = new HashMap<String, String>();

    Game g = getLastGame();
    Player p = g.getCurrentPlayer();

    response.put("response", "Votre compte est de " + p.getBalance() + " et votre capital est de " + p.getCapital());

    return response;
    }

    @RequestMapping("announceCard")
    public HashMap<String, String> announceCard(){
        HashMap<String, String> response = new HashMap<String, String>();

        Game g = getLastGame();
        Player p = g.getCurrentPlayer();
        Board b = getLastBoard();

        Card c = b.getCards().get(p.getPosition());


        String user = "";
        String color = "";
        String level = "";


        if (c instanceof Property){

            if (((Property)c).getUser() == null){
                user = " n'appartenant à aucun joueur";
            } else { user = " appartenant à " + ((Property)c).getUser().getName();}

            if (((Property)c).getLevel() == 0){
                level = " C'est un terrain non acheté";
            }
            else if (((Property)c).getLevel() == 1){
                level = " C'est un terrain plat";
            }
            else if (((Property)c).getLevel() == 2){
                level = " C'est une maison";
            }
            else if (((Property)c).getLevel() == 3){
                level = " C'est un hotel";
            }

            if (((Property)c).getColor() == null){
                color = " et n'a aucune couleur";
            }else { color = ((Property)c).getColor(); }

            response.put("response", "Vous arrivez sur :" + ((Property)c).getName() + " de type " + ((Property)c).getNature() + user + " dont le terrain coûte" + ((Property)c).getLandCost() + " et dont le prix total est de " + ((Property)c).getCost() + level + color);

        }


        else if (c instanceof Chance){

            response.put("response", "Vous arrivez sur : " + ((Property)c).getName() + " une case de type chance");

        }

        return response;

    }


    @RequestMapping("announceCardLight")
    public HashMap<String, String> announceCardLight(){

        HashMap<String, String> response = new HashMap<String, String>();

        Game g = getLastGame();
        Player p = g.getCurrentPlayer();
        Board b = getLastBoard();

        Card c = b.getCards().get(p.getPosition());

        String level = "";

        if (c instanceof Property) {
            Property property = (Property)c;

            if (property.getUser() == null){
                response.put(
                    "response",
                    "Vous arrivez sur : " + property.getName() + " dont le prix du terrain est de " + property.getLandCost()
                );
            }
            else if (property.getUser().getName() != p.getName()) {
                response.put(
                    "response",
                    "Vous arrivez sur : " + property.getName() + " mais il appartient à un joueur adverse : " + property.getUser().getName()
                );
            }
            else if (property.getUser().getName() == p.getName()) {

                if (property.getLevel() == 1) {
                    level = " terrain plat";
                }
                else if (property.getLevel() == 2) {
                    level = " maison";
                }
                else if (property.getLevel() == 3) {
                    level = " hotel";
                }

                if (property.getLevel() < 3) {
                    response.put(
                        "response",
                        "Vous arrivez sur : " + property.getName() + " mais il vous appartient déjà avec un niveau : " + level + ". Rappel : une maison coûte : " + HOUSEPRICE + " et un hotel : " + HOSTELPRICE
                    );
                }
                else if (property.getLevel() == 3) {
                    response.put("response", "Vous arrivez sur : " + property.getName() + " mais il vous appartient déjà avec avec un niveau maximum");
                }
            }
        }

        else if (c instanceof Chance) {
            response.put(
                "response",
                "Vous arrivez sur : " + ((Chance)c).getName() + " c'est une carte chance."
            );
        }

        return response;
    }


}
