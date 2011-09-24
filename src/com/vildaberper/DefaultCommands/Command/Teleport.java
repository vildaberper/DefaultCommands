package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;

public class Teleport implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dcteleport " + ((Player) sender).getName() + " " + args[0]);
			return true;
		}else if(args.length == 2){
			if(Misc.getPlayers(sender, args[0]).size() != 0 && Misc.getPlayers(sender, args[1]).size() == 1){
				if(sender instanceof Player && Misc.isFreeze(((Player) sender).getName())){
					Misc.sendString(sender, "frozen");
					return false;
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.teleport.all." + Misc.getPlayers(sender, args[1]).get(0).getWorld().getName())){
					}else if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() == 1){
						if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.teleport.self." + Misc.getPlayers(sender, args[1]).get(0).getWorld().getName())){
						}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.teleport.other." + Misc.getPlayers(sender, args[1]).get(0).getWorld().getName())){
						}else{
							player.teleport(Util.getSafeLocationAt(Misc.getPlayers(sender, args[1]).get(0)));
						}
					}else{
						player.teleport(Util.getSafeLocationAt(Misc.getPlayers(sender, args[1]).get(0)));
					}
				}
				L.log(Misc.getColoredString("c_teleport").replace("<player>", Misc.getSenderName(sender)).replace("<target>", Misc.getPlayers(sender, args[1]).get(0).getName()).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				Misc.sendMessage(sender, Misc.getColoredString("c_teleport").replace("<player>", Misc.getSenderName(sender)).replace("<target>", Misc.getPlayers(sender, args[1]).get(0).getName()).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}