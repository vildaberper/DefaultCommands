package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Whitelist{
	public static boolean whitelist(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 2){
			if(args[0].equalsIgnoreCase("add")){
				if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.whitelist.add")){
					return V.return_;
				}
				if(Misc.isWhitelist(args[1])){
					sender.sendMessage(args[1] + " is already on the whitelist!");
				}else{
					Misc.setWhitelist(args[1], true);
					sender.sendMessage(args[1] + " has been added to the whitelist.");
				}
				return true;
			}else if(args[0].equalsIgnoreCase("remove")){
				if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.whitelist.remove")){
					return false;
				}
				if(!Misc.isWhitelist(args[1])){
					sender.sendMessage(args[1] + " is not on the whitelist!");
				}else{
					Misc.setWhitelist(args[1], false);
					sender.sendMessage(args[1] + " has been removed from the whitelist.");
				}
				return true;
			}
		}
		return false;
	}
}