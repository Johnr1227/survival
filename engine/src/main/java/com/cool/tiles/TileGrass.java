package com.cool.tiles;

import com.cool.SoundType;
import com.cool.Textures;
import com.cool.tile.Tile;

public class TileGrass extends Tile {
	
	public TileGrass() {
		setTexture(Textures.GRASS);
		setTileName("grass");
		this.soundType = SoundType.DIRT;
	}
	@Override
	public String getDisplayName() {
		return "Grass";
	}
}
