package com.vildaberper.DefaultCommands.Class;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class DCKit{
	private String name;
	private List<ItemStack> items = new LinkedList<ItemStack>();

	public DCKit(String name, List<ItemStack> items){
		this.name = name;
		this.items = items;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setItems(List<ItemStack> items){
		this.items = items;
	}
	public List<ItemStack> getItems(){
		return items;
	}
}