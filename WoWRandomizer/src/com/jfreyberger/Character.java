package com.jfreyberger;

public class Character {
	private String faction;
	private String role;
	private String charClass;
	private String spec;
	private String race;
	
	public Character() {
		setFaction(CharRandomizer.rollFaction(Filter.isFacAlliance(), Filter.isFacHorde()));
		setRace(CharRandomizer.rollRace(getFaction()));
		setRole(CharRandomizer.rollRole());
		setSpec(CharRandomizer.rollSpec(getRole(), getRace()));
		setCharClass(CharRandomizer.rollClass(getSpec(), getRace()));

		
		while (getRace() == null) {
			System.out.println("race not selected, trying again");
			setRace(CharRandomizer.rollRace(getFaction()));
			setSpec(CharRandomizer.rollSpec(getRole(), getRace()));
			setCharClass(CharRandomizer.rollClass(getSpec(), getRace()));
		}
	}

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
