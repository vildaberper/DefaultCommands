package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Sethome{
	public static boolean sethome(CommandSender sender, Command command, String label, String[] args){
		if(!(sender instanceof Player)){
			return false;
		}
		if(args.length == 0){
			((Player) sender).chat("/dcsethome " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.sethome.all")){
					}else if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
						if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.sethome.self")){
						}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.sethome.other")){
						}else{
							Misc.setHome(player.getName(), ((Player) sender).getLocation());
						}
					}else{
						Misc.setHome(player.getName(), ((Player) sender).getLocation());
					}
				}
				L.log(Misc.getColoredString("c_sethome").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				Misc.sendMessage(sender, Misc.getColoredString("c_sethome").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}