package com.cool.tiles;

import java.util.Random;

import com.cool.Main;
import com.cool.Textures;
import com.cool.item.Items;
import com.cool.lib.Renderer;
import com.cool.lib.Vertex;
import com.cool.particle.Particles;
import com.cool.particles.ParticleTile;
import com.cool.player.Player;
import com.cool.tile.SoundType;
import com.cool.tile.TileObstacle;
import com.cool.world.generation.SimplexNoise;

public class TileTree extends TileObstacle {
	
	public Random random = new Random();
	
	public TileTree() {
		super();
		setTexture(Textures.TREE);
		setTileName("tree");
		this.soundType = SoundType.TREE;
		this.layer = 1;
	}
	@Override
	public String getDisplayName() {
		return "Tree";
	}
	@Override
	public void render(Player p, Vertex pos, int ogX1, int ogY1, int ogX2, int ogY2, int tWidth) {
		ogX1 -= tWidth;
		ogY1 -= tWidth;
		ogX2 += tWidth;
		ogY2 += tWidth;
		Renderer.drawTexture3d(ogX1 + 
				(int)(tWidth/8*Math.sin((Main.TICKS/100f+SimplexNoise.noise(1000d/System.currentTimeMillis(), 0)))), ogY1 + 
				(int)(tWidth/8*Math.sin((Main.TICKS/100f+SimplexNoise.noise(1000d/System.currentTimeMillis(), 0)))), 1, ogX2 + 
				(int)(tWidth/8*Math.sin((Main.TICKS/100f+SimplexNoise.noise(1000d/System.currentTimeMillis(), 0)))), ogY2 + 
				(int)(tWidth/8*Math.sin((Main.TICKS/100f+SimplexNoise.noise(1000d/System.currentTimeMillis(), 0)))), 1, texture);
		if(random.nextDouble() > 0.5) {
			for(int i = 0; i < random.nextInt(2); i++) {
				Particles.spawn(new ParticleTile(this,2), 1, pos.x + random.nextFloat(), 0, pos.z + random.nextFloat());
			}
		}
	}
	@Override
	public void onBroken(Player p, Vertex pos) {
		super.onBroken(p,pos);
		Random random = new Random();
		for(int i = 0; i < 70; i++) {
			Particles.spawn(new ParticleTile(this,2), 1, pos.x + random.nextFloat(), 0, pos.z + random.nextFloat());
		}
		p.giveItem(Items.LOG, random.nextInt(3)+1);
		p.giveItem(Items.ACORN, random.nextInt(9));
	}
}
