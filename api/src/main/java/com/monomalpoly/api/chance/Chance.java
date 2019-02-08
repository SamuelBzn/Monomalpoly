package com.monomalpoly.api.chance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chance {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    // private int effect;
    // private String message;

    public Chance() {

    }

    public Chance(String name) {
        this.name = name;
        this.effect = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // public int getEffect() {
    //     return effect;
    // }

    // public String getMessage() {
    //     return message;
    // }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public void setEffect(int effect) {
    //     this.effect = effect;
    // }

    // public void setMessage (String message) {
    //     this.message = message;
    // }
}
