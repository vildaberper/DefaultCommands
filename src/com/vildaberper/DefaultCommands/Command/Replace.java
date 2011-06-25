package com.vildaberper.DefaultCommands.Command;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Edit;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.Class.DCIdData;

public class Replace{
	public static boolean replace(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.replace")){
				return false;
			}
			if(args.length > 1){
				if(Misc.getSelection(((Player) sender).getName()) != null){
					List<DCIdData> dcid = new LinkedList<DCIdData>();

					for(int i = 0; i < args.length - 1; i++){
						Material material = Material.matchMaterial(args[i].split(":")[0]);
						byte data = 0;

						if(args[i].split(":").length == 2){
							data = Byte.parseByte(args[i].split(":")[1]);
						}else if(args[i].split(":").length > 2){
							sender.sendMessage("Syntax error.");
							return false;
						}
						if(material == null){
							sender.sendMessage("Invalid material.");
							return false;
						}
						dcid.add(new DCIdData(material.getId(), data));
					}

					List<Block> blocks = Edit.getReplace(Misc.getSelection(((Player) sender).getName()).getBlock1(), Misc.getSelection(((Player) sender).getName()).getBlock2(), dcid);
					Material material = Material.matchMaterial(args[args.length - 1].split(":")[0]);
					byte data = 0;


					if(args[args.length - 1].split(":").length == 2){
						data = Byte.parseByte(args[args.length - 1].split(":")[1]);
					}else if(args[args.length - 1].split(":").length > 2){
						sender.sendMessage("Syntax error.");
						return false;
					}
					if(material == null){
						sender.sendMessage("Invalid material.");
						return false;
					}
					Misc.setUndo(((Player) sender).getName(), blocks);
					for(Block block : blocks){
						block.setType(material);
						block.setData(data);
					}
					Misc.sendMessage(sender, "Replaced " + blocks.size() + " blocks with " + Util.getItemName(material) + ".");
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