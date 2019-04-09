package com.cool.tiles;

import com.cool.Textures;
import com.cool.tile.Tile;

public class TileGrass extends Tile {
	
	public TileGrass() {
		setTexture(Textures.GRASS);
		setTileName("grass");
	}
	@Override
	public String getDisplayName() {
		return "Grass";
	}
}
