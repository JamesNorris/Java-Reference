package com.github.Reference.JamesNorris;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.IronGolem;

import net.minecraft.server.EntityIronGolem;
import net.minecraft.server.MathHelper;

public class GolemHit extends EntityIronGolem {
	private IronGolem ig;

	/*
	 * Creates a new instance of GolemHit, which extends NMS EntityIronGolem.
	 */
	public GolemHit(net.minecraft.server.World world) {
		super(world);
	}

	/**
	 * Makes the golem hit something with sound, effect, and break.
	 */
	public void hit() {
		World w = ig.getWorld();
		Location loc = ig.getLocation();
		int i = MathHelper.floor(loc.getX());
		int j = MathHelper.floor(loc.getY() - 0.20000000298023224D - (double) ig.getEyeHeight());
		int k = MathHelper.floor(loc.getZ());
		int l = w.getBlockAt(i, j, k).getTypeId();
		if (l > 0) {
			w.getBlockAt(i, j, k).breakNaturally();
			this.world.broadcastEntityEffect(this, (byte) 4);// I also know that 11 works!
			this.world.makeSound(this, "mob.irongolem.throw", 1.0F, 1.0F);
		}
	}
}
