package com.github.Reference.JamesNorris;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockStorage {
	public String world;
	public int id, x, y, z;
	public byte data;
	
	public BlockStorage(Block block) {
		Location loc = block.getLocation();
		this.world = loc.getWorld().getName();
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
		this.id = block.getTypeId();
		this.data = block.getData();
	}
	
	public BlockStorage(Location loc, Integer id, Byte data) {
		this.world = loc.getWorld().getName();
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
		this.id = id;
		this.data = data;
	}
	
	public Location getLocation() {
		return Bukkit.getWorld(world).getBlockAt(x, y, z).getLocation();
	}
}
