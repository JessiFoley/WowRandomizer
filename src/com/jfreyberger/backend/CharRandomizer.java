package com.jfreyberger.backend;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

public class CharRandomizer {
	
	public static String rollRace(Connection conn, String faction) {
		return roll(Database.getRaceList(conn, faction));
	}
	
	public static String rollClass(Connection conn, String spec, String race) {
		return Database.getClassBySpec(conn, spec, race);
	}
	
	public static String rollSpec(Connection conn, String role, String race) {
		return roll(Database.getSpec(conn, role, race));
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
	
	private static String roll(ArrayList<String> list) {
		Random rand = new Random();
		
		return list.get(rand.nextInt(list.size()));
	}
}
