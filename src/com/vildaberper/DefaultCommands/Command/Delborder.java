package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Delborder{
	public static boolean delborder(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.delborder")){
			return false;
		}
		if(args.length == 1){
			if(Misc.getBorder(args[0]) != null){
				Misc.setBorder(args[0], null);
				Misc.sendMessage(sender, "Removed border " + args[0] + ".");
				return true;
			}else{
				Misc.sendString(sender, "invalid_border");
			}
		}
		return false;
	}
}