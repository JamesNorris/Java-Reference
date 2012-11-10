package com.github.Reference.JamesNorris;

import org.bukkit.Location;
import org.bukkit.World;

public class Region {
	private Location loc1, loc2, loc3, loc4;
	private World world;
	private int highX, highZ, lowX, lowZ;
	
	/**
	 * Creates a new 2D region from 2 corner points.
	 * 
	 * @param loc1 The first corner
	 * @param loc2 The second corner
	 */
	public Region(Location loc1, Location loc2) {
		world = loc1.getWorld();
		this.loc1 = loc1;
		this.loc2 = loc2;
		loc3 = world.getBlockAt(loc2.getBlockX(), 0, loc1.getBlockZ()).getLocation();
		loc4 = world.getBlockAt(loc1.getBlockX(), 0, loc2.getBlockZ()).getLocation();
		if (loc1.getBlockX() > loc2.getBlockX()) {
			highX = loc1.getBlockX();
			lowX = loc2.getBlockX();
		} else {
			highX = loc2.getBlockX();
			lowX = loc1.getBlockX();
		}
		if (loc1.getBlockZ() > loc2.getBlockZ()) {
			highZ = loc1.getBlockZ();
			lowZ = loc2.getBlockZ();
		} else {
			highZ = loc2.getBlockZ();
			lowZ = loc1.getBlockZ();
		}
	}
	
	/**
	 * Gets the corner that matches the given number.
	 * 
	 * @param corner The corner number from 1-4
	 * @return The corner of thet number
	 */
	public Location getCorner(int corner) {
		switch(corner) {
			case 1:
				return loc1;
			case 2: 
				return loc2;
			case 3: 
				return loc3;
			case 4: 
				return loc4;
		}
		return null;
	}
	
	/**
	 * Checks if the given region touches or overlaps this region.
	 * 
	 * @param other The region to check for
	 * @return Whether or not they touch or overlap
	 */
	public boolean overlaps(Region other) {
		boolean lows = false, highs = false, lowZs = false, lowXs = false, highZs = false, highXs = false;
		if (lowZ <= other.getLowestZ() && highZ >= other.getLowestZ())
			lowZs = true;
		if (lowX <= other.getLowestX() && highX >= other.getLowestX())
			lowXs = true;
		if (lowZs && lowXs)
			lows = true;
		if (lowZ <= other.getHighestZ() && highZ >= other.getHighestZ())
			highZs = true;
		if (lowX <= other.getHighestX() && highX >= other.getHighestX())
			highXs = true;
		if (highZs && highXs)
			highs = true;
		return (lows && highs);		
	}
	
	/**
	 * Gets the highest X of all 4 locations in this region.
	 * @return The highest X value for this region
	 */
	public int getHighestX() {
		return highX;
	}
	
	/**
	 * Gets the lowest X of all 4 locations in this region.
	 * @return The lowest X value for this region
	 */
	public int getLowestX() {
		return lowX;
	}
	
	/**
	 * Gets the highest Z of all 4 locations in this region.
	 * @return The highest Z value for this region
	 */
	public int getHighestZ() {
		return highZ;
	}
	
	/**
	 * Gets the lowest Z of all 4 locations in this region.
	 * @return The lowest Z value for this region
	 */
	public int getLowestZ() {
		return lowZ;
	}
}
