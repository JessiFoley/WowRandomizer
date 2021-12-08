package com.jfreyberger.backend;

import java.sql.Connection;

public class Character {
	private String faction;
	private String role;
	private String charClass;
	private String spec;
	private String race;
	
	//instantiates a new object by using CharRandomizer to get information for the variables
	public Character() {
		Connection conn = Database.Connect();
		
		setFaction(CharRandomizer.rollFaction(Filter.isFacAlliance(), Filter.isFacHorde()));
		setRace(CharRandomizer.rollRace(conn, getFaction()));
		setRole(CharRandomizer.rollRole());
		setSpec(CharRandomizer.rollSpec(conn, getRole(), getRace()));
		setCharClass(CharRandomizer.rollClass(conn, getSpec(), getRace()));

		Database.closeConnection(conn);
	}

	//getters and setters for the variables
	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCharClass() {
		return charClass;
	}

	public void setCharClass(String charClass) {
		this.charClass = charClass;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

}
