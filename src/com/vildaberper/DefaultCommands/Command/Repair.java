package com.vildaberper.DefaultCommands.Command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Repair implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length == 0){
				if(!Perm.hasPermission((Player) sender, "dc.repair")){
					return V.return_;
				}
				if(((Player) sender).getItemInHand() != null && !((Player) sender).getItemInHand().getType().equals(Material.AIR)){
					((Player) sender).getItemInHand().setDurability((short) 0);
					L.log(Misc.getColoredString("c_repair").replace("<player>", Misc.getSenderName(sender)).replace("<item>", Util.getItemName(((Player) sender).getItemInHand().getTypeId())));
					Misc.sendMessage(sender, Misc.getColoredString("c_repair").replace("<player>", Misc.getSenderName(sender)).replace("<item>", Util.getItemName(((Player) sender).getItemInHand().getTypeId())));
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
