package com.cool.menu;

import java.util.Random;

import com.cool.Controls;
import com.cool.Main;
import com.cool.Sounds;
import com.cool.Textures;
import com.cool.lib.Color;
import com.cool.lib.Keyboard;
import com.cool.lib.Renderer;

public class CharacterCustomize extends Menu {

	public int hairstyle = 1;

	public int pRenderX = 256;
	public int pRenderY = Main.WINDOW_HEIGHT - 384;
	public int pRenderX2 = 0;
	public int pRenderY2 = Main.WINDOW_HEIGHT;

	public Color pantsColor;
	public Color sleeveColor;
	public Color skinColor;
	public Color eyeColor;
	public Color shirtColor;
	public Color hairColor;

	public Color tempColor = new Color(1, 1, 1);
	public boolean changeColor = false;
	public boolean changingColor = false;
	public int selectedPart = 0;
	public int selectedColor = 0;

	private Color[] pantsColors = { 
			new Color(68, 136, 255),
			new Color(208, 68, 255),
			new Color(255, 68, 167),
			new Color(255, 68, 74),
			new Color(255, 251, 68),
			new Color(5, 252, 78),
			new Color(64, 252, 170),
			new Color(255, 48, 106)
			};
	private Color[] sleeveColors = { 
			new Color(68, 136, 255),
			new Color(208, 68, 255),
			new Color(255, 68, 167),
			new Color(255, 68, 74),
			new Color(255, 251, 68),
			new Color(5, 252, 78),
			new Color(64, 252, 170),
			new Color(255, 48, 106)
			};
	private Color[] shirtColors = { 
			new Color(68, 136, 255),
			new Color(208, 68, 255),
			new Color(255, 68, 167),
			new Color(255, 68, 74),
			new Color(255, 251, 68),
			new Color(5, 252, 78),
			new Color(64, 252, 170),
			new Color(255, 48, 106)
			};
	private Color[] hairColors = {
			new Color(91, 33, 0),
			new Color(168, 56, 0),
			new Color(255, 251, 237),
			new Color(242, 230, 181),
			new Color(160, 136, 77),
			new Color(30, 22, 2),
			new Color(255, 171, 112),
			new Color(255, 212, 112)
	};
	private Color[] eyeColors = { 
			new Color(68, 136, 255),
			new Color(91, 33, 0),
			new Color(58, 186, 0),
			new Color(208,195,131)
			};
	private Color[] skinColors = {
			new Color(91, 33, 0),
			new Color(255, 224, 201),
			new Color(255, 241, 232)
	};

	@Override
	public void init() {
		Random random = new Random();
		createButton(704, 240, 16, 336, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "PLAY GAME", 40);
		createButton(704, 128, 16, 224, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "CHANGE COLOR", 40);
		createButton(704, 16, 16, 112, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "CHANGE HAIRSTYLE", 40);
		setButtonSelectSound(Sounds.BUTTON_SELECT);
		setButtonClickSound(Sounds.BUTTON_SELECT);
		pantsColor = pantsColors[random.nextInt(pantsColors.length)];
		sleeveColor = sleeveColors[random.nextInt(sleeveColors.length)];
		skinColor = skinColors[random.nextInt(skinColors.length)];
		eyeColor = eyeColors[random.nextInt(eyeColors.length)];
		shirtColor = shirtColors[random.nextInt(shirtColors.length)];
		hairColor = hairColors[random.nextInt(hairColors.length)];
		hairstyle = random.nextInt(5) + 1;
		if(Math.random() > 0.99) {
			skinColor = new Color(255, 178, 102);
			shirtColor = new Color(33, 33, 33);
			sleeveColor = new Color(33, 33, 33);
			pantsColor = new Color(56, 56, 56);
			hairColor = new Color(255, 250, 204);
			eyeColor = new Color(42, 66, 175);
			hairstyle = 4;
		}
	}

	@Override
	public void tick() {
		if (!changeColor) {
			super.tick();
			Keyboard.isKeyPressed(Controls.BREAK);
			if (buttonPressed) {
				buttonPressed = false;
				switch (buttonSelected) {
				case 0:
					Menu.currentMenu = MenuTypes.GAME;
					Main.game.init();
					break;
				case 1:
					changeColor = true;
					break;
				case 2:
					hairstyle++;
					if (hairstyle > 5) {
						hairstyle = 1;
					}
					break;
				}
				buttonPressed = false;
			}
		} else {
			if (changingColor) {
				if (Keyboard.isKeyPressed(Controls.BREAK)) {
					changingColor = false;
					Sounds.BUTTON_SELECT.play();
				}
				if (Keyboard.isKeyPressed(Controls.INTERACT)) {
					switch (selectedPart) {
					case 0:
						pantsColor = tempColor;
						break;
					case 1:
						sleeveColor = tempColor;
						break;
					case 2:
						skinColor = tempColor;
						break;
					case 3:
						eyeColor = tempColor;
						break;
					case 4:
						shirtColor = tempColor;
						break;
					case 5:
						hairColor = tempColor;
						break;
					}
					changingColor = false;
					Sounds.BUTTON_SELECT.play();
				}
				if (Keyboard.isKeyPressed(Controls.MOVE_DOWN)) {
					if (selectedColor < 2)
						selectedColor++;
					Sounds.BUTTON_SELECT.play();
				}
				if (Keyboard.isKeyPressed(Controls.MOVE_UP)) {
					if (selectedColor > 0)
						selectedColor--;
					Sounds.BUTTON_SELECT.play();
				}
				if (Keyboard.isKeyDown(Controls.MOVE_RIGHT)) {
					switch (selectedColor) {
					case 0:
						tempColor.r += 0.01f;
						if (tempColor.r >= 1) {
							tempColor.r = 1;
						}
						break;
					case 1:
						tempColor.g += 0.01f;
						if (tempColor.g >= 1) {
							tempColor.g = 1;
						}
						break;
					case 2:
						tempColor.b += 0.01f;
						if (tempColor.b >= 1) {
							tempColor.b = 1;
						}
						break;
					}
				}
				if (Keyboard.isKeyDown(Controls.MOVE_LEFT)) {
					switch (selectedColor) {
					case 0:
						tempColor.r -= 0.01f;
						if (tempColor.r <= 0) {
							tempColor.r = 0;
						}
						break;
					case 1:
						tempColor.g -= 0.01f;
						if (tempColor.g <= 0) {
							tempColor.g = 0;
						}
						break;
					case 2:
						tempColor.b -= 0.01f;
						if (tempColor.b <= 0) {
							tempColor.b = 0;
						}
						break;
					}
				}
			} else {
				tempColor = new Color((float) (Math.sin(Main.TICKS / 30d) / 4f + 1f / 1.1f),
						(float) (Math.sin(Main.TICKS / 30d) / 4f + 1f / 1.1f),
						(float) (Math.sin(Main.TICKS / 30d) / 4f + 1f / 1.1f));
				if (Keyboard.isKeyPressed(Controls.BREAK)) {
					changeColor = false;
					Sounds.BUTTON_SELECT.play();
				}
				if (Keyboard.isKeyPressed(Controls.MOVE_UP) || Keyboard.isKeyPressed(Controls.MOVE_RIGHT)) {
					if (selectedPart < 5)
						selectedPart++;
					Sounds.BUTTON_SELECT.play();
				}
				if (Keyboard.isKeyPressed(Controls.MOVE_DOWN) || Keyboard.isKeyPressed(Controls.MOVE_LEFT)) {
					if (selectedPart > 0)
						selectedPart--;
					Sounds.BUTTON_SELECT.play();
				}
				if (Keyboard.isKeyPressed(Controls.INTERACT)) {
					changingColor = true;
					switch (selectedPart) {
					case 0:
						tempColor = pantsColor;
						break;
					case 1:
						tempColor = sleeveColor;
						break;
					case 2:
						tempColor = skinColor;
						break;
					case 3:
						tempColor = eyeColor;
						break;
					case 4:
						tempColor = shirtColor;
						break;
					case 5:
						tempColor = hairColor;
						break;
					}
					Sounds.BUTTON_SELECT.play();
				}
			}
		}
	}

	@Override
	public void render() {
		super.render();
		Renderer.drawTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_BASE);
		if (changeColor && selectedPart == 0) {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_PANTS,
					tempColor);
			Renderer.drawString(pRenderX, Main.WINDOW_HEIGHT - 50, 50, "Pants");
		} else {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_PANTS,
					pantsColor);
		}
		if (changeColor && selectedPart == 1) {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_SLEEVES,
					tempColor);
			Renderer.drawString(pRenderX, Main.WINDOW_HEIGHT - 50, 50, "Sleeves");
		} else {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_SLEEVES,
					sleeveColor);
		}
		if (changeColor && selectedPart == 2) {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_SKIN,
					tempColor);
			Renderer.drawString(pRenderX, Main.WINDOW_HEIGHT - 50, 50, "Skin");
		} else {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_IDLE_SKIN,
					skinColor);
		}
		if (changeColor && selectedPart == 3) {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_EYES, tempColor);
			Renderer.drawString(pRenderX, Main.WINDOW_HEIGHT - 50, 50, "Eyes");
		} else {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_EYES, eyeColor);
		}
		if (changeColor && selectedPart == 4) {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_SHIRT, tempColor);
			Renderer.drawString(pRenderX, Main.WINDOW_HEIGHT - 50, 50, "Shirt");
		} else {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2, Textures.P_FORWARD_SHIRT, shirtColor);
		}
		if (changeColor && selectedPart == 5) {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
					Textures.forwardHairstyles[hairstyle - 1], tempColor);
			Renderer.drawString(pRenderX, Main.WINDOW_HEIGHT - 50, 50, "Hair");
		} else {
			Renderer.drawColoredTexture(pRenderX, pRenderY, pRenderX2, pRenderY2,
					Textures.forwardHairstyles[hairstyle - 1], hairColor);
		}
		if (changingColor) {
			int barWidth = Main.WINDOW_WIDTH - 16 - (pRenderX + 16);
			Renderer.drawRect(pRenderX + 16, Main.WINDOW_HEIGHT - 64, Main.WINDOW_WIDTH - 16, Main.WINDOW_HEIGHT - 128,
					new Color(1f, 0f, 0f));
			Renderer.drawRect((int) (pRenderX + 16 + barWidth * tempColor.r), Main.WINDOW_HEIGHT - 62,
					(int) (pRenderX + 16 + barWidth * tempColor.r + 4), Main.WINDOW_HEIGHT - 130,
					selectedColor == 0 ? new Color(1f, 0.3f, 1f) : new Color(1f, 1f, 1f));

			Renderer.drawRect(pRenderX + 16, Main.WINDOW_HEIGHT - 144, Main.WINDOW_WIDTH - 16, Main.WINDOW_HEIGHT - 208,
					new Color(0f, 1f, 0f));
			Renderer.drawRect((int) (pRenderX + 16 + barWidth * tempColor.g), Main.WINDOW_HEIGHT - 142,
					(int) (pRenderX + 16 + barWidth * tempColor.g + 4), Main.WINDOW_HEIGHT - 210,
					selectedColor == 1 ? new Color(1f, 0.3f, 1f) : new Color(1f, 1f, 1f));

			Renderer.drawRect(pRenderX + 16, Main.WINDOW_HEIGHT - 224, Main.WINDOW_WIDTH - 16, Main.WINDOW_HEIGHT - 288,
					new Color(0f, 0f, 1f));
			Renderer.drawRect((int) (pRenderX + 16 + barWidth * tempColor.b), Main.WINDOW_HEIGHT - 222,
					(int) (pRenderX + 16 + barWidth * tempColor.b + 4), Main.WINDOW_HEIGHT - 290,
					selectedColor == 2 ? new Color(1f, 0.3f, 1f) : new Color(1f, 1f, 1f));
		}
	}
}
