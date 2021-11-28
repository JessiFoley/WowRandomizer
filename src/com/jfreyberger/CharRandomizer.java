package com.jfreyberger;

import java.util.Random;

public class CharRandomizer {
	
	public static String rollRace(String faction) {
		String[] raceList = null;
		
		raceList = Database.getRace(faction);
		
		if (raceList == null)
			return null;
		else if (raceList.length < 2)
			return raceList[0];
		else
			return roll(raceList);
	}
	
	public static String rollClass(String spec, String race) {
		return Database.getClassBySpec(spec, race);
	}
	
	public static String rollSpec(String role, String race) {
		String[] specList = Database.getSpec(role, race);
		
		return roll(specList);
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
	
	public static String rollRole()  {
		Random rand = new Random();
		
		if ((Filter.isRoleDPS() && Filter.isRoleHealer() && Filter.isRoleTank()) || (!Filter.isRoleDPS() && !Filter.isRoleHealer() && !Filter.isRoleTank())) {
			switch (rand.nextInt(3)) {
			case 0:
				return "tank";
			case 1:
				return "healer";
			case 2:
				return "dps";
			default:
				return "dps";
			}
		} else if (Filter.isRoleDPS() && Filter.isRoleHealer()) {
			if (rand.nextInt(100) > 49)
				return "dps";
			else
				return "healer";
		} else if (Filter.isRoleDPS() && Filter.isRoleTank()) {
			if (rand.nextInt(100) > 49)
				return "dps";
			else
				return "tank";
		} else if (Filter.isRoleHealer() && Filter.isRoleTank()) {
			if (rand.nextInt(100) > 49)
				return "healer";
			else
				return "tank";
		} else if (Filter.isRoleTank()) {
			return "tank";
		} else if (Filter.isRoleHealer()) {
			return "healer";
		} else {
			return "dps";
		}
	}
	
	private static String roll(String[] list) {
		Random rand = new Random();
		
		return list[rand.nextInt(list.length)];
	}
}
