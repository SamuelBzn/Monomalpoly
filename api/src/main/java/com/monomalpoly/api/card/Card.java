package com.monomalpoly.api.card;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import com.monomalpoly.api.board.Board;
import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.player.Player;

@Entity
public abstract class Card {
    @Id
    @GeneratedValue
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
