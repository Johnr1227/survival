package com.cool;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import com.cool.audio.Audio;
import com.cool.lib.Keyboard;
import com.cool.menu.MainMenu;
import com.cool.menu.Menu;

public class Main {

	public static long window;

	public static int WINDOW_WIDTH = 0;
	public static int WINDOW_HEIGHT = 0;

	public static int MOUSEX;
	public static int MOUSEY;

	public static int TICKS = 0;

	public static Game game;
	
	public static boolean windowFocused = false;

	public static void main(String[] args) throws IOException {
		if (!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		WINDOW_WIDTH = 720;
		WINDOW_HEIGHT = 720;

		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Top Down", 0, 0);
		if (window == 0) {
			throw new IllegalStateException("Failed to create window!");
		}
		glfwSetWindowPos(window, (videoMode.width() - WINDOW_WIDTH) / 2, (videoMode.height() - WINDOW_WIDTH) / 2);

		glfwShowWindow(window);

		glfwMakeContextCurrent(window);
		@SuppressWarnings("unused")
		GLFWKeyCallback keyCallback;
		glfwSetKeyCallback(window, keyCallback = new Keyboard());

		GL.createCapabilities();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glOrtho(0, WINDOW_WIDTH, 0, WINDOW_HEIGHT, 1, -1);

		Textures.setTextures();

		// set background color
		glClearColor(0f, 0f, 0f, 1f);

		Audio.init();
		Sounds.init();

		game = new Game(window);

		glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				WINDOW_WIDTH = width;
				WINDOW_HEIGHT = height;
//				glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);

			}
		});
		glfwSetWindowFocusCallback(window, new GLFWWindowFocusCallback() {
			
			@Override
			public void invoke(long arg0, boolean focused) {
				if(focused) {
					windowFocused = true;
				} else {
					windowFocused = false;
					if(Menu.currentMenu == Menu.MenuTypes.GAME) {
						game.paused = true;
					}
				}
			}
		});
//		Sounds.MUSIC.loop();
		game.init();
		MainMenu mainMenu = new MainMenu();
		mainMenu.init();
		while (!glfwWindowShouldClose(window)) {
			glfwPollEvents();
			if(windowFocused) {
				if(Menu.currentMenu == Menu.MenuTypes.MAIN) {
					mainMenu.tick();
					glClear(GL_COLOR_BUFFER_BIT);
					mainMenu.render();
					MOUSEX = game.getCursorPosX();
					MOUSEY = game.getCursorPosY();
					glfwSwapBuffers(window);
				}
				if(Menu.currentMenu == Menu.MenuTypes.GAME) {
					glClear(GL_COLOR_BUFFER_BIT);
					game.draw();
					game.tick();
					MOUSEX = game.getCursorPosX();
					MOUSEY = game.getCursorPosY();
					glfwSwapBuffers(window);
				}
			}
		}

		Audio.destroy();
		glfwTerminate();
	}
}
