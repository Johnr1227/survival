package com.cool.world;

import com.cool.world.generation.WorldGeneratorOverworld;

public class Worlds {
	
	public static World OVERWORLD = new World(new WorldGeneratorOverworld(), 512, 512);
	
	
}
