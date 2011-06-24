package com.vildaberper.DefaultCommands.Command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Repair{
	public static boolean repair(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length == 0){
				if(!Perm.hasPermission((Player) sender, "dc.repair")){
					return false;
				}
				if(((Player) sender).getItemInHand() != null && !((Player) sender).getItemInHand().getType().equals(Material.AIR)){
					((Player) sender).getItemInHand().setDurability((short) 0);
					L.log(Misc.getColoredString("c_repair").replace("<player>", Misc.getSenderName(sender)).replace("<item>", Misc.getItemName(((Player) sender).getItemInHand().getTypeId())));
					Misc.sendMessage(sender, Misc.getColoredString("c_repair").replace("<player>", Misc.getSenderName(sender)).replace("<item>", Misc.getItemName(((Player) sender).getItemInHand().getTypeId())));
					return true;
				}else{
					Misc.sendString(sender, "item_in_hand");
				}
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}
