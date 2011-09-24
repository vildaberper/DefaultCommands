package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.V;

public class Reply implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length >= 1){
				if(Misc.getReply(((Player) sender).getName()) != null && V.plugin.getServer().getPlayer(Misc.getReply(((Player) sender).getName())) != null){
					String message = args[0];

					for(int i = 1; i < args.length; i++){
						message += " " + args[i];
					}
					((Player) sender).chat("/dcmessage " + Misc.getReply(((Player) sender).getName()) + " " + message);
					return true;
				}else{
					Misc.sendString(sender, "invalid_player");
				}
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}