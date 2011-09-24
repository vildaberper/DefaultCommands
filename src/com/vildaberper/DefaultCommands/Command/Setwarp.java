package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Setwarp implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(!(sender instanceof Player)){
			return false;
		}
		if(args.length == 1){
			if(!Perm.hasPermission((Player) sender, "dc.setwarp")){
				return V.return_;
			}
			Misc.setWarp(args[0], ((Player) sender).getLocation());
			L.log(Misc.getColoredString("c_setwarp").replace("<player>", Misc.getSenderName(sender)).replace("<warp>", args[0]));
			Misc.sendMessage(sender, Misc.getColoredString("c_setwarp").replace("<player>", Misc.getSenderName(sender)).replace("<warp>", args[0]));
			return true;
		}
		return false;
	}
}