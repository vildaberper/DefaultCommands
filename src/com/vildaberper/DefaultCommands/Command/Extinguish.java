package com.vildaberper.DefaultCommands.Command;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Extinguish{
	public static boolean extinguish(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length == 0){
				((Player) sender).chat("/dcextinguish 20");
				return true;
			}else if(args.length == 1){
				if(!Perm.hasPermission((Player) sender, "dc.extinguish")){
					return false;
				}
				if(Misc.isValidInt(args[0]) && Integer.parseInt(args[0]) >= 0){
					Block block = ((Player) sender).getLocation().getBlock();
					int count = 0;

					for(int x = block.getX() - Integer.parseInt(args[0]); x <= block.getX() + Integer.parseInt(args[0]); x++){
						for(int y = block.getY() - Integer.parseInt(args[0]); y <= block.getY() + Integer.parseInt(args[0]); y++){
							for(int z = block.getZ() - Integer.parseInt(args[0]); z <= block.getZ() + Integer.parseInt(args[0]); z++){
								if(block.getWorld().getBlockAt(x, y, z).getType().equals(Material.FIRE)){
									block.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
									count++;
								}
							}
						}
					}
					Misc.sendMessage(sender,"Extinguished " + count + " blocks.");
					return true;
				}else{
					Misc.sendString(sender, "invalid_distance");
				}
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}