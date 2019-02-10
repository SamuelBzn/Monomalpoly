package com.monomalpoly.api.game;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

import com.monomalpoly.api.player.Player;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;
    private int nbUsers;
    private int countNbUsers;
    private String state;
    @OneToOne
    private Player currentPlayer;

    public Game() {
        this.currentPlayer = null;
    }

    public int getNbUsers() {
        return nbUsers;
    }

    public int getCountNbUsers() {
        return countNbUsers;
    }

    public String getState() {
        return state;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setNbUsers(int nbUsers) {
        this.nbUsers = nbUsers;
    }

    public void setCountNbUsers(int countNbUsers) {
        this.countNbUsers = countNbUsers;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
