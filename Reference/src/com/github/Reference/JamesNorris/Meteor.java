package com.github.Reference.JamesNorris;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;

public class Meteor {
	private Location spawn, target;
	private World world;
	private Entity entity;
	private Fireball fireball;
	private Creature creature;
	private Random random = new Random();
	private float yield = 5;
	
	public Meteor(Location spawn, Location target) {
		this.spawn = spawn;
		this.world = spawn.getWorld();
		this.entity = world.spawnEntity(spawn, EntityType.FIREBALL);
		this.fireball = (Fireball) entity;
		this.creature = (Creature) entity;
		this.yield = random.nextInt(18) + 2;
		setTarget(target);
	}
	
	public float getPower() {
		return yield;
	}
	
	public void setPower(float yield) {
		this.yield = yield;
		fireball.setYield(yield);
	}
	
	public void setTarget(Location target) {
		this.target = target;
		fireball.setDirection(new Vector(target.getX(), target.getY(), target.getZ()));
	}
	
	public Location getTarget() {
		return target;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Fireball getFireball() {
		return fireball;
	}
	
	public Creature getCreature() {
		return creature;
	}
}