package com.monomalpoly.api.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;
    private int nbUsers;
    private int countNbUsers;
    private String state;

    public Game() {

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

    public void setNbUsers(int nbUsers) {
        this.nbUsers = nbUsers;
    }

    public void setCountNbUsers(int countNbUsers) {
        this.countNbUsers = countNbUsers;
    }

    public void setState(String state) {
        this.state = state;
    }
}
