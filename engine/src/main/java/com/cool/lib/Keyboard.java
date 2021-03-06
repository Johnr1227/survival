package com.cool.lib;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class Keyboard extends GLFWKeyCallback {

	public static boolean[] keysDown = new boolean[65536];

	public static boolean[] keysPressed = new boolean[65536];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if (key > -1) {
			keysDown[key] = action != GLFW_RELEASE;

			if (action == GLFW_PRESS) {
				keysPressed[key] = true;
			}
		}
	}

	public static boolean isKeyDown(int keycode) {
		return keysDown[keycode];
	}

	public static boolean isKeyPressed(int keycode) {
		if (keysPressed[keycode]) {
			keysPressed[keycode] = false;
			return true;
		}
		return false;
	}
}
