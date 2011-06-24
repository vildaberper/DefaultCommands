package com.vildaberper.DefaultCommands.Class;

import java.util.LinkedList;
import java.util.List;

public class DCUndo{
	private String name;
	private List<DCBlock> blocks = new LinkedList<DCBlock>();

	public DCUndo(String name, List<DCBlock> blocks){
		this.name = name;
		this.blocks = blocks;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setBlocks(List<DCBlock> blocks){
		this.blocks = blocks;
	}
	public List<DCBlock> getBlocks(){
		return blocks;
	}

	public void undo(){
		for(DCBlock dcblock : blocks){
			dcblock.getBlock().setTypeId(dcblock.getId());
			dcblock.getBlock().setData(dcblock.getData());
		}
	}
}