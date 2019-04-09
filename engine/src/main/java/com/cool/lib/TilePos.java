package com.cool.lib;

public class TilePos {
	public int x;
	public int y;
	public TilePos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public TilePos add(TilePos v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	public TilePos add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}
	public static TilePos addVertices(TilePos v1, TilePos v2) {
		return v1.clone().add(v2);
	}
	public static TilePos addVertices(TilePos v1, int x, int y) {
		return v1.clone().add(new TilePos(x,y));
	}
	public void subtract(TilePos v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	public TilePos clone() {
		return new TilePos(x,y);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
