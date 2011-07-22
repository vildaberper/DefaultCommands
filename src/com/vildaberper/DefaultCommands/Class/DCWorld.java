package com.vildaberper.DefaultCommands.Class;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.V;

public class DCWorld{
	private String name;
	private Environment environment;
	private long seed;
	private List<DCConfiguration> configuration = new LinkedList<DCConfiguration>();
	private List<DCInventoryPlayer> inventory = new LinkedList<DCInventoryPlayer>();
	private List<DCArmor> armor = new LinkedList<DCArmor>();
	private int runnablesheep = 0, runnablepoop = 0, runnableheal = 0;

	public DCWorld(String world, Environment environment, long seed){
		name = world;
		this.environment = environment;
		this.seed = seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}
	public long getSeed() {
		return seed;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setEnviroment(World.Environment enviroment){
		environment = enviroment;
	}
	public  World.Environment getEnviroment(){
		return environment;
	}
	public void setConfiguration(List<DCConfiguration> configuration){
		this.configuration = configuration;
	}
	public List<DCConfiguration> getConfiguration(){
		return configuration;
	}
	public void setInventory(List<DCInventoryPlayer> inventory){
		this.inventory = inventory;
	}
	public List<DCInventoryPlayer> getInventory(){
		return inventory;
	}
	public void setArmor(List<DCArmor> armor){
		this.armor = armor;
	}
	public List<DCArmor> getArmor(){
		return armor;
	}
	public void setRunnablesheep(int i){
		runnablesheep = i;
	}
	public int getRunnablesheep(){
		return runnablesheep;
	}
	public void setRunnablepoop(int i){
		runnablepoop = i;
	}
	public int getRunnablepoop(){
		return runnablepoop;
	}
	public void setRunnableheal(int runnableheal){
		this.runnableheal = runnableheal;
	}
	public int getRunnableheal(){
		return runnableheal;
	}

	public Location getSpawn(){
		return new Location(V.plugin.getServer().getWorld(getName()), Double.parseDouble(getString("spawn").split(" ")[0]), Double.parseDouble(getString("spawn").split(" ")[1]), Double.parseDouble(getString("spawn").split(" ")[2]), Float.parseFloat(getString("spawn").split(" ")[3]), Float.parseFloat(getString("spawn").split(" ")[4]));
	}
	public void setSpawn(Location location){
		V.plugin.getServer().getWorld(getName()).setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
		Misc.setConfig(getName(), "spawn", location.getX() + " " + location.getY() + " " + location.getZ() + " " + location.getYaw() + " " + location.getPitch());
	}

	public void setInventory(String name, ItemStack[] itemstack){
		while(getInventory(name) != null){
			inventory.remove(getInventory(name));
		}
		if(itemstack != null){
			inventory.add(new DCInventoryPlayer(name, itemstack));
		}
	}
	public DCInventoryPlayer getInventory(String name){
		for(DCInventoryPlayer dcinventory : inventory){
			if(dcinventory.getName().equals(name)){
				return dcinventory;
			}
		}
		return null;
	}

	public void setArmor(String name, ItemStack[] itemstack){
		while(getArmor(name) != null){
			armor.remove(getArmor(name));
		}
		if(itemstack != null){
			armor.add(new DCArmor(name, itemstack));
		}
	}
	public DCArmor getArmor(String name){
		for(DCArmor dcarmor : armor){
			if(dcarmor.getName().equals(name)){
				return dcarmor;
			}
		}
		return null;
	}

	public Object getObject(String configuration){
		String copy = null;

		if(!configuration.equals("spawn")){
			for(DCConfiguration config : this.configuration){
				if(config.getConfiguration().equals("copy")){
					copy = (String) config.getValue();
				}
			}
			if(copy != null){
				return Misc.getConfig(copy).getObject(configuration);
			}
		}
		for(DCConfiguration config : this.configuration){
			if(config.getConfiguration().equals(configuration)){
				return config.getValue();
			}
		}
		return null;
	}
	public boolean getBoolean(String configuration){
		return (Boolean) getObject(configuration);
	}
	public int getInt(String configuration){
		return (Integer) getObject(configuration);
	}
	public double getDouble(String configuration){
		try{
			return (Double) getObject(configuration);
		}catch(Exception e){
		}
		return Double.parseDouble(Integer.toString(getInt(configuration)));
	}
	public String getString(String configuration){
		return (String) getObject(configuration);
	}
	@SuppressWarnings("unchecked")
	public List<String> getStringList(String configuration){
		return (List<String>) getObject(configuration);
	}

	public class RunnableHeal implements Runnable{
		@Override
		public void run(){
			for(Player player : V.plugin.getServer().getWorld(name).getPlayers()){
				if(player.getHealth() > 0 && !player.isDead() && player.isOnline()){
					if(player.getHealth() + getInt("auto_heal_amount") <= 20){
						player.setHealth(player.getHealth() + getInt("auto_heal_amount"));
					}else{
						if(player.getHealth() != 20){
							player.setHealth(20);
						}
					}
				}
			}
		}
	}

	public class RunnableSheep implements Runnable{
		@Override
		public void run(){
			for(Entity entity : V.plugin.getServer().getWorld(name).getEntities()){
				if(entity instanceof Sheep && !((Sheep) entity).isSheared() && Math.random() <= getDouble("magic_sheep_rate")){
					((Sheep) entity).setColor(DyeColor.values()[(int) Math.round((Math.random() * 15))]);
				}
			}
		}
	}

	public class RunnablePoop implements Runnable{
		@Override
		public void run(){
			for(Entity entity : V.plugin.getServer().getWorld(name).getEntities()){
				if(entity instanceof Cow && entity.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.GRASS) && Math.random() <= Misc.getConfig(name).getDouble("hungry_cows_rate")){
					Location location = entity.getLocation();

					location.setY(location.getY() + 0.5);
					entity.getWorld().getBlockAt(location.getBlock().getRelative(BlockFace.DOWN).getLocation()).setType(Material.DIRT);

					final Item item = entity.getWorld().dropItem(location, new ItemStack(Material.DIRT, 0));
					V.nopickups.add(item);
					entity.setVelocity(new Vector(0, 0.15, 0));
					V.plugin.getServer().getScheduler().scheduleSyncDelayedTask(V.plugin,
							new Runnable(){
						@Override
						public void run(){
							V.nopickups.remove(item);
							item.remove();
						}
					},
					(long) (20 * getDouble("poop_despawn_time")));
				}
			}
		}
	}
}