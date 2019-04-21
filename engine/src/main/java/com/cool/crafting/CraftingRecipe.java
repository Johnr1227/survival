package com.cool.crafting;

import java.util.ArrayList;
import java.util.Arrays;

import com.cool.item.ItemStack;

public class CraftingRecipe {
	public ArrayList<ItemStack> itemsInput;
	public ArrayList<ItemStack> itemsOutput;
	
	public CraftingRecipe(ItemStack output, ItemStack... input) {
		this.itemsOutput = new ArrayList<>(Arrays.asList(output));
		this.itemsInput = new ArrayList<>(Arrays.asList(input));
	}
}
