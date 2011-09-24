package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;

public class Home implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dchome " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				if(sender instanceof Player && Misc.isFreeze(((Player) sender).getName())){
					Misc.sendString(sender, "frozen");
					return false;
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.home.all")){
					}else if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
						if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.home.self")){
						}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.home.other")){
						}else{
							if(Misc.getHome(player.getName()) != null){
								player.teleport(Util.getSafeLocationAt(Misc.getHome(player.getName()).getLocation()));
							}else{
								Misc.sendString(sender, "invalid_home");
								return false;
							}
						}
					}else{
						if(Misc.getHome(player.getName()) != null){
							player.teleport(Util.getSafeLocationAt(Misc.getHome(player.getName()).getLocation()));
						}
					}
				}
				L.log(Misc.getColoredString("c_home").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				Misc.sendMessage(sender, Misc.getColoredString("c_home").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}