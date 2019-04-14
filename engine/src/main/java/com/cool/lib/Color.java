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
	public String toString() {
		return "(" + r + "," + g + "," + b + ")";
	}
	public static Color random() {
		Random random = new Random();
		return new Color(random.nextFloat(),random.nextFloat(),random.nextFloat());
	}
}
