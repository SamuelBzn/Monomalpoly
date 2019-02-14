package com.monomalpoly.api.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.chance.Chance;
import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.player.PlayerRepository;
import com.monomalpoly.api.property.Property;
import com.monomalpoly.api.property.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import java.util.Random;

@Service
public class ForwardService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public String outOfJail(Player user) {
        if (user.getInJail() == true && user.getNbToursToGo() > 0) {
            user.setNbToursToGo(user.getNbToursToGo() -1);

            Dice d = Dice.draw();

            if (d.getIsDouble() == true) {
                user.setInJail(false);
                user.setNbToursToGo(0);

                return "Vous avez fait un double, vous pouvez sortir de prison. ";
            } else {
                if(user.getNbToursToGo() == 0) {
                    user.setInJail(false);

                    return "Vous avez purgé votre peine, vous pouvez sortir de prison. ";
                } else {
                    user.getGame().setNextCurrentPlayer();

                    return "Il vous reste " + user.getNbToursToGo() + " tours à attendre car vous n'avez pas fait de double avec ce lancé. "
                        + user.getGame().nextPlayerMessage();
                }
            }
        }
        return " ";
    }

    public String forward(Player user, Dice d) {
        List<Card> cards = user.getGame().getBoard().getCards();
        int totalCases = cards.size();
        String message = d.getMessage();

        // Si le joueur courant est en prison on essaie de voir
        // s'il peut en sortir ou non.
        if (user.getInJail() == true)
            return outOfJail(user);

        int position = user.getPosition();
        int balance  = user.getBalance();

        if (position + d.getValue() >= totalCases) {
            position = (position + d.getValue()) % totalCases;
            user.setNbToursToGo(user.getNbToursToGo() + 1);
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
                position = 10;
                user.setInJail(true);
                user.setNbToursToGo(3);

                message += "Vous êtes sur la case Aller en prison. Les N dèques vous y amènent pour 3 tours. Le seul moyen pour vous de sortir en avance sera de faire un double. ";
            }

            // Case prison (simple visite) ou parking gratuit
            if (property.getName().equals("PRISON") || property.getName().equals("PARKING GRATUIT")) {
                message += "Vous êtes sur la case " + property.getName() + ". Vous êtes tranquille pour ce tour! ";
            }

            // Possibilité d'acheter
            if (property.isBuyable() == true && property.getUser() == null) {
                if (balance >= property.getLandCost()) {
                    message += "Vous pouvez acheter " + property.getName() + " pour un montant de " + property.getLandCost() + " euros."
                    + " Pour acheter dites Oui je veux acheter le terrain. Sinon dites Non je ne veux pas acheter le terrain. ";
                    user.getGame().setState("attente_achat");
                } else {
                    message += "Vous êtes sur la case " + property.getName() + ". Vous ne pouvez pas l'acheter car vous n'avez pas assez d'argent. ";
                }
            // Terrain déjà possédé par un autre joueur
            } else if (property.getUser() != null
                && property.getUser().getId() != user.getId()) {
                message += "Vous devez payer " + property.getLoyer() + " euros de loyer à " + property.getUser().getName() + ". ";

                // Gestion de la balance et des ventes auto
                if (balance >= property.getLoyer()) {
                    // Paiement instantané
                    balance -= property.getLoyer();
                    property.getUser().addToBalance(property.getLoyer());
                } else {
                    // Vente auto
                    message += "Vous n’avez pas assez d’argent n’est ce pas ?! Il va falloir vendre une ou plusieurs propriétés, cheh! Je vais m’en occuper. ";

                    int accumulator  = 0;
                    boolean solvable = false;

                    Iterator<Property> iterator = user
                        .getProperties()
                        .iterator();

                    ArrayList<Property> toSell = new ArrayList<>();

                    while (iterator.hasNext() && !solvable) {
                        Property p = iterator.next();

                        accumulator += p.getLandCost();

                        if (p.getNbHotels() == 1) {
                            accumulator += Property.HOSTELPRICE;
                        } else if (p.getNbHouses() >= 1) {
                            accumulator += p.getNbHouses() * Property.HOUSEPRICE;
                        }

                        toSell.add(p);

                        solvable = accumulator >= property.getLoyer();
                    }

                    toSell.forEach((x) -> {
                        x.getUser().getProperties().remove(x);
                        playerRepository.save(x.getUser());

                        x.setNbHouses(0);
                        x.setNbHotels(0);
                        x.setUser(null);

                        propertyRepository.save(x);
                    });

                    property.getUser().addToBalance(accumulator);
                    playerRepository.save(property.getUser());
                }
            } else if (property.getUser() == user) {
                message += "Vous êtes chez vous. Souhaitez vous améliorer votre propriété ? Si oui dites Oui je veux améliorer, sinon dites Non je ne veux pas améliorer. ";
                user.getGame().setState("attente_amelioration");
            }
        } else if (current instanceof Chance) {
            message += "Vous tombez sur une case Chance. ";

            Random r = new Random();
            int result = r.nextInt(5);

            if (result >= 2) {
                balance -= 50;
                message += "La banque te prend 50 euros, cheh ! ";
            } else {
                balance += 50;
                message += "Tu gagnes 50 balles, gégé le sang. Mais tékaté ça n'arrivera pas souvent. ";
            }
        }

        if (!user.getGame().getState().equals("attente_achat") &&
                !user.getGame().getState().equals("attente_amelioration")) {
            user.getGame().setNextCurrentPlayer();

            message += user.getGame().nextPlayerMessage();
        }

        user.setBalance(balance);
        user.setPosition(position);

        return message;
    }
}
