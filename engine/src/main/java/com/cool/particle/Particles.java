package com.cool.particle;

import java.util.ArrayList;
import java.util.Random;

import com.cool.player.Player;

public class Particles {
	public static ArrayList<Particle> particles = new ArrayList<>();
	
	
	public static void render(double rX, double rY, Player player, int tWidth) {
		for(Particle p : particles) {
			p.render(rX, rY, player, tWidth);
		}
	}
	public static void tick(Player player) {
		for(int i = particles.size()-1; i >= 0; i--) {
			Particle p = particles.get(i);
			p.tick();
			if(p.ticksExisted > 100) {
//				if(Math.abs(player.xPos-p.x) > player.zoom) {
					particles.remove(i);
//				}
			}
		}
	}
	public static void spawn(ParticleType type, int amt, float x, float y, float z) {
		Random random = new Random();
		for(int i = 0; i < amt; i++) {
			spawn(new Particle(x,y,z,
					randomBetween(type.minVelX, type.maxVelX, random),
					randomBetween(type.minVelY, type.maxVelY, random),
					randomBetween(type.minVelZ, type.maxVelZ, random),
					randomBetween(type.minSize, type.maxSize, random),
					type.texture));
		}
	}
	public static void spawn(Particle p) {
		particles.add(p);
	}
	private static float randomBetween(float min, float max, Random random) {
		return random.nextFloat()*(max-min)+min;
	}
}
