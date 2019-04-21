package com.cool.item;

import com.cool.Textures;
import com.cool.items.ItemAcorn;
import com.cool.tile.Tiles;

public class Items {
	public static Item CARROT = new Item(Textures.CARROT_FULL, "carrot", "Carrot");
	public static Item LOG = new Item(Textures.LOG, "log", "Log");
	
	public static ItemAcorn ACORN = new ItemAcorn();
	
	public static ItemTileObstacle STONE = new ItemTileObstacle(Tiles.STONE);
	public static ItemTileObstacleAndFloor WOOD_PLANKS = new ItemTileObstacleAndFloor(Tiles.WOOD_PLANKS);
	
	public static ItemTileObstacle WORKBENCH = new ItemTileObstacle(Tiles.WORKBENCH);
}
