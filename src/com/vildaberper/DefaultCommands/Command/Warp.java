package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;

public class Warp{
	public static boolean warp(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dcwarp " + args[0] + " " + ((Player) sender).getName());
			return true;
		}else if(args.length == 2){
			if(Misc.getWarp(args[0]) == null){
				Misc.sendString(sender, "invalid_warp");
				return false;
			}
			if(sender instanceof Player && Misc.isFreeze(((Player) sender).getName())){
				Misc.sendString(sender, "frozen");
				return false;
			}
			if(Misc.getPlayers(sender, args[1]).size() != 0){
				for(Player player : Misc.getPlayers(sender, args[1])){
					if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.warp.all." + Misc.getWarp(args[0]).getName())){
					}else if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() == 1){
						if(Misc.getPlayers(sender, args[1]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.warp.self." + Misc.getWarp(args[0]).getName())){
						}else if(Misc.getPlayers(sender, args[1]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.warp.other." + Misc.getWarp(args[0]).getName())){
						}else{
							player.teleport(Util.getSafeLocationAt(Misc.getWarp(args[0]).getLocation()));
						}
					}else{
						player.teleport(Util.getSafeLocationAt(Misc.getWarp(args[0]).getLocation()));
					}
				}
				L.log(Misc.getColoredString("c_warp").replace("<player>", Misc.getSenderName(sender)).replace("<warp>", Misc.getWarp(args[0]).getName()).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
				Misc.sendMessage(sender, Misc.getColoredString("c_warp").replace("<player>", Misc.getSenderName(sender)).replace("<warp>", Misc.getWarp(args[0]).getName()).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}