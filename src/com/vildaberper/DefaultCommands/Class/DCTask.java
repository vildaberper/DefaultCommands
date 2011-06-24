package com.vildaberper.DefaultCommands.Class;

import org.bukkit.World;

public class DCTask{
	private double id;
	private int task;
	private String player;
	private World world;

	public DCTask(double id, int task, String player, World world){
		this.id = id;
		this.task = task;
		this.player = player;
		this.world = world;
	}

	public void setId(double id){
		this.id = id;
	}
	public double getId(){
		return id;
	}
	public void setTask(int task){
		this.task = task;
	}
	public int getTask(){
		return task;
	}
	public void setPlayer(String player){
		this.player = player;
	}
	public String getPlayer(){
		return player;
	}
	public void setWorld(World world){
		this.world = world;
	}
	public World getWorld(){
		return world;
	}
}