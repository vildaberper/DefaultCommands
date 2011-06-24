package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Mute{
	public static boolean mute(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dcmute " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.mute.all")){
					return false;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
					if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.mute.self")){
						return false;
					}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.mute.other")){
						return false;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(Misc.isMute(player.getName())){
						Misc.setMute(player.getName(), false);
					}else{
						Misc.setMute(player.getName(), true);
					}
				}
				if(Misc.getPlayers(sender, args[0]).size() > 1){
					L.log(Misc.getSenderCmdMsg("c_mute", sender, Misc.getPlayers(sender, args[0])));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_mute", sender, Misc.getPlayers(sender, args[0])));
				}else{
					L.log(Misc.getSenderCmdMsg("c_mute", sender, Misc.getPlayers(sender, args[0]), Misc.isMute(Misc.getPlayers(sender, args[0]).get(0).getName())));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_mute", sender, Misc.getPlayers(sender, args[0]), Misc.isMute(Misc.getPlayers(sender, args[0]).get(0).getName())));
				}
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}