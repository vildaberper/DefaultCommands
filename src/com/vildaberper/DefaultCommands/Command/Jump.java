package com.vildaberper.DefaultCommands.Command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Jump{
	public static boolean jump(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length == 0){
				if(!Perm.hasPermission((Player) sender, "dc.jump." + ((Player) sender).getWorld().getName())){
					return false;
				}
				if(Misc.isFreeze(((Player) sender).getName())){
					Misc.sendString(sender, "frozen");
					return false;
				}
				Location location = Util.getSafeLocationAt(((Player) sender).getTargetBlock(null, V.targetmax));
				location.setYaw(((Player) sender).getLocation().getYaw());
				location.setPitch(((Player) sender).getLocation().getPitch());
				((Player) sender).teleport(location);
				return true;
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}