package com.cool.entities;

import java.util.Random;

import com.cool.SoundType;
import com.cool.Textures;
import com.cool.entity.EntityMob;
import com.cool.entity.behaviors.EntityBehaviorWander;
import com.cool.lib.Direction;
import com.cool.player.Player;
import com.cool.world.World;

public class EntityPig extends EntityMob{

	Random random = new Random();
	
	public EntityPig(double xPos, double zPos, World world, Direction facing) {
		super(xPos, zPos, world, facing);
		this.map = Textures.PIG_MAP;
		behaviors.add(new EntityBehaviorWander(0.05));
		this.width = 1.625f;
		this.depth = 1.625f;
		this.soundType = SoundType.PIG;
	}
	@Override
	public void tick(Player p) {
		super.tick(p);
		if(random.nextDouble() > 0.9995) {
			soundType.playSound(SoundType.RANDOM);	
		}
	}
	@Override
	public void render(Player p, int tWidth) {
		super.render(p, tWidth);
	}

}
