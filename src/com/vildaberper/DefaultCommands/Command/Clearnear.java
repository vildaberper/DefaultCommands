package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Clearnear implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.clearnear." + ((Player) sender).getWorld().getName())){
				return V.return_;
			}
			if(Util.isValidInt(args[0]) && Integer.parseInt(args[0]) > 0){
				int amount = 0;

				for(Entity entity : Util.getNearbyItems(((Player) sender).getLocation(), Integer.parseInt(args[0]))){
					entity.remove();
					amount++;
				}
				L.log(Misc.getString("c_clearnear").replace("<player>", Misc.getSenderName(sender)).replace("<world>", ((Player) sender).getWorld().getName()).replace("<amount>", Integer.toString(amount)));
				Misc.sendMessage(sender, Misc.getString("c_clearnear").replace("<player>", Misc.getSenderName(sender)).replace("<world>", ((Player) sender).getWorld().getName()).replace("<amount>", Integer.toString(amount)));
				return true;
			}else{
				Misc.sendString(sender, "invalid_distance");
			}
		}
		return false;
	}
}