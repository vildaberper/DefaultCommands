package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Time{
	public static boolean time(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dctime " + args[0] + " " + ((Player) sender).getWorld().getName());
			return true;
		}else if(args.length == 2){
			if(V.plugin.getServer().getWorld(args[1]) != null){
				if(Util.isValidInt(args[0]) && Integer.parseInt(args[0]) >= 0 && Integer.parseInt(args[0]) <= 24000){
					if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.time." + V.plugin.getServer().getWorld(args[1]).getName())){
						return V.return_;
					}
					Misc.setTime(V.plugin.getServer().getWorld(args[1]).getName(), Long.parseLong(args[0]));
					L.log(Misc.getColoredString("c_time").replace("<player>", Misc.getSenderName(sender)).replace("<world>", V.plugin.getServer().getWorld(args[1]).getName()).replace("<time>", args[0]));
					Misc.sendMessage(sender, Misc.getColoredString("c_time").replace("<player>", Misc.getSenderName(sender)).replace("<world>", V.plugin.getServer().getWorld(args[1]).getName()).replace("<time>", args[0]));
					return true;
				}else{
					Misc.sendString(sender, "invalid_time");
				}
			}else{
				Misc.sendString(sender, "invalid_world");
			}
		}
		return false;
	}
}