package com.vildaberper.DefaultCommands.Command;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;

public class Cuboidhollow{
	public static boolean cuboidhollow(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.cuboidhollow")){
				return false;
			}
			if(args.length == 1){
				if(Misc.getSelection(((Player) sender).getName()) != null){
					List<Block> blocks = Misc.getCuboidhollow(Misc.getSelection(((Player) sender).getName()).getBlock1(), Misc.getSelection(((Player) sender).getName()).getBlock2());
					Material material = Material.matchMaterial(args[0].split(":")[0]);
					byte data = 0;

					if(args[0].split(":").length == 2){
						data = Byte.parseByte(args[0].split(":")[1]);
					}else if(args[0].split(":").length > 2){
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
					Misc.sendMessage(sender, "Filled " + blocks.size() + " blocks with " + Misc.getItemName(material) + ".");
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