package com.github.Reference.JamesNorris;

import net.minecraft.server.v1_4_6.*;
import org.bukkit.craftbukkit.v1_4_6.entity.*;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MobTargetter {
	private final Plugin plugin;
	private Player p;
	private Creature c;
	private Location location;
	private int id;
	private boolean hasTarget = false;
	private float speed = 0.18F;
	private float radius = 16.0F;

	/**
	 * Creates a new mobtargetter, that can target specific locations.
	 * 
	 * @param plugin The plugin to use to run the thread
	 * @param c The creature instance to move
	 * @param p The player to start targetting
	 */
	public MobTargetter(Plugin plugin, Creature c, Player p) {
		this.plugin = plugin;
		this.c = c;
		this.p = p;
		setTarget(p);
	}

	/**
	 * Creates a new mobtargetter, that can target specific locations.
	 * 
	 * @param plugin The plugin to use to run the thread
	 * @param c The creature instance to move
	 * @param loc The location to start targetting
	 */
	public MobTargetter(Plugin plugin, Creature c, Location loc) {
		this.plugin = plugin;
		this.c = c;
		this.location = loc;
		setTarget(loc);
	}

	/**
	 * Gets the speed of the creature moving.
	 * 
	 * @return The creature speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Changes the target of the mob.
	 * 
	 * @param l The new target
	 */
	public void setTarget(Player p) {
		cancel();
		if (p != null)
			this.p = p;
		target();
	}

	/**
	 * Changes the target of the mob.
	 * 
	 * @param l The new target
	 */
	public void setTarget(Location l) {
		cancel();
		this.location = l;
		target();
	}

	/**
	 * Sets the speed of movement of the mob.
	 * 
	 * @param speed How fast the mob should move
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/*
	 * Begins the targetting thread.
	 */
	private void target() {
		hasTarget = true;
		id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if (!c.isDead() && (p == null || !p.isDead())) {
					moveMob();
				} else {
					cancel();
				}
			}
		}, 1, 1);
	}

	/**
	 * Cancels the thread.
	 */
	protected void cancel() {
		hasTarget = false;
		plugin.getServer().getScheduler().cancelTask(id);
	}

	/*
	 * Moves the mob towards the target.
	 */
	private void moveMob() {
		Location loc = null;
		if (p != null)
			loc = p.getLocation();
		else if (location != null)
			loc = location;
		if (p != null || location != null) {
			EntityCreature mob = (EntityCreature) ((CraftCreature) c).getHandle();
			PathEntity path = mob.world.a(mob, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), radius, true, false, false, true);
			mob.setPathEntity(path);
			mob.getNavigation().a(path, speed);
		}
	}

	/**
	 * Checks if the mob has a target.
	 * 
	 * @return Whether or not the mob has a target
	 */
	public boolean hasTarget() {
		return hasTarget;
	}
}
