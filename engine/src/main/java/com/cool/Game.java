package com.cool;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.opengl.GL11.*;

import java.io.Serializable;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;

import com.cool.item.ItemStack;
import com.cool.item.ItemTileObstacle;
import com.cool.item.Items;
import com.cool.lib.Color;
import com.cool.lib.Controls;
import com.cool.lib.Direction;
import com.cool.lib.IO;
import com.cool.lib.Keyboard;
import com.cool.lib.Renderer;
import com.cool.lib.Vertex;
import com.cool.menu.Menu;
import com.cool.particle.Particles;
import com.cool.particles.ParticleTile;
import com.cool.player.Player;
import com.cool.tile.Tile;
import com.cool.tile.TileObstacle;
import com.cool.world.Worlds;

public class Game extends Menu implements Serializable {

	private static final long serialVersionUID = -4908274186970551379L;

	public long window;

	public Player player;
	public boolean debug = false;

	public boolean invOpened = false;
	public boolean paused = false;

	public int slotNumber;

	public int iSelectedNumber = 0;

	public Game(long window) {
		this.window = window;
	}

	public void init() {
		player = new Player(Worlds.OVERWORLD, 510, 510, Direction.SOUTH, Main.characterCustomize.eyeColor,
				Main.characterCustomize.shirtColor, Main.characterCustomize.hairColor,
				Main.characterCustomize.pantsColor, Main.characterCustomize.sleeveColor,
				Main.characterCustomize.skinColor, Main.characterCustomize.hairstyle);
		IO.write("./save" + slotNumber, this);
	}

	public void tick() {
		if (!paused) {
			if (invOpened) {
				if (Keyboard.isKeyPressed(Controls.MOVE_UP)) {
					Sounds.BUTTON_SELECT.play();
					iSelectedNumber--;
					if (iSelectedNumber < 0) {
						iSelectedNumber = player.inventory.size() - 1;
					}
				}
				if (Keyboard.isKeyPressed(Controls.MOVE_DOWN)) {
					Sounds.BUTTON_SELECT.play();
					iSelectedNumber++;
					if (iSelectedNumber > player.inventory.size() - 1) {
						iSelectedNumber = 0;
					}
				}
				int moveySlot = 0;
				boolean movey = false;

				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_0)) {
					moveySlot = 0;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_1)) {
					moveySlot = 1;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_2)) {
					moveySlot = 2;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_3)) {
					moveySlot = 3;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_4)) {
					moveySlot = 4;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_5)) {
					moveySlot = 5;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_6)) {
					moveySlot = 6;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_7)) {
					moveySlot = 7;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_8)) {
					moveySlot = 8;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_9)) {
					moveySlot = 9;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_10)) {
					moveySlot = 10;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_11)) {
					moveySlot = 11;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_12)) {
					moveySlot = 12;
					movey = true;
				}
				if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_0) || Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_1)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_2)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_3)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_4)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_5)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_6)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_7)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_8)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_9)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_10)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_11)
						|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_12)) {
					Sounds.BUTTON_SELECT.play();
				}
				if (movey == true) {
					ItemStack hbTingy = player.hotbar[moveySlot];
					player.hotbar[moveySlot] = player.inventory.get(iSelectedNumber);
					player.inventory.set(iSelectedNumber, hbTingy);
				}
			} else {
				for (Tile[] ta : player.world.tiles) {
					for (Tile t : ta) {
						t.tick();
					}
				}
				for (Tile[] ta : player.world.obstacles) {
					for (Tile t : ta) {
						if (t != null)
							t.tick();
					}
				}
				player.walking = false;
				if (Keyboard.isKeyDown(Controls.MOVE_LEFT)) {
					player.attemptMove(Direction.WEST);
				}
				if (Keyboard.isKeyDown(Controls.MOVE_RIGHT)) {
					player.attemptMove(Direction.EAST);
				}
				if (Keyboard.isKeyDown(Controls.MOVE_UP)) {
					player.attemptMove(Direction.NORTH);
				}
				if (Keyboard.isKeyDown(Controls.MOVE_DOWN)) {
					player.attemptMove(Direction.SOUTH);
				}
				if (player.walking) {
					player.walkingTicks++;
				} else {
					player.walkingTicks = 0;
				}
				if (Keyboard.isKeyPressed(Controls.BREAK)) {
					Vertex fPos = player.getFacingPosition();
					if (player.world.obstacles[fPos.x][fPos.z] != null) {
						Particles.spawn(new ParticleTile(player.getFacingObstacle(), 1), 30, fPos.x, 0, fPos.z);
						player.getFacingObstacle().onBroken(player, fPos);
					}
					player.world.obstacles[fPos.x][fPos.z] = null;
				}
				if (Keyboard.isKeyPressed(Controls.INTERACT)) {
					if (player.heldItemStack() != null) {
						player.heldItemStack().item.onUse(player);
					}
				}
			}
			if (Keyboard.isKeyPressed(Controls.ZOOM_CLOSER)) {
				if (player.zoom > 2)
					player.zoom -= 1f;
			}
			if (Keyboard.isKeyPressed(Controls.ZOOM_FARTHER)) {
				if (player.zoom < 10)
					player.zoom += 1f;
			}
			if (Keyboard.isKeyPressed(Controls.DEBUG_TOGGLE)) {
				debug = !debug;
			}
			if (Keyboard.isKeyDown(Controls.SPRINT) && player.walking) {
				player.run = true;
			}
			if (Keyboard.isKeyPressed(Controls.OEPN_INVENTORY)) {
				invOpened = !invOpened;
				Sounds.BUTTON_SELECT.play();
			}
			if (!player.walking) {
				player.run = false;
			}
			hotbarControls();
			Particles.tick(player);

		}
		if (Keyboard.isKeyPressed(Controls.PAUSE)) {
			paused = !paused;
			IO.write("./save" + slotNumber, (Object) this);
			player.giveItem(Items.carrot, 1);
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

		double rX = -(pDiffX * tWidth);
		double rY = -(pDiffZ * tWidth);

		int offsetX = (int) (pX - player.zoom);
		int offsetY = (int) (pZ - player.zoom);

		/**
		 * Tile Highlight Variables
		 */
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;

		for (int x = -1; x < tilesAcross; x++) {
			for (int y = -1; y < tilesUp; y++) {
				int tileX = (offsetX + x);
				int tileZ = (offsetY + y);

				if (tileX < 0 || tileZ < 0 || tileX >= player.world.tiles.length
						|| tileZ >= player.world.tiles[0].length) {

				} else {
					if (player.heldItemStack() != null && player.heldItemStack().item instanceof ItemTileObstacle) {
						if (tileX == player.getFacingPosition().x && tileZ == player.getFacingPosition().z) {
							x1 = (int) (rX + x * tWidth);
							y1 = (int) (rY + y * tWidth);
							x2 = (int) (rX + (1 + x) * tWidth);
							y2 = (int) (rY + (1 + y) * tWidth);
						}
					}
					if (player.world.obstacles[tileX][tileZ] == null) {
						Tile t = player.world.tiles[tileX][tileZ];
						t.render(new Vertex(tileX, tileZ), (int) (rX + x * tWidth), (int) (rY + y * tWidth),
								(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), tWidth);
//						Renderer.drawTexture((int) (rX + x * tWidth), (int) (rY + y * tWidth),
//								(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), texture);
						if (debug) {
							Renderer.drawString((int) rX + x * tWidth, (int) rY + y * tWidth,
									tWidth / (tileX + "," + tileZ).length(), tileX + "," + tileZ,
									new Color(1, tileX % 2, tileZ % 2));
						}
					} else {
						TileObstacle t = player.world.obstacles[tileX][tileZ];
						if (t.layer == 0) {
							t.render(new Vertex(tileX, tileZ), (int) (rX + x * tWidth), (int) (rY + y * tWidth),
									(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), tWidth);
							if (debug) {
								Renderer.drawString((int) rX + x * tWidth, (int) rY + y * tWidth,
										tWidth / (tileX + "," + tileZ).length(), tileX + "," + tileZ,
										new Color(1, tileX % 2, tileZ % 2));
							}
						}
						Vertex pos = player.getFacingPosition();
						if (tileX == pos.x && tileZ == pos.z) {
							x1 = (int) (rX + x * tWidth);
							y1 = (int) (rY + y * tWidth);
							x2 = (int) (rX + (1 + x) * tWidth);
							y2 = (int) (rY + (1 + y) * tWidth);
						}
					}
				}
			}
		}

		Particles.render(rX, rY, player, tWidth);

		int pRenderX = (Main.WINDOW_WIDTH - tWidth) / 2;
		int pRenderY = (int) ((Main.WINDOW_HEIGHT - tWidth * 1.625) / 2);

		int pRenderX2 = pRenderX + tWidth;
		int pRenderY2 = (int) (pRenderY + tWidth * 1.62500);

		Renderer.drawRect(x1, y1, x2, y2, 1, 1, 1, (float) (Math.abs(Math.sin(Main.TICKS / 30f)) / 2 + 0.3f));

		switch (player.facing) {

		case SOUTH:
			// walking animation
			if (player.walking) {
				if (player.walkingTicks % 30 > 15) {
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK1_BASE);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_FORWARD_WALK1_PANTS, player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_FORWARD_WALK1_SLEEVES, player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK1_SKIN,
							player.skinColor);
				} else {
					if (player.walkingTicks % 30 == 0) {
						player.getStandingOn().playStepSound();
					}
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_WALK2_BASE);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_FORWARD_WALK2_PANTS, player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_FORWARD_WALK2_SLEEVES, player.sleeveColor);
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
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_EYES,
					player.eyeColor);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_SHIRT,
					player.shirtColor);

			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
					Textures.forwardHairstyles[player.hairstyle - 1], player.hairColor);
			break;
		case EAST:
			// walking animation
			if (player.walking) {
				if (player.walkingTicks % 30 > 15) {
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_WALK1_PANTS,
							player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_SHIRT,
							player.shirtColor);

					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_RIGHT_WALK1_SLEEVES, player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_WALK1_SKIN,
							player.skinColor);
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_WALK1_BASE);
				} else {
					if (player.walkingTicks % 30 == 0) {
						player.getStandingOn().playStepSound();
					}
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_PANTS,
							player.pantsColor);

					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_SHIRT,
							player.shirtColor);

					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_SLEEVES,
							player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_SKIN,
							player.skinColor);
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_BASE);
				}
			} else {
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_PANTS,
						player.pantsColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_SHIRT,
						player.shirtColor);

				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_SLEEVES,
						player.sleeveColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_SKIN,
						player.skinColor);
				Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_IDLE_BASE);
			}

			// stays the same
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_RIGHT_EYES,
					player.eyeColor);

			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
					Textures.rightHairstyles[player.hairstyle - 1], player.hairColor);
			break;
		case NORTH:
			// walking animation
			if (player.walking) {
				if (player.walkingTicks % 30 > 15) {
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK1_BASE);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_BACKWARD_WALK1_PANTS, player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_BACKWARD_WALK1_SLEEVES, player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_BACKWARD_WALK1_SKIN, player.skinColor);
				} else {
					if (player.walkingTicks % 30 == 0) {
						player.getStandingOn().playStepSound();
					}
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_WALK2_BASE);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_BACKWARD_WALK2_PANTS, player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_BACKWARD_WALK2_SLEEVES, player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
							Textures.P_BACKWARD_WALK2_SKIN, player.skinColor);
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
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_EYES,
					player.eyeColor);
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_BACKWARD_SHIRT,
					player.shirtColor);

			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
					Textures.backwardHairstyles[player.hairstyle - 1], player.hairColor);
			break;
		case WEST:
			// walking animation
			if (player.walking) {
				if (player.walkingTicks % 30 > 15) {
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_WALK1_PANTS,
							player.pantsColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_SHIRT,
							player.shirtColor);

					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_WALK1_SLEEVES,
							player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_WALK1_SKIN,
							player.skinColor);
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_WALK1_BASE);
				} else {
					if (player.walkingTicks % 30 == 0) {
						player.getStandingOn().playStepSound();
					}
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_PANTS,
							player.pantsColor);

					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_SHIRT,
							player.shirtColor);

					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_SLEEVES,
							player.sleeveColor);
					Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_SKIN,
							player.skinColor);
					Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_BASE);
				}
			} else {
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_PANTS,
						player.pantsColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_SHIRT,
						player.shirtColor);

				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_SLEEVES,
						player.sleeveColor);
				Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_SKIN,
						player.skinColor);
				Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_IDLE_BASE);
			}

			// stays the same
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_LEFT_EYES,
					player.eyeColor);

			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
					Textures.leftHairstyles[player.hairstyle - 1], player.hairColor);
			break;
		default:
			break;
		}
		if (debug) {
			String t = String.format("(%5.2f,%5.2f)", player.xPos, player.zPos);

			Renderer.drawString((int) ((0 + t.length() * 20) / 2), Main.WINDOW_HEIGHT - 100, 20, t,
					new Color(0f, 0f, 1f));
		}

		for (int x = -1; x < tilesAcross; x++) {
			for (int y = -1; y < tilesUp; y++) {
				int tileX = (offsetX + x);
				int tileZ = (offsetY + y);

				if (tileX < 0 || tileZ < 0 || tileX >= player.world.tiles.length
						|| tileZ >= player.world.tiles[0].length) {

				} else {
					if (player.world.obstacles[tileX][tileZ] != null) {
						TileObstacle t = player.world.obstacles[tileX][tileZ];

						if (t.layer == 1) {
							t.render(new Vertex(tileX, tileZ), (int) (rX + x * tWidth), (int) (rY + y * tWidth),
									(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), tWidth);
							Vertex pos = player.getFacingPosition();
							if (pos.x == tileX && pos.z == tileZ) {
								Renderer.drawRect((int) (rX + x * tWidth), (int) (rY + y * tWidth),
										(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), 1, 1, 1,
										(float) (Math.abs(Math.sin(Main.TICKS / 30f)) / 2 + 0.3f));
							}
							if (debug) {
								Renderer.drawString((int) rX + x * tWidth, (int) rY + y * tWidth,
										tWidth / (tileX + "," + tileZ).length(), tileX + "," + tileZ,
										new Color(1, tileX % 2, tileZ % 2));
							}
						}
					}
				}
				if (debug) {
					if (tileX == Math.round(player.xPos) && tileZ == Math.round(player.zPos)) {
						Renderer.drawRect((int) (rX + x * tWidth), (int) (rY + y * tWidth),
								(int) (rX + (1 + x) * tWidth), (int) (rY + (1 + y) * tWidth), 0, 0, 1f, 0.3f);
					}
				}
			}
		}

		int h = player.health;
		int currX = 0;
		int hSize = Main.WINDOW_WIDTH / 2 / 11;
		int currY = Main.WINDOW_HEIGHT - hSize;
		for (int i = 0; i < 10; i++) {
			if (h > 1) {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.HEART_FULL);
			} else if (h == 1) {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.HEART_HALF);
			} else {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.HEART_EMPTY);
			}
			h -= 2;
			currX += hSize;
		}
		currX = Main.WINDOW_WIDTH - hSize * 10;
		h = player.hunger;
		for (int i = 0; i < 10; i++) {
			if (h > 1) {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.CARROT_FULL);
			} else if (h == 1) {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.CARROT_HALF);
			} else {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.CARROT_EMPTY);
			}
			h -= 2;
			currX += hSize;
		}
		currY -= hSize;
		currX = 0;
		h = player.thirst;
		for (int i = 0; i < 10; i++) {
			if (h > 1) {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.WATER_FULL);
			} else if (h == 1) {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.WATER_HALF);
			} else {
				Renderer.drawTexture(currX, currY, currX + hSize, currY + hSize, Textures.WATER_EMPTY);
			}
			h -= 2;
			currX += hSize;
		}

		int hbWidth = 64;
		currY = hbWidth + 10;
		currX = Main.WINDOW_WIDTH / 2 - ((hbWidth + 2) * 10 / 2);

		for (int i = 3; i < 13; i++) {
			Renderer.drawTexture(currX, currY, currX + hbWidth, currY + hbWidth,
					player.selectedSlot == i ? Textures.HOTBAR_SLOT_SELECTED : Textures.HOTBAR_SLOT_NORMAL);
			if (player.hotbar[i] != null) {
				Renderer.drawTexture(currX + 4, currY + 4, currX + hbWidth - 4, currY + hbWidth - 4,
						player.hotbar[i].item.texture);
				Renderer.drawString(currX + 7, currY + 7, 15, Integer.toString(player.hotbar[i].count));

			}
			currX += hbWidth + 2;
		}
		currY = hbWidth + 6;
		hbWidth = 72;
		currX = Main.WINDOW_WIDTH / 2 - ((hbWidth + 2) * 3 / 2);
		currY -= hbWidth - 2;

		for (int i = 0; i < 3; i++) {
			Renderer.drawTexture(currX, currY, currX + hbWidth, currY + hbWidth,
					player.selectedSlot == i ? Textures.HOTBAR_SLOT_SELECTED : Textures.HOTBAR_SLOT_NORMAL);
			if (player.hotbar[i] != null) {
				Renderer.drawTexture(currX + 4, currY + 4, currX + hbWidth - 4, currY + hbWidth - 4,
						player.hotbar[i].item.texture);
				Renderer.drawString(currX + 9, currY + 9, 18, Integer.toString(player.hotbar[i].count));
			}
			currX += hbWidth + 2;
		}
		if (player.heldItemStack() != null) {
			String txt = player.heldItemStack().item.getDisplayName();
			int fSize = 30;

			int textLengthPx = txt.toCharArray().length * fSize;

			int x = (Main.WINDOW_WIDTH - textLengthPx) / 2;

			Renderer.drawString(x, 128+fSize, fSize, txt);
		}

		if (invOpened) {
			glDisable(GL_TEXTURE_2D);
			glLoadIdentity();

			glBegin(GL_QUADS);
			glColor4f(0, 0, 0, 0.1f);
			glVertex2i(0, Main.WINDOW_HEIGHT);

			glVertex2i(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);

			glVertex2i(Main.WINDOW_WIDTH, 0);

			glVertex2i(0, 0);

			glEnd();

			int fSize = 20;
			int cY = Main.WINDOW_HEIGHT - fSize * 2 - 70 - 40;

			Renderer.drawRect(50, Main.WINDOW_HEIGHT - fSize - 70, Main.WINDOW_WIDTH - 50, fSize + 120,
					new Color(0, 0, 0));

			for (int i = 0; i < player.inventory.size(); i++) {
				ItemStack s = player.inventory.get(i);

				Renderer.drawString(100, cY, fSize, s.item.getDisplayName() + " (" + s.count + ")",
						i == iSelectedNumber ? new Color(1f, 1f, 0f) : new Color(1f, 1f, 1f));
				cY -= fSize;
			}
		}

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

	public void hotbarControls() {
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_0)) {
			player.selectedSlot = 0;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_1)) {
			player.selectedSlot = 1;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_2)) {
			player.selectedSlot = 2;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_3)) {
			player.selectedSlot = 3;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_4)) {
			player.selectedSlot = 4;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_5)) {
			player.selectedSlot = 5;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_6)) {
			player.selectedSlot = 6;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_7)) {
			player.selectedSlot = 7;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_8)) {
			player.selectedSlot = 8;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_9)) {
			player.selectedSlot = 9;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_10)) {
			player.selectedSlot = 10;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_11)) {
			player.selectedSlot = 11;
		}
		if (Keyboard.isKeyDown(Controls.HOTBAR_SLOT_12)) {
			player.selectedSlot = 12;
		}
		if (Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_0) || Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_1)
				|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_2) || Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_3)
				|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_4) || Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_5)
				|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_6) || Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_7)
				|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_8) || Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_9)
				|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_10) || Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_11)
				|| Keyboard.isKeyPressed(Controls.HOTBAR_SLOT_12)) {
			Sounds.BUTTON_SELECT.play();
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
