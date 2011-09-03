package com.vildaberper.DefaultCommands.Command;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;
import com.vildaberper.DefaultCommands.Reset;
import com.vildaberper.DefaultCommands.Class.DCTask;

public class Removefloat{
	public static boolean removefloat(CommandSender sender, Command command, String label, String[] args){
		final double id = Math.random() * Math.random();
		final List<Block> unchecked = new LinkedList<Block>();
		final List<Material> replace = new LinkedList<Material>(), exclude = new LinkedList<Material>();

		if(sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.removefloat")){
				return V.return_;
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("stop")){
				Reset.resetRemovefloat();
				sender.sendMessage("Canceled all removefloats.");
				return true;
			}
			if(args.length > 0){
				for(String s : args){
					if(s.charAt(0) == '-'){
						if(Material.matchMaterial(s.substring(1)) != null){
							if(!replace.contains(Material.matchMaterial(s.substring(1)))){
								exclude.add(Material.matchMaterial(s.substring(1)));
							}
						}else{
							sender.sendMessage("Invalid material: " + s.substring(1));
							return false;
						}
					}else{
						if(Material.matchMaterial(s) != null){
							if(!replace.contains(Material.matchMaterial(s))){
								replace.add(Material.matchMaterial(s));
							}
						}else{
							sender.sendMessage("Invalid material: " + s);
							return false;
						}
					}
				}
			}
			unchecked.add(((Player) sender).getTargetBlock(null, V.targetmax));
			V.removefloats.add(
					new DCTask(
							id,
							V.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(
									V.plugin,
									new Runnable(){
										@Override
										public void run(){
											if(unchecked.size() > 0){
												if(!unchecked.get(0).getType().equals(Material.AIR) && !unchecked.get(0).getType().equals(Material.BEDROCK)){
													if(replace.size() == 0 || replace.contains(unchecked.get(0).getRelative(BlockFace.DOWN).getType()) && !unchecked.get(0).getRelative(BlockFace.DOWN).getType().equals(Material.AIR) && !unchecked.contains(unchecked.get(0).getRelative(BlockFace.DOWN))){
														unchecked.add(unchecked.get(0).getRelative(BlockFace.DOWN));
													}
													if(replace.size() == 0 || replace.contains(unchecked.get(0).getRelative(BlockFace.EAST).getType()) && !unchecked.get(0).getRelative(BlockFace.EAST).getType().equals(Material.AIR) && !unchecked.contains(unchecked.get(0).getRelative(BlockFace.EAST))){
														unchecked.add(unchecked.get(0).getRelative(BlockFace.EAST));
													}
													if(replace.size() == 0 || replace.contains(unchecked.get(0).getRelative(BlockFace.NORTH).getType()) && !unchecked.get(0).getRelative(BlockFace.NORTH).getType().equals(Material.AIR) && !unchecked.contains(unchecked.get(0).getRelative(BlockFace.NORTH))){
														unchecked.add(unchecked.get(0).getRelative(BlockFace.NORTH));
													}
													if(replace.size() == 0 || replace.contains(unchecked.get(0).getRelative(BlockFace.SOUTH).getType()) && !unchecked.get(0).getRelative(BlockFace.SOUTH).getType().equals(Material.AIR) && !unchecked.contains(unchecked.get(0).getRelative(BlockFace.SOUTH))){
														unchecked.add(unchecked.get(0).getRelative(BlockFace.SOUTH));
													}
													if(replace.size() == 0 || replace.contains(unchecked.get(0).getRelative(BlockFace.UP).getType()) && !unchecked.get(0).getRelative(BlockFace.UP).getType().equals(Material.AIR) && !unchecked.contains(unchecked.get(0).getRelative(BlockFace.UP))){
														unchecked.add(unchecked.get(0).getRelative(BlockFace.UP));
													}
													if(replace.size() == 0 || replace.contains(unchecked.get(0).getRelative(BlockFace.WEST).getType()) && !unchecked.get(0).getRelative(BlockFace.WEST).getType().equals(Material.AIR) && !unchecked.contains(unchecked.get(0).getRelative(BlockFace.WEST))){
														unchecked.add(unchecked.get(0).getRelative(BlockFace.WEST));
													}
													if((replace.size() == 0 || replace.contains(unchecked.get(0).getType())) && !exclude.contains(unchecked.get(0).getType())){
														unchecked.get(0).setType(Material.AIR);
													}
												}
												unchecked.remove(0);
											}else{
												Misc.cancelRemovefloat(id);
											}
										}
									},
									0L,
									1L
							),
							((Player) sender).getName(),
							((Player) sender).getWorld()
					)
			);
			sender.sendMessage("Removefloat started.");
			return true;
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}