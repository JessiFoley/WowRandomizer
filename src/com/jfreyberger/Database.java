package com.jfreyberger;

import java.sql.*;


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
		//Adds selected Alliance allied races if allied race is not preferred
		if (faction.equals("Alliance") && !Filter.isPreferAR()) {
			if (Filter.isARDIDwarves()) {
				raceList = push("Dark Iron Dwarf", raceList);
			}
			if (Filter.isARVElves()) {
				raceList = push("Void Elf", raceList);
			}
			if (Filter.isARLDraenei()) {
				raceList = push("Lightforged Draenei", raceList);
			}
			if (Filter.isARKTHumans()) {
				raceList = push("Kul Tiran", raceList);
			}
			if (Filter.isARMechagnomes()) {
				raceList = push("Mechagnome", raceList);
			}
		//Adds selected Horde allied races if allied race is not preferred
		} else if (faction.equals("Horde") && !Filter.isPreferAR()){
			if (Filter.isARHTauren()) {
				raceList = push("Highmountain Tauren", raceList);
			}
			if (Filter.isARMOrcs()) {
				raceList = push("Mag\'har Orc", raceList);
			}
			if (Filter.isARNightborne()) {
				raceList = push("Nightborne", raceList);
			}
			if (Filter.isARZTrolls()) {
				raceList = push("Zandalari Troll", raceList);
			}
			if (Filter.isARVulpera()) {
				raceList = push("Vulpera", raceList);
			}
		//Removes unselected Alliance allied races if allied race is preferred
		} else if (faction.equals("Alliance") && Filter.isPreferAR()) {
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
		//Removes unselected Horde allied races if allied race is preferred
		} else if (faction.equals("Horde") && Filter.isPreferAR()) {
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
		}
		
		return raceList;
	}
	
	/*
	 * Adds a new String to a String array and returns the modified array
	 */
	private static String[] push(String addition, String[] oldStringArray) {
		String[] newStringArray = new String[oldStringArray.length + 1];
		
		System.arraycopy(oldStringArray, 0, newStringArray, 0, oldStringArray.length);
		newStringArray[newStringArray.length-1] = addition;
		
		return newStringArray;
	}
	
	/*
	 * Removes a String from a String array and returns the modified array
	 */
	private static String[] pop(String subtraction, String[] oldStringArray) {
		String[] newStringArray = new String[oldStringArray.length - 1];
		int count = 0;
		
		for (int i = 0; i < oldStringArray.length; i++) {
			if (!oldStringArray[i].equals(subtraction)) {
				newStringArray[count] = oldStringArray[i];
				count++;
			}
		}
		
		return newStringArray;
	}
	
	/*
	 * Gets the races list from the database based on chosen faction and allied race status
	 */
	public static String[] getRace(String faction, boolean allied) {
		String[] returnRace = null;
		String[] raceList = null;
		
		//open database connection
		Connection conn = Connect();
		
		try {
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("SELECT race FROM \"tfreyberger/WoWRandomizer\".\"racelist\" WHERE FACTION LIKE \'" + faction + "\' AND ALLIED = " + allied, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
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
	 * Gets the class list from the database based on spec
	 */
	public static String[] getClass(String spec) {
		return null;
	}
	
	/*
	 * Gets the spec list from the database based on race
	 */
	public static String[] getSpec(String race) {
		return null;
	}
	
	/*
	 * Gets the role list from the database based on selected role
	 */
	public static String[] getRole(String role) {
		return null;
	}

}
