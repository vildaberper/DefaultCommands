package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Undo{
	public static boolean undo(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.undo")){
				return V.return_;
			}
			if(args.length == 0){
				if(Misc.getUndo(((Player) sender).getName()) != null){
					Misc.getUndo(((Player) sender).getName()).undo();
					Misc.setUndo(((Player) sender).getName(), null);
					Misc.sendMessage(sender, "Undid.");
					return true;
				}else{
					sender.sendMessage("Theres nothing to undo!");
				}
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}