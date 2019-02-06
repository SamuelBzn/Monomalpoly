package com.monomalpoly.api.player;

public class Player {

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
        (this.balance - amount > 0) ? this.balance -= amount : 0;
    }

    public void addToProperties(int amount) {
        this.properties += amount;
    }

    public void removeToProperties(int amount) {
        (this.properties - amount > 0) ? this.properties -= amount : 0;
    }

    public void addToHouses(int amount) {
        this.houses += amount;
    }

    public void removeToHouses(int amount) {
        (this.houses - amount > 0) ? this.houses -= amount : 0;
    }

    public void addToHotels(int amount) {
        this.hotels += amount;
    }

    public void removeToHotels(int amount) {
        (this.hotels - amount > 0) ? this.hotels -= amount : 0;
    }
}
