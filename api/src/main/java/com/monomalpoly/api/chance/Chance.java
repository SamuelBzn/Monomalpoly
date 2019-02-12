package com.monomalpoly.api.chance;

import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.board.Board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import java.util.HashMap;
import java.util.Random;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Chance extends Card {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne
    protected Board board;

    private String cardType;

    public Chance() {

    }

    public Chance(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String action(Player player) {
        Random r = new Random();
        int result = r.nextInt((1 - 0) + 1) + 0;

        HashMap<String, String> response = new HashMap<String, String>();

        if (result == 0) {
            player.removeToBalance(50);
            return "La banque te prend 50 euros, cheh ! ";
        } else {
            player.addToBalance(50);
            return "Tu gagnes 50 balles, gégé le sang. Mais tékaté ça n'arrivera pas souvent. ";
        }
    }
}
