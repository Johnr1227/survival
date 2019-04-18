package com.cool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.cool.lib.Texture;

public class Textures {
	// Tiles
	public static Texture GRASS;
	public static Texture STONE;
	public static Texture TREE;
	
	// UI
	public static Texture BUTTON_NORMAL;
	public static Texture BUTTON_SELECTED;
	
	public static Texture FONT;
	public static Texture LOGO;
	
	public static Texture HOTBAR_SLOT_SELECTED;
	public static Texture HOTBAR_SLOT_NORMAL;
	
	public static Texture HEART_FULL;
	public static Texture HEART_HALF;
	public static Texture HEART_EMPTY;
	
	public static Texture CARROT_FULL;
	public static Texture CARROT_HALF;
	public static Texture CARROT_EMPTY;
	
	public static Texture WATER_FULL;
	public static Texture WATER_HALF;
	public static Texture WATER_EMPTY;
	
	/** Player **/	
	public static Texture[] forwardHairstyles = new Texture[5];
	public static Texture[] backwardHairstyles = new Texture[5];
	public static Texture[] rightHairstyles = new Texture[5];
	public static Texture[] leftHairstyles = new Texture[5];
	
	// Forward
	
	public static Texture P_FORWARD_SHIRT;
	public static Texture P_FORWARD_EYES;
	
	public static Texture P_FORWARD_HAIRSTYLE1;
	public static Texture P_FORWARD_HAIRSTYLE2;
	public static Texture P_FORWARD_HAIRSTYLE3;
	public static Texture P_FORWARD_HAIRSTYLE4;
	public static Texture P_FORWARD_HAIRSTYLE5;
	
	// Idle
	public static Texture P_FORWARD_IDLE_BASE;
	public static Texture P_FORWARD_IDLE_PANTS;
	public static Texture P_FORWARD_IDLE_SKIN;
	public static Texture P_FORWARD_IDLE_SLEEVES;
	
	// Walking 1
	
	public static Texture P_FORWARD_WALK1_BASE;
	public static Texture P_FORWARD_WALK1_PANTS;
	public static Texture P_FORWARD_WALK1_SKIN;
	public static Texture P_FORWARD_WALK1_SLEEVES;
	
	// Walking 2
	
	public static Texture P_FORWARD_WALK2_BASE;
	public static Texture P_FORWARD_WALK2_PANTS;
	public static Texture P_FORWARD_WALK2_SKIN;
	public static Texture P_FORWARD_WALK2_SLEEVES;
	
	// Backward
	
	public static Texture P_BACKWARD_SHIRT;
	public static Texture P_BACKWARD_EYES;
	
	public static Texture P_BACKWARD_HAIRSTYLE1;
	public static Texture P_BACKWARD_HAIRSTYLE2;
	public static Texture P_BACKWARD_HAIRSTYLE3;
	public static Texture P_BACKWARD_HAIRSTYLE4;
	public static Texture P_BACKWARD_HAIRSTYLE5;
	
	// Idle
	public static Texture P_BACKWARD_IDLE_BASE;
	public static Texture P_BACKWARD_IDLE_PANTS;
	public static Texture P_BACKWARD_IDLE_SKIN;
	public static Texture P_BACKWARD_IDLE_SLEEVES;
	
	// Walking 1
	
	public static Texture P_BACKWARD_WALK1_BASE;
	public static Texture P_BACKWARD_WALK1_PANTS;
	public static Texture P_BACKWARD_WALK1_SKIN;
	public static Texture P_BACKWARD_WALK1_SLEEVES;
	
	// Walking 2
	
	public static Texture P_BACKWARD_WALK2_BASE;
	public static Texture P_BACKWARD_WALK2_PANTS;
	public static Texture P_BACKWARD_WALK2_SKIN;
	public static Texture P_BACKWARD_WALK2_SLEEVES;
	
	// Left
	
	public static Texture P_LEFT_SHIRT;
	public static Texture P_LEFT_EYES;
	
	public static Texture P_LEFT_HAIRSTYLE1;
	public static Texture P_LEFT_HAIRSTYLE2;
	public static Texture P_LEFT_HAIRSTYLE3;
	public static Texture P_LEFT_HAIRSTYLE4;
	public static Texture P_LEFT_HAIRSTYLE5;
	
	// Idle
	public static Texture P_LEFT_IDLE_BASE;
	public static Texture P_LEFT_IDLE_PANTS;
	public static Texture P_LEFT_IDLE_SKIN;
	public static Texture P_LEFT_IDLE_SLEEVES;
	
	// Walking 1
	
	public static Texture P_LEFT_WALK1_BASE;
	public static Texture P_LEFT_WALK1_PANTS;
	public static Texture P_LEFT_WALK1_SKIN;
	public static Texture P_LEFT_WALK1_SLEEVES;
	
	// Walking 2
	
	public static Texture P_LEFT_WALK2_BASE;
	public static Texture P_LEFT_WALK2_PANTS;
	public static Texture P_LEFT_WALK2_SKIN;
	public static Texture P_LEFT_WALK2_SLEEVES;
	
	// Right
	
	public static Texture P_RIGHT_SHIRT;
	public static Texture P_RIGHT_EYES;
	
	public static Texture P_RIGHT_HAIRSTYLE1;
	public static Texture P_RIGHT_HAIRSTYLE2;
	public static Texture P_RIGHT_HAIRSTYLE3;
	public static Texture P_RIGHT_HAIRSTYLE4;
	public static Texture P_RIGHT_HAIRSTYLE5;
	
	// Idle
	public static Texture P_RIGHT_IDLE_BASE;
	public static Texture P_RIGHT_IDLE_PANTS;
	public static Texture P_RIGHT_IDLE_SKIN;
	public static Texture P_RIGHT_IDLE_SLEEVES;
	
	// Walking 1
	
	public static Texture P_RIGHT_WALK1_BASE;
	public static Texture P_RIGHT_WALK1_PANTS;
	public static Texture P_RIGHT_WALK1_SKIN;
	public static Texture P_RIGHT_WALK1_SLEEVES;
	
	// Walking 2
	
	public static Texture P_RIGHT_WALK2_BASE;
	public static Texture P_RIGHT_WALK2_PANTS;
	public static Texture P_RIGHT_WALK2_SKIN;
	public static Texture P_RIGHT_WALK2_SLEEVES;
	
	/** End Player 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * **/
	
	// Items
	
	public static Texture LOG;
	public static Texture ACORN;
	
	public static void setTextures() throws IOException {
		GRASS = Texture.loadTexture(unpackResource("tiles/grass.png"));
		STONE = Texture.loadTexture(unpackResource("tiles/stone.png"));
		TREE = Texture.loadTexture(unpackResource("tiles/tree.png"));
		
		BUTTON_NORMAL = Texture.loadTexture(unpackResource("ui/button_normal.png"));
		BUTTON_SELECTED = Texture.loadTexture(unpackResource("ui/button_selected.png"));
		FONT = Texture.loadTexture(unpackResource("ui/font.png"));
		LOGO = Texture.loadTexture(unpackResource("ui/logo.png"));
		
		HOTBAR_SLOT_SELECTED = Texture.loadTexture(unpackResource("ui/hotbar/slot_selected.png"));
		HOTBAR_SLOT_NORMAL = Texture.loadTexture(unpackResource("ui/hotbar/slot_normal.png"));
		
		HEART_FULL = Texture.loadTexture(unpackResource("ui/hearts/full.png"));
		HEART_HALF = Texture.loadTexture(unpackResource("ui/hearts/half.png"));
		HEART_EMPTY = Texture.loadTexture(unpackResource("ui/hearts/empty.png"));
		
		CARROT_FULL = Texture.loadTexture(unpackResource("ui/hunger/full.png"));
		CARROT_HALF = Texture.loadTexture(unpackResource("ui/hunger/half.png"));
		CARROT_EMPTY = Texture.loadTexture(unpackResource("ui/hunger/empty.png"));
		
		WATER_FULL = Texture.loadTexture(unpackResource("ui/thirst/full.png"));
		WATER_HALF = Texture.loadTexture(unpackResource("ui/thirst/half.png"));
		WATER_EMPTY = Texture.loadTexture(unpackResource("ui/thirst/empty.png"));
		/** Player **/
		
		// Forwards
		P_FORWARD_SHIRT = Texture.loadTexture(unpackResource("player/forward/shirt.png"));
		P_FORWARD_EYES = Texture.loadTexture(unpackResource("player/forward/eyes.png"));
		
		forwardHairstyles[0] = Texture.loadTexture(unpackResource("player/forward/hairstyle1.png"));
		forwardHairstyles[1] = Texture.loadTexture(unpackResource("player/forward/hairstyle2.png"));
		forwardHairstyles[2] = Texture.loadTexture(unpackResource("player/forward/hairstyle3.png"));
		forwardHairstyles[3] = Texture.loadTexture(unpackResource("player/forward/hairstyle4.png"));
		forwardHairstyles[4] = Texture.loadTexture(unpackResource("player/forward/hairstyle5.png"));
		
		//Idle
		P_FORWARD_IDLE_BASE = Texture.loadTexture(unpackResource("player/forward/idle/base.png"));
		P_FORWARD_IDLE_PANTS = Texture.loadTexture(unpackResource("player/forward/idle/pants.png"));
		P_FORWARD_IDLE_SKIN = Texture.loadTexture(unpackResource("player/forward/idle/skin.png"));
		P_FORWARD_IDLE_SLEEVES = Texture.loadTexture(unpackResource("player/forward/idle/sleeves.png"));
		
		// Walking 1
		
		P_FORWARD_WALK1_BASE = Texture.loadTexture(unpackResource("player/forward/walking1/base.png"));
		P_FORWARD_WALK1_PANTS = Texture.loadTexture(unpackResource("player/forward/walking1/pants.png"));
		P_FORWARD_WALK1_SKIN = Texture.loadTexture(unpackResource("player/forward/walking1/skin.png"));
		P_FORWARD_WALK1_SLEEVES = Texture.loadTexture(unpackResource("player/forward/walking1/sleeves.png"));
		
		// Walking 2
		
		P_FORWARD_WALK2_BASE = Texture.loadTexture(unpackResource("player/forward/walking2/base.png"));
		P_FORWARD_WALK2_PANTS = Texture.loadTexture(unpackResource("player/forward/walking2/pants.png"));
		P_FORWARD_WALK2_SKIN = Texture.loadTexture(unpackResource("player/forward/walking2/skin.png"));
		P_FORWARD_WALK2_SLEEVES = Texture.loadTexture(unpackResource("player/forward/walking2/sleeves.png"));
		
		// Backwards
		P_BACKWARD_SHIRT = Texture.loadTexture(unpackResource("player/backward/shirt.png"));
		P_BACKWARD_EYES = Texture.loadTexture(unpackResource("player/backward/eyes.png"));
		
		backwardHairstyles[0] = Texture.loadTexture(unpackResource("player/backward/hairstyle1.png"));
		backwardHairstyles[1] = Texture.loadTexture(unpackResource("player/backward/hairstyle2.png"));
		backwardHairstyles[2] = Texture.loadTexture(unpackResource("player/backward/hairstyle3.png"));
		backwardHairstyles[3] = Texture.loadTexture(unpackResource("player/backward/hairstyle4.png"));
		backwardHairstyles[4] = Texture.loadTexture(unpackResource("player/backward/hairstyle5.png"));
		
		//Idle
		P_BACKWARD_IDLE_BASE = Texture.loadTexture(unpackResource("player/backward/idle/base.png"));
		P_BACKWARD_IDLE_PANTS = Texture.loadTexture(unpackResource("player/backward/idle/pants.png"));
		P_BACKWARD_IDLE_SKIN = Texture.loadTexture(unpackResource("player/backward/idle/skin.png"));
		P_BACKWARD_IDLE_SLEEVES = Texture.loadTexture(unpackResource("player/backward/idle/sleeves.png"));
		
		// Walking 1
		
		P_BACKWARD_WALK1_BASE = Texture.loadTexture(unpackResource("player/backward/walking1/base.png"));
		P_BACKWARD_WALK1_PANTS = Texture.loadTexture(unpackResource("player/backward/walking1/pants.png"));
		P_BACKWARD_WALK1_SKIN = Texture.loadTexture(unpackResource("player/backward/walking1/skin.png"));
		P_BACKWARD_WALK1_SLEEVES = Texture.loadTexture(unpackResource("player/backward/walking1/sleeves.png"));
		
		// Walking 2
		
		P_BACKWARD_WALK2_BASE = Texture.loadTexture(unpackResource("player/backward/walking2/base.png"));
		P_BACKWARD_WALK2_PANTS = Texture.loadTexture(unpackResource("player/backward/walking2/pants.png"));
		P_BACKWARD_WALK2_SKIN = Texture.loadTexture(unpackResource("player/backward/walking2/skin.png"));
		P_BACKWARD_WALK2_SLEEVES = Texture.loadTexture(unpackResource("player/backward/walking2/sleeves.png"));	
		
		// Right
		P_RIGHT_SHIRT = Texture.loadTexture(unpackResource("player/right/shirt.png"));
		P_RIGHT_EYES = Texture.loadTexture(unpackResource("player/right/eyes.png"));
		
		rightHairstyles[0] = Texture.loadTexture(unpackResource("player/right/hairstyle1.png"));
		rightHairstyles[1] = Texture.loadTexture(unpackResource("player/right/hairstyle2.png"));
		rightHairstyles[2] = Texture.loadTexture(unpackResource("player/right/hairstyle3.png"));
		rightHairstyles[3] = Texture.loadTexture(unpackResource("player/right/hairstyle4.png"));
		rightHairstyles[4] = Texture.loadTexture(unpackResource("player/right/hairstyle5.png"));
		
		//Idle
		P_RIGHT_IDLE_BASE = Texture.loadTexture(unpackResource("player/right/idle/base.png"));
		P_RIGHT_IDLE_PANTS = Texture.loadTexture(unpackResource("player/right/idle/pants.png"));
		P_RIGHT_IDLE_SKIN = Texture.loadTexture(unpackResource("player/right/idle/skin.png"));
		P_RIGHT_IDLE_SLEEVES = Texture.loadTexture(unpackResource("player/right/idle/sleeves.png"));
		
		
		// Walking 1
		
		P_RIGHT_WALK1_BASE = Texture.loadTexture(unpackResource("player/right/walking1/base.png"));
		P_RIGHT_WALK1_PANTS = Texture.loadTexture(unpackResource("player/right/walking1/pants.png"));
		P_RIGHT_WALK1_SKIN = Texture.loadTexture(unpackResource("player/right/walking1/skin.png"));
		P_RIGHT_WALK1_SLEEVES = Texture.loadTexture(unpackResource("player/right/walking1/sleeves.png"));	
		
		
		// Left
		P_LEFT_SHIRT = Texture.loadTexture(unpackResource("player/left/shirt.png"));
		P_LEFT_EYES = Texture.loadTexture(unpackResource("player/left/eyes.png"));
		
		leftHairstyles[0] = Texture.loadTexture(unpackResource("player/left/hairstyle1.png"));
		leftHairstyles[1] = Texture.loadTexture(unpackResource("player/left/hairstyle2.png"));
		leftHairstyles[2] = Texture.loadTexture(unpackResource("player/left/hairstyle3.png"));
		leftHairstyles[3] = Texture.loadTexture(unpackResource("player/left/hairstyle4.png"));
		leftHairstyles[4] = Texture.loadTexture(unpackResource("player/left/hairstyle5.png"));
		
		//Idle
		P_LEFT_IDLE_BASE = Texture.loadTexture(unpackResource("player/left/idle/base.png"));
		P_LEFT_IDLE_PANTS = Texture.loadTexture(unpackResource("player/left/idle/pants.png"));
		P_LEFT_IDLE_SKIN = Texture.loadTexture(unpackResource("player/left/idle/skin.png"));
		P_LEFT_IDLE_SLEEVES = Texture.loadTexture(unpackResource("player/left/idle/sleeves.png"));
		
		
		// Walking 1
		
		P_LEFT_WALK1_BASE = Texture.loadTexture(unpackResource("player/left/walking1/base.png"));
		P_LEFT_WALK1_PANTS = Texture.loadTexture(unpackResource("player/left/walking1/pants.png"));
		P_LEFT_WALK1_SKIN = Texture.loadTexture(unpackResource("player/left/walking1/skin.png"));
		P_LEFT_WALK1_SLEEVES = Texture.loadTexture(unpackResource("player/left/walking1/sleeves.png"));
		
		/** End Player 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * **/
		// Items
		LOG = Texture.loadTexture(unpackResource("item/log.png"));
		ACORN = Texture.loadTexture(unpackResource("item/acorn.png"));
	}
	
	public static String unpackResource(String path) throws IOException {
		path = "textures/" + path;
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		File file = new File(tempDir,path);
		file.getParentFile().mkdirs();
		FileOutputStream fileOutputSteam = new FileOutputStream(file);
		IOUtils.copy(ClassLoader.getSystemResourceAsStream(path),fileOutputSteam);
		return file.getAbsolutePath();
	}
}
