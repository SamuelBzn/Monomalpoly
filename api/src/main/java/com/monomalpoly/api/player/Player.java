package com.monomalpoly.api.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.dice.Dice;
import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.chance.Chance;
import com.monomalpoly.api.property.Property;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int balance;
    private int position;
    private int nbTours;

    // Gestion prison
    private boolean inJail;
    private int nbToursToGo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Property> properties;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
        this.balance = 1500;
        this.position = 0;
        this.properties = new ArrayList<Property>();
        this.inJail = false;
        this.nbToursToGo = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public int getPosition() {
        return position;
    }

    public boolean getInJail() {
        return inJail;
    }

    public int getNbToursToGo() {
        return nbToursToGo;
    }

    @JsonIgnore
    public Game getGame() {
        return game;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setNbToursToGo(int nbToursToGo) {
        this.nbToursToGo = nbToursToGo;
    }

    public void addToBalance(int amount) {
        this.balance += amount;
    }

    public void removeToBalance(int amount) {           // cas d'une amélioration ou achat d'une case neutre : solde retiré du compte mais pas de son capital
        this.balance = (this.balance - amount > 0) ? balance - amount : 0;
    }

    public void removeToBalanceAndCapital(int amount) {  // cas d'un achat d'une propriété adverse ou paiement d'une rente.
        this.balance = (this.balance - amount > 0) ? balance - amount : 0;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Permet de calculer la capital de l'utilisateur.
     */
    public int getCapital() {
        if (properties != null) {

            int propertiesValue = properties
                .stream()
                .mapToInt(x -> x.getValue())
                .sum();

            return this.balance + propertiesValue;
        } else {
            return this.balance;
        }
    }
}
