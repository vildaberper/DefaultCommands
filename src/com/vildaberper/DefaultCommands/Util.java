package com.vildaberper.DefaultCommands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.entity.CraftChicken;
import org.bukkit.craftbukkit.entity.CraftCow;
import org.bukkit.craftbukkit.entity.CraftCreeper;
import org.bukkit.craftbukkit.entity.CraftGhast;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.craftbukkit.entity.CraftMonster;
import org.bukkit.craftbukkit.entity.CraftPig;
import org.bukkit.craftbukkit.entity.CraftPigZombie;
import org.bukkit.craftbukkit.entity.CraftSheep;
import org.bukkit.craftbukkit.entity.CraftSkeleton;
import org.bukkit.craftbukkit.entity.CraftSlime;
import org.bukkit.craftbukkit.entity.CraftSpider;
import org.bukkit.craftbukkit.entity.CraftSquid;
import org.bukkit.craftbukkit.entity.CraftWolf;
import org.bukkit.craftbukkit.entity.CraftZombie;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util{
	public static boolean addIfNotInInventory(Player player, ItemStack itemstack){
		if(!Util.containsIgnoreAmount(player.getInventory().getContents(), itemstack)){
			player.getInventory().addItem(itemstack);
		}
		return !Util.containsIgnoreAmount(player.getInventory().getContents(), itemstack);
	}
	
	public static boolean containsIgnoreAmount(ItemStack[] inventory, ItemStack itemstack){
		for(ItemStack is : inventory){
			if(is != null && itemstack != null && is.getType().equals(itemstack.getType()) && ((isSameDifferentData(is.getTypeId())) || (is.getData() == null && itemstack.getData() == null) || (is.getData() != null && itemstack.getData() != null && is.getData().equals(itemstack.getData())))){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSameDifferentData(int id){
		return ":6:17:18:31:35:43:44:263:351:".indexOf(":" + id + ":") == -1;
	}

	public static void sleep(int duration){
		try{
			Thread.sleep(duration);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public static Location getSafeLocationAt(Block block){
		Location location = new Location(block.getWorld(), block.getX() + 0.5, block.getY(), block.getZ() + 0.5);

		return getSafeLocationAt(location);
	}

	public static Location getSafeLocationAt(Player player){
		return getSafeLocationAt(player.getLocation());
	}

	public static Location getSafeLocationAt(Location location){
		for(int y = location.getBlockY(); y < 125; y++){
			location.setY(y);
			if(location.getBlock().getType().equals(Material.AIR) && location.getBlock().getFace(BlockFace.UP).getType().equals(Material.AIR)){
				return location;
			}
		}
		location.setY(127);
		return location;
	}

	public static String getItemName(int id){
		return Material.matchMaterial(String.valueOf(id)).name().substring(0, 1).toUpperCase() + Material.matchMaterial(String.valueOf(id)).name().substring(1).replace("_", " ").toLowerCase();
	}

	public static String getItemName(Material material){
		return getItemName(material.getId());
	}

	public static boolean isRightClickInteractAble(Block block){
		if(block == null){
			return false;
		}
		if(
				block.getTypeId() == 23 ||
				block.getTypeId() == 25 ||
				block.getTypeId() == 26 ||
				block.getTypeId() == 54 ||
				block.getTypeId() == 58 ||
				block.getTypeId() == 61 ||
				block.getTypeId() == 62 ||
				block.getTypeId() == 64 ||
				block.getTypeId() == 69 ||
				block.getTypeId() == 77 ||
				block.getTypeId() == 84 ||
				block.getTypeId() == 92 ||
				block.getTypeId() == 93 ||
				block.getTypeId() == 94 ||
				block.getTypeId() == 96
		){
			return true;
		}
		return false;
	}

	public static boolean isLeftClickInteractAble(Block block){
		if(block == null){
			return false;
		}
		if(
				block.getTypeId() == 46 ||
				block.getTypeId() == 51 ||
				block.getTypeId() == 64 ||
				block.getTypeId() == 69 ||
				block.getTypeId() == 77 ||
				block.getTypeId() == 96
		){
			return true;
		}
		return false;
	}

	public static boolean isValidInt(String string){
		if(string == null){
			return false;
		}
		if(string.length() < 1){
			return false;
		}
		if(string.charAt(0) == '-' && string.length() < 2){
			return false;
		}
		if(string.charAt(0) == '-'){
			string = string.substring(1);
		}
		for(char c : string.toCharArray()){
			if("0123456789".indexOf(c) == -1){
				return false;
			}
		}
		return true;
	}

	public static boolean isValidDouble(String string){
		if(string == null){
			return false;
		}
		if(string.length() < 1){
			return false;
		}
		if(string.contains(".")){
			return isValidInt(string.substring(0, string.indexOf("."))) && isValidInt(string.substring(string.indexOf(".") + 1)) && Integer.parseInt(string.substring(string.indexOf(".") + 1)) >= 0;
		}else{
			return isValidInt(string);
		}
	}

	public static List<Entity> getNearbyMobs(Location location, double distance){
		List<Entity> entities = getNearbyEntities(location, distance);

		for(int i = 0; i < entities.size(); i++){
			if(!(
					entities.get(i) instanceof CraftChicken
					|| entities.get(i) instanceof CraftCow
					|| entities.get(i) instanceof CraftCreeper
					|| entities.get(i) instanceof CraftGhast
					|| entities.get(i) instanceof CraftMonster
					|| entities.get(i) instanceof CraftPig
					|| entities.get(i) instanceof CraftPigZombie
					|| entities.get(i) instanceof CraftSheep
					|| entities.get(i) instanceof CraftSkeleton
					|| entities.get(i) instanceof CraftSlime
					|| entities.get(i) instanceof CraftSpider
					|| entities.get(i) instanceof CraftSquid
					|| entities.get(i) instanceof CraftWolf
					|| entities.get(i) instanceof CraftZombie
			)){
				entities.remove(i);
				i--;
			}
		}
		return entities;
	}

	public static List<Entity> getNearbyItems(Location location, double distance){
		List<Entity> entities = Util.getNearbyEntities(location, distance);

		for(int i = 0; i < entities.size(); i++){
			if(!(entities.get(i) instanceof CraftItem)){
				entities.remove(i);
				i--;
			}
		}
		return entities;
	}

	public static List<Entity> getNearbyEntities(Location location, double distance){
		List<Entity> entities = new LinkedList<Entity>();

		for(Entity e : location.getWorld().getEntities()){
			if(getDistance(location, e.getLocation()) <= distance){
				entities.add(e);
			}
		}
		return entities;
	}

	public static double getDistance(Location l1, Location l2){
		return sutractIgnoreNegative(l1.getX(), l2.getX()) + sutractIgnoreNegative(l1.getY(), l2.getY()) + sutractIgnoreNegative(l1.getZ(), l2.getZ());
	}

	public static double getDistanceIgnoreY(Location l1, Location l2){
		return sutractIgnoreNegative(l1.getX(), l2.getX()) + sutractIgnoreNegative(l1.getZ(), l2.getZ());
	}

	public static double sutractIgnoreNegative(double d1, double d2){
		if(d1 - d2 >= 0){
			return d1 - d2;
		}
		return d2 - d1;
	}

	public static Environment getEnvironment(String string){
		for(Environment environment : Environment.values()){
			if(string.equalsIgnoreCase(environment.toString())){
				return environment;
			}
		}
		return null;
	}

	public static  net.minecraft.server.ItemStack[] convertItemStack(ItemStack[] itemstack){
		net.minecraft.server.ItemStack[] is = new net.minecraft.server.ItemStack[itemstack.length];

		for(int i = 0; i < itemstack.length; i++){
			if(itemstack[i] != null){
				is[i] = new net.minecraft.server.ItemStack(itemstack[i].getTypeId(), itemstack[i].getAmount(), itemstack[i].getDurability());
			}else{
				is[i] = null;
			}
		}
		return is;
	}

	public static ItemStack[] convertItemStack(net.minecraft.server.ItemStack[] itemstack){
		ItemStack[] is = new ItemStack[itemstack.length];

		for(int i = 0; i < itemstack.length; i++){
			if(itemstack[i] != null){
				is[i] = new ItemStack(itemstack[i].id, itemstack[i].count, (short) itemstack[i].damage);
			}else{
				is[i] = null;
			}
		}
		return is;
	}

	public static String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		sdf.setTimeZone(TimeZone.getTimeZone(V.timezone));
		return sdf.format(new Date());
	}

	public static String getIp(Player player){
		return player.getAddress().toString().substring(1).split(":")[0];
	}

	public static CreatureType getCreatureType(String string){
		for(CreatureType creaturetype : CreatureType.values()){
			if(string.equalsIgnoreCase(creaturetype.getName())){
				return creaturetype;
			}
		}
		return null;
	}

	public static String replaceColor(String string){
		return string
		.replace("&4", ChatColor.DARK_RED.toString())
		.replace("&c", ChatColor.RED.toString())
		.replace("&6", ChatColor.GOLD.toString())
		.replace("&e", ChatColor.YELLOW.toString())
		.replace("&2", ChatColor.DARK_GREEN.toString())
		.replace("&a", ChatColor.GREEN.toString())
		.replace("&b", ChatColor.AQUA.toString())
		.replace("&3", ChatColor.DARK_AQUA.toString())
		.replace("&1", ChatColor.DARK_BLUE.toString())
		.replace("&9", ChatColor.BLUE.toString())
		.replace("&d", ChatColor.LIGHT_PURPLE.toString())
		.replace("&5", ChatColor.DARK_PURPLE.toString())
		.replace("&f", ChatColor.WHITE.toString())
		.replace("&7", ChatColor.GRAY.toString())
		.replace("&8", ChatColor.DARK_GRAY.toString())
		.replace("&0", ChatColor.BLACK.toString());
	}

	public static String removeColor(String string){
		return string
		.replace(ChatColor.DARK_RED.toString(), "")
		.replace(ChatColor.RED.toString(), "")
		.replace(ChatColor.GOLD.toString(), "")
		.replace(ChatColor.YELLOW.toString(), "")
		.replace(ChatColor.DARK_GREEN.toString(), "")
		.replace(ChatColor.GREEN.toString(), "")
		.replace(ChatColor.AQUA.toString(), "")
		.replace(ChatColor.DARK_AQUA.toString(), "")
		.replace(ChatColor.DARK_BLUE.toString(), "")
		.replace(ChatColor.BLUE.toString(), "")
		.replace(ChatColor.LIGHT_PURPLE.toString(), "")
		.replace(ChatColor.DARK_PURPLE.toString(), "")
		.replace(ChatColor.WHITE.toString(), "")
		.replace(ChatColor.GRAY.toString(), "")
		.replace(ChatColor.DARK_GRAY.toString(), "")
		.replace(ChatColor.BLACK.toString(), "")
		.replace("&4", "")
		.replace("&c", "")
		.replace("&6", "")
		.replace("&e", "")
		.replace("&2", "")
		.replace("&a", "")
		.replace("&b", "")
		.replace("&3", "")
		.replace("&1", "")
		.replace("&9", "")
		.replace("&d", "")
		.replace("&5", "")
		.replace("&f", "")
		.replace("&7", "")
		.replace("&8", "")
		.replace("&0", "");
	}
}