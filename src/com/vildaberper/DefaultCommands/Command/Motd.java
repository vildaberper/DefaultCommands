package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Motd{
	public static boolean motd(CommandSender sender, Command command, String label, String[] args){
		if(!(sender instanceof Player) || Perm.hasPermission((Player) sender, "dc.motd")){
			Misc.sendMessage(sender, Misc.getMotd(Misc.getSenderName(sender)));
			return true;
		}
		return false;
	}
}
