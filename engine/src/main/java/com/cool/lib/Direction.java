package com.cool.lib;

import java.util.Random;

public enum Direction {
	NORTH(0,1),SOUTH(0,-1),WEST(-1,0),EAST(1,0);
	
	private Vertex v;
	
	Direction(int x, int y) {
		this.v = new Vertex(x,y);
	}
	public Vertex getDirection() {
		return v;
	}
	public static Direction random() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
