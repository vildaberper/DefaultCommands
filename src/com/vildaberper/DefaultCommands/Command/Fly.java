package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Fly{
	public static boolean fly(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dcfly " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.fly.all")){
					return false;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
					if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.fly.self")){
						return false;
					}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.fly.other")){
						return false;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(Misc.isFly(player.getEntityId())){
						Misc.setFly(player.getEntityId(), false);
					}else{
						Misc.setFly(player.getEntityId(), true);
					}
				}
				if(Misc.getPlayers(sender, args[0]).size() > 1){
					L.log(Misc.getSenderCmdMsg("c_fly", sender, Misc.getPlayers(sender, args[0])));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_fly", sender, Misc.getPlayers(sender, args[0])));
				}else{
					L.log(Misc.getSenderCmdMsg("c_fly", sender, Misc.getPlayers(sender, args[0]), Misc.isFly(Misc.getPlayers(sender, args[0]).get(0).getEntityId())));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_fly", sender, Misc.getPlayers(sender, args[0]), Misc.isFly(Misc.getPlayers(sender, args[0]).get(0).getEntityId())));
				}
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}