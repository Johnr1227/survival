package com.cool.player;

import com.cool.lib.Color;
import com.cool.lib.Direction;
import com.cool.lib.Texture;
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
	public boolean canMove(Direction dir, float amt) {
		switch(dir) {
		case NORTH:
		{
			
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().y * amt;
			
			double x1 = fPosX;
			
			double z = fPosZ-1.625/1.5;
			
			double x2 = fPosX + 1;
			
			if (world.obstacles[(int)x1][(int)z] == null && world.obstacles[(int)x2][(int)z] == null) {
				return true;
			}
			return false;
		}
		case SOUTH:
		{
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().y * amt;
			
			double x1 = fPosX;
			
			double z = fPosZ + 1.625;
			
			double x2 = fPosX - 1;
			
			if (world.obstacles[(int)x1][(int)z] == null && world.obstacles[(int)x2][(int)z] == null) {
				return true;
			}
			return false;
		}
		case EAST:
		{
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().y * amt;
			
			double x = fPosX;
			
			double z1 = fPosZ - 1.625;
			double z2 = fPosZ;
			
			if (world.obstacles[(int)x][(int)z1] == null && world.obstacles[(int)x][(int)z2] == null) {
				return true;
			}
			return false;
		}
		case WEST:
			double fPosX = xPos + dir.getDirection().x * amt;
			double fPosZ = zPos + dir.getDirection().y * amt;
			
			double x = fPosX + 1;
			
			double z1 = fPosZ - 1.625;
			double z2 = fPosZ;
						
			if (world.obstacles[(int)x][(int)z1] == null && world.obstacles[(int)x][(int)z2] == null) {
				return true;
			}
			return false;
		}
		return false;
	}
}
