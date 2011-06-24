package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Lightning{
	public static boolean lightning(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length == 0){
				if(!Perm.hasPermission((Player) sender, "dc.lightning")){
					return false;
				}
				((Player) sender).getWorld().strikeLightning(((Player) sender).getTargetBlock(null, V.targetmax).getLocation());
				return true;
			}
		}
		return false;
	}
}