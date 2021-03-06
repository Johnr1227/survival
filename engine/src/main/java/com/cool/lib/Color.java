package com.cool.lib;

import java.util.Random;

public class Color {
	
	public float r;
	public float g;
	public float b;
	
	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public Color(int r, int g, int b) {
		this.r = r/255f;
		this.g = g/255f;
		this.b = b/255f;
	}
	public String toString() {
		return r + "," + g + "," + b;
	}
	public static Color random() {
		Random random = new Random();
		return new Color(random.nextFloat(),random.nextFloat(),random.nextFloat());
	}
	public static Color fromString(String s) {
		int r,g,b;
		String[] colors = s.split(",");
		r = Integer.valueOf(colors[0]);
		g = Integer.valueOf(colors[1]);
		b = Integer.valueOf(colors[2]);
		
		return new Color(r/255f,g/255f,b/255f);
	}
}
