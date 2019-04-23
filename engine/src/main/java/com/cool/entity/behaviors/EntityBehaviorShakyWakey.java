package com.cool.entity.behaviors;

import com.cool.entity.Entity;
import com.cool.entity.EntityBehavior;

public class EntityBehaviorShakyWakey extends EntityBehavior{
	@Override
	public void tick(Entity e) {
		e.xPos += (Math.random()-0.5)/40;
		e.zPos += (Math.random()-0.5)/40;
	}
}
