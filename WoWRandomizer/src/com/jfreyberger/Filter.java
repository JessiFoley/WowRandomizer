package com.jfreyberger;

public class Filter {
	//faction
	private static boolean facAlliance = true;
	private static boolean facHorde = true;
	
	//role
	private static boolean roleTank = true;
	private static boolean roleHealer = true;
	private static boolean roleDPS = true;
	
	//Prefer Allied Race
	private static boolean PreferAR = false;
	
	//Horde Allied Race
	private static boolean ARNightborne = true;
	private static boolean ARHTauren = true;
	private static boolean ARMOrcs = true;
	private static boolean ARZTrolls = true;
	private static boolean ARVulpera = true;
	
	//Alliance Allied Race
	private static boolean ARLDraenei = true;
	private static boolean ARVElves = true;
	private static boolean ARDIDwarves = true;
	private static boolean ARKTHumans = true;
	private static boolean ARMechagnomes = true;
	
	//getters and setters for all variables
	public static boolean isFacAlliance() {
		return facAlliance;
	}
	public static void setFacAlliance(boolean facAlliance) {
		Filter.facAlliance = facAlliance;
	}
	public static boolean isFacHorde() {
		return facHorde;
	}
	public static void setFacHorde(boolean facHorde) {
		Filter.facHorde = facHorde;
	}
	public static boolean isRoleTank() {
		return roleTank;
	}
	public static void setRoleTank(boolean roleTank) {
		Filter.roleTank = roleTank;
	}
	public static boolean isRoleHealer() {
		return roleHealer;
	}
	public static void setRoleHealer(boolean roleHealer) {
		Filter.roleHealer = roleHealer;
	}
	public static boolean isRoleDPS() {
		return roleDPS;
	}
	public static void setRoleDPS(boolean roleDPS) {
		Filter.roleDPS = roleDPS;
	}
	public static boolean isARNightborne() {
		return ARNightborne;
	}
	public static void setARNightborne(boolean aRNightborne) {
		ARNightborne = aRNightborne;
	}
	public static boolean isARHTauren() {
		return ARHTauren;
	}
	public static void setARHTauren(boolean aRHTauren) {
		ARHTauren = aRHTauren;
	}
	public static boolean isARMOrcs() {
		return ARMOrcs;
	}
	public static void setARMOrcs(boolean aRMOrcs) {
		ARMOrcs = aRMOrcs;
	}
	public static boolean isARZTrolls() {
		return ARZTrolls;
	}
	public static void setARZTrolls(boolean aRZTrolls) {
		ARZTrolls = aRZTrolls;
	}
	public static boolean isARVulpera() {
		return ARVulpera;
	}
	public static void setARVulpera(boolean aRVulpera) {
		ARVulpera = aRVulpera;
	}
	public static boolean isARLDraenei() {
		return ARLDraenei;
	}
	public static void setARLDraenei(boolean aRLDraenei) {
		ARLDraenei = aRLDraenei;
	}
	public static boolean isARVElves() {
		return ARVElves;
	}
	public static void setARVElves(boolean aRVElves) {
		ARVElves = aRVElves;
	}
	public static boolean isARDIDwarves() {
		return ARDIDwarves;
	}
	public static void setARDIDwarves(boolean aRDIDwarves) {
		ARDIDwarves = aRDIDwarves;
	}
	public static boolean isARKTHumans() {
		return ARKTHumans;
	}
	public static void setARKTHumans(boolean aRKTHumans) {
		ARKTHumans = aRKTHumans;
	}
	public static boolean isARMechagnomes() {
		return ARMechagnomes;
	}
	public static void setARMechagnomes(boolean aRMechagnomes) {
		ARMechagnomes = aRMechagnomes;
	}
	public static boolean isPreferAR() {
		return PreferAR;
	}
	public static void setPreferAR(boolean preferAR) {
		PreferAR = preferAR;
	}

}
