package com.monomalpoly.api.board;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Board {

    @Id
    @GeneratedValue
    private int id;
    // @OneToMany
    ArrayList cards;

    public Board() {
        cards = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public ArrayList getCards() {
        return cards;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCards(ArrayList list) {
        this.cards = list;
    }

    public void setCard(int index, Object object) {
        this.cards.set(index, object);
    }
}
