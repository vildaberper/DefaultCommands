package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Delwarp{
	public static boolean delwarp(CommandSender sender, Command command, String label, String[] args){
		if(!(sender instanceof Player)){
			return false;
		}
		if(args.length == 1){
			if(!Perm.hasPermission((Player) sender, "dc.delwarp")){
				return false;
			}
			Misc.setWarp(Misc.getWarp(args[0]).getName(), null);
			L.log(Misc.getColoredString("c_delwarp").replace("<player>", Misc.getSenderName(sender)).replace("<warp>", args[0]));
			Misc.sendMessage(sender, Misc.getColoredString("c_delwarp").replace("<player>", Misc.getSenderName(sender)).replace("<warp>", args[0]));
			return true;
		}
		return false;
	}
}