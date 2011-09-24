package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Create implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.create")){
			return V.return_;
		}
		if(args.length >= 1){
			String environment = "NORMAL";

			if(args.length >= 2){
				environment = args[1].toUpperCase();
			}
			if(Util.getEnvironment(environment) == null){
				Misc.sendString(sender, "invalid_environment");
				return false;
			}
			if(args.length >= 3){
				String seed = "";

				seed = args[2];
				for(int i = 3; i < args.length; i++){
					seed += " " + args[i];
				}
				Misc.addWorld(args[0], Util.getEnvironment(environment), seed.hashCode());
			}else{
				Misc.addWorld(args[0], Util.getEnvironment(environment));
			}
			Misc.sendMessage(sender, Misc.getColoredString("c_create").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<enviroment>", environment));
			L.log(Misc.getColoredString("c_create").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]).replace("<enviroment>", environment));
			return true;
		}
		return false;
	}
}