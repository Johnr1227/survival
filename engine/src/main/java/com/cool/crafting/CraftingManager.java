package com.cool.crafting;

import java.util.ArrayList;

import com.cool.item.ItemStack;
import com.cool.item.Items;

public class CraftingManager {
	
	public static ArrayList<CraftingRecipe> recipes = new ArrayList<>();
	
	public static void init() {
		addCraftingRecipe(new CraftingRecipe(new ItemStack(Items.WOOD_PLANKS, 3), new ItemStack(Items.LOG)));
		addCraftingRecipe(new CraftingRecipe(new ItemStack(Items.WORKBENCH),new ItemStack(Items.STONE), new ItemStack(Items.WOOD_PLANKS,5)));
	}
	public static void addCraftingRecipe(CraftingRecipe recipe) {
		recipes.add(recipe);
	}
}