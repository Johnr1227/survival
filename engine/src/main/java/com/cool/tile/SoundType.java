package com.cool.tile;

import java.util.Random;

import com.cool.Sounds;
import com.cool.audio.Audio.Sound;

public class SoundType {
	public static SoundType DIRT = new SoundType(new Sound[] {
			Sounds.DIRT_STEP_1,Sounds.DIRT_STEP_2,
			Sounds.DIRT_STEP_3
	}, null);
	public static SoundType TREE = new SoundType(null, new Sound[] {Sounds.TREE_BREAK});
	public static SoundType ROCK = new SoundType(null, new Sound[] {Sounds.ROCK_BREAK1, Sounds.ROCK_BREAK2});
	
	
	public static final int STEP = 0;
	public static final int BREAK = 1;
	protected static Random random = new Random();
	
	
	public Sound[] stepSounds;
	public Sound[] breakSounds;

	public SoundType(Sound[] stepSounds, Sound[] breakSounds) {
		this.stepSounds = stepSounds;
		this.breakSounds = breakSounds;
	}
	
	public void playSound(int type) {
		switch(type) {
		case STEP:
			stepSounds[random.nextInt(stepSounds.length)].play();
			break;
		case BREAK:
			breakSounds[random.nextInt(breakSounds.length)].play();
			break;
		default:
			System.out.println("Failed to play sound type " + type);
		}
	}
}
