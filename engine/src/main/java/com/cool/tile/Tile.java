package com.cool.tile;

import com.cool.lib.Texture;

public class Tile {
	protected Texture texture;
	protected String tileName;
	
	protected String displayName;
	
	public Tile(Texture texture, String name, String displayName) {
		this.texture = texture;
		this.tileName = name;
		this.displayName = displayName;
	}
	public Tile() {
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public String getTileName() {
		return tileName;
	}
	public void setTileName (String tileName) {
		this.tileName = tileName;
	}
	public String getDisplayName() {
		return displayName == null ? tileName : displayName;
	}
	public String toString() {
		return getDisplayName();
	}
}
