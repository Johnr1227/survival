package com.cool.menu.lib;

import com.cool.lib.Texture;

public class Button {
	public int x;
	public int y;
	public int x2;
	public int y2;
	
	public Texture normalTexture;
	public Texture selectedTexture;
	
	public String text;
	public int textSize;
	
	public Button(int x, int y, int x2, int y2, Texture normal, Texture selected, String text, int textSize) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		
		this.text = text;
		this.textSize = textSize;
		
		this.normalTexture = normal;
		this.selectedTexture = selected;
	}
}
