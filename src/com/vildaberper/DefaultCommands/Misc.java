package com.vildaberper.DefaultCommands;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftChicken;
import org.bukkit.craftbukkit.entity.CraftCow;
import org.bukkit.craftbukkit.entity.CraftCreeper;
import org.bukkit.craftbukkit.entity.CraftGhast;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.craftbukkit.entity.CraftMonster;
import org.bukkit.craftbukkit.entity.CraftPig;
import org.bukkit.craftbukkit.entity.CraftPigZombie;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.entity.CraftSheep;
import org.bukkit.craftbukkit.entity.CraftSkeleton;
import org.bukkit.craftbukkit.entity.CraftSlime;
import org.bukkit.craftbukkit.entity.CraftSpider;
import org.bukkit.craftbukkit.entity.CraftSquid;
import org.bukkit.craftbukkit.entity.CraftWolf;
import org.bukkit.craftbukkit.entity.CraftZombie;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import com.vildaberper.DefaultCommands.Class.DCAfkPlayer;
import com.vildaberper.DefaultCommands.Class.DCBlock;
import com.vildaberper.DefaultCommands.Class.DCCommand;
import com.vildaberper.DefaultCommands.Class.DCConfiguration;
import com.vildaberper.DefaultCommands.Class.DCHome;
import com.vildaberper.DefaultCommands.Class.DCIdData;
import com.vildaberper.DefaultCommands.Class.DCInventoryPlayer;
import com.vildaberper.DefaultCommands.Class.DCItem;
import com.vildaberper.DefaultCommands.Class.DCKit;
import com.vildaberper.DefaultCommands.Class.DCPlayer;
import com.vildaberper.DefaultCommands.Class.DCPortal;
import com.vildaberper.DefaultCommands.Class.DCReply;
import com.vildaberper.DefaultCommands.Class.DCSelection;
import com.vildaberper.DefaultCommands.Class.DCString;
import com.vildaberper.DefaultCommands.Class.DCTask;
import com.vildaberper.DefaultCommands.Class.DCUndo;
import com.vildaberper.DefaultCommands.Class.DCWarp;
import com.vildaberper.DefaultCommands.Class.DCWorld;
import com.vildaberper.DefaultCommands.Enum.Instrument;

public class Misc{
	public static void removePlayerFromLists(Player player){
		setGod(player.getEntityId(), false);
		setFly(player.getEntityId(), false);
		setCreative(player.getEntityId(), false);
		setIgnoremob(player.getEntityId(), false);
		setNopickup(player.getEntityId(), false);
		setInstakill(player.getEntityId(), false);
		setLastCommand(player.getName(), null);
		setSelection(player.getName(), null, null);
		setReply(player.getName(), null);
		setAfk(player.getEntityId(), false);
	}

	public static Environment getEnvironment(String string){
		for(Environment environment : Environment.values()){
			if(string.equalsIgnoreCase(environment.toString())){
				return environment;
			}
		}
		return null;
	}

	public static String getMotd(String player){
		return getString("motd")
		.replace("<player>", player)
		.replace("<online>", Integer.toString(getPlayerList(V.plugin.getServer().getPlayer(player)).size()))
		.replace("<max>", Integer.toString(V.plugin.getServer().getMaxPlayers()))
		.replace("<players>", getPlayerList(V.plugin.getServer().getPlayer(player)).toString().substring(1, getPlayerList(V.plugin.getServer().getPlayer(player)).toString().length() - 1).replace(",", "&2,"));
	}

	public static String getLatestVersion(){
		downloadFile("http://dl.dropbox.com/u/19411765/Release/DefaultCommands/Version.txt", new File(V.plugin.getDataFolder(), "temp"));

		String string = readTextFile(new File(V.plugin.getDataFolder(), "temp")).get(0), latest = "";
		List<Character> chars = new LinkedList<Character>();

		new File(V.plugin.getDataFolder(), "temp").delete();
		chars.add('.');
		for(int i = 0; i <= 9; i++){
			chars.add(Integer.toString(i).charAt(0));
		}
		for(int i = 0; i < string.length(); i++){
			if(chars.contains(string.charAt(i))){
				latest += string.charAt(i);
			}
		}
		return latest;
	}

	@SuppressWarnings("deprecation")
	public static List<String> readTextFile(File target){
		try{
			List<String> rows = new LinkedList<String>();
			FileInputStream fis = new FileInputStream(target);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);

			while(dis.available() != 0){
				rows.add(dis.readLine());
			}
			fis.close();
			bis.close();
			dis.close();
			return rows;
		}catch(Exception e){
			return null;
		}
	}

	public static void downloadFile(String url, File target){
		OutputStream out = null;
		URLConnection conn = null;
		InputStream in = null;
		try{
			URL _url = new URL(url);
			byte[] buffer = new byte[1024];
			int numRead;
			long numWritten = 0;

			out = new BufferedOutputStream(new FileOutputStream(target));
			conn = _url.openConnection();
			in = conn.getInputStream();
			while((numRead = in.read(buffer)) != -1){
				out.write(buffer, 0, numRead);
				numWritten += numRead;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(in != null){
					in.close();
				}
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static boolean isRemoveFloat(World world){
		for(DCTask dctask : V.removefloats){
			if(dctask.getWorld().equals(world)){
				return true;
			}
		}
		return false;
	}

	public static DCAfkPlayer getAfkPlayer(int id){
		for(DCAfkPlayer dcafkplayer : V.afkplayers){
			if(dcafkplayer.getId() == id){
				return dcafkplayer;
			}
		}
		return null;
	}

	public static void setAfkPlayer(int id, DCAfkPlayer dcafkplayer){
		while(getAfkPlayer(id) != null){
			V.afkplayers.remove(getAfkPlayer(id));
		}
		if(dcafkplayer != null){
			V.afkplayers.add(dcafkplayer);
		}
	}

	public static void giveKit(Player player, DCKit kit){
		for(ItemStack itemstack : kit.getItems()){
			player.getInventory().addItem(itemstack);
		}
	}

	public static DCKit getKit(String name){
		for(DCKit dckit : V.kits){
			if(dckit.getName().equalsIgnoreCase(name)){
				return dckit;
			}
		}
		return null;
	}

	public static void setInventory(String name, ItemStack[] itemstack, String world){
		if(!getConfig(world).getBoolean("separate_inventory")){
			for(int i = 0; i < V.inventories.size(); i++){
				if(V.inventories.get(i).getName().equals(name)){
					V.inventories.remove(i);
				}
			}
			if(itemstack != null){
				V.inventories.add(new DCInventoryPlayer(name, itemstack));
			}
		}else{
			getConfig(world).setInventory(name, itemstack);
		}
	}

	public static DCInventoryPlayer getInventory(String name, String world){
		if(!getConfig(world).getBoolean("separate_inventory")){
			for(DCInventoryPlayer dcinventory : V.inventories){
				if(dcinventory.getName().equals(name)){
					return dcinventory;
				}
			}
		}else{
			return getConfig(world).getInventory(name);
		}
		return null;
	}

	public static void playMessageSound(final Player player){
		V.plugin.getServer().getScheduler().scheduleAsyncDelayedTask(
				V.plugin,
				new Runnable(){
					public void run(){
						Misc.playNote(player, Instrument.PIANO, 5);
						Misc.sleep(100);
						Misc.playNote(player, Instrument.PIANO, 6);
						Misc.sleep(100);
						Misc.playNote(player, Instrument.PIANO, 8);
					}
				}
		);
	}

	public static void playNote(final Player player, final Instrument instrument, final int note){
		final Block block = player.getLocation().getBlock().getFace(BlockFace.DOWN).getFace(BlockFace.DOWN);
		final Byte data = block.getData();
		final Material material = block.getType();

		player.sendBlockChange(block.getLocation(), Material.NOTE_BLOCK, instrument.getType());
		V.plugin.getServer().getScheduler().scheduleSyncDelayedTask(
				V.plugin,
				new Runnable(){
					public void run(){
						player.playNote(block.getLocation(), instrument.getType(), (byte) note);
						player.sendBlockChange(block.getLocation(), material, data);
					}
				},
				1
		);
	}

	public static DCUndo getUndo(String name){
		for(DCUndo dcundo : V.undos){
			if(dcundo.getName().equals(name)){
				return dcundo;
			}
		}
		return null;
	}

	public static void setUndo(String name, List<Block> blocks){
		for(int i = 0; i < V.undos.size(); i++){
			if(V.undos.get(i).getName().equals(name)){
				V.undos.remove(i);
				i--;
			}
		}
		if(blocks != null){
			List<DCBlock> dcblocks = new LinkedList<DCBlock>();

			for(Block block : blocks){
				dcblocks.add(new DCBlock(block));
			}
			V.undos.add(new DCUndo(name, dcblocks));
		}
	}

	public static List<Block> getWalls(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z1));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z2));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x1, i2, i3));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x2, i2, i3));
			}
		}
		return blocks;
	}

	public static List<Block> getReplace(Block block1, Block block2, List<DCIdData> replace){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				for(int i3 = z1; i3 <= z2; i3++){
					for(DCIdData dciddata : replace){
						if(block1.getWorld().getBlockAt(i1, i2, i3).getTypeId() == dciddata.getId() && block1.getWorld().getBlockAt(i1, i2, i3).getData() == dciddata.getData()){
							blocks.add(block1.getWorld().getBlockAt(i1, i2, i3));
						}
					}
				}
			}
		}
		return blocks;
	}

	public static List<Block> getCuboid(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				for(int i3 = z1; i3 <= z2; i3++){
					blocks.add(block1.getWorld().getBlockAt(i1, i2, i3));
				}
			}
		}
		return blocks;
	}

	public static List<Block> getCuboidwireframe(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y1, z1));
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y1, z2));
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y2, z1));
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y2, z2));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x1, i2, z1));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x2, i2, z1));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x1, i2, z2));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x2, i2, z2));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x1, y1, i3));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x1, y2, i3));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x2, y1, i3));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x2, y2, i3));
		}
		return blocks;
	}

	public static List<Block> getCuboidhollow(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z1));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z2));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x1, i2, i3));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x2, i2, i3));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(i1, y1, i3));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(i1, y2, i3));
			}
		}
		return blocks;
	}

	public static List<Block> getSphere(Block block1, int radius){
		List<Block> blocks = new LinkedList<Block>();
		double xi = block1.getLocation().getX() + 0.5;
		double yi = block1.getLocation().getY() + 0.5;
		double zi = block1.getLocation().getZ() + 0.5;

		for(int v1 = 0; v1 <= 90; v1++){
			double y = Math.sin(Math.PI / 180 * v1) * radius;
			double r = Math.cos(Math.PI / 180 * v1) * radius;

			if(v1 == 90){
				r = 0;
			}
			for(int v2 = 0; v2 <= 90; v2++){
				double x = Math.sin(Math.PI / 180 * v2) * r;
				double z = Math.cos(Math.PI / 180 * v2) * r;

				if(v2 == 90){
					z = 0;
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi + z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi + z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi + z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi + z)));
				}
			}
		}
		return blocks;
	}

	public static void setSelection(String name, Block block1, Block block2){
		for(int i = 0; i < V.selections.size(); i++){
			if(V.selections.get(i).getName().equals(name)){
				if(block1 != null){
					V.selections.get(i).setBlock1(block1);
				}
				if(block2 != null){
					V.selections.get(i).setBlock2(block2);
				}
				if(block1 == null && block2 == null){
					V.selections.remove(i);
				}
				return;
			}
		}
		V.selections.add(new DCSelection(name, block1, block2));
	}

	public static DCSelection getSelection(String name){
		for(DCSelection dcs : V.selections){
			if(dcs.getName().equals(name) && dcs.getBlock1() != null && dcs.getBlock2() != null && dcs.getBlock1().getWorld() .equals(dcs.getBlock2().getWorld())){
				return dcs;
			}
		}
		return null;
	}

	public static DCPortal getPortal(Location location){
		int x = location.getBlock().getX(), y = location.getBlock().getY(), z = location.getBlock().getZ();

		for(DCPortal dcp : V.portals){
			if(dcp.getWorld().equals(location.getWorld().getName())){
				int x1, x2, y1, y2, z1, z2;

				if(dcp.getBlock1().getX() < dcp.getBlock2().getX()){
					x1 = dcp.getBlock1().getX();
					x2 = dcp.getBlock2().getX();
				}else{
					x2 = dcp.getBlock1().getX();
					x1 = dcp.getBlock2().getX();
				}
				if(dcp.getBlock1().getY() - 1 < dcp.getBlock2().getY() - 1){
					y1 = dcp.getBlock1().getY() - 2;
					y2 = dcp.getBlock2().getY() - 2;
				}else{
					y2 = dcp.getBlock1().getY();
					y1 = dcp.getBlock2().getY();
				}
				if(dcp.getBlock1().getZ() < dcp.getBlock2().getZ()){
					z1 = dcp.getBlock1().getZ();
					z2 = dcp.getBlock2().getZ();
				}else{
					z2 = dcp.getBlock1().getZ();
					z1 = dcp.getBlock2().getZ();
				}
				for(int i1 = x1; i1 <= x2; i1++){
					for(int i2 = y1; i2 <= y2; i2++){
						for(int i3 = z1; i3 <= z2; i3++){
							if(i1 == x && i2 == y && i3 == z){
								return dcp;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static DCPortal getPortal(String name){
		for(DCPortal dcportal : V.portals){
			if(dcportal.getName().equals(name)){
				return dcportal;
			}
		}
		return null;
	}

	public static void setPortal(String name, Block block1, Block block2, String target){
		while(getPortal(name) != null){
			V.portals.remove(getPortal(name));
		}
		if(block1 != null && block2 != null && target != null){
			V.portals.add(new DCPortal(name, block1, block2, target));
		}
	}

	public static void sleep(int duration){
		try{
			Thread.sleep(duration);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public static void cancelRemovefloat(double id){
		for(DCTask dctask : V.removefloats){
			if(dctask.getId() == id){
				V.plugin.getServer().getScheduler().cancelTask(dctask.getTask());
				if(V.plugin.getServer().getPlayer(dctask.getPlayer()) != null){

					V.plugin.getServer().getPlayer(dctask.getPlayer()).sendMessage("Removefloat finished.");
				}
				V.removefloats.remove(dctask);
				return;
			}
		}
	}

	public static void broadcastJoin(Player player, String world, boolean hide){
		for(Player p : V.plugin.getServer().getOnlinePlayers()){
			if(!player.equals(p) && (!isHide(player.getName()) || Perm.hasPermissionSilent(p, "dc.hide.see"))){
				sendMessage(p,
						getColoredString("join_world")
						.replace("<player>", player.getDisplayName())
						.replace("<world>", world)
				);
			}
		}
		if(!hide){
			L.log(getColoredString("join_world")
					.replace("<player>", player.getDisplayName())
					.replace("<world>", world));
		}
	}

	public static void broadcastDisconnect(Player player, boolean hide){
		for(Player p : V.plugin.getServer().getOnlinePlayers()){
			if(!player.equals(p) && (!isHide(player.getName()) || Perm.hasPermissionSilent(p, "dc.hide.see"))){
				sendMessage(
						p,
						replaceColor(
								getString("disconnect_message")
								.replace("<player>", player.getDisplayName())
								.replace("<world>", player.getWorld().getName())
								.replace("<ip>", getIp(player))
						)
				);
			}
		}
		if(!hide){
			L.log(replaceColor(
					getString("disconnect_message")
					.replace("<player>", player.getDisplayName())
					.replace("<world>", player.getWorld().getName())
					.replace("<ip>", player.getAddress().toString().substring(1).split(":")[0])
			));
			getConfig(player).setInventory(player.getName(), player.getInventory().getContents());
			setPlayer(new DCPlayer(player.getName(), getIp(player), getDateTime()));
			removePlayerFromLists(player);
		}
	}

	public static void broadcastConnect(Player player, boolean hide){
		String message = "connect_message";


		if(isFirstTimer(player.getName())){
			player.teleport(getConfig(player).getSpawn());
			message = "connect_message_first_time";
		}
		for(Player p : V.plugin.getServer().getOnlinePlayers()){
			if(!player.equals(p) && (!isHide(player.getName()) || Perm.hasPermission(p, "dc.hide.see"))){
				sendMessage(
						p,
						replaceColor(
								getColoredString(message)
								.replace("<player>", player.getDisplayName())
								.replace("<world>", player.getWorld().getName())
								.replace("<ip>", getIp(player))
						)
				);
			}
		}
		if(!hide){
			if(isHide(player.getName())){
				player.sendMessage("Note: You are still hidden.");
			}
			L.log(getColoredString(message)
					.replace("<player>", player.getDisplayName())
					.replace("<world>", player.getWorld().getName())
					.replace("<ip>", getIp(player))
			);
			if(getConfig(player).getInventory(player.getName()) != null){
				player.getInventory().setContents(getItemStack(getConfig(player).getInventory(player.getName()).getContents()));
			}
			player.setDisplayName(getColoredName(player.getName()));
			setPlayer(new DCPlayer(player.getName(), getIp(player), getDateTime()));
			updateHide();
		}
	}

	public static  net.minecraft.server.ItemStack[] getItemStack(ItemStack[] itemstack){
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

	public static ItemStack[] getItemStack(net.minecraft.server.ItemStack[] itemstack){
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

	public static ItemStack[] getNiceItemStack(ItemStack[] itemstack){
		ItemStack[] is = new ItemStack[36];

		for(int i = 27; i < 36; i++){
			is[i] = itemstack[i - 27];
		}
		for(int i = 0; i < 27; i++){
			is[i] = itemstack[i + 9];
		}
		return is;
	}

	public static ItemStack[] getItemStackFromString(String string){
		ItemStack[] itemstack = new ItemStack[36];

		for(int i = 0; i < string.split(";").length; i++){
			if(Integer.parseInt(string.split(";")[i].split(":")[0]) != 0){
				itemstack[i] = new ItemStack(Integer.parseInt(string.split(";")[i].split(":")[0]), Integer.parseInt(string.split(";")[i].split(":")[2]), Short.parseShort(string.split(";")[i].split(":")[1]));
			}else{
				itemstack[i] = null;
			}
		}
		return itemstack;
	}

	public static String getStringFromItemStack(ItemStack[] itemstack){
		String string = "";

		for(ItemStack is : itemstack){
			if(is != null){
				string += is.getTypeId() + ":" + is.getDurability() + ":" + is.getAmount() + ";";
			}else{
				string += "0:0:0:0;";
			}
		}
		return string;
	}

	public static List<World> getNotSeparateInventoryWorlds(){
		List<World> worlds = new LinkedList<World>();

		for(DCWorld dcworld : V.worlds){
			if(!dcworld.getBoolean("separate_inventory")){
				worlds.add(V.plugin.getServer().getWorld(dcworld.getName()));
			}
		}
		return worlds;
	}

	public static void updateHide(){
		for(Player player : V.plugin.getServer().getOnlinePlayers()){
			if(isHide(player.getName())){
				hidePlayerFromAll(player);
			}
		}
	}

	public static void hidePlayerFromAll(Player hide){
		for(Player player : hide.getServer().getOnlinePlayers()){
			hidePlayer(hide, player);
		}
	}

	public static void appearPlayerFromAll(Player appear){
		for(Player player : appear.getServer().getOnlinePlayers()){
			appearPlayer(appear, player);
		}
	}

	public static void hidePlayer(Player hide, Player player){
		if(hide != player && !Perm.hasPermissionSilent(player, "dc.hide.see")){
			((CraftPlayer) player).getHandle().netServerHandler.sendPacket(new Packet29DestroyEntity(hide.getEntityId()));
		}
	}

	public static void appearPlayer(Player appear, Player player){
		if(appear != player){
			((CraftPlayer) player).getHandle().netServerHandler.sendPacket(new Packet20NamedEntitySpawn(((org.bukkit.craftbukkit.entity.CraftPlayer) appear).getHandle()));
		}
	}

	public static boolean isRideAble(Entity entity){
		if(!(entity instanceof Pig) && !(entity instanceof Giant)){
			return true;
		}
		return false;
	}

	public static boolean isAnimal(Entity entity){
		if(
				entity instanceof Chicken
				|| entity instanceof Cow
				|| entity instanceof Pig
				|| entity instanceof Sheep
				|| entity instanceof Squid
				|| entity instanceof Wolf
				|| entity instanceof Spider && ((Spider) entity).getTarget() == null
		){
			return true;
		}
		return false;
	}

	public static boolean isMonster(Entity entity){
		if(
				entity instanceof Creeper
				|| entity instanceof Ghast
				|| entity instanceof Monster
				|| entity instanceof PigZombie
				|| entity instanceof Skeleton
				|| entity instanceof Slime
				|| entity instanceof Spider && ((Spider) entity).getTarget() != null
				|| entity instanceof Zombie
				|| entity instanceof Giant
		){
			return true;
		}
		return false;
	}

	public static boolean isFirstTimer(String player){
		for(World world : V.plugin.getServer().getWorlds()){
			if(new File(world.getName() + File.separator + "players", player + ".dat").exists()){
				return false;
			}
		}
		return true;
	}

	public static Location getSafeLocationAt(Block block){
		Location location = new Location(block.getWorld(), block.getX() + 0.5, block.getY(), block.getZ() + 0.5);

		return getSafeLocationAt(location);
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

	public static Location getSafeLocationAt(Player player){
		return getSafeLocationAt(player.getLocation());
	}

	public static List<Entity> getNearbyItems(Location location, double distance){
		List<Entity> entities = getNearbyEntities(location, distance);

		for(int i = 0; i < entities.size(); i++){
			if(!(entities.get(i) instanceof CraftItem)){
				entities.remove(i);
				i--;
			}
		}
		return entities;
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

	public static void setTime(String world, long time){
		V.plugin.getServer().getWorld(world).setTime(time);
		if(getConfig(world).getBoolean("sync_time")){
			for(DCWorld dcworld : V.worlds){
				if(!dcworld.equals(getConfig(world)) && dcworld.getBoolean("sync_time")){
					V.plugin.getServer().getWorld(dcworld.getName()).setTime(time);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static List<Player> getPlayers(Player p, String string){
		List<Player> players = new LinkedList<Player>();

		for(String s : string.split(",")){
			if(s.equals(V.all)){
				for(Player player : V.plugin.getServer().getOnlinePlayers()){
					for(int i = 0; i < players.size(); i++){
						if(players.get(i).equals(player)){
							players.remove(i);
							i--;
						}
					}
					if(p == null || p.equals(player) || !isHide(player.getName()) || isHide(player.getName()) && Perm.hasPermissionSilent(p, "dc.hide.see")){
						players.add(player);
					}
				}
			}else if(s.startsWith("w:") && V.plugin.getServer().getWorld(s.substring(2)) != null){
				for(Player player : V.plugin.getServer().getWorld(s.substring(2)).getPlayers()){
					for(int i = 0; i < players.size(); i++){
						if(players.get(i).equals(player)){
							players.remove(i);
							i--;
						}
					}
					if(p == null || p.equals(player) || !isHide(player.getName()) || isHide(player.getName()) && Perm.hasPermissionSilent(p, "dc.hide.see")){
						players.add(player);
					}
				}
			}else if(s.startsWith("g:")){
				for(World world : V.plugin.getServer().getWorlds()){
					if(Perm.PermissionsHandler != null && Perm.PermissionsHandler.getGroup(world.getName(), s.substring(2)) != null){
						for(Player player : world.getPlayers()){
							if(Perm.PermissionsHandler.inGroup(world.getName(), player.getName(), s.substring(2))){
								for(int i = 0; i < players.size(); i++){
									if(players.get(i).equals(player)){
										players.remove(i);
										i--;
									}
								}
								if(p == null || p.equals(player) || !isHide(player.getName()) || isHide(player.getName()) && Perm.hasPermissionSilent(p, "dc.hide.see")){
									players.add(player);
								}
							}
						}
					}
				}
			}else if(V.plugin.getServer().getPlayer(s) != null){
				for(int i = 0; i < players.size(); i++){
					if(players.get(i).equals(V.plugin.getServer().getPlayer(s))){
						players.remove(i);
						i--;
					}
				}
				if(p == null || p.equals(V.plugin.getServer().getPlayer(s)) || !isHide(V.plugin.getServer().getPlayer(s).getName()) || isHide(V.plugin.getServer().getPlayer(s).getName()) && Perm.hasPermissionSilent(p, "dc.hide.see")){
					players.add(V.plugin.getServer().getPlayer(s));
				}
			}
		}
		return players;
	}

	public static List<Player> getPlayers(CommandSender sender, String string){
		if(sender instanceof Player){
			return getPlayers((Player) sender, string);
		}
		return getPlayers(null, string);
	}

	public static List<String> getPlayerList(Player player){
		List<String> names = new LinkedList<String>();

		for(Player p : getPlayers(player, V.all)){
			if(isAfk(p.getEntityId())){
				names.add("&8" + p.getName());
			}else{
				names.add("&7" + p.getName());
			}
		}
		return names;
	}

	public static List<String> getPlayerList(CommandSender sender){
		if(sender instanceof Player){
			return getPlayerList((Player) sender);
		}
		return getPlayerList(null);
	}

	public static List<Material> getItems(String string){
		List<Material> materials = new LinkedList<Material>();

		for(String s : string.split(" ")){
			if(s.equals(V.all)){
				materials.clear();
				for(Material m : Material.values()){
					materials.add(m);
				}
				return materials;
			}
			if(Material.matchMaterial(s) != null){
				materials.add(Material.matchMaterial(s));
			}
		}
		return materials;
	}

	public static String getSenderName(CommandSender sender){
		if(sender instanceof Player){
			return ((Player) sender).getName();
		}
		return V.console_name;
	}

	public static String getPlayerNames(List<Player> players){
		if(players.size() == 0){
			return "";
		}
		if(players.size() > 1){
			return players.size() + " players";
		}
		return players.get(0).getName();
	}

	public static String getSenderCmdMsg(String id, CommandSender sender, List<Player> players, boolean enabled){
		String s = "disable";

		if(enabled){
			s = "enable";
		}
		return getColoredString(id).replace("<sender>", getSenderName(sender)).replace("<player>", getPlayerNames(players)).replace("<players>", getPlayerNames(players)).replace("<status>", s);
	}

	public static String getSenderCmdMsg(String id, CommandSender sender, List<Player> players){
		return getColoredString(id).replace("<sender>", getSenderName(sender)).replace("<player>", getPlayerNames(players)).replace("<players>", getPlayerNames(players)).replace("<status>", "toggle");
	}

	public static String getSenderCmdMsg(String id, CommandSender sender, List<Player> players, List<ItemStack> itemstacks){
		int amount = 0;

		for(ItemStack itemstack : itemstacks){
			amount += itemstack.getAmount();
		}
		return getSenderCmdMsg(id, sender, players).replace("<item>", getItemName(itemstacks.get(0).getType())).replace("<amount>", String.valueOf(amount));
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

	public static List<ItemStack> getItemStacks(String string, String amount){
		if(string == null || amount == null){
			return new LinkedList<ItemStack>();
		}
		if(!isValidInt(amount)){
			return new LinkedList<ItemStack>();
		}

		String material = getItem(string).split(":")[0], data = "0";
		List<ItemStack> itemstacks = new LinkedList<ItemStack>();
		int a = Integer.parseInt(amount);

		if(getItem(string).split(":").length < 3){
			if(getItem(string).split(":").length == 2){
				data = getItem(string).split(":")[1];
			}
			if(Material.matchMaterial(material) != null && Material.matchMaterial(material) != Material.AIR && isValidInt(data) && Integer.parseInt(data) > -1 && isValidInt(amount) && Integer.parseInt(amount) > 0){
				while(a > 0){
					if(a - 64 > 0){
						itemstacks.add(new ItemStack(Material.matchMaterial(material), 64, (short) 0, Byte.parseByte(data)));
						a -= 64;
					}else{
						itemstacks.add(new ItemStack(Material.matchMaterial(material), a, (short) 0, Byte.parseByte(data)));
						a -= a;
					}
				}
			}
		}
		return itemstacks;
	}

	public static String getGive(String[] args, String get){
		if(V.give.split(" ").length == args.length){
			for(int i = 0; i < args.length; i++){
				if(V.give.split(" ")[i].equals(get)){
					return args[i];
				}
			}
		}
		return null;
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
		if(string.equalsIgnoreCase("chicken")){
			return CreatureType.CHICKEN;
		}else if(string.equalsIgnoreCase("cow")){
			return CreatureType.COW;
		}else if(string.equalsIgnoreCase("creeper")){
			return CreatureType.CREEPER;
		}else if(string.equalsIgnoreCase("ghast")){
			return CreatureType.GHAST;
		}else if(string.equalsIgnoreCase("giant")){
			return CreatureType.GIANT;
		}else if(string.equalsIgnoreCase("monster")){
			return CreatureType.MONSTER;
		}else if(string.equalsIgnoreCase("pig")){
			return CreatureType.PIG;
		}else if(string.equalsIgnoreCase("pigzombie")){
			return CreatureType.PIG_ZOMBIE;
		}else if(string.equalsIgnoreCase("sheep")){
			return CreatureType.SHEEP;
		}else if(string.equalsIgnoreCase("skeleton")){
			return CreatureType.SKELETON;
		}else if(string.equalsIgnoreCase("slime")){
			return CreatureType.SLIME;
		}else if(string.equalsIgnoreCase("spider")){
			return CreatureType.SPIDER;
		}else if(string.equalsIgnoreCase("squid")){
			return CreatureType.SQUID;
		}else if(string.equalsIgnoreCase("wolf")){
			return CreatureType.WOLF;
		}else if(string.equalsIgnoreCase("zombie")){
			return CreatureType.ZOMBIE;
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

	public static void broadcastMessage(String message){
		for(Player player : V.plugin.getServer().getOnlinePlayers()){
			sendMessage(player, message);
		}
	}

	public static void sendMessage(Player player, String message){
		if(removeColor(message).length() > 0){
			for(String string : message.split("<br>")){
				player.sendMessage(replaceColor(string));
			}
		}
	}

	public static void sendMessage(CommandSender sender, String message){
		if(removeColor(message).length() > 0){
			for(String string : message.split("<br>")){
				sender.sendMessage(replaceColor(string));
			}
		}
	}

	public static void sendString(Player player, String id){
		for(String string : getColoredString(id).split("<br>")){
			player.sendMessage(string);
		}
	}

	public static void sendString(CommandSender sender, String id){
		for(String string : getColoredString(id).split("<br>")){
			sender.sendMessage(string);
		}
	}

	public static void addWorld(String world, Environment environment){
		if(getConfig(world) == null){
			delWorld(world);
			V.worlds.add(new DCWorld(world, environment, 0));
			V.plugin.getServer().createWorld(world, environment);
			getConfig(world).setSeed(V.plugin.getServer().getWorld(world).getSeed());
			setConfig(world, Reset.resetDCConfiguration(world));
		}
	}

	public static void addWorld(String world, Environment environment, long seed){
		if(getConfig(world) == null){
			delWorld(world);
			V.worlds.add(new DCWorld(world, environment, seed));
			V.plugin.getServer().createWorld(world, environment, seed);
			setConfig(world, Reset.resetDCConfiguration(world));
		}
	}

	public static void delWorld(String world){
		if(getConfig(world) != null){
			while(V.worlds.contains(getConfig(world))){
				V.worlds.remove(getConfig(world));
			}
		}
	}

	public static void removeWorld(String world, boolean save){
		if(V.plugin.getServer().getWorld(world) != null){
			for(Player player : V.plugin.getServer().getWorld(world).getPlayers()){
				player.kickPlayer("The world you were in was removed!");
			}
			delWorld(world);
			V.plugin.getServer().unloadWorld(V.plugin.getServer().getWorld(world), true);
			deleteDirectory(new File(V.plugin.getDataFolder(), "Worlds" + File.separator + world));
			if(!save){
				deleteDirectory(new File(world));
			}
		}
	}

	public static void deleteDirectory(File directory){
		if(directory.exists()){
			File[] files = directory.listFiles();

			if(files != null){
				for(int i = 0; i < files.length; i++){
					if(files[i].isDirectory()){
						deleteDirectory(files[i]);
					}else{
						files[i].delete();
					}
				}
			}
			directory.delete();
		}
	}

	public static void setConfig(String world, List<DCConfiguration> configuration){
		for(int i = 0; i < V.worlds.size(); i++){
			if(V.worlds.get(i).getName().equals(world)){
				V.worlds.get(i).setConfiguration(configuration);
			}
		}
	}

	public static void setConfig(String world, String configuration, Object value){
		for(DCWorld dcworld : V.worlds){
			if(dcworld.getName().equals(world)){
				for(int i = 0; i < dcworld.getConfiguration().size(); i++){
					if(dcworld.getConfiguration().get(i).getConfiguration().equals(configuration)){
						dcworld.getConfiguration().get(i).setValue(value);
						return;
					}
				}
			}
		}
	}

	public static DCWorld getConfig(String world){
		for(DCWorld dcworld : V.worlds){
			if(dcworld.getName().equals(world)){
				return dcworld;
			}
		}
		return null;
	}

	public static DCWorld getConfig(World world){
		return getConfig(world.getName());
	}

	public static DCWorld getConfig(Entity entity){
		return getConfig(entity.getWorld().getName());
	}

	public static DCWorld getConfig(Player player){
		return getConfig(player.getWorld().getName());
	}

	public static DCWorld getConfig(Block block){
		return getConfig(block.getWorld().getName());
	}

	public static String getCommand(String alias){
		for(DCCommand dccommand : V.commands){
			for(String dcalias : dccommand.getAlias()){
				if(dcalias.equalsIgnoreCase(alias)){
					return dccommand.getCommand();
				}
			}
		}
		return null;
	}

	public static List<String> getAlias(String command){
		for(DCCommand dccommand : V.commands){
			if(dccommand.getCommand().equalsIgnoreCase(command)){
				return dccommand.getAlias();
			}
		}
		return null;
	}

	public static void setAlias(String command, List<String> alias){
		for(DCCommand dccommand : V.commands){
			if(dccommand.getCommand().equals(command)){
				dccommand.setAlias(alias);
				return;
			}
		}
		V.commands.add(new DCCommand(command, alias));
	}

	public static void addAlias(String command, String alias){
		List<String> old = new LinkedList<String>();
		for(int i = 0; i < V.commands.size(); i++){
			if(V.commands.get(i).getCommand().equals(command)){
				old = V.commands.get(i).getAlias();
				for(String a : old){
					if(a.equalsIgnoreCase(alias)){
						return;
					}
				}
				old.add(alias);
				V.commands.get(i).setAlias(old);
				return;
			}
		}
		old.add(alias);
		V.commands.add(new DCCommand(command, old));
	}

	public static void setItemAlias(String id, List<String> alias){
		for(int i = 0; i < V.items.size(); i++){
			if(V.items.get(i).getId().equals(id)){
				V.items.get(i).setAlias(alias);
				return;
			}
		}
		V.items.add(new DCItem(id, alias));
	}

	public static String getItem(String string){
		for(DCItem item : V.items){
			for(String alias : item.getAlias()){
				if(string.equalsIgnoreCase(alias)){
					return item.getId();
				}
			}
		}
		return string;
	}

	public static void addItemAlias(String id, String alias){
		List<String> na = new LinkedList<String>();

		for(DCItem item : V.items){
			if(item.getId().equals(id)){
				for(String a : item.getAlias()){
					if(alias.equalsIgnoreCase(a)){
						return;
					}
				}
				item.getAlias().add(alias);
				return;
			}
		}
		na.add(alias);
		V.items.add(new DCItem(id, na));
	}

	public static void setString(String id, String value){
		for(int i = 0; i < V.strings.size(); i++){
			if(V.strings.get(i).getId().equals(id)){
				V.strings.get(i).setValue(value);
			}
		}
	}

	public static String getString(String id){
		for(int i = 0; i < V.strings.size(); i++){
			if(V.strings.get(i).getId().equals(id)){
				return V.strings.get(i).getValue();
			}
		}
		return "";
	}

	public static String getColoredString(String id){
		return replaceColor(getString(id));
	}

	public static void setLastCommand(String player, String command){
		for(int i = 0; i < V.last_commands.size(); i++){
			if(V.last_commands.get(i).getId().equals(player)){
				V.last_commands.remove(i);
				i--;
			}
		}
		if(command != null){
			V.last_commands.add(new DCString(player, command));
		}
	}

	public static String getLastCommand(String player){
		for(int i = 0; i < V.last_commands.size(); i++){
			if(V.last_commands.get(i).getId().equals(player)){
				return V.last_commands.get(i).getValue();
			}
		}
		return null;
	}

	public static void setName(String name, String displayname){
		for(int i = 0; i < V.names.size(); i++){
			if(V.names.get(i).getId().equals(name)){
				V.names.remove(i);
				i--;
			}
		}
		if(displayname != null && !name.equals(displayname)){
			V.names.add(new DCString(name, displayname));
		}
		if(displayname != null){
			if(V.plugin.getServer().getPlayer(name) != null){
				V.plugin.getServer().getPlayer(name).setDisplayName(getColoredName(name));
			}
		}
	}

	public static String getName(String name){
		for(int i = 0; i < V.names.size(); i++){
			if(V.names.get(i).getId().equals(name)){
				return V.names.get(i).getValue();
			}
		}
		return name;
	}

	public static String getColoredName(String name){
		return replaceColor(getName(name));
	}

	public static void setPlayer(DCPlayer dcplayer){
		while(getPlayer(dcplayer.getName()) != null){
			V.players.remove(getPlayer(dcplayer.getName()));
		}
		V.players.add(dcplayer);
	}

	public static DCPlayer getPlayer(String name){
		for(DCPlayer dcplayer : V.players){
			if(dcplayer.getName().equals(name)){
				return dcplayer;
			}
		}
		for(DCPlayer dcplayer : V.players){
			if(dcplayer.getName().startsWith(name)){
				return dcplayer;
			}
		}
		return null;
	}

	public static DCHome getHome(String name){
		for(DCHome dchome : V.homes){
			if(dchome.getName().equals(name)){
				return dchome;
			}
		}
		return null;
	}

	public static DCHome getHome(Player player){
		return getHome(player.getName());
	}

	public static void setHome(String name, Location location){
		while(getHome(name) != null){
			V.homes.remove(getHome(name));
		}
		if(location != null){
			V.homes.add(new DCHome(name, location));
		}
	}

	public static DCWarp getWarp(String name){
		for(DCWarp dcwarp : V.warps){
			if(dcwarp.getName().equals(name)){
				return dcwarp;
			}
		}
		return null;
	}

	public static void setWarp(String name, Location location){
		while(getWarp(name) != null){
			V.warps.remove(getWarp(name));
		}
		if(location != null){
			V.warps.add(new DCWarp(name, location));
		}
	}

	public static Player getPlayer(int id){
		for(Player player : V.plugin.getServer().getOnlinePlayers()){
			if(player.getEntityId() == id){
				return player;
			}
		}
		return null;
	}

	public static void setReply(String sender, String reciever){
		for(int i = 0; i < V.replies.size(); i++){
			if(V.replies.get(i).getReciever().equals(reciever)){
				V.replies.remove(i);
				i--;
			}
		}
		if(reciever != null){
			V.replies.add(new DCReply(sender, reciever));
		}
	}

	public static String getReply(String reciever){
		for(int i = 0; i < V.replies.size(); i++){
			if(V.replies.get(i).getReciever().equals(reciever)){
				return V.replies.get(i).getSender();
			}
		}
		return null;
	}

	public static void setGod(int id, boolean enabled){
		V.god.remove((Object) id);
		if(enabled){
			V.god.add(id);
		}
	}

	public static boolean isGod(int id){
		return V.god.contains(id);
	}

	public static void setFly(int id, boolean enabled){
		V.fly.remove((Object) id);
		if(enabled){
			V.fly.add(id);
		}
	}

	public static boolean isFly(int id){
		return V.fly.contains(id);
	}

	public static void setCreative(int id, boolean enabled){
		V.creative.remove((Object) id);
		if(enabled){
			V.creative.add(id);
		}
	}

	public static boolean isCreative(int id){
		return V.creative.contains(id);
	}

	public static void setIgnoremob(int id, boolean enabled){
		V.ignoremob.remove((Object) id);
		if(enabled){
			V.ignoremob.add(id);
		}
	}

	public static boolean isIgnoremob(int id){
		return V.ignoremob.contains(id);
	}

	public static void setNopickup(int id, boolean enabled){
		V.nopickup.remove((Object) id);
		if(enabled){
			V.nopickup.add(id);
		}
	}

	public static boolean isNopickup(int id){
		return V.nopickup.contains(id);
	}

	public static void setInstakill(int id, boolean enabled){
		V.instakill.remove((Object) id);
		if(enabled){
			V.instakill.add(id);
		}
	}

	public static boolean isInstakill(int id){
		return V.instakill.contains(id);
	}

	public static void setAfk(int id, boolean enabled){
		V.afk.remove((Object) id);
		if(enabled){
			V.afk.add(id);
		}
	}

	public static boolean isAfk(int id){
		return V.afk.contains(id);
	}

	public static void setFreeze(String name, boolean enabled){
		V.freeze.remove(name);
		if(enabled){
			V.freeze.add(name);
		}
	}

	public static boolean isFreeze(String name){
		return V.freeze.contains(name);
	}

	public static void setMute(String name, boolean enabled){
		V.mute.remove(name);
		if(enabled){
			V.mute.add(name);
		}
	}

	public static boolean isMute(String name){
		return V.mute.contains(name);
	}

	public static void setHide(String name, boolean enabled){
		if(!enabled){
			if(V.plugin.getServer().getPlayer(name) != null){
				appearPlayerFromAll(V.plugin.getServer().getPlayer(name));
			}
			V.hide.remove(name);
		}else{
			if(V.plugin.getServer().getPlayer(name) != null){
				hidePlayerFromAll(V.plugin.getServer().getPlayer(name));
			}
			V.hide.add(name);
		}
	}

	public static boolean isHide(String name){
		return V.hide.contains(name);
	}

	public static void setWhitelist(String name, boolean enabled){
		V.whitelist_.remove(name);
		if(enabled){
			V.whitelist_.add(name);
		}
	}

	public static boolean isWhitelist(String name){
		return V.whitelist_.contains(name);
	}
}