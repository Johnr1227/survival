package com.cool.tile;

import com.cool.lib.Texture;

public class TileObstacle extends Tile {
	
	public TileObstacle(Texture texture, String name, String displayName) {
		this.texture = texture;
		this.tileName = name;
		this.displayName = displayName;
	}
}