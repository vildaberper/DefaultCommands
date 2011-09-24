package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Setportal implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.setportal")){
				return V.return_;
			}
			if(args.length == 2){
				if(Misc.getSelection(((Player) sender).getName()) != null){
					Misc.setPortal(args[0], Misc.getSelection(((Player) sender).getName()).getBlock1(), Misc.getSelection(((Player) sender).getName()).getBlock2(), args[1]);
					Misc.sendMessage(sender, "Created portal " + args[0] + ", target " + args[1] + ".");
					return true;
				}else{
					Misc.sendString(sender, "invalid_selection");
				}
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}