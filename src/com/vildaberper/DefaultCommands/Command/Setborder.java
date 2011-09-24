package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;
import com.vildaberper.DefaultCommands.Class.DCBorder;

public class Setborder implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.setborder")){
				return V.return_;
			}
			if(args.length == 2){
				if(!Util.isValidInt(args[1])){
					Misc.sendMessage(sender, Misc.getColoredString("invalid_radius"));
					return false;
				}
				Misc.setBorder(args[0], new DCBorder(args[0], ((Player) sender).getLocation(), Integer.parseInt(args[1])));
				Misc.sendMessage(sender, "Created border " + args[0] + ", radius " + args[1] + ".");
				return true;
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}