package com.vildaberper.DefaultCommands.Class;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DCTeleport{
	private Player player;
	private Location location;

	public DCTeleport(Player player, Location location){
		this.player = player;
		this.location = location;
	}

	public void setPlayer(Player player){
		this.player = player;
	}
	public Player getPlayer(){
		return player;
	}
	public void setLocation(Location location){
		this.location = location;
	}
	public Location getLocation(){
		return location;
	}
}