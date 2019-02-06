package com.monomalpoly.api.player;

public class Player {

    @GeneratedValue
    private final int id;
    private final String name;
    private final int balance;
    private final int properties;
    private final int houses;
    private final int hotels;

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

    public addToBalance(int amount) {
        this.balance += amount;
    }

    public removeToBalance(int amount) {
        (this.balance - amount > 0) ? this.balance -= amount : 0;
    }

    public addToProperties(int amount) {
        this.properties += amount;
    }

    public removeToProperties(int amount) {
        (this.properties - amount > 0) ? this.properties -= amount : 0;
    }

    public addToHouses(int amount) {
        this.houses += amount;
    }

    public removeToHouses(int amount) {
        (this.houses - amount > 0) ? this.houses -= amount : 0;
    }

    public addToHotels(int amount) {
        this.hotels += amount;
    }

    public removeToHotels(int amount) {
        (this.hotels - amount > 0) ? this.hotels -= amount : 0;
    }
}
