package com.cool.particle;

import com.cool.lib.Texture;

public class ParticleType {

	public float minVelX;
	public float maxVelX;
	
	public float minVelY;
	public float maxVelY;
	
	public float minVelZ;
	public float maxVelZ;

	
	public float minSize;
	public float maxSize;
	
	public Texture texture;
	
	public ParticleType(float minVelX, float maxVelX, float minVelY, float maxVelY, float minVelZ, float maxVelZ, float minSize, float maxSize, Texture texture) {
		this.minVelX = minVelX;
		this.maxVelX = maxVelX;
		
		this.minVelY = minVelY;
		this.maxVelY = maxVelY;
		
		this.minVelZ = minVelZ;
		this.maxVelZ = maxVelZ;
		
		this.minSize = minSize;
		this.maxSize = maxSize;
		
		this.texture = texture;
	}
}
