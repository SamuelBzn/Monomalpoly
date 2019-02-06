package com.monomalpoly.api.case;

public class Case {

    @GeneratedValue
    private final int id;
    private final String name;
	private final String nature;
    private final boolean buyable;
    private final Player user;
    private final int rent;
    
	public Case(String name, String nature, boolean buyable, Player user, int rent) {
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