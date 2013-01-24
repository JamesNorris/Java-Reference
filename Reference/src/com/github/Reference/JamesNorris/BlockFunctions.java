package com.github.Reference.JamesNorris;

import java.util.ArrayList;

import org.bukkit.block.Block;

public class BlockFunctions {
	
	public static ArrayList<BlockStorage> cut(ArrayList<Block> blocks) {
		ArrayList<BlockStorage> storage = new ArrayList<BlockStorage>();
		for (Block block : blocks) {
			storage.add(new BlockStorage(block));
			block.setTypeId(0);
		}
		return storage;
	}
	
	public static ArrayList<BlockStorage> copy(ArrayList<Block> blocks) {
		ArrayList<BlockStorage> storage = new ArrayList<BlockStorage>();
		for (Block block : blocks)
			storage.add(new BlockStorage(block));
		return storage;
	}
	
	public static void move(ArrayList<Block> blocks, int modX, int modY, int modZ) {
		for (Block block : blocks) {
			BlockStorage store = new BlockStorage(block);
			store.x += modX;
			store.y += modY;
			store.z += modZ;
			Block newBlock = store.getLocation().getBlock();
			newBlock.setTypeId(store.id);
			newBlock.setData(store.data);
		}
	}
	
	public static void paste(ArrayList<BlockStorage> storage, int x, int y, int z) {
		for (BlockStorage store : storage) {
			store.x = x;
			store.y = y;
			store.z = z;
			Block block = store.getLocation().getBlock();
			block.setTypeId(store.id);
			block.setData(store.data);
		}
	}
}
