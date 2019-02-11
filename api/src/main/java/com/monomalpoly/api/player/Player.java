package com.monomalpoly.api.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.dice.Dice;
import com.monomalpoly.api.card.Card;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int balance;
    private int capital;
    private int properties;
    private int houses;
    private int hotels;
    private int position;
    private int nbTours;

    @ManyToOne
    private Game game;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
        this.balance = 1500;
        this.capital = this.balance;
        this.properties = 0;
        this.houses = 0;
        this.hotels = 0;
        this.position = 0;
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

    public int getCapital() {
        return capital;
    }

    public int getProperties() {
        return properties;
    }

    public int getHouses() {
        return houses;
    }

    public int getHotels() {
        return hotels;
    }

    public int getPosition() {
        return position;
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

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public void setHotels(int hotels) {
        this.hotels = hotels;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addToBalance(int amount) {
        this.balance += amount;
        this.capital += amount;
    }

    public void removeToBalance(int amount) {           // cas d'une amélioration ou achat d'une case neutre : solde retiré du compte mais pas de son capital
        this.balance = (this.balance - amount > 0) ? balance - amount : 0;
    }

    public void removeToBalanceAndCapital(int amount) {  // cas d'un achat d'une propriété adverse ou paiement d'une rente.
        this.balance = (this.balance - amount > 0) ? balance - amount : 0;
        this.capital = (this.capital - amount > 0) ? capital - amount : 0;
    }
    public void addToProperties(int amount) {
        this.properties += amount;
    }

    public void removeToProperties(int amount) {
        this.properties = (this.properties - amount > 0) ? properties - amount : 0;
    }

    public void addToHouses(int amount) {
        this.houses += amount;
    }

    public void removeToHouses(int amount) {
        this.houses = (this.houses - amount > 0) ? houses - amount : 0;
    }

    public void addToHotels(int amount) {
        this.hotels += amount;
    }

    public void removeToHotels(int amount) {
        this.hotels = (this.hotels - amount > 0) ? hotels - amount : 0;
    }

    public String forward(Dice d) {
        List<Card> cards = new ArrayList<>();//this.game.getBoard().getCards();
        int totalCases = cards.size();
        String message = d.getMessage();

        if (position + d.getValue() > totalCases) {
            position = totalCases % (position + d.getValue());
            nbTours += 1;
            balance += 200;

            message += "Vous passez par la case départ et touchez 200. ";
        } else {
            position += d.getValue();
        }

        Card current = cards.get(position);

        current.action(this);

        return "action";
    }
}
