package com.github.Reference.JamesNorris.While;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitSafeTask {
	private int id = -1;

	public BukkitSafeTask(final JavaPlugin plugin, final BukkitSafeRun run, final int interval) {
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if (run.shouldRun())
					run.run();
				else
					cancel();
			}
		}, interval, interval);
	}

	public void cancel() {
		Bukkit.getScheduler().cancelTask(id);
		id = -1;
	}
}