package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill{
	public static boolean kill(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dchealth " + ((Player) sender).getName() + " 0");
			return true;
		}else if(args.length == 1){
			((Player) sender).chat("/dchealth " + args[0] + " 0");
			return true;
		}
		return false;
	}
}