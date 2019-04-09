package com.cool.menu;

import com.cool.Sounds;
import com.cool.Textures;
import com.cool.lib.Renderer;

public class MainMenu extends Menu {
	@Override
	public void init() {
		createButton(704, 248, 16, 348, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "START GAME", 40);
		createButton(704, 132, 16, 232, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "OPTIONS", 40);
		createButton(704, 16, 16, 116, Textures.BUTTON_NORMAL, Textures.BUTTON_SELECTED, "QUIT GAME", 40);
		setButtonSelectSound(Sounds.MENU_SELECT);
		setButtonClickSound(Sounds.MENU_CLICK);
	}

	@Override
	public void tick() {
		super.tick();
		if (buttonPressed) {
			switch (buttonSelected) {
			case 0: // Start Game
				Menu.currentMenu = MenuTypes.GAME;
				break;
			case 1: // Options
				Menu.currentMenu = MenuTypes.OPTIONS;
				break;
			case 2: // Quit
				System.exit(-1);
				break;
			}
		}
	}

	@Override
	public void render() {
		super.render();
		Renderer.drawTexture(0, 320, 720, 720, Textures.LOGO);
	}
}
