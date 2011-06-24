package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Name{
	public static boolean name(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dcname " + ((Player) sender).getName() + " " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dcname " + args[0] + " " + ((Player) sender).getName());
			return true;
		}else if(args.length == 2){
			if(Misc.getPlayers(sender, args[1]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.name.all")){
					return false;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() == 1){
					if(Misc.getPlayers(sender, args[1]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.name.self")){
						return false;
					}else if(Misc.getPlayers(sender, args[1]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.name.other")){
						return false;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[1])){
					Misc.setName(player.getName(), args[0]);
				}
				L.log(Misc.getColoredString("c_name").replace("<player>", Misc.getSenderName(sender)).replace("<name>", args[0]).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
				Misc.sendMessage(sender, Misc.getColoredString("c_name").replace("<player>", Misc.getSenderName(sender)).replace("<name>", args[0]).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}