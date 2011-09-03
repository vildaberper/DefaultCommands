package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Unban{
	public static boolean unban(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.unban")){
			return V.return_;
		}
		if(args.length == 1){
			if(Misc.getBan(args[0]) != null){
				Misc.setBan(args[0], null);
				sender.sendMessage("Unbanned " + args[0] + ".");
			}else{
				sender.sendMessage(args[0] + " is not banned!");
			}
			return true;
		}
		return false;
	}
}
