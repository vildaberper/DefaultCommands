package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Disconnect{
	public static boolean disconnect(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.disconnect")){
				return false;
			}
			((Player) sender).kickPlayer(Misc.getString("c_disconnect_message").replace("<player>", ((Player) sender).getName()));
			return true;
		}
		return false;
	}
}