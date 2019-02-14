package com.monomalpoly.api.chance;

import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.board.Board;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Chance extends Card {

    private String name;

    private String cardType;

    public Chance() {

    }

    public Chance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String action(Player player) {
        Random r = new Random();
        int result = r.nextInt(5);

        if (result >= 2) {
            player.removeToBalance(50);
            return "La banque te prend 50 euros, cheh ! ";
        } else {
            player.addToBalance(50);
            return "Tu gagnes 50 balles, gégé le sang. Mais tékaté ça n'arrivera pas souvent. ";
        }
    }
}
