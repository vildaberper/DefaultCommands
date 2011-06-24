package com.vildaberper.DefaultCommands.Class;

import org.bukkit.Location;

public class DCAfkPlayer{
	private int id;
	private Location location;
	private int time;

	public DCAfkPlayer(int id, Location location, int time){
		this.id = id;
		this.location = location;
		this.time = time;
	}

	public DCAfkPlayer(int id, Location location){
		this.id = id;
		this.location = location;
		time = 0;
	}

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setLocation(Location location){
		this.location = location;
	}
	public Location getLocation(){
		return location;
	}
	public void setTime(int time){
		this.time = time;
	}
	public int getTime(){
		return time;
	}

	public void addTime(){
		time++;
	}

	public void resetTime(){
		time = 0;
	}
}