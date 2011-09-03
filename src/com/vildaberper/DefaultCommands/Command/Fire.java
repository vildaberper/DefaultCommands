package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Fire{
	public static boolean fire(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 2){
			if(Misc.getPlayers(sender, args[1]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.fire.all")){
					return V.return_;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() == 1){
					if(Misc.getPlayers(sender, args[1]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.fire.self")){
						return V.return_;
					}else if(Misc.getPlayers(sender, args[1]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.fire.other")){
						return V.return_;
					}
				}
				if(Util.isValidInt(args[0]) && Integer.parseInt(args[0]) >= 0){
					for(Player player : Misc.getPlayers(sender, args[1])){
						player.setFireTicks(Integer.parseInt(args[0]));
					}
					L.log(Misc.getColoredString("c_fire").replace("<player>", Misc.getSenderName(sender)).replace("<duration>", args[0]).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
					Misc.sendMessage(sender, Misc.getColoredString("c_fire").replace("<player>", Misc.getSenderName(sender)).replace("<duration>", args[0]).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
					return true;
				}else{
					Misc.sendString(sender, "invalid_duration");
				}
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}