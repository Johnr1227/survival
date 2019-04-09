package com.cool.world.generation;

import java.util.Random;

import com.cool.tile.Tile;
import com.cool.tile.TileObstacle;
import com.cool.tile.Tiles;

public class WorldGenerator {
	public long seed;
	
	public WorldGenerator(long seed) {
		this.seed = seed;
	}
	public WorldGenerator() {
		Random random = new Random();
		this.seed = random.nextLong();
	}
	public Tile generate (int x, int z) {
		return Tiles.GRASS;
	}
	public TileObstacle generateObstacle(int x, int z) {
		return null;
	}
}
