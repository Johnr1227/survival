package com.cool.tile;

import com.cool.lib.Color;
import com.cool.lib.Renderer;
import com.cool.lib.Texture;
import com.cool.lib.Vertex;
import com.cool.player.Player;

public class Tile {
	protected Texture texture;
	protected String tileName;

	protected String displayName;

	protected SoundType soundType;
	
	public int layer;

	public Tile(Texture texture, SoundType soundType, String name, String displayName) {
		this.texture = texture;
		this.soundType = soundType;

		this.tileName = name;
		this.displayName = displayName;
	}

	public Tile() {
	}

	public Texture getTexture() {
		return texture;
	}

	public void playStepSound() {
		soundType.playSound(SoundType.STEP);
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public String getTileName() {
		return tileName;
	}

	public void setTileName(String tileName) {
		this.tileName = tileName;
	}

	public String getDisplayName() {
		return displayName == null ? tileName : displayName;
	}

	public String toString() {
		return getDisplayName();
	}

	public void tick() {
		
	}

	public void render(Player p, Vertex pos, int ogX1, int ogY1, int ogX2, int ogY2, int tWidth) {
		Color c;
		if(pos.z > 0) {
			if(p.world.obstacles[pos.x][pos.z-1] == null) {
				c = new Color(1f,1f,1f);
			} else {
				c = new Color(0.9f, 0.9f, 0.9f);
			}
		} else {
			c = new Color(1f,1f,1f);
		}
		
		Renderer.drawColoredTexture(ogX1, ogY1, ogX2, ogY2, texture, c);
	}

	public void onBroken(Player p, Vertex pos) {
		soundType.playSound(SoundType.BREAK);
	}
}
