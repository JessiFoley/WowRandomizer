package com.jfreyberger;

import java.sql.*;
import java.util.Random;


public class Database {
	/*
	 * Opens the connection to the database
	 */
	private static Connection Connect() {
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER_NAME, Constant.PASSWORD);
			
			//System.out.print("DB Connected");
		} catch (SQLException | ClassNotFoundException e) {
			throw new Error("Problem", e);
		}
		
		return conn;
	}
	
	/*
	 * Closes the database connection
	 */
	private static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String[] addAlliedRaces(String faction, String[] raceList) {
		//Adds selected Alliance allied races
		if (faction.equals("Alliance")) {
			if (!Filter.isARDIDwarves()) {
				raceList = pop("Dark Iron Dwarf", raceList);
			}
			if (!Filter.isARVElves()) {
				raceList = pop("Void Elf", raceList);
			}
			if (!Filter.isARLDraenei()) {
				raceList = pop("Lightforged Draenei", raceList);
			}
			if (!Filter.isARKTHumans()) {
				raceList = pop("Kul Tiran", raceList);
			}
			if (!Filter.isARMechagnomes()) {
				raceList = pop("Mechagnome", raceList);
			}
			if (Filter.isPreferAR() && raceList.length > 7) {
				raceList = pop("Human", raceList);
				raceList = pop("Dwarf", raceList);
				raceList = pop("Night Elf", raceList);
				raceList = pop("Gnome", raceList);
				raceList = pop("Draenei", raceList);
				raceList = pop("Worgen", raceList);
				raceList = pop("Alliance Pandaren", raceList);
			}
		//Removes unselected Horde allied races
		} else if (faction.equals("Horde")) {
			if (!Filter.isARHTauren()) {
				raceList = pop("Highmountain Tauren", raceList);
			}
			if (!Filter.isARMOrcs()) {
				raceList = pop("Mag\'har Orc", raceList);
			}
			if (!Filter.isARNightborne()) {
				raceList = pop("Nightborne", raceList);
			}
			if (!Filter.isARZTrolls()) {
				raceList = pop("Zandalari Troll", raceList);
			}
			if (!Filter.isARVulpera()) {
				raceList = pop("Vulpera", raceList);
			}
			if (Filter.isPreferAR() && raceList.length > 7) {
				raceList = pop("Orc", raceList);
				raceList = pop("Undead", raceList);
				raceList = pop("Tauren", raceList);
				raceList = pop("Troll", raceList);
				raceList = pop("Blood Elf", raceList);
				raceList = pop("Goblin", raceList);
				raceList = pop("Horde Pandaren", raceList);
			}
		}
		
		return raceList;
	}
	
	/*
	 * Adds a new String to a String array and returns the modified array
	 *
	private static String[] push(String addition, String[] oldStringArray) {
		String[] newStringArray = new String[oldStringArray.length + 1];
		
		System.arraycopy(oldStringArray, 0, newStringArray, 0, oldStringArray.length);
		newStringArray[newStringArray.length-1] = addition;
		
		return newStringArray;
	}*/
	
	/*
	 * Removes a String from a String array and returns the modified array
	 */
	private static String[] pop(String subtraction, String[] oldStringArray) {
		String[] newStringArray = null;
		int count = 0;
		boolean found = false;
		
		if (oldStringArray != null && subtraction != null) {
			for (int i = 0; i < oldStringArray.length; i++) {
				if (oldStringArray[i].equals(subtraction)) {
					newStringArray = new String[oldStringArray.length - 1];
					found = true;
				}
			}
			
			if (found) {
				for (int i = 0; i < oldStringArray.length; i++) {
					if (!oldStringArray[i].equals(subtraction)) {
						newStringArray[count] = oldStringArray[i];
						count++;
					}
				}
				
				return newStringArray;
			} else {
				return oldStringArray;
			}
		} else {
			return oldStringArray;
		}
		
		
	}
	
	/*
	 * Gets the races list from the database based on chosen faction and allied race status
	 */
	public static String[] getRace(String faction) {
		String[] returnRace = null;
		String[] raceList = null;
		
		//open database connection
		Connection conn = Connect();
		
		try {
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("SELECT race FROM \"tfreyberger/WoWRandomizer\".\"racelist\" WHERE FACTION LIKE \'" + faction + "\'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();

			rs.last();
			raceList = new String [rs.getRow()];
			rs.first();
			
			for(int c = 0; c < raceList.length; c++) {
				raceList[c] = rs.getString(1);
				rs.next();
			}
		} catch (SQLException e) {
			throw new Error("Problem: Race", e);
		}
		
		//close database connection
		closeConnection(conn);
		
		returnRace = addAlliedRaces(faction, raceList);
		return returnRace;
	}
	
	/*
	 * Gets the spec list from the database based on race
	 */
	public static String[] getSpec(String role, String race) {
		String[] specList = null;
		Connection conn = Connect();
		
		race = race.replace(' ', '_');
		race = race.replace("\'", "");
		try {
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("SELECT spec FROM \"tfreyberger/WoWRandomizer\".\"classlist\" WHERE role LIKE \'" + role + "\' AND " + race + " = TRUE", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.last();
			specList = new String [rs.getRow()];
			rs.first();
			
			for(int i = 0; i < specList.length; i++) {
				specList[i] = rs.getString(1);
				rs.next();
			}
		} catch (SQLException e) {
			throw new Error("Problem: Spec", e);
		}
		
		//close database connection
		closeConnection(conn);
		
		return specList;
	}
	
	/*
	 * Gets the class list from the database based on selected role
	 */
	public static String getClassBySpec(String spec, String race) {
		String[] classList = null;
		Connection conn = Connect();
		
		race = race.replace(' ', '_');
		race = race.replace("\'", "");
		try {
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("SELECT class FROM \"tfreyberger/WoWRandomizer\".\"classlist\" WHERE spec LIKE \'" + spec + "\' AND " + race + " = TRUE", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.last();
			classList = new String [rs.getRow()];
			rs.first();
			
			for(int i = 0; i < classList.length; i++) {
				classList[i] = rs.getString(1);
				rs.next();
			}
		} catch (SQLException e) {
			throw new Error("Problem: Class", e);
		}
		
		//close database connection
		closeConnection(conn);
		
		if (classList.length > 1) {
			Random rand = new Random();
			
			return classList[rand.nextInt(classList.length)];
		} else {
			return classList[0];
		}
	}

}
