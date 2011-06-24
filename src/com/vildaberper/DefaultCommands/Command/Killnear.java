package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftCreature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Killnear {
	public static boolean killnear(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.killnear." + ((Player) sender).getWorld().getName())){
				return false;
			}
			if(Misc.isValidInt(args[0]) && Integer.parseInt(args[0]) >= 0){
				int amount = 0;

				for(Entity entity : Misc.getNearbyMobs(((Player) sender).getLocation(), Integer.parseInt(args[0]))){
					amount++;
					if(entity instanceof CraftCreature){
						((CraftCreature) entity).damage(((CraftCreature) entity).getHealth());
					}
				}
				L.log(Misc.getString("c_killnear").replace("<player>", Misc.getSenderName(sender)).replace("<world>", ((Player) sender).getWorld().getName()).replace("<amount>", Integer.toString(amount)));
				Misc.sendMessage(sender, Misc.getString("c_killnear").replace("<player>", Misc.getSenderName(sender)).replace("<world>", ((Player) sender).getWorld().getName()).replace("<amount>", Integer.toString(amount)));
				return true;
			}else{
				Misc.sendString(sender, "invalid_distance");
			}
		}
		return false;
	}
}
