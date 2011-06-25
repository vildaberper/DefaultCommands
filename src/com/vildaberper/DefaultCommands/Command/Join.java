package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;

public class Join{
	public static boolean join(CommandSender sender, Command command, String label, String[] args){

		if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dcjoin " + args[0] + " " + ((Player) sender).getName());
			return true;
		}else if(args.length == 2){
			if(Misc.getPlayers(sender, args[1]).size() != 0){
				if(Misc.getConfig(args[0]) != null){
					if(sender instanceof Player && Misc.isFreeze(((Player) sender).getName())){
						Misc.sendString(sender, "frozen");
						return false;
					}
					for(Player player : Misc.getPlayers(sender, args[1])){
						if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.join.all." + args[0])){
						}else if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() == 1){
							if(Misc.getPlayers(sender, args[1]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.join.self." + args[0])){
							}else if(Misc.getPlayers(sender, args[1]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.join.other." + args[0])){
							}else{
								player.teleport(Util.getSafeLocationAt(Misc.getConfig(args[0]).getSpawn()));
							}
						}else{
							player.teleport(Util.getSafeLocationAt(Misc.getConfig(args[0]).getSpawn()));
						}
					}
					L.log(Misc.getColoredString("c_join").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
					Misc.sendMessage(sender, Misc.getColoredString("c_join").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
					return true;
				}else{
					Misc.sendString(sender, "invalid_world");
				}
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}