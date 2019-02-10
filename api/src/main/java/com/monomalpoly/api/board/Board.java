package com.monomalpoly.api.board;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

import com.monomalpoly.api.card.Card;

@Entity
public class Board {

    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy="board")
    List<Card> cards;

    public Board() {
        cards = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCards(ArrayList list) {
        this.cards = list;
    }

    public void setCard(int index, Card card) {
        this.cards.set(index, card);
    }
}
