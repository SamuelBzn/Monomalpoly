package com.monomalpoly.api.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.monomalpoly.api.game.Game;
import com.monomalpoly.api.dice.Dice;
import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.chance.Chance;
import com.monomalpoly.api.property.Property;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int balance;
    private int capital;
    private int position;
    private int nbTours;
    // Gestion prison
    private boolean inJail;
    private int nbToursToGo;

    @ManyToOne
    private Game game;

    @OneToMany
    private List<Property> properties;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
        this.balance = 1500;
        this.capital = this.balance;
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

    public int getCapital() {
        return capital;
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
        this.capital += amount;
    }

    public void removeToBalance(int amount) {           // cas d'une amélioration ou achat d'une case neutre : solde retiré du compte mais pas de son capital
        this.balance = (this.balance - amount > 0) ? balance - amount : 0;
    }

    public void removeToBalanceAndCapital(int amount) {  // cas d'un achat d'une propriété adverse ou paiement d'une rente.
        this.balance = (this.balance - amount > 0) ? balance - amount : 0;
        this.capital = (this.capital - amount > 0) ? capital - amount : 0;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String outOfJail() {
        if(this.inJail == true && this.nbToursToGo > 0) {
            this.nbToursToGo--;
            Dice d = Dice.draw();
            if(d.getIsDouble() == true) {
                this.inJail = false;
                this.nbToursToGo = 0;
                forward(d);
                return "Vous avez fait un double, vous pouvez sortir de prison. ";
            } else {
                if(this.nbToursToGo == 0) {
                    forward(d);
                    return "Vous avez purgé votre peine, vous pouvez sortir de prison. ";
                }
            }
        }
        return "Il vous reste " + this.nbToursToGo + " tours à attendre car vous n'avez pas fait de double avec ce lancé. ";
    } 

    public String forward(Dice d) {
        System.out.println(this.game);
        System.out.println(this.game.getBoard());

        List<Card> cards = this.game.getBoard().getCards();
        int totalCases = cards.size();
        String message = d.getMessage();

        System.out.println(totalCases);

        message += outOfJail();

        if (position + d.getValue() > totalCases) {
            position = totalCases % (position + d.getValue());
            nbTours += 1;
            balance += 200;

            message += "Vous passez par la case départ et touchez 200. ";
        } else {
            position += d.getValue();
        }

        Card current = cards.get(position);

        if (current instanceof Property) {
            Property property = (Property)current;

            // Va directement en prison pendant 3 tours cheh
            if(property.getName().equals("GO TO JAIL")) {
                Property prison = (Property)(cards.stream().filter((card) -> {
                    return ((Property)card).getName() == "PRISON";
                }).findFirst().get());
                int id = prison.getId();
                this.position = cards.indexOf(id);
                this.inJail = true;
                this.nbToursToGo = 3;
                message += "Vous êtes sur la case Aller en prison. Les N dèques vous y amènent pour 3 tours. Le seul moyen pour vous de sortir en avance sera de faire un double. ";
            }

            // Case prison (simple visite) ou parking gratuit
            if(property.getName().equals("PRISON") || property.getName().equals("PARKING GRATUIT")) {
                message += "Vous êtes sur la case " + property.getName() + ". Vous êtes tranquille pour ce tour! ";
            }

            // Possibilité d'acheter
            if (property.isBuyable() == true && property.getUser() == null) {
                message += "Vous pouvez acheter " + property.getName() + " pour un montant de " + property.getLandCost() + " euros." 
                + " Pour acheter dites Oui je veux acheter le terrain. Sinon dites Non je ne veux pas acheter le terrain.";
                this.game.setState("attente_achat");
            // Terrain déjà possédé par un autre joueur
            } else if (property.getUser() != null) {
                message += "Vous devez payer " + property.getLoyer() + " euros de loyer à " + property.getUser().getName() + ". ";

                // Gestion de la balance et des ventes auto
                if (this.getBalance() >= property.getLoyer()) {
                    // Paiement instantané
                    this.removeToBalance(property.getLoyer());
                    property.getUser().addToBalance(property.getLoyer());
                } else {
                    // Vente auto
                    message += "Vous n’avez pas assez d’argent n’est ce pas ?! Il va falloir vendre une ou plusieurs propriétés, cheh! Je vais m’en occuper. ";

                    int accumulator    = this.balance;
                    boolean unsolvable = true;

                    Iterator<Property> iterator = this
                        .getProperties()
                        .iterator();

                    while (iterator.hasNext() && unsolvable) {
                        Property p = iterator.next();

                        if (p.getNbHotels() == 1) {
                            accumulator += Property.HOSTELPRICE;
                        } else if (p.getNbHouses() >= 1) {
                            accumulator += p.getNbHouses() * Property.HOUSEPRICE;
                        }

                        unsolvable = accumulator >= property.getLoyer();
                    }

                    property.getUser().addToBalance(this.getBalance());
                }
            } else if (property.getUser() == this) {
                message += "Vous êtes chez vous. Souhaitez vous améliorer votre propriété ? Si oui dites Oui je veux améliorer, sinon dites Non je ne veux pas améliorer. ";
                this.game.setState("attente_amelioration");
            }
        } else if (current instanceof Chance) {
            message += "Vous tombez sur une case Chance. ";
            message += current.action(this);
        }

        return message;
    }
}
