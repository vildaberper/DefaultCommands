package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;

public class Spawn implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dcspawn " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(sender instanceof Player){
				if(Misc.getPlayers(sender, args[0]).size() != 0){
					if(sender instanceof Player && Misc.isFreeze(((Player) sender).getName())){
						Misc.sendString(sender, "frozen");
						return false;
					}
					for(Player player : Misc.getPlayers(sender, args[0])){
						if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.spawn.all." + player.getWorld().getName())){
						}else if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
							if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.spawn.self." + player.getWorld().getName())){
							}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.spawn.other." + player.getWorld().getName())){
							}else{
								player.teleport(Util.getSafeLocationAt(Misc.getConfig(player).getSpawn()));
							}
						}else{
							player.teleport(Util.getSafeLocationAt(Misc.getConfig(player).getSpawn()));
						}
					}
					L.log(Misc.getSenderCmdMsg("c_spawn", sender, Misc.getPlayers(sender, args[0])));
					Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_spawn", sender, Misc.getPlayers(sender, args[0])));
					return true;
				}else{
					Misc.sendString(sender, "invalid_player");
				}
			}else{
				Misc.sendString(sender, "not_console");
			}
		}
		return false;
	}
}