package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;
import com.vildaberper.DefaultCommands.Class.DCInventoryPlayer;

public class Inventory{
	public static boolean inventory(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player){
			if(args.length == 0){
				((Player) sender).chat("/dcinventory " + ((Player) sender).getName() + " " + ((Player) sender).getWorld().getName());
				return true;
			}else if(args.length == 1){
				if(Misc.getPlayers(sender, args[0]).size() == 1){
					((Player) sender).chat("/dcinventory " + Misc.getPlayers(sender, args[0]).get(0).getName() + " " + Misc.getPlayers(sender, args[0]).get(0).getWorld().getName());
				}else{
					((Player) sender).chat("/dcinventory " + args[0] + " " + ((Player) sender).getWorld().getName());
				}
				return true;
			}else if(args.length == 2){
				if(!Perm.hasPermission((Player) sender, "dc.inventory")){
					return V.return_;
				}
				if(Misc.getPlayers((Player) sender, args[0]).size() == 1 && Misc.getPlayers(sender, args[0]).get(0).getWorld().getName().equals(args[1])){
					DCInventoryPlayer dcip = new DCInventoryPlayer(Misc.getPlayers((Player) sender, args[0]).get(0).getName(), ((CraftPlayer) Misc.getPlayers((Player) sender, args[0]).get(0)).getHandle());
					((CraftPlayer) (Player) sender).getHandle().a(dcip);
				}else{
					if(Misc.getConfig(args[1]) == null){
						Misc.sendString(sender, "invalid_world");
						return false;
					}
					if(Misc.getInventory(args[0], args[1]) == null){
						Misc.sendString(sender, "invalid_player");
						return false;
					}
					DCInventoryPlayer dcip = new DCInventoryPlayer(args[0], Misc.getInventory(args[0], args[1]).getContents());
					dcip.update = false;
					((CraftPlayer) (Player) sender).getHandle().a(dcip);

				}
				return true;
			}
		}
		return false;
	}
}