package com.cool.item;

import com.cool.lib.Texture;
import com.cool.player.Player;

public class Item {
	public Texture texture;
	
	public int maxStackSize = 99;
	
	public String itemName;
	public String displayName;
	
	public Item(Texture texture, String itemName, String displayName) {
		this.texture = texture;
		this.itemName = itemName;
		this.displayName = displayName;
	}
	public boolean onUse(Player p) {
		return true;
	}
	public boolean onSecondaryUse(Player p) {
		return true;
	}
	public String getDisplayName() {
		return displayName == null ? itemName : displayName;
	}
	public String toString() {	
		return getDisplayName();
	}
	public ItemStack asItemStack(int count) {
		return new ItemStack(this, count);
	}
}
