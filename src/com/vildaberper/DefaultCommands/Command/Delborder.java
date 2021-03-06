package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Delborder implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.delborder")){
			return V.return_;
		}
		if(args.length == 1){
			if(Misc.getBorder(args[0]) != null){
				Misc.setBorder(args[0], null);
				Misc.sendMessage(sender, "Removed border " + args[0] + ".");
				return true;
			}else{
				Misc.sendString(sender, "invalid_border");
			}
		}
		return false;
	}
}