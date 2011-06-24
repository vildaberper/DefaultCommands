package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Create{
	public static boolean create(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.create")){
			return false;
		}
		if(args.length >= 2){
			args[1] = args[1].toUpperCase();
		}
		if(Misc.getEnvironment(args[1]) == null){
			Misc.sendString(sender, "invalid_environment");
			return false;
		}
		if(args.length == 2){
			Misc.addWorld(args[0], Misc.getEnvironment(args[1]));
			Perm.setupPermissions(args[0]);
			Misc.sendMessage(sender, Misc.getColoredString("c_create").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<enviroment>", args[1]));
			L.log(Misc.getColoredString("c_create").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<enviroment>", args[1]));
			return true;
		}else if(args.length == 3){
			Misc.addWorld(args[0], Misc.getEnvironment(args[1]), args[2].hashCode());
			Perm.setupPermissions(args[0]);
			Misc.sendMessage(sender, Misc.getColoredString("c_create").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<enviroment>", args[1]));
			L.log(Misc.getColoredString("c_create").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<enviroment>", args[1]));
			return true;
		}
		return false;
	}
}