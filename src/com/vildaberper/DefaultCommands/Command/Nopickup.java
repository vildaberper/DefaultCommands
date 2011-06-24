package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Nopickup{
	public static boolean nopickup(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dcnopickup " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.nopickup.all")){
					return false;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
					if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.nopickup.self")){
						return false;
					}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.nopickup.other")){
						return false;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(Misc.isNopickup(player.getEntityId())){
						Misc.setNopickup(player.getEntityId(), false);
					}else{
						Misc.setNopickup(player.getEntityId(), true);
					}
				}
				if(Misc.getPlayers(sender, args[0]).size() > 1){
					L.log(Misc.getSenderCmdMsg("c_nopickup", sender, Misc.getPlayers(sender, args[0])));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_nopickup", sender, Misc.getPlayers(sender, args[0])));
				}else{
					L.log(Misc.getSenderCmdMsg("c_nopickup", sender, Misc.getPlayers(sender, args[0]), Misc.isNopickup(Misc.getPlayers(sender, args[0]).get(0).getEntityId())));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_nopickup", sender, Misc.getPlayers(sender, args[0]), Misc.isNopickup(Misc.getPlayers(sender, args[0]).get(0).getEntityId())));
				}
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}