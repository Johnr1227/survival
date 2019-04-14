package com.cool.lib;

public enum Direction {
	NORTH(0,1),SOUTH(0,-1),WEST(-1,0),EAST(1,0);
	
	private Vertex v;
	
	Direction(int x, int y) {
		this.v = new Vertex(x,y);
	}
	public Vertex getDirection() {
		return v;
	}
}
