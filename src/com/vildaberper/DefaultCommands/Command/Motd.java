package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Motd implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0){
			if(!(sender instanceof Player) || Perm.hasPermission((Player) sender, "dc.motd")){
				Misc.sendMessage(sender, Misc.getMotd(Misc.getSenderName(sender)));
				return true;
			}else{
				return V.return_;
			}
		}
		return false;
	}
}
