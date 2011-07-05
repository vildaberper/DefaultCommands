package com.vildaberper.DefaultCommands.Command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;

public class Stack{
	public static boolean stack(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length > 0){
				if(Util.isValidInt(args[0]) && Integer.parseInt(args[0]) == 0){
					if(!Perm.hasPermission((Player) sender, "dc.remove")){
						return false;
					}
				}else{
					if(!Perm.hasPermission((Player) sender, "dc.stack")){
						return false;
					}
				}
			}
			if(args.length == 0){
				((Player) sender).chat("/dcstack 64");
				return true;
			}else if(args.length == 1){
				if(((Player) sender).getItemInHand() != null && !((Player) sender).getItemInHand().getType().equals(Material.AIR)){
					ItemStack itemstack = ((Player) sender).getItemInHand();

					if(!Util.isValidInt(args[0]) || Util.isValidInt(args[0]) && Integer.parseInt(args[0]) > 64 || Util.isValidInt(args[0]) && Integer.parseInt(args[0]) < 0){
						Misc.sendMessage(sender, Misc.getColoredString("invalid_amount"));
						return false;
					}
					if(Integer.parseInt(args[0]) == 0){
						((Player) sender).setItemInHand(null);
					}else{
						itemstack.setAmount(Integer.parseInt(args[0]));
						((Player) sender).setItemInHand(itemstack);
					}
					L.log(Misc.getColoredString("c_stack").replace("<player>", Misc.getSenderName(sender)).replace("<item>", Util.getItemName(itemstack.getTypeId())).replace("<amount>", String.valueOf(Integer.parseInt(args[0]))));
					Misc.sendMessage(sender, Misc.getColoredString("c_stack").replace("<player>", Misc.getSenderName(sender)).replace("<item>", Util.getItemName(itemstack.getTypeId())).replace("<amount>", String.valueOf(Integer.parseInt(args[0]))));
					return true;
				}
			}else if(args.length > 1){
				String string = "";

				for(int i = 1; i < args.length; i++){
					if(i != 1){
						string += " ";
					}
					string += args[i];
				}
				if(!Util.isValidInt(args[0]) || Util.isValidInt(args[0]) && Integer.parseInt(args[0]) > 64 || Util.isValidInt(args[0]) && Integer.parseInt(args[0]) < 0){
					Misc.sendMessage(sender, Misc.getColoredString("invalid_amount"));
					return false;
				}
				for(Material material : Misc.getItems(string)){
					if(Integer.parseInt(args[0]) == 0){
						((Player) sender).getInventory().remove(material);
					}else{
						for(int i = ((Player) sender).getInventory().getContents().length - 1; i >= 0; i--){
							if(((Player) sender).getInventory().getContents()[i] != null && ((Player) sender).getInventory().getContents()[i].getType().equals(material)){
								((Player) sender).getInventory().getContents()[i].setAmount(Integer.parseInt(args[0]));
							}
						}
					}
				}
				if(Misc.getItems(string).size() == 1){
					L.log(Misc.getColoredString("c_stack").replace("<player>", Misc.getSenderName(sender)).replace("<item>", String.valueOf(Misc.getItems(string).size()) + " item").replace("<amount>", String.valueOf(Integer.parseInt(args[0]))));
					Misc.sendMessage(sender, Misc.getColoredString("c_stack").replace("<player>", Misc.getSenderName(sender)).replace("<item>", String.valueOf(Misc.getItems(string).size()) + " item").replace("<amount>", String.valueOf(Integer.parseInt(args[0]))));
				}else{
					L.log(Misc.getColoredString("c_stack").replace("<player>", Misc.getSenderName(sender)).replace("<item>", String.valueOf(Misc.getItems(string).size()) + " items").replace("<amount>", String.valueOf(Integer.parseInt(args[0]))));
					Misc.sendMessage(sender, Misc.getColoredString("c_stack").replace("<player>", Misc.getSenderName(sender)).replace("<item>", String.valueOf(Misc.getItems(string).size()) + " items").replace("<amount>", String.valueOf(Integer.parseInt(args[0]))));
				}
				return true;
			}
		}else{
			Misc.sendMessage(sender, Misc.getColoredString("not_console"));
		}
		return false;
	}
}