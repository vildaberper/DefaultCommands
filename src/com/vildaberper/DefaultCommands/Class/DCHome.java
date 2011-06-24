package com.vildaberper.DefaultCommands.Class;

import org.bukkit.Location;

import com.vildaberper.DefaultCommands.V;

public class DCHome{
	private String name;
	private String world;
	private double x, y, z;
	private float yaw, pitch;

	public DCHome(String name, Location location){
		setName(name);
		setLocation(location);
	}

	DCHome(String name, String world, double x, double y, double z, float yaw, float pitch){
		this.world = world;
		this.x = x;
		this.y =y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setWorld(String world) {
		this.world = world;
	}
	public String getWorld() {
		return world;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getX() {
		return x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getY() {
		return y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getZ() {
		return z;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	public float getYaw() {
		return yaw;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public float getPitch() {
		return pitch;
	}

	public void setLocation(Location location){
		world = location.getWorld().getName();
		x = location.getX();
		y = location.getY();
		z = location.getZ();
		yaw = location.getYaw();
		pitch = location.getPitch();
	}
	public Location getLocation(){
		return new Location(V.plugin.getServer().getWorld(world), x, y, z, yaw, pitch);
	}
}