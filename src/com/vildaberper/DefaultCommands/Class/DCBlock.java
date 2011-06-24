package com.vildaberper.DefaultCommands.Class;

import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

public class DCBlock{
	private Block block;
	private int id;
	private byte data;

	public DCBlock(Block block, int id, byte data, Inventory inventory){
		this.block = block;
		this.id = id;
		this.data = data;
	}
	public DCBlock(Block block){
		this.block = block;
		id = block.getTypeId();
		data = block.getData();
	}

	public Block getBlock(){
		return block;
	}
	public void setBlock(Block block){
		this.block = block;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public byte getData(){
		return data;
	}
	public void setData(byte data){
		this.data = data;
	}
}