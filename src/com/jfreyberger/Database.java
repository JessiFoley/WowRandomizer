package com.jfreyberger;

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
	
	private static ArrayList<String> getDatabaseInfo(Connection conn, String statement) {
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
	
	public static ArrayList<String> getRaceList(Connection conn, String faction) {
		ArrayList<String> returnRace = null;
		
		returnRace = getDatabaseInfo(conn, "SELECT race FROM \"tfreyberger/WoWRandomizer\".\"racelist\" WHERE FACTION LIKE \'" + faction + "\'");
		
		returnRace = alliedAdjust(faction, returnRace);
		
		return returnRace;
	}
	
	/*
	 * Gets the spec list from the database based on race
	 */
	public static String[] getSpec(Connection conn, String role, String race) {
		String[] specList = null;
		
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
		
		return specList;
	}
	
	/*
	 * Gets the class list from the database based on selected role
	 */
	public static String getClassBySpec(Connection conn, String spec, String race) {
		String[] classList = null;
		
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
		
		if (classList.length > 1) {
			Random rand = new Random();
			
			return classList[rand.nextInt(classList.length)];
		} else {
			return classList[0];
		}
	}

}
