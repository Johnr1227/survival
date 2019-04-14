package com.cool.lib;

import static org.lwjgl.opengl.GL11.*;

import com.cool.Textures;


public class Renderer {
	
	public static void drawTexture(int x, int y, int x2, int y2, Texture tex) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();	
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2i(1, 0);
		glVertex2i(x2, y);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y);
		
		glTexCoord2i(0, 1);
		glVertex2i(x, y2);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y2);
		
		glEnd();
	}
	public static void drawTexture3d(int x, int y, int z, int x2, int y2, int z2, Texture tex) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();	
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2i(1, 0);
		glVertex3i(x2, y,z);
		
		glTexCoord2i(0, 0);
		glVertex3i(x, y,z);
		
		glTexCoord2i(0, 1);
		glVertex3i(x, y2,z2);
		
		glTexCoord2i(1, 1);
		glVertex3i(x2, y2,z2);
		
		glEnd();
	}
	public static void drawColoredTexture(int x, int y, int x2, int y2, Texture tex, Color c) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(c.r, c.g, c.b);
		glTexCoord2i(1, 0);
		glVertex2i(x2, y);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y);
		
		glTexCoord2i(0, 1);
		glVertex2i(x, y2);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y2);
		
		glEnd();
		// (1,0),(0,0),(0,1),(1,1)
	}
	public static void drawColoredRotatedTexture(int x, int y, int x2, int y2, Texture tex, float r, float g, float b, float rot) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		glTranslatef(0.5f,0.5f,0.0f);
		glRotatef(rot,0,0,1);
		glTranslatef(-0.5f,-0.5f,0.0f);
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(r, g, b);
		glTexCoord2i(0, 1);
		glVertex2i(x, y);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y);
		
		glTexCoord2i(1, 0);
		glVertex2i(x2, y2);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawColoredRotatedTexture(int x, int y, int x2, int y2, Texture tex, Color c, float rot) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		glTranslatef(0.5f,0.5f,0.0f);
		glRotatef(rot,0,0,1);
		glTranslatef(-0.5f,-0.5f,0.0f);
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(c.r, c.g, c.b);
		glTexCoord2i(0, 1);
		glVertex2i(x, y);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y);
		
		glTexCoord2i(1, 0);
		glVertex2i(x2, y2);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawRotatedTexture(int x, int y, int x2, int y2, Texture tex, float rot) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		glTranslatef(0.5f,0.5f,0.0f);
		glRotatef(rot,0,0,1);
		glTranslatef(-0.5f,-0.5f,0.0f);
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2i(0, 1);
		glVertex2i(x, y);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y);
		
		glTexCoord2i(1, 0);
		glVertex2i(x2, y2);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawRect(int x, int y, int x2, int y2, float r, float g, float b) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		glColor3f(r, g, b);
		
		glVertex2i(x, y);
		
		glVertex2i(x2, y);
		
		glVertex2i(x2, y2);
		
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawRect(int x, int y, int x2, int y2, float r, float g, float b, float a) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		glColor4f(r, g, b, a);
		
		glVertex2i(x, y);
		
		glVertex2i(x2, y);
		
		glVertex2i(x2, y2);
		
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawRect(int x, int y, int x2, int y2, Color c) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		glColor3f(c.r, c.g, c.b);
		
		glVertex2i(x, y);
		
		glVertex2i(x2, y);
		
		glVertex2i(x2, y2);
		
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawQuad(Vertex p1, Vertex p2, Vertex p3, Vertex p4, float r, float g, float b) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_POLYGON);
		glColor3f(r, g, b);
		
		glVertex2i(p1.x, p1.z);
		
		glVertex2i(p2.x, p2.z);
		
		glVertex2i(p3.x, p3.z);

		glVertex2i(p4.x, p4.z);
		
		glEnd();
	}
	public static void drawQuad(Vertex p1, Vertex p2, Vertex p3, Vertex p4, Color c) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_POLYGON);
		glColor3f(c.r, c.g, c.b);
		
		glVertex2i(p1.x, p1.z);
		
		glVertex2i(p2.x, p2.z);
		
		glVertex2i(p3.x, p3.z);

		glVertex2i(p4.x, p4.z);
		
		glEnd();
	}
	public static void drawCircle(Vertex pos, int radius, float r, float g, float b) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_POLYGON);
		glColor3f(r, g, b);
		
		glVertex2i(pos.x, pos.z);
		for(float i = 0; i < Math.PI*2; i += Math.PI / 20) {
			glVertex2i((int)Math.round((pos.x+Math.cos(i)*radius)), (int)Math.round((pos.z+Math.sin(i)*radius)));
		}
		
		glEnd();
	}
	public static void drawCircle(Vertex pos, int radius, Color c) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_POLYGON);
		glColor3f(c.r, c.g, c.b);
		
		glVertex2i(pos.x, pos.z);
		for(float i = 0; i < Math.PI*2; i += Math.PI / 20) {
			glVertex2i((int)Math.round((pos.x+Math.cos(i)*radius)), (int)Math.round((pos.z+Math.sin(i)*radius)));
		}
		
		glEnd();
	}
	public static void drawString(int x, int y, int size, String text) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		Textures.FONT.bind();
		for(char c : text.toCharArray()) {
			Vertex texMap = getCharRenderPos(c);
			glLoadIdentity();
			
			glBegin(GL_QUADS);
			glColor3f(1, 1, 1);
			glTexCoord2f((texMap.x+8)/128f, texMap.z/128f);
			glVertex2i(x+size, y);
			
			glTexCoord2f(texMap.x/128f, texMap.z/128f);
			glVertex2i(x, y);
			
			glTexCoord2f(texMap.x/128f, (texMap.z+8)/128f);
			glVertex2i(x, y+size);
			
			glTexCoord2f((texMap.x+8)/128f, (texMap.z+8)/128f);
			glVertex2i(x+size, y+size);
			
			glEnd();
			x += size;
		}
	}
	public static void drawString(int x, int y, int size, String text, Color color) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		Textures.FONT.bind();
		for(char c : text.toCharArray()) {
			Vertex texMap = getCharRenderPos(c);
			glLoadIdentity();
			
			glBegin(GL_QUADS);
			glColor3f(color.r, color.g, color.b);
			glTexCoord2f((texMap.x+8)/128f, texMap.z/128f);
			glVertex2i(x+size, y);
			
			glTexCoord2f(texMap.x/128f, texMap.z/128f);
			glVertex2i(x, y);
			
			glTexCoord2f(texMap.x/128f, (texMap.z+8)/128f);
			glVertex2i(x, y+size);
			
			glTexCoord2f((texMap.x+8)/128f, (texMap.z+8)/128f);
			glVertex2i(x+size, y+size);
			
			glEnd();
			x += size;
		}
	}
	private static Vertex getCharRenderPos(char c) {
		int cint = (int)c;
		int column = cint / 16;
		int row = cint % 16;
		return new Vertex(row*8,120-column*8);
	}
}
