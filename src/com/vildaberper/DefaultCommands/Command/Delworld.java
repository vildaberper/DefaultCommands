package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Delworld{
	public static boolean delworld(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.delworld")){
			return V.return_;
		}
		if(args.length == 1 || args.length == 2 && (args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("remove"))){
			if(Misc.getConfig(args[0]) != null){
				Misc.removeWorld(args[0], !(args.length == 2 && (args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("remove"))));
				Misc.sendMessage(sender, Misc.getColoredString("c_delworld").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]));
				L.log(Misc.getColoredString("c_delworld").replace("<player>", Misc.getSenderName(sender)).replace("<world>", args[0]));
				return true;
			}else{
				Misc.sendString(sender, "invalid_world");
			}
		}
		return false;
	}
}