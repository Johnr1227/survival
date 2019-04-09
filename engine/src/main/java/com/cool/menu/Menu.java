	package com.cool.menu;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.cool.audio.Audio.Sound;
import com.cool.lib.Keyboard;
import com.cool.lib.Renderer;
import com.cool.lib.Texture;
import com.cool.menu.lib.Button;

public class Menu {

	public static MenuTypes currentMenu = MenuTypes.MAIN;

	public static enum MenuTypes {
		NONE, MAIN, OPTIONS, GAME;

		MenuTypes() {

		}
	}

	public ArrayList<Button> buttons = new ArrayList<>();
	public boolean prevBtnDown = false;

	public int buttonSelected = 0;
	public boolean buttonPressed = false;
	
	public Sound buttonSelectSound;
	public Sound buttonClickSound;

	public void init() {

	}
	public void setButtonSelectSound(Sound sound) {
		buttonSelectSound = sound;
	}
	public void setButtonClickSound(Sound sound) {
		buttonClickSound = sound;
	}
	
	public void tick() {
		if(!(Keyboard.isKeyDown(GLFW.GLFW_KEY_UP) || Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN) || Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT) || Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT) || Keyboard.isKeyDown(GLFW.GLFW_KEY_ENTER))){
			prevBtnDown = false;
		}
		if(!prevBtnDown) {
			if(Keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
				decrementButton();
				prevBtnDown = true;
				buttonSelectSound.play();
			} else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
				incrementButton();
				prevBtnDown = true;
				buttonSelectSound.play();
			} else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
				decrementButton();
				prevBtnDown = true;
				buttonSelectSound.play();
			} else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
				incrementButton();
				prevBtnDown = true;
				buttonSelectSound.play();
			}
			if(Keyboard.isKeyDown(GLFW.GLFW_KEY_ENTER)) {
				buttonPressed = true;
				prevBtnDown = true;
				buttonClickSound.play();
			}
		}
	}
	private void incrementButton() {
		buttonSelected++;
		if(buttonSelected > buttons.size()-1) {
			buttonSelected = 0;
		}
	}
	private void decrementButton() {
		buttonSelected--;
		if(buttonSelected < 0) {
			buttonSelected = buttons.size()-1;
		}
	}
	public void render() {
		int i = 0;
		for (Button b : buttons) {
			Texture tex = b.normalTexture;
			if (i == buttonSelected) {
				tex = b.selectedTexture;
			}
			Renderer.drawTexture(b.x, b.y, b.x2, b.y2, tex);
			int textLengthPx = b.text.toCharArray().length*b.textSize;
			int btnWidth = Math.abs(b.x2-b.x);
			
			int textHeightPx = b.textSize;
			int btnHeight = Math.abs(b.y2-b.y);
			
			int x = b.x2 + (btnWidth - textLengthPx)/2;
			int y = b.y + (btnHeight - textHeightPx)/2;
			
			Renderer.drawString(x, y, b.textSize, b.text);
			i++;
		}
	}

	public void createButton(int x, int y, int x2, int y2, Texture normal, Texture selected, String text, int textSize) {
		buttons.add(new Button(x, y, x2, y2, normal, selected, text, textSize));
	}
}
