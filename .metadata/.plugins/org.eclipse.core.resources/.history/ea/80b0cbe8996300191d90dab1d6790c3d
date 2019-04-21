package com.cool.world;

import com.cool.tile.Tile;
import com.cool.tile.TileObstacle;
import com.cool.world.generation.WorldGenerator;

public class World {
	public Tile[][] tiles;
	public TileObstacle[][] obstacles;
	
	public World (WorldGenerator generator, int xSize, int zSize) {
		tiles = new Tile[xSize][zSize];
		for(int x = 0; x < xSize; x++) {
			for(int z = 0; z < zSize; z++) {
				tiles[x][z] = generator.generate(x, z);
			}
		}
		obstacles = new TileObstacle[xSize][zSize];
		for(int x = 0; x < xSize; x++) {
			for(int z = 0; z < zSize; z++) {
				obstacles[x][z] = generator.generateObstacle(x, z);
			}
		}
	}
}
