package com.vildaberper.DefaultCommands.Class;

import org.bukkit.inventory.ItemStack;

public class DCArmor{
	private String name;
	private ItemStack[] armor = new ItemStack[4];

	public DCArmor(String name, ItemStack[] armor){
		this.name = name;
		this.armor = armor;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setArmor(ItemStack[] armor){
		this.armor = armor;
	}
	public ItemStack[] getArmor(){
		return armor;
	}
}