package com.cool.item;

import com.cool.lib.Texture;
import com.cool.player.Player;

public class Item {
	public Texture texture;
	public String itemName;
	public String displayName;
	
	public Item(Texture texture, String itemName, String displayName) {
		this.texture = texture;
		this.itemName = itemName;
		this.displayName = displayName;
	}
	public void onUse(Player p) {
		
	}
	public String getDisplayName() {
		return displayName == null ? itemName : displayName;
	}
	public String toString() {	
		return getDisplayName();
	}
}
