package com.cool;

import java.util.Random;

import com.cool.audio.Audio.Sound;

public class SoundType {
	public static SoundType DIRT = new SoundType(new Sound[] {
			Sounds.DIRT_STEP_1,Sounds.DIRT_STEP_2,
			Sounds.DIRT_STEP_3
	}, null);
	public static SoundType WOOD = new SoundType(new Sound[] {
			Sounds.WOOD_STEP_1,Sounds.WOOD_STEP_2,
			Sounds.WOOD_STEP_3,Sounds.WOOD_STEP_4,
			Sounds.WOOD_STEP_5
	}, new Sound[] {Sounds.TREE_BREAK});
	public static SoundType PIG = new SoundType(null, null, new Sound[] {			
			Sounds.OINK_1,Sounds.OINK_2,Sounds.OINK_3
	});
	
	public static SoundType TREE = new SoundType(null, new Sound[] {Sounds.TREE_BREAK});
	public static SoundType STONE = new SoundType(null, new Sound[] {Sounds.ROCK_BREAK1, Sounds.ROCK_BREAK2});
	
	
	public static final int STEP = 0;
	public static final int BREAK = 1;
	
	public static final int HURT = 2;
	public static final int DEATH = 3;
	public static final int RANDOM = 4;
	
	protected static Random random = new Random();
	
	
	public Sound[] stepSounds;
	public Sound[] breakSounds;
	
	public Sound[] hurtSounds;
	public Sound[] deathSounds;
	public Sound[] randomSounds;

	public SoundType(Sound[] stepSounds, Sound[] breakSounds) {
		this.stepSounds = stepSounds;
		this.breakSounds = breakSounds;
	}
	public SoundType(Sound[] hurtSounds, Sound[] deathSounds, Sound[] randomSounds) {
		this.hurtSounds = hurtSounds;
		this.deathSounds = deathSounds;
		this.randomSounds = randomSounds;
	}
	
	public void playSound(int type) {
		switch(type) {
		case STEP:
			stepSounds[random.nextInt(stepSounds.length)].play();
			break;
		case BREAK:
			breakSounds[random.nextInt(breakSounds.length)].play();
			break;
		case HURT:
			hurtSounds[random.nextInt(hurtSounds.length)].play();
			break;
		case DEATH:
			deathSounds[random.nextInt(deathSounds.length)].play();
			break;
		case RANDOM:
			randomSounds[random.nextInt(randomSounds.length)].play();
			break;
		default:
			System.out.println("Failed to play sound type " + type);
		}
	}
}
