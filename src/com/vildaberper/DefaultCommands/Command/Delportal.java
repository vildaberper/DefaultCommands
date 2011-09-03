package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Delportal{
	public static boolean delportal(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.delportal")){
			return V.return_;
		}
		if(args.length == 1){
			if(Misc.getPortal(args[0]) != null){
				Misc.setPortal(args[0], null, null, null);
				Misc.sendMessage(sender, "Removed portal " + args[0] + ".");
				return true;
			}else{
				Misc.sendString(sender, "invalid_portal");
			}
		}
		return false;
	}
}