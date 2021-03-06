package com.cool.world.generation;

import com.cool.tile.Tile;
import com.cool.tile.TileObstacle;
import com.cool.tile.Tiles;

public class WorldGeneratorOverworld extends WorldGenerator {

	public WorldGeneratorOverworld(long seed) {
		super(seed);
	}

	public WorldGeneratorOverworld() {
		super();
	}

	@Override
	public Tile generate(int x, int z) {
		return Tiles.GRASS;
	}

	@Override
	public TileObstacle generateObstacle(int x, int z) {
		if (SimplexNoise.noise(x / 30f, z / 30f) > 0.4) {
			return Tiles.STONE;
		} else {
			if(Math.random() > 0.95) {
				return Tiles.TREE;
			}
		}
		return null;
	}

}
