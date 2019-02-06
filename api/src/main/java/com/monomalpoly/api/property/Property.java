package com.monomalpoly.api.property;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.monomalpoly.api.player.Player;


@Entity
public class Property {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String nature;
    private boolean buyable;
    @OneToOne
    private Player user;
    private int rent;

    public Property() {

    }

	public Property(String name, String nature, boolean buyable, Player user, int rent) {
		super();
		this.name = name;
		this.nature = nature;
		this.buyable = buyable;
		this.user = user;
		this.rent = rent;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNature() {
		return nature;
	}

	public boolean isBuyable() {
		return buyable;
	}

	public Player getUser() {
		return user;
	}

	public int getRent() {
		return rent;
	}
}
