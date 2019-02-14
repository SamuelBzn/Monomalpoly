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
}
