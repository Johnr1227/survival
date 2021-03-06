package com.cool.particle;

import com.cool.Main;
import com.cool.lib.Renderer;
import com.cool.lib.Texture;
import com.cool.player.Player;

public class Particle {
	public float x;
	public float y;
	public float z;

	public float velX;
	public float velY;
	public float velZ;

	public float size;

	public Texture t;
	
	public int ticksExisted = 0;

	public Particle(float x, float y, float z, float velX, float velY, float velZ, float size, Texture t) {
		this.x = x;
		this.y = y;
		this.z = z;

		this.velX = velX;
		this.velY = velY;
		this.velZ = velZ;
		
		this.size = size;
		
		this.t = t;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		z += velZ;
		velX *= 0.9;
		velZ *= 0.9;
		ticksExisted++;
	}
	public void render(double rX, double rY, Player p, int tWidth) {
		int x1 = Main.WINDOW_WIDTH-(int) ((p.xPos-x+p.zoom) * tWidth);
		int y1 = Main.WINDOW_HEIGHT-(int) ((p.zPos-z+p.zoom) * tWidth);
		int x2 = Main.WINDOW_WIDTH-(int) ((size + (p.xPos-x)+p.zoom) * tWidth);
		int y2 = Main.WINDOW_HEIGHT-(int) ((size + (p.zPos-z)+p.zoom) * tWidth);
		
		Renderer.drawTexture(x1, y1, x2, y2, t);
	}
}
