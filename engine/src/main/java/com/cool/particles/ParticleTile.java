package com.cool.particles;

import com.cool.particle.ParticleType;
import com.cool.tile.Tile;

public class ParticleTile extends ParticleType{

	public ParticleTile(Tile tile, int size) {
		super(-0.1f, 0.1f, 0f, 0.1f, -0.1f, 0.1f, size*0.05f, size*0.2f, tile.getTexture());
	}

}
