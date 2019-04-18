package com.cool.menu;

import com.cool.Game;
import com.cool.Main;
import com.cool.Sounds;
import com.cool.Textures;
import com.cool.lib.Controls;
import com.cool.lib.IO;
import com.cool.lib.Keyboard;
import com.cool.lib.Renderer;

public class SaveMenu extends Menu {
	public Game save1,save2,save3;
	
	@Override
	public void init() {
		save1 = (Game)IO.read("./save1");
		save2 = (Game)IO.read("./save2");
		save3 = (Game)IO.read("./save3");
		
		if(save1 == null) { 
			createButton(704, 248, 16, 348, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "Save Slot A: Empty", 40);
		}
		else {
			createButton(704, 248, 16, 348, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "Save Slot A", 40);
		}
		if(save2 == null) {  
			createButton(704, 132, 16, 232, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "Save Slot B: Empty", 40);
		} else {
			createButton(704, 132, 16, 232, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "Save Slot B", 40);
		}
		if(save3 == null) {
			createButton(704, 16, 16, 116, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "Save Slot C: Empty", 40);
		} else {
			createButton(704, 16, 16, 116, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "Save Slot C", 40);
		}
		setButtonSelectSound(Sounds.BUTTON_SELECT);
		setButtonClickSound(Sounds.BUTTON_SELECT);
	}

	@Override
	public void tick() {
		super.tick();
		if (buttonPressed) {
			switch (buttonSelected) {
			case 0: 
				if(save1 == null) {
					currentMenu = MenuTypes.CHARACTER_CUSTOMIZE;
					Main.game.slotNumber = 1;
				} else {
					Main.game = save1;
					currentMenu = MenuTypes.GAME;
				}
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			}
			buttonPressed = false;
		}
		if(Keyboard.isKeyPressed(Controls.BREAK)) {
			Sounds.BUTTON_SELECT.play();
			Menu.currentMenu = MenuTypes.MAIN;
		}
	}

	@Override
	public void render() {
		super.render();
		int textSize = 50;
		String text = "SAVE SELECT";
		Renderer.drawString(Main.WINDOW_WIDTH/2 - text.length()*textSize/2, Main.WINDOW_HEIGHT-textSize*2, textSize, text);
	}
}
