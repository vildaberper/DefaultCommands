package com.vildaberper.DefaultCommands.Command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Teleportposition implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 3 && sender instanceof Player){
			((Player) sender).chat("/dcteleportposition " + args[0] + " " + args[1] + " " + args[2] + " " + ((Player) sender).getName() + " " + ((Player) sender).getWorld().getName());
			return true;
		}else if(args.length == 4 && sender instanceof Player){
			((Player) sender).chat("/dcteleportposition " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + ((Player) sender).getWorld().getName());
			return true;
		}else if(args.length == 5){
			if(Misc.getPlayers(sender, args[3]).size() != 0){
				if(!Util.isValidDouble(args[0]) || !Util.isValidDouble(args[1]) || !Util.isValidDouble(args[2])){
					Misc.sendString(sender, "invalid_coordinate");
					return false;
				}
				if(V.plugin.getServer().getWorld(args[4]) == null){
					Misc.sendString(sender, "invalid_world");
					return false;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[3]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.teleportposition.all")){
					return V.return_;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[3]).size() == 1){
					if(Misc.getPlayers(sender, args[3]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.teleportposition.self")){
						return V.return_;
					}else if(Misc.getPlayers(sender, args[3]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.teleportposition.other")){
						return V.return_;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[3])){
					player.teleport(
							new Location(
									V.plugin.getServer().getWorld(args[4]),
									Double.parseDouble(args[0]),
									Double.parseDouble(args[1]),
									Double.parseDouble(args[2])
									)
							);
				}
				Misc.sendMessage(sender, Misc.getString("c_teleportposition").replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))).replace("<player>", Misc.getSenderName(sender)).replace("<position>", args[0] + " " + args[1] + " " + args[2] + " " + V.plugin.getServer().getWorld(args[4]).getName()));
				L.log(Misc.getString("c_teleportposition").replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))).replace("<player>", Misc.getSenderName(sender)).replace("<position>", args[0] + " " + args[1] + " " + args[2] + " " + V.plugin.getServer().getWorld(args[4]).getName()));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}