package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Kit implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dckit " + args[0] + " " + ((Player) sender).getName());
			return true;
		}else if(args.length == 2){
			if(Misc.getKit(args[0]) == null){
				Misc.sendString(sender, "invalid_kit");
				return false;
			}
			if(Misc.getPlayers(sender, args[1]).size() != 0){
				for(Player player : Misc.getPlayers(sender, args[1])){
					if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.kit.all." + Misc.getKit(args[0]).getName())){
					}else if(sender instanceof Player && Misc.getPlayers(sender, args[1]).size() == 1){
						if(Misc.getPlayers(sender, args[1]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.kit.self." + Misc.getKit(args[0]).getName())){
						}else if(Misc.getPlayers(sender, args[1]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.kit.other." + Misc.getKit(args[0]).getName())){
						}else{
							Misc.giveKit(player, Misc.getKit(args[0]));
						}
					}else{
						Misc.giveKit(player, Misc.getKit(args[0]));
					}
				}
				L.log(Misc.getColoredString("c_kit").replace("<player>", Misc.getSenderName(sender)).replace("<kit>", Misc.getKit(args[0]).getName()).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
				Misc.sendMessage(sender, Misc.getColoredString("c_kit").replace("<player>", Misc.getSenderName(sender)).replace("<kit>", Misc.getKit(args[0]).getName()).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[1]))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}