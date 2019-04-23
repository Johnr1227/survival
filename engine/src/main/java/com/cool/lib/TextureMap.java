package com.cool.lib;

import java.util.ArrayList;

public class TextureMap {
	public Texture texture;
	public int textureWidth;
	public int textureHeight;
	
	public ArrayList<Thing> things = new ArrayList<>();
	
	public TextureMap(Texture tex, int texWidth, int texHeight) {
		this.texture = tex;
		this.textureWidth = texWidth;
		this.textureHeight = texHeight;
	}
	public void addThing(int x1, int y1, int x2, int y2) {
		things.add(new Thing((float)x1/textureWidth, (float)y1/textureHeight, (float)x2/textureWidth, (float)y2/textureHeight));
	}
	public void generateThings(int x, int y, int width, int height, int count) {
		int cX = x;
		int cY = y;
		
		for(int i = 0; i < count; i++) {
			addThing(cX, cY, cX+width, cY+height);
			System.out.println("(" +cX + "," + cY + "),("  + (cX + width) + "," + (cY + height) + ")");
			cX += width;
			if(cX >= textureWidth) {
				cX = 0;
				cY += height;
			}
		}
	}
	public void draw(int tex, int x1, int y1, int x2, int y2) {
		things.get(tex).draw(this.texture,x1,y1,x2,y2);
	}
	
	public class Thing {
		public float x1;
		public float y1;
		
		public float x2;
		public float y2;
		
		public Thing(float x1, float y1, float x2, float y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		public void draw(Texture t, int x1, int y1, int x2, int y2) {
			Renderer.drawTexture(x1, y1, x2, y2, t, this.x1, this.y1, this.x2, this.y2);
		}
	}
}
