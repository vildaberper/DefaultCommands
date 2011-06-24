package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Warps {
	public static boolean warps(CommandSender sender, Command command, String label, String[] args){
		int page = 1, max = 0;

		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.warps")){
			return false;
		}
		if(args.length < 2){
			if(args.length == 1){
				if(Misc.isValidInt(args[0]) && Integer.parseInt(args[0]) > 0){
					page = Integer.parseInt(args[0]);
				}else{
					return false;
				}
			}
			max = Math.round(V.warps.size() / V.per_page);
			if(V.warps.size() > max * V.per_page){
				max += 1;
			}
			if(page > max){
				Misc.sendString(sender, "invalid_page");
				return false;
			}
			sender.sendMessage("Page " + page + " of " + max + ":");
			for(int i = (page - 1) * V.per_page; i < (page - 1) * V.per_page + V.per_page && i < V.warps.size(); i++){
				sender.sendMessage(V.warps.get(i).getName() + " - " + V.warps.get(i).getWorld() + " " + Math.round(V.warps.get(i).getX()) + " " + Math.round(V.warps.get(i).getY()) + " " + Math.round(V.warps.get(i).getZ()));
			}
			return true;
		}
		return false;
	}
}
