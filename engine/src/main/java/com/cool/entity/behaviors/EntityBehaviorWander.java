package com.cool.entity.behaviors;

import java.util.Random;

import com.cool.entity.Entity;
import com.cool.entity.EntityBehavior;
import com.cool.entity.EntityMob;
import com.cool.lib.Direction;

public class EntityBehaviorWander extends EntityBehavior {
	public Random random = new Random();
	public double moveSpeed = 0.05;
	
	public EntityBehaviorWander(double speed) {
		this.moveSpeed = speed;
	}

	@Override
	public void tick(Entity e) {
		EntityMob m = (EntityMob) e;
		if (m.moving) {
			if(m.canMove(m.facing, moveSpeed)) {
				m.xPos += m.facing.getDirection().x * moveSpeed;
				m.zPos += m.facing.getDirection().z * moveSpeed;
			}
			if (random.nextFloat() < 0.005f * m.movingTicks / 10f) {
				m.moving = false;
			}
		} else {
			if (random.nextFloat() < 0.005f) {
				m.moving = true;
				m.facing = Direction.random();
			}
		}
	}
}
