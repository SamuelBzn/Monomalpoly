package com.monomalpoly.api.property;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.monomalpoly.api.player.Player;
import com.monomalpoly.api.card.Card;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Property extends Card {

	public static final int HOUSEPRICE = 100;
	public static final int HOSTELPRICE = 300;

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String nature; // normal, special, etc
    private boolean buyable;
    @ManyToOne
    private Player user;
    private int landCost; // prix terrain
    private int cost;  // prix terrain + aménagement
    private int level; // 0 = pas acheter 1 = terrain, 2 = maison, 3 = hotel
    private String color;
    private int nbHouses;
    private int nbHotels;

    public Property() {

    }

	public Property(String name, String nature, boolean buyable, Player user, int landCost) {
		this.name = name;
		this.nature = nature;
		this.buyable = buyable;
		this.user = user;
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

	public int getLevel() {
		return level;
	}

	public String getColor() {
		return color;
	}

	public int getCost() {
		return cost;
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

	public void setColor(String color) {
		this.color = color;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setCost(int cost) {
		this.cost = cost;
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

	public String action(Player player) {
		if (user == player) {
			return "T'es chez toi le sang. ";
		} else {
			player.removeToBalance(10);

			return "Cheh, tu dois payer " + landCost + " à " + player.getName() + " . ";
		}
	}

	public void addToHotels(int amount) {
    this.nbHotels += amount;
  }

  public void removeToHotels(int amount) {
    this.nbHotels = (this.nbHotels - amount > 0) ? nbHotels - amount : 0;
	}

	public void addToHouses(int amount) {
    this.nbHouses += amount;
  }

	public void removeToHouses(int amount) {
    this.nbHouses = (this.nbHouses - amount > 0) ? nbHouses - amount : 0;
  }

	public int getLoyer() {
		switch(nbHouses) {
		  case 1:
		    return getLandCost() * 4;
		  case 2:
		    return getLandCost() * 12;
		  case 3:
		    return getLandCost() * 28;
		  case 4:
		    return getLandCost() * 34;
		  default:
		    switch(nbHotels) {
		    	case 1:
		    		return getLandCost() * 40;
		    	default:
		    		return getLandCost();
		    }
		}
	}
}
