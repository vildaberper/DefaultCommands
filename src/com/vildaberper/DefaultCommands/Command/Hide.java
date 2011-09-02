package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.Class.DCPlayer;

public class Hide {
	public static boolean hide(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dchide " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.hide.all")){
					return false;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
					if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.hide.self")){
						return false;
					}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.hide.other")){
						return false;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(Misc.isHide(player.getName())){
						Misc.setHide(player.getName(), false);
					}else{
						Misc.setHide(player.getName(), true);
					}
					Misc.setPlayer(new DCPlayer(player.getName(), Util.getIp(player), Util.getDateTime()));
				}
				if(Misc.getPlayers(sender, args[0]).size() > 1){
					L.log(Misc.getSenderCmdMsg("c_hide", sender, Misc.getPlayers(sender, args[0])));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_hide", sender, Misc.getPlayers(sender, args[0])));
				}else{
					L.log(Misc.getSenderCmdMsg("c_hide", sender, Misc.getPlayers(sender, args[0]), Misc.isHide(Misc.getPlayers(sender, args[0]).get(0).getName())));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_hide", sender, Misc.getPlayers(sender, args[0]), Misc.isHide(Misc.getPlayers(sender, args[0]).get(0).getName())));
				}
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}
