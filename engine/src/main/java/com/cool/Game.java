package com.cool;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;

import com.cool.lib.Color;
import com.cool.lib.Direction;
import com.cool.lib.Keyboard;
import com.cool.lib.Renderer;
import com.cool.lib.Texture;
import com.cool.lib.Vertex;
import com.cool.menu.Menu;
import com.cool.player.Player;
import com.cool.tile.Tile;
import com.cool.tile.Tiles;
import com.cool.world.Worlds;
import com.cool.world.generation.SimplexNoise;

public class Game extends Menu {
	public long window;

	public Player player;

	public boolean paused = false;
	private boolean prevEscPressed = false;
	private boolean prevScaleChanged = false;

	public Game(long window) {
		this.window = window;
	}

	public void init() {
		player = new Player(Worlds.OVERWORLD, 250, 250, Direction.SOUTH, new Color(0, 0.3f, 1f),
				new Color(0.6f, 0f, 0.7f), new Color(0.05f, 0.05f, 0.01f), new Color(0.1f, 0.05f, 0.4f),
				new Color(0f, 0.3f, 0.7f), new Color(0.95f, 0.95f, 0.8f), 1);
	}

	public void tick() {
		if (!paused) {
			player.walking = false;
			if (Keyboard.isKeyDown(GLFW_KEY_LEFT)) {
				if (player.canMove(Direction.WEST, 0.1f)) {
					player.xPos += 0.1f;
					player.facing = Direction.WEST;
				}
				player.walking = true;
				System.out.println((int) Math.round(player.xPos) + "," + (int) Math.round(player.zPos) + ","
						+ player.world.tiles[(int) Math.round(player.xPos)][(int) Math.round(player.zPos)]);
			}
			if (Keyboard.isKeyDown(GLFW_KEY_RIGHT)) {
				if (player.canMove(Direction.EAST, 0.1f)) {
					player.xPos -= 0.1f;
					player.facing = Direction.EAST;
				}
				player.walking = true;
				System.out.println((int) Math.round(player.xPos) + "," + (int) Math.round(player.zPos) + ","
						+ player.world.tiles[(int) Math.round(player.xPos)][(int) Math.round(player.zPos)]);
			}
			if (Keyboard.isKeyDown(GLFW_KEY_UP)) {
				if (player.canMove(Direction.NORTH, 0.1f)) {
					player.zPos -= 0.1f;
					player.facing = Direction.NORTH;
				}
				player.walking = true;
				System.out.println((int) Math.round(player.xPos) + "," + (int) Math.round(player.zPos) + ","
						+ player.world.tiles[(int) Math.round(player.xPos)][(int) Math.round(player.zPos)]);
			}
			if (Keyboard.isKeyDown(GLFW_KEY_DOWN)) {
				if (player.canMove(Direction.SOUTH, 0.1f)) {
					player.zPos += 0.1f;
					player.facing = Direction.SOUTH;
				}
				player.walking = true;
				System.out.println((int) Math.round(player.xPos) + "," + (int) Math.round(player.zPos) + ","
						+ player.world.tiles[(int) Math.round(player.xPos)][(int) Math.round(player.zPos)]);
			}
			if (player.walking) {
				player.walkingTicks++;
			} else {
				player.walkingTicks = 0;
			}
			if (prevScaleChanged) {
				if (!(Keyboard.isKeyDown(GLFW_KEY_EQUAL) || Keyboard.isKeyDown(GLFW_KEY_MINUS))) {
					prevScaleChanged = false;
				}
			} else {
				if (Keyboard.isKeyDown(GLFW_KEY_EQUAL)) {
					prevScaleChanged = true;
					if (player.zoom > 2)
						player.zoom -= 1f;
					System.out.println(player.zoom);
				}
				if (Keyboard.isKeyDown(GLFW_KEY_MINUS)) {
					prevScaleChanged = true;
					if (player.zoom < 10)
						player.zoom += 1f;
				}
			}
		}
		if (Keyboard.isKeyDown(GLFW_KEY_ESCAPE)) {
			if (!prevEscPressed) {
				paused = !paused;
				prevEscPressed = true;
			}
		} else {
			prevEscPressed = false;
		}
	}

	public void draw() {
		int pX = (int) player.xPos;
		int pZ = (int) player.zPos;

		double pDiffX = player.xPos - pX;
		double pDiffZ = player.zPos - pZ;

		int tWidth = (int) (Main.WINDOW_WIDTH / (player.zoom * 2));

		int tilesAcross = (int) Math.ceil(player.zoom * 2 + 2);
		int tilesUp = (int) Math.ceil(player.zoom * 2 + 2);

		double rX = (pDiffX * tWidth);
		double rY = (pDiffZ * tWidth);

		int offsetX = (int) (pX - player.zoom);
		int offsetY = (int) (pZ - player.zoom);

		for (int x = -1; x < tilesAcross; x++) {
			for (int y = -1; y < tilesUp; y++) {
				int tileX = (offsetX + x);
				int tileZ = (offsetY + y);

				Texture texture = null;
				if (tileX < 0 || tileZ < 0 || tileX >= player.world.tiles.length
						|| tileZ >= player.world.tiles[0].length) {

				} else {
					if (player.world.obstacles[tileX][tileZ] == null) {
						texture = player.world.tiles[tileX][tileZ].getTexture();
						Renderer.drawTexture((int) (rX + x * tWidth), (int) (rY + y * tWidth),
								(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), texture);
//						Renderer.drawString((int) rX + x * tWidth, (int) rY + y * tWidth,
//								tWidth / (tileX + "," + tileZ).length(), tileX + "," + tileZ,
//								new Color(1, tileX % 2, tileZ % 2));
//
//						if (tileX == Math.round(player.xPos)) {
//							Renderer.drawRect((int) (rX + x * tWidth), (int) (rY + y * tWidth),
//									(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), 1f, 0f, 0f, 0.2f);
//						}
//						if (tileZ == Math.round(player.zPos)) {
//							Renderer.drawRect((int) (rX + x * tWidth), (int) (rY + y * tWidth),
//									(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), 0f, 0f, 1f, 0.2f);
//						}
					} else {
						texture = player.world.obstacles[tileX][tileZ].getTexture();
						Renderer.drawTexture((int) (rX + x * tWidth), (int) (rY + y * tWidth),
								(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), texture);
						Renderer.drawString((int) rX + x * tWidth, (int) rY + y * tWidth,
								tWidth / (tileX + "," + tileZ).length(), tileX + "," + tileZ,
								new Color(1, tileX % 2, tileZ % 2));

						if (tileX == Math.round(player.xPos)) {
							Renderer.drawRect((int) (rX + x * tWidth), (int) (rY + y * tWidth),
									(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), 1f, 0f, 0f, 0.2f);
						}
						if (tileZ == Math.round(player.zPos)) {
							Renderer.drawRect((int) (rX + x * tWidth), (int) (rY + y * tWidth),
									(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), 0f, 0f, 1f, 0.2f);
						}
					}
				}
			}
		}

		int pRenderX = (Main.WINDOW_WIDTH - tWidth) / 2;
		int pRenderY = (int) ((Main.WINDOW_HEIGHT - tWidth * 1.625) / 2);

		int pRenderX2 = pRenderX + tWidth;
		int pRenderY2 = (int) (pRenderY + tWidth * 1.62500);

		switch(player.facing) {
		
		case SOUTH:
		// walking animation
		if (player.walking) {
			if (player.walkingTicks % 30 > 15) {
				Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK1_BASE);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK1_PANTS,
						player.pantsColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK1_SLEEVES,
						player.sleeveColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK1_SKIN,
						player.skinColor);
			} else {
				Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK2_BASE);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK2_PANTS,
						player.pantsColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK2_SLEEVES,
						player.sleeveColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK2_SKIN,
						player.skinColor);
			}
		} else {
			Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_BASE);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_PANTS,
					player.pantsColor);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_SLEEVES,
					player.sleeveColor);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_SKIN,
					player.skinColor);
		}
		
		// stays the same
		Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_EYES, player.eyeColor);
		Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_SHIRT,
				player.shirtColor);
			
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.forwardHairstyles[player.hairstyle-1], player.hairColor);
			break;
		case EAST:
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_SHIRT,
					player.shirtColor);
			
			
			
			Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_BASE);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_PANTS,
					player.pantsColor);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_SLEEVES,
					player.sleeveColor);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_SKIN,
					player.skinColor);
			
			
			
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_EYES, player.eyeColor);
			
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.rightHairstyles[player.hairstyle-1], player.hairColor);
			break;
		case NORTH:
			// walking animation
			if (player.walking) {
				if (player.walkingTicks % 30 > 15) {
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK1_BASE);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK1_PANTS,
							player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK1_SLEEVES,
							player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK1_SKIN,
							player.skinColor);
				} else {
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK2_BASE);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK2_PANTS,
							player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK2_SLEEVES,
							player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK2_SKIN,
							player.skinColor);
				}
			} else {
				Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_IDLE_BASE);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_IDLE_PANTS,
						player.pantsColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_IDLE_SLEEVES,
						player.sleeveColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_IDLE_SKIN,
						player.skinColor);
			}
			
			// stays the same
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_EYES, player.eyeColor);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_SHIRT,
					player.shirtColor);
				
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.backwardHairstyles[player.hairstyle-1], player.hairColor);
			break;
		case WEST:
			break;
		default:
			break;
		}

//		Renderer.drawRect(pRenderX, pRenderY, pRenderX2, pRenderY2, 1f, 0f, 0.2f, 0.4f);

		int xAvg = (int) ((pRenderX + pRenderX2) / 2);
		int yAvg = (int) ((pRenderY + pRenderY2) / 2);

		Renderer.drawCircle(new Vertex(xAvg, yAvg), 2, new Color(0f, 1f, 0f));
		Renderer.drawCircle(new Vertex(xAvg - tWidth / 2, yAvg - (int) (tWidth * 1.625 / 2)), 2, new Color(0f, 1f, 0f));
		Renderer.drawCircle(new Vertex(xAvg + tWidth / 2, yAvg - (int) (tWidth * 1.625 / 2)), 2, new Color(0f, 1f, 0f));
		Renderer.drawCircle(new Vertex(xAvg + tWidth / 2, yAvg + (int) (tWidth * 1.625 / 2)), 2, new Color(0f, 1f, 0f));
		Renderer.drawCircle(new Vertex(xAvg - tWidth / 2, yAvg + (int) (tWidth * 1.625 / 2)), 2, new Color(0f, 1f, 0f));

		String t = String.format("(%5.2f,%5.2f)", player.xPos, player.zPos);

		Renderer.drawString((int) ((xAvg + t.length() * 20) / 2), pRenderY - 30, 20, t, new Color(0f, 0f, 1f));

		if (paused) {
			glDisable(GL_TEXTURE_2D);
			glLoadIdentity();

			glBegin(GL_QUADS);
			glColor4f(0, 0, 0, 0.3f);
			glVertex2i(0, Main.WINDOW_HEIGHT);

			glVertex2i(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

			glVertex2i(Main.WINDOW_WIDTH, 0);

			glVertex2i(0, 0);

			glEnd();

			int textLengthPx = "Game Paused".toCharArray().length * 48;

			int x = (Main.WINDOW_WIDTH - textLengthPx) / 2;

			Renderer.drawString(x, Main.WINDOW_HEIGHT - 64, 48, "Game Paused");
		}
	}

	public int getCursorPosX() {
		DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, posX, null);
		return (int) posX.get(0);
	}

	public int getCursorPosY() {
		DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, null, posY);
		return (int) posY.get(0);
	}
}
