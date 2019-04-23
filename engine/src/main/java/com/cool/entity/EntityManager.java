package com.cool.entity;

import java.util.ArrayList;

import com.cool.lib.Vertex;
import com.cool.player.Player;

public class EntityManager {
	public static ArrayList<Entity> entities = new ArrayList<>();
	
	public static final int MAX_ENTITY_DISTANCE = 128;
	
	public static void tick(Player p) {
		for(int i = entities.size()-1; i > -1; i--) {
			Entity e = entities.get(i);
			if(distanceTaxicab(new Vertex((int)p.xPos,(int) p.zPos), new Vertex((int)e.xPos, (int)e.zPos)) > MAX_ENTITY_DISTANCE) {
				entities.remove(i);
				continue;
			}

			e.tick(p);
		}
	}
	public static void render(Player p, int tWidth) {
		for(Entity e : entities) {
			if(Math.abs(e.xPos-p.xPos) < p.zoom+2 && Math.abs(e.zPos-p.zPos) < p.zoom+2) {
				e.render(p, tWidth);
			}
		}
	}
	public static int distanceTaxicab(Vertex a, Vertex b) {
		return a.x - b.x + a.z - b.z;
	}
}
