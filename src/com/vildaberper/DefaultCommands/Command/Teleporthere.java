package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleporthere{
	public static boolean teleporthere(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dcteleport " + args[0] + " " + ((Player) sender).getName());
			return true;
		}
		return false;
	}
}