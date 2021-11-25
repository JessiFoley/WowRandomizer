package com.jfreyberger;

import java.util.Random;

public class CharRandomizer {
	/*
	private static final String[] CLASS_LIST = {"Warrior","Paladin","Hunter","Rogue","Priest","Shaman","Mage","Warlock","Monk","Druid","Demon Hunter","Death Knight"};
	private static final String[] A_RACE_LIST = {"Human", "Dwarf", "Night Elf", "Gnome", "Draenei", "Worgen", "Alliance Pandaren"};
	private static final String[] A_ALLIED_RACE_LIST = {"Void Elf", "Lightforged Draenei", "Dark Iron Dwarf", "Kul Tiran", "Mechagnome"};
	private static final String[] H_RACE_LIST = {"Orc", "Undead", "Tauren", "Troll", "Blood Elf", "Goblin", "Horde Pandaren"};
	private static final String[] H_ALLIED_RACE_LIST = {"Nightborne", "Highmountain Tauren", "Mag\'har Orc", "Zandalari Troll", "Vulpera"};
	*/
	
	public static String rollRace(String faction) {
		String[] race = Database.getRace(faction, Filter.isPreferAR());
		
		return roll(race);
	}
	
	public static String rollClass(String charClass) {
		return "Warrior";
		//return roll(Database.getClass(charClass));
	}
	
	public static String rollSpec(String race) {
		//return roll(Database.getSpec(race));
		return "Fury";
	}
	
	public static String rollFaction(Boolean alliance, Boolean horde) {
		Random rand = new Random();
		
		if (alliance == horde) {
			if (rand.nextInt(100) > 49) {
				return ("Alliance");
			} else {
				return ("Horde");
			}
		} else if (alliance == true) {
			return "Alliance";
		} else {
			return "Horde";
		}
	}
	
	private static String roll(String[] list) {
		Random rand = new Random();
		
		return list[rand.nextInt(list.length)];
	}
}
