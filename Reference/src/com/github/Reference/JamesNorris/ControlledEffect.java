package com.github.Reference.JamesNorris;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class ControlledEffect {
	private World world;
	private int radius, direction, X, Y, Z, id, interval, length, l;
	private JavaPlugin plugin;
	private Effect effect;
	private boolean running;
	private Location center;

	/**
	 * Creates a new controlled effect WITHOUT the scheduler.
	 * 
	 * @param plugin The plugin to run this from
	 * @param world The world to play the effect in
	 * @param effect The type of effect to play
	 * @param radius The radius of the effect
	 * @param direction The direction of the effect
	 * @param center The center of the effect
	 * @param autorun Whether or not to autorun the effect when the instance is created
	 */
	public ControlledEffect(World world, Effect effect, int radius, int direction, Location center, boolean autorun) {
		this.world = world;
		this.radius = radius;
		this.direction = direction;
		this.effect = effect;
		this.center = center;
		this.X = center.getBlockX();
		this.Y = center.getBlockY();
		this.Z = center.getBlockZ();
		if (autorun)
			effect();
	}

	/**
	 * Creates a new controlled effect WITH the scheduler.
	 * 
	 * @param plugin The plugin to run this from
	 * @param world The world to play the effect in
	 * @param effect The type of effect to play
	 * @param radius The radius of the effect
	 * @param direction The direction of the effect
	 * @param center The center of the effect
	 * @param autorun Whether or not to autorun the effect when the instance is created
	 * @param scheduler Whether or not to use the scheduler
	 * @param interval The amout of time to wait between playing the effect
	 */
	public ControlledEffect(JavaPlugin plugin, World world, Effect effect, int radius, int direction, Location center, boolean autorun, boolean scheduler, int interval, int length) {
		this.interval = interval;
		this.plugin = plugin;
		this.world = world;
		this.radius = radius;
		this.direction = direction;
		this.effect = effect;
		this.length = length;
		this.center = center;
		this.X = center.getBlockX();
		this.Y = center.getBlockY();
		this.Z = center.getBlockZ();
		if (autorun)
			effect();
		if (scheduler)
			schedule();
	}

	/**
	 * Creates a single effect using this ControlledEffect instance.
	 */
	protected void effect() {
		if (radius > 1) {
			for (int x = -radius; x <= radius; ++x) {
				for (int y = -radius; y <= radius; ++x) {
					for (int z = -radius; z <= radius; ++z) {
						Location loc = world.getBlockAt(X + x, Y + y, Z + z).getLocation();
						world.playEffect(loc, effect, direction);
					}
				}
			}
		} else
			world.playEffect(center, effect, direction);
	}

	/**
	 * Schedules a new set of effects with this instance.
	 */
	protected void schedule() {
		if (!running) {
			running = true;
			this.l = length;
			id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
					effect();
					--l;
					if (l <= 0) {
						cancel();
					}
				}
			}, interval, interval);
		}
	}

	/**
	 * Stops the scheduler.
	 */
	protected void cancel() {
		Bukkit.getScheduler().cancelTask(id);
		running = false;
	}
}
