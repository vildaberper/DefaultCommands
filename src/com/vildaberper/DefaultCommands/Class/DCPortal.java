package com.vildaberper.DefaultCommands.Class;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class DCPortal{
	private String name;
	private String world;
	private int x1, y1, z1;
	private int x2, y2, z2;
	private String target;

	public DCPortal(String name, Block block1, Block block2, String target){
		this.name = name;
		x1 = block1.getX();
		y1 = block1.getY();
		z1 = block1.getZ();
		x2 = block2.getX();
		y2 = block2.getY();
		z2 = block2.getZ();
		this.target = target;
		world = block1.getWorld().getName();
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setTarget(String target){
		this.target = target;
	}
	public String getTarget(){
		return target;
	}
	public void setWorld(String world){
		this.world = world;
	}
	public String getWorld(){
		return world;
	}

	public Block getBlock1(){
		if(V.plugin.getServer().getWorld(world) != null){
			return V.plugin.getServer().getWorld(world).getBlockAt(x1, y1, z1);
		}
		return null;
	}
	public Block getBlock2(){
		if(V.plugin.getServer().getWorld(world) != null){
			return V.plugin.getServer().getWorld(world).getBlockAt(x2, y2, z2);
		}
		return null;
	}
	public Location getLocation(){
		if(getBlock1() != null && getBlock2() != null){
			return new Location(V.plugin.getServer().getWorld(world), x1, y1, z1);
		}
		return null;
	}

	public Location getTarget(Player player){
		if(Perm.hasPermission(player, "dc.portal." + name)){
			if(target.toLowerCase().startsWith("warp:")){
				if(Misc.getWarp(target.split(":")[1]) != null){
					return Misc.getWarp(target.split(":")[1]).getLocation();
				}
			}else if(target.toLowerCase().startsWith("home:")){
				if(Misc.getHome(target.split(":")[1].replace("<player>", player.getName())) != null){
					return Misc.getHome(target.split(":")[1]).getLocation();
				}
			}else if(target.toLowerCase().startsWith("world:")){
				if(V.plugin.getServer().getWorld(target.split(":")[1]) != null){
					return Misc.getConfig(V.plugin.getServer().getWorld(target.split(":")[1])).getSpawn();
				}
			}else if(target.toLowerCase().startsWith("player:")){
				if(Misc.getPlayers(player, target.split(":")[1]).size() == 1){
					return Misc.getPlayers(player, target.split(":")[1]).get(0).getLocation();
				}
			}
		}
		return null;
	}
}