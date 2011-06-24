package com.vildaberper.DefaultCommands.Class;

import org.bukkit.block.Block;

public class DCSelection{
	private String name;
	private Block block1, block2;

	public DCSelection(String name, Block block1, Block block2){
		this.name = name;
		this.block1 = block1;
		this.block2 = block2;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setBlock2(Block block2){
		this.block2 = block2;
	}
	public Block getBlock2(){
		return block2;
	}
	public void setBlock1(Block block1){
		this.block1 = block1;
	}
	public Block getBlock1(){
		return block1;
	}
}