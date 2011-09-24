package com.vildaberper.DefaultCommands.Command;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Edit;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Cuboidwireframe implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(!Perm.hasPermission((Player) sender, "dc.cuboidwireframe")){
				return V.return_;
			}
			if(args.length == 1){
				if(Misc.getSelection(((Player) sender).getName()) != null){
					List<Block> blocks = Edit.getCuboidwireframe(Misc.getSelection(((Player) sender).getName()).getBlock1(), Misc.getSelection(((Player) sender).getName()).getBlock2());
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
					Misc.sendMessage(sender, "Filled " + blocks.size() + " blocks with " + Util.getItemName(material) + ".");
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