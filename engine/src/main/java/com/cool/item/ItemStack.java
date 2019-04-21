package com.cool.item;

public class ItemStack {
	public Item item;
	public int count;
	
	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	public ItemStack(Item item) {
		this.item = item;
		this.count = 1;
	}
	@Override
	public String toString() {
		return item.getDisplayName() + "x" + count;
	}
}
