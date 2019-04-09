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
//		if (SimplexNoise.noise(x / 30f, z / 30f, 1f / seed) > 0.6) {
//			return Tiles.STONE;
//		} else {
//			return null;
//		}
//		if(x % 10 < 3 && z % 10 < 3) {
//			return Tiles.STONE;
//		}
//		return null;
		if(x == 0 && z == 0) {
			return Tiles.STONE;
		}
		return null;
	}

}
