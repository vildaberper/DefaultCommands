package com.vildaberper.DefaultCommands.Class;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class DCBorder{
	private double x, z, radius;
	private String name, world;

	public DCBorder(String name, double x, double z, double radius, String world){
		this.name = name;
		this.x = x;
		this.z = z;
		this.radius = radius;
		this.world = world;
	}

	public DCBorder(String name, Location location, double radius){
		this.name = name;
		this.x = location.getX();
		this.z = location.getZ();
		this.radius = radius;
		this.world = location.getWorld().getName();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(double x){
		this.x = x;
	}
	public double getZ(){
		return z;
	}
	public void setZ(double z){
		this.z = z;
	}
	public double getRadius(){
		return radius;
	}
	public void setRadius(double radius){
		this.radius = radius;
	}
	public String getWorld(){
		return world;
	}
	public void setWorld(String world){
		this.world = world;
	}

	public boolean isInside(Entity entity, boolean teleportback){
		if(entity.getLocation().getWorld().getName().equals(this.world)){
			double nx = entity.getLocation().getX(), nz = entity.getLocation().getZ();

			if(entity.getLocation().getX() >= this.x + this.radius){
				nx = this.x + this.radius - 1;
			}
			if(entity.getLocation().getX() <= this.x - this.radius){
				nx = this.x - this.radius + 1;
			}
			if(entity.getLocation().getZ() >= this.z + this.radius){
				nz = this.z + this.radius - 1;
			}
			if(entity.getLocation().getZ() <= this.z - this.radius){
				nz = this.z - this.radius + 1;
			}
			if((nx != entity.getLocation().getX() || nz != entity.getLocation().getZ()) && teleportback){
				Location location = entity.getLocation();

				location.setX(nx);
				location.setZ(nz);
				location.setY(location.getWorld().getHighestBlockYAt(location));
				entity.teleport(location);
			}else{
				return true;
			}
		}
		return false;
	}
}