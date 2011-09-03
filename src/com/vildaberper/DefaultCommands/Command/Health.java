package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Health{
	public static boolean health(final CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dchealth " + ((Player) sender).getName() + " 20");
			return true;
		}else if(args.length == 1){
			((Player) sender).chat("/dchealth " + args[0] + " 20");
			return true;
		}else if(args.length == 2){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.health.all")){
					return V.return_;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
					if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.health.self")){
						return V.return_;
					}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.health.other")){
						return V.return_;
					}
				}
				if(Util.isValidInt(args[1]) && Integer.parseInt(args[1]) >= 0 && Integer.parseInt(args[1]) <= 20){
					for(Player player : Misc.getPlayers(sender, args[0])){
						if(Integer.parseInt(args[1]) > 0){
							player.setHealth(Integer.parseInt(args[1]));
						}else{
							((CraftPlayer) player).damage(((CraftPlayer) player).getHealth());
						}
					}
					L.log(Misc.getColoredString("c_health").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))).replace("<hp>", args[1]));
					Misc.sendMessage(sender, Misc.getColoredString("c_health").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))).replace("<hp>", args[1]));
					return true;
				}else{
					Misc.sendString(sender, "invalid_health");
				}
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}