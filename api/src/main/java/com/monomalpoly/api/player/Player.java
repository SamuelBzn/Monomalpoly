package com.monomalpoly.api.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.dice.Dice;
import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.property.Property;

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

    public void setGame(Game game) {
        this.game = game;
    }

    public String forward(Dice d) {
        System.out.println(this.game);
        System.out.println(this.game.getBoard());

        List<Card> cards = this.game.getBoard().getCards();
        int totalCases = cards.size();
        String message = d.getMessage();

        System.out.println(totalCases);

        if (position + d.getValue() > totalCases) {
            position = totalCases % (position + d.getValue());
            nbTours += 1;
            balance += 200;

            message += "Vous passez par la case départ et touchez 200. ";
        } else {
            position += d.getValue();
        }

        Card current = cards.get(position);

        //current.action(this);

        if (current instanceof Property) {
            Property property = (Property)current;

            if (property.isBuyable() == true && property.getUser() == null) {
                message += "Vous pouvez acheter cette propriété pour un montant de " + property.getLandCost() + " euros. ";
            } else if (property.getUser() != null) {
                message += "Vous devez payer " + property.getLoyer() + " euros de loyer à " + property.getUser().getName() + ". ";

                if (this.getBalance() >= property.getLoyer()) {
                    this.removeToBalance(property.getLoyer());
                    property.getUser().addToBalance(property.getLoyer());
                } else {
                    message += "Vous n’avez pas assez d’argent n’est ce pas?! Il va falloir vendre une ou plusieurs propriétés, cheh! Je vais m’en occuper. ";
                    // Vendre jusqu’à avoir assez de cash, sinon faillite et donner tout l’argent post vente au joueur devant recevoir le loyer
                    property.getUser().addToBalance(this.getBalance());
                }
            } else if (property.getUser() == this) {
                message += "Vous êtes chez vous. Souhaitez vous améliorer votre propriété? ";
            }
        }

        return message;
    }
}
