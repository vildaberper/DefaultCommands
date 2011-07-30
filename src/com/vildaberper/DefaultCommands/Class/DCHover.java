package com.vildaberper.DefaultCommands.Class;

import org.bukkit.World;
import org.bukkit.block.Block;

public class DCHover{
	private int id, x, y, z;
	private World world;

	public DCHover(int id){
		this.id = id;
	}

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return y;
	}
	public void setZ(int z){
		this.z = z;
	}
	public int getZ(){
		return z;
	}
	public void setWorld(World world){
		this.world = world;
	}
	public World getWorld(){
		return world;
	}

	public void setBlock(Block block){
		if(block != null){
			this.x = block.getX();
			this.y = block.getY();
			this.z = block.getZ();
			this.world = block.getWorld();
		}else{
			this.world = null;
		}
	}

	public Block getBlock(){
		if(this.world != null){
			return world.getBlockAt(x, y, z);
		}
		return null;
	}

	public boolean isHover(){
		return this.world != null;
	}
}