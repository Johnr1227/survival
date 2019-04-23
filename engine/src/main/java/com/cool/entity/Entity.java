package com.cool.entity;

import java.util.ArrayList;

import com.cool.Main;
import com.cool.Textures;
import com.cool.entity.behaviors.EntityBehaviorShakyWakey;
import com.cool.lib.Renderer;
import com.cool.player.Player;
import com.cool.world.World;

public class Entity {
	public double xPos;
	public double zPos;

	public World world;

	public ArrayList<EntityBehavior> behaviors = new ArrayList<>();

	public Entity(double xPos, double zPos, World world) {
		this.xPos = xPos;
		this.zPos = zPos;
		this.world = world;
	}

	public void tick(Player p) {
		for (EntityBehavior b : behaviors) {
			b.tick(this);
		}
	}

	public void render(Player p, int tWidth) {
		Renderer.drawTexture((int) (Main.WINDOW_WIDTH / 2 + (1 + (xPos - p.xPos)) * tWidth),
				(int) (Main.WINDOW_WIDTH / 2 + (1 + (zPos - p.zPos)) * tWidth),
				(int) (Main.WINDOW_HEIGHT / 2 + (xPos - p.xPos) * tWidth),
				(int) (Main.WINDOW_HEIGHT / 2 + (zPos - p.zPos) * tWidth), Textures.LOG);
	}
}
