package com.vildaberper.DefaultCommands.Command;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Spawnmob implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		List<LivingEntity> entities = new LinkedList<LivingEntity>();

		if(sender instanceof Player && args.length > 0){
			if(!Util.isValidInt(args[0])){
				if(Util.getCreatureType(args[0]) != null){
					String c = "";

					for(String arg : args){
						c += " " + arg;
					}
					((Player) sender).chat("/dcspawnmob 1" + c);
					return true;
				}
				return false;
			}
			if(args.length > 1){
				if(Integer.parseInt(args[0]) < 1){
					return false;
				}
				for(int i = 1; i < args.length; i++){
					if(Util.getCreatureType(args[i]) == null){
						return false;
					}
					if(!Perm.hasPermission((Player) sender, "dc.spawnmob." + args[0].toLowerCase())){
						return false;
					}
				}
				for(int i = 0; i < Integer.parseInt(args[0]); i++){
					entities.clear();
					for(int u = 1; u < args.length; u++){
						boolean blockspawn = Misc.getConfig((Player) sender).getBoolean("block_" + Util.getCreatureType(args[u]).getName().toLowerCase() + "_spawn");

						Misc.setConfig(((Player) sender).getWorld().getName(), "block_" + Util.getCreatureType(args[u]).getName().toLowerCase() + "_spawn", false);
						entities.add(((Player) sender).getWorld().spawnCreature(Util.getSafeLocationAt(((Player) sender).getTargetBlock(null, V.targetmax)), Util.getCreatureType(args[u])));
						if(entities.size() > 1){
							entities.get(entities.size() - 2).setPassenger(entities.get(entities.size() - 1));
						}
						Misc.setConfig(((Player) sender).getWorld().getName(), "block_" + Util.getCreatureType(args[u]).getName().toLowerCase() + "_spawn", blockspawn);
					}
				}
				if(Integer.parseInt(args[0]) == 1){
					L.log(Misc.getColoredString("c_spawnmob").replace("<player>", Misc.getSenderName(sender)).replace("<entities>", Integer.parseInt(args[0]) + " entity").replace("<world>", ((Player) sender).getWorld().getName()));
					Misc.sendMessage(sender, Misc.getColoredString("c_spawnmob").replace("<player>", Misc.getSenderName(sender)).replace("<entities>", Integer.parseInt(args[0]) + " entity").replace("<world>", ((Player) sender).getWorld().getName()));
				}else{
					L.log(Misc.getColoredString("c_spawnmob").replace("<player>", Misc.getSenderName(sender)).replace("<entities>", Integer.parseInt(args[0]) + " entities").replace("<world>", ((Player) sender).getWorld().getName()));
					Misc.sendMessage(sender, Misc.getColoredString("c_spawnmob").replace("<player>", Misc.getSenderName(sender)).replace("<entities>", Integer.parseInt(args[0]) + " entities").replace("<world>", ((Player) sender).getWorld().getName()));
				}
				return true;
			}
		}
		return false;
	}
}