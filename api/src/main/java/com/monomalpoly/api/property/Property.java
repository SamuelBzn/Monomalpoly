package com.monomalpoly.api.property;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.monomalpoly.api.player.Player;


@Entity
public class Property {

	public static final int HOUSEPRICE = 100;
	public static final int HOSTELPRICE = 300;

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String nature; // normal, special, etc
    private boolean buyable;
    @OneToOne
    private Player user;
    private int landCost; // prix terrain
    private int cost;  // prix terrain + aménagement
    private int level; // 0 = pas acheter 1 = terrain, 2 = maison, 3 = hotel
    private String color;

    public Property() {

    }

	public Property(String name, String nature, boolean buyable, Player user, int landCost) {
		super();
		this.name = name;
		this.nature = nature;
		this.buyable = buyable;
		this.user = null;
		this.landCost = landCost;
		this.cost = landCost;
		this.level = 0;
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

	public int getLandCost() {
		return landCost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}

	public void setUser(Player user) {
		this.user = user;
	}

	public void setLandCost(int landCost) {
		this.landCost = landCost;
	}

	public void improve(Player player) {
		if (this.level < 3) {
			switch(this.level) {
			case 1: this.level++; this.cost = (int) (this.landCost * 0.1); player.removeToBalance(HOUSEPRICE); // augmentation de 10% du prix du terrain si maison (level 1)
			break;
			case 2: this.level++; this.cost = (int) (this.landCost * 0.3); player.removeToBalance(HOSTELPRICE);// augmentation de 30% du prix du terrain si hotel (level 2)
			break;			
			}
		}
	}
	
	public void downgrade(Player player){
		if (this.level > 1) {
			switch(this.level) {
			case 3: this.level--; this.cost = (int) (this.landCost * 0.1); player.addToBalance(HOSTELPRICE);//passage au level 2
			break;
			case 2: this.level--; this.cost = (int) (this.landCost); player.addToBalance(HOUSEPRICE);// passage au level 1
			break;			
			}
		}
	}

	public void buy(Player player) {
		this.level = 1; // achat du terrain
		player.removeToBalance(landCost);
	}
	
	public void sell(Player player) {
		switch(this.level) {
		case 3: player.addToBalance(HOSTELPRICE + HOUSEPRICE + this.landCost);
		break;
		case 2: player.addToBalance(HOUSEPRICE + this.landCost);
		break;
		case 1: player.addToBalance(this.landCost);
		break;
		}
		this.level = 0; // vend le terrain
	}
}
