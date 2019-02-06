package com.monomalpoly.api.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int balance;
    private int properties;
    private int houses;
    private int hotels;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
        this.balance = 1500;
        this.properties = 0;
        this.houses = 0;
        this.hotels = 0;
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

    public int getProperties() {
        return properties;
    }

    public int getHouses() {
        return houses;
    }

    public int getHotels() {
        return hotels;
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

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public void setHotels(int hotels) {
        this.hotels = hotels;
    }

    public void addToBalance(int amount) {
        this.balance += amount;
    }

    public void removeToBalance(int amount) {
        this.balance = (this.balance - amount > 0) ? balance - amount : 0;
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
}
