package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Clearinventory{
	public static boolean clearinventory(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dcclearinventory " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.clearinventory.all")){
					return V.return_;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
					if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.clearinventory.self")){
						return V.return_;
					}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.clearinventory.other")){
						return V.return_;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					player.getInventory().clear();
				}
				L.log(Misc.getColoredString("c_clearinventory").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				Misc.sendMessage(sender, Misc.getColoredString("c_clearinventory").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}