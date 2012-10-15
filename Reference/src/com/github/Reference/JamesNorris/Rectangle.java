package com.github.Reference.JamesNorris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

public class Rectangle {
	private Location loc1;
	private Location loc2;
	private HashMap<Location, Material> locs = new HashMap<Location, Material>();
	
	/**
	 * Creates a new rectangle from one location to the next.
	 * 
	 * @param loc1 The first location
	 * @param loc2 The second location
	 */
	public Rectangle(Location loc1, Location loc2) {
		this.loc1 = loc1;
		this.loc2 = loc2;
		calculateRectangle();
	}
	
	/*
	 * Calculates a rectangle from loc1 to loc2.
	 */
	private void calculateRectangle() {
		locs.clear();
		int x = loc1.getBlockX();
		int y = loc1.getBlockY();
		int z = loc1.getBlockZ();
		int x2 = loc2.getBlockX();
		int y2 = loc2.getBlockY();
		int z2 = loc2.getBlockZ();
		int modX = 0;
		int highX = 0;
		if (x < x2) {
			modX = x;
			highX = x2;
		} else {
			modX = x2;
			highX = x;
		}
		int modY = 0;
		int highY = 0;
		if (y < y2) {
			modY = y;
			highY = y2;
		} else {
			modY = y2;
			highY = y;
		}
		int modZ = 0;
		int highZ = 0;
		if (z < z2) {
			modZ = z;
			highZ = z2;
		} else {
			modZ = z2;
			highZ = z;
		}
		for (int i = modX; i <= highX; i++) {
			for (int j = modY; j <= highY; j++) {
				for (int k = modZ; k <= highZ; k++) {
					Location l = loc1.getWorld().getBlockAt(i, j, k).getLocation();
					locs.put(l, l.getBlock().getType());
				}
			}
		}
	}
	
	/**
	 * Gets all the locations from the rectangle in a list.
	 * 
	 * @return A list of all locations in the rectangle
	 */
	public List<Location> getLocations() {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (Location l : locs.keySet()) {
			locations.add(l);
		}
		return locations;
	}
}
