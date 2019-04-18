package com.cool.player;

import com.cool.item.Item;
import com.cool.item.ItemStack;
import com.cool.lib.Color;
import com.cool.lib.Direction;
import com.cool.lib.Vertex;
import com.cool.tile.Tile;
import com.cool.world.World;

public class Player {
	public double xPos;
	public double zPos;
	
	public float zoom = 6.0f;
	
	public World world;
	
	public Color eyeColor;
	public Color shirtColor;
	public Color hairColor;
	public Color pantsColor;
	public Color sleeveColor;
	public Color skinColor;
	
	public int hairstyle;
	
	public Direction facing;
	
	public boolean walking = false;
	public int walkingTicks = 0;
	
	public double height = 1.625;
	
	public boolean run = false;
	
	public float walkSpeed = 0.1f;
	public float runSpeed = 0.3f;
	
	public int health = 20;
	public int hunger = 20;
	
	public int thirst = 20;
	
	public ItemStack[] hotbar = new ItemStack[13];
	public int selectedSlot = 0;
	
	public Player (World world, double xPos, double zPos, Direction facing, Color eyeColor, Color shirtColor, Color hairColor, Color pantsColor, Color sleeveColor, Color skinColor, int hairstyle) {
		this.xPos = xPos;
		this.zPos = zPos;
		this.world = world;
		
		this.facing = facing;
		
		this.eyeColor = eyeColor;
		this.shirtColor = shirtColor;
		this.hairColor = hairColor;
		this.pantsColor = pantsColor;
		this.sleeveColor = sleeveColor;
		this.skinColor = skinColor;
		
		this.hairstyle = hairstyle;
	}
	
	public Tile getStandingOn() {
		return world.tiles[(int)Math.round(xPos)][(int)Math.round(zPos)];
	}
	public Tile getFacingTile() {
		return world.tiles[(int)Math.round(xPos+facing.getDirection().x-0.5f)][(int)Math.round(zPos+facing.getDirection().z-0.5f)];
	}
	public Tile getFacingObstacle() {
		return world.obstacles[(int)Math.round(xPos+facing.getDirection().x-0.5f)][(int)Math.round(zPos+facing.getDirection().z-0.5f)];
	}
	public Vertex getFacingPosition() {
		return new Vertex((int)Math.round(xPos+facing.getDirection().x-0.5f),(int)Math.round(zPos+facing.getDirection().z-0.5f));
	}
	public boolean canMove(Direction dir, float amt) {
		switch(dir) {
		case NORTH:
		{
			
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().z * amt;
			
			double x1 = fPosX-0.3;
			double x2 = fPosX + 0.3;
			
			double z = fPosZ;
				
			if (z < world.obstacles[0].length && world.obstacles[(int)x1][(int)z] == null && world.obstacles[(int)x2][(int)z] == null) {
				return true;
			}
			return false;
		}
		case SOUTH:
		{
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().z * amt;
			
			double x1 = fPosX - 0.3;
			double x2 = fPosX + 0.3;
			
			double z = fPosZ - height/2;
			
			if (z >= 0 && world.obstacles[(int)x1][(int)z] == null && world.obstacles[(int)x2][(int)z] == null) {
				return true;
			}
			return false;
		}
		case EAST:
		{
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().z * amt;
			
			double x = fPosX + .5;
			
			double z1 = fPosZ;
			double z2 = fPosZ - height/2.3;
			
			if (x < world.obstacles.length && world.obstacles[(int)x][(int)z1] == null && world.obstacles[(int)x][(int)z2] == null) {
				return true;
			}
			return false;
		}
		case WEST:
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().z * amt;
			
			double x = fPosX - .5;
			
			double z1 = fPosZ;
			double z2 = fPosZ - height/2.3;
						
			if (x >= 0 && world.obstacles[(int)x][(int)z1] == null && world.obstacles[(int)x][(int)z2] == null) {
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean attemptMove(Direction dir) {
		facing = dir;
		float speed = run ? runSpeed : walkSpeed;
		if (canMove(dir, speed)) {
			xPos += dir.getDirection().x * speed;
			zPos += dir.getDirection().z * speed;
			walking = true;
			return true;
		}
		return false;
	}

	public void giveItem(Item item, int count) {
		for(int i = 0; i < hotbar.length; i++) {
			if(hotbar[i] == null) {
				hotbar[i] = new ItemStack(item, count);
				return;
			}
		}
	}
}
