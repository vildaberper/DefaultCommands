package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Setspawn implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0){
			if(sender instanceof Player){
				if(!Perm.hasPermission((Player) sender, "dc.setspawn." + ((Player) sender).getWorld().getName())){
					return V.return_;
				}
				Misc.getConfig((Player) sender).setSpawn(((Player) sender).getLocation());
				L.log(Misc.getColoredString("c_setspawn").replace("<player>", Misc.getSenderName(sender)).replace("<world>", Misc.getConfig((Player) sender).getName()));
				Misc.sendMessage(sender, Misc.getColoredString("c_setspawn").replace("<player>", Misc.getSenderName(sender)).replace("<world>", Misc.getConfig((Player) sender).getName()));
				return true;
			}else{
				Misc.sendString(sender, "not_console");
			}
		}
		return false;
	}
}