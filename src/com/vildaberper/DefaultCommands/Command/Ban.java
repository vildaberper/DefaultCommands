package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Ban implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.ban")){
			return V.return_;
		}
		if(args.length >= 1){
			if(Misc.getBan(args[0]) == null){
				String message = "";

				if(args.length >= 2){
					message = args[1];
					for(int i = 2; i < args.length; i++){
						message += " " + args[i];
					}
				}
				if(V.plugin.getServer().getPlayer(args[0]) != null && V.plugin.getServer().getPlayer(args[0]).getName().equals(args[0])){
					V.plugin.getServer().getPlayer(args[0]).kickPlayer(Util.replaceColor(message));
				}
				Misc.setBan(args[0], message);
				sender.sendMessage("Banned " + args[0] + ".");
			}else{
				sender.sendMessage(args[0] + " is already banned!");
			}
			return true;
		}
		return false;
	}
}
