package com.jfreyberger.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


class Database {
	/*
	 * Opens the connection to the database
	 */
	static Connection Connect() {
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
	static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * uses statement to get information from the database
	 * returns in a ArrayList<String> format
	 */
	private static ArrayList<String> getDatabaseInfoArrayList(Connection conn, String statement) {
		ArrayList<String> info = new ArrayList<String>();
		
		try {
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement(statement);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				info.add(rs.getString(1));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return info;
	}
	
	/*
	 * uses statement to get information from the database
	 * returns in a String[] format
	 */
	private static String[] getDatabaseInfo(Connection conn, String statement) {
		String[] data = null;
		
		try {
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement(statement, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.last();
			data = new String [rs.getRow()];
			rs.first();
			
			for(int i = 0; i < data.length; i++) {
				data[i] = rs.getString(1);
				rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	/*
	 * Removes races that are not selected by the filters
	 */
	private static ArrayList<String> alliedAdjust (String faction, ArrayList<String> raceList) {
		//Adds selected Alliance allied races
		if (faction.equals("Alliance")) {
			if (!Filter.isARDIDwarves()) {
				raceList.remove("Dark Iron Dwarf");
			}
			if (!Filter.isARVElves()) {
				raceList.remove("Void Elf");
			}
			if (!Filter.isARLDraenei()) {
				raceList.remove("Lightforged Draenei");
			}
			if (!Filter.isARKTHumans()) {
				raceList.remove("Kul Tiran");
			}
			if (!Filter.isARMechagnomes()) {
				raceList.remove("Mechagnome");
			}
			if (Filter.isPreferAR() && raceList.size() > 7) {
				raceList.remove("Human");
				raceList.remove("Dwarf");
				raceList.remove("Night Elf");
				raceList.remove("Gnome");
				raceList.remove("Draenei");
				raceList.remove("Worgen");
				raceList.remove("Alliance Pandaren");
			}
			//Removes unselected Horde allied races
		} else if (faction.equals("Horde")) {
			if (!Filter.isARHTauren()) {
				raceList.remove("Highmountain Tauren");
			}
			if (!Filter.isARMOrcs()) {
				raceList.remove("Mag\'har Orc");
			}
			if (!Filter.isARNightborne()) {
				raceList.remove("Nightborne");
			}
			if (!Filter.isARZTrolls()) {
				raceList.remove("Zandalari Troll");
			}
			if (!Filter.isARVulpera()) {
				raceList.remove("Vulpera");
			}
			if (Filter.isPreferAR() && raceList.size() > 7) {
				raceList.remove("Orc");
				raceList.remove("Undead");
				raceList.remove("Tauren");
				raceList.remove("Troll");
				raceList.remove("Blood Elf");
				raceList.remove("Goblin");
				raceList.remove("Horde Pandaren");
			}
		}
		
		return raceList;
	}
	
	/*
	 * Get the list of races from the database
	 */
	public static ArrayList<String> getRaceList(Connection conn, String faction) {
		ArrayList<String> returnRace = null;
		
		returnRace = getDatabaseInfoArrayList(conn, "SELECT race FROM \"tfreyberger/WoWRandomizer\".\"racelist\" WHERE FACTION LIKE \'" + faction + "\'");
		
		returnRace = alliedAdjust(faction, returnRace);
		
		return returnRace;
	}
	
	/*
	 * Gets the spec list from the database based on race
	 */
	public static String[] getSpec(Connection conn, String role, String race) {
		race = race.replace(' ', '_');
		race = race.replace("\'", "");
		
		return getDatabaseInfo(conn, "SELECT spec FROM \"tfreyberger/WoWRandomizer\".\"classlist\" WHERE role LIKE \'" + role + "\' AND " + race + " = TRUE");
	}
	
	/*
	 * Gets the class list from the database based on selected role
	 */
	public static String getClassBySpec(Connection conn, String spec, String race) {
		String[] classList = null;
		
		race = race.replace(' ', '_');
		race = race.replace("\'", "");
		
		classList = getDatabaseInfo(conn, "SELECT class FROM \"tfreyberger/WoWRandomizer\".\"classlist\" WHERE spec LIKE \'" + spec + "\' AND " + race + " = TRUE");
		
		if (classList.length > 1) {
			Random rand = new Random();
			
			return classList[rand.nextInt(classList.length)];
		} else {
			return classList[0];
		}
	}

}
