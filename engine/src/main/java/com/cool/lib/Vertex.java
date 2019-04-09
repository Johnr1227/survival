package com.cool.lib;

public class Vertex {
	public int x;
	public int y;
	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Vertex add(Vertex v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	public Vertex add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}
	public static Vertex addVertices(Vertex v1, Vertex v2) {
		return v1.clone().add(v2);
	}
	public static Vertex addVertices(Vertex v1, int x, int y) {
		return v1.clone().add(new Vertex(x,y));
	}
	public void subtract(Vertex v) {
		this.x -= v.x;
		this.y -= v.y;
	}
	public Vertex clone() {
		return new Vertex(x,y);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
