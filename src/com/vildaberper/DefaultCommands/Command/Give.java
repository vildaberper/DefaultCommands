package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Give{
	public static boolean give(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 1 && sender instanceof Player && V.give.equals("item amount target")){
			((Player) sender).chat("/dcgive " + args[0] + " 1 " + ((Player) sender).getName());
			return true;
		}else if(args.length == 2 && sender instanceof Player && V.give.equals("item amount target")){
			((Player) sender).chat("/dcgive " + args[0] + " " + args[1] + " " + ((Player) sender).getName());
			return true;
		}else if(args.length == 3){
			if(Misc.getPlayers(sender, Misc.getGive(args, "target")).size() > 0){
				if(sender instanceof Player){
					if(Misc.getPlayers(sender, Misc.getGive(args, "target")).size() > 1 && !Perm.hasPermission((Player) sender, "dc.give.all")){
						return false;
					}else if(Misc.getPlayers(sender, Misc.getGive(args, "target")).size() == 1){
						if(Misc.getPlayers(sender, Misc.getGive(args, "target")).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.give.self")){
							return false;
						}else if(Misc.getPlayers(sender, Misc.getGive(args, "target")).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.give.other")){
							return false;
						}
					}
				}
				if(!Util.isValidInt(Misc.getGive(args, "amount"))){
					Misc.sendString(sender, "invalid_amount");
					return false;
				}
				if(Misc.getItemStacks(Misc.getGive(args, "item"), Misc.getGive(args, "amount")).size() == 0){
					Misc.sendString(sender, "invalid_item");
					return false;
				}
				for(Player player : Misc.getPlayers(sender, Misc.getGive(args, "target"))){
					for(ItemStack itemstack : Misc.getItemStacks(Misc.getGive(args, "item"), Misc.getGive(args, "amount"))){
						player.getInventory().addItem(itemstack);
					}
				}
				L.log(Misc.getSenderCmdMsg("c_give", sender, Misc.getPlayers(sender, Misc.getGive(args, "target")), Misc.getItemStacks(Misc.getGive(args, "item"), Misc.getGive(args, "amount"))));
				Misc.sendMessage(sender, Misc.getSenderCmdMsg("c_give", sender, Misc.getPlayers(sender, Misc.getGive(args, "target")), Misc.getItemStacks(Misc.getGive(args, "item"), Misc.getGive(args, "amount"))));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}