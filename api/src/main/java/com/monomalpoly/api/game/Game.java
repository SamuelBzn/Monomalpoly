package com.monomalpoly.api.game;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.card.Card;
import com.monomalpoly.api.board.Board;

import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;
    private int nbUsers;
    private int countNbUsers;
    private String state;
    @OneToOne(cascade = CascadeType.ALL)
    private Player currentPlayer;
    @OneToOne(cascade = CascadeType.ALL)
    private Board board;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Card> getCards() {
        return this.board.getCards();
    }
}
