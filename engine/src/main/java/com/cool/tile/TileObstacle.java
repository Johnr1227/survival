package com.cool.tile;

import com.cool.lib.Texture;

public class TileObstacle extends Tile {
	
	public int layer = 0;
	
	public TileObstacle(Texture texture, String name, String displayName, int layer) {
		this.texture = texture;
		this.tileName = name;
		this.displayName = displayName;
		this.layer = layer;
	}
	public TileObstacle() {
		
	}
}
