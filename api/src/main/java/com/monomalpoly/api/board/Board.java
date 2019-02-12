package com.monomalpoly.api.board;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MetaValue;

import java.util.List;
import java.util.ArrayList;

import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.chance.Chance;
import com.monomalpoly.api.property.Property;

@Entity
public class Board {

    @Id
    @GeneratedValue
    private int id;

    @ManyToAny(
        metaColumn = @Column( name = "cardType" )
    )
    @AnyMetaDef(idType = "long", metaType = "string",
        metaValues = {
            @MetaValue(targetEntity = Chance.class, value = "chance"),
            @MetaValue(targetEntity = Property.class, value = "property")
    })
    @Cascade( { org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(
        name = "board_cards",
        joinColumns = @JoinColumn( name = "board_id" ),
        inverseJoinColumns = @JoinColumn( name = "card_id" )
    )
    private List<Card> cards;

    public Board() {
        this.cards = new ArrayList<Card>();
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

    public void setCards(ArrayList<Card> list) {
        this.cards = list;
    }

    public void setCard(int index, Card card) {
        this.cards.set(index, card);
    }
}
