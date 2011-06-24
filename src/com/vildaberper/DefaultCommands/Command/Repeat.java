package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Repeat{
	public static boolean repeat(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player && Misc.getLastCommand(((Player) sender).getName()) != null){
			if(!Perm.hasPermission((Player) sender, "dc.repeat")){
				return false;
			}
			((Player) sender).chat("/" + Misc.getLastCommand(((Player) sender).getName()));
			return true;
		}
		return false;
	}
}