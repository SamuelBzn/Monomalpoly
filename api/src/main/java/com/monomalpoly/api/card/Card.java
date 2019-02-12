package com.monomalpoly.api.card;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.monomalpoly.api.board.Board;
import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.player.Player;

@Entity
public abstract class Card {
    @Id
    @GeneratedValue
    protected int id;

    @ManyToOne
    protected Board board;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String action(Player player) {
        return "action";
    }
}
