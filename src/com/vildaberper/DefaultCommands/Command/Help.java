package com.vildaberper.DefaultCommands.Command;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Help{
	@SuppressWarnings("unchecked")
	public static boolean help(CommandSender sender, Command command, String label, String[] args){
		List<String> commands = new LinkedList<String>();
		int page = 1, max = 0;

		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.help")){
			return V.return_;
		}
		if(args.length < 2){
			for(Plugin plugin : V.plugin.getServer().getPluginManager().getPlugins()){
				if(plugin != null && plugin.getDescription() != null && plugin.getDescription().getCommands() != null){
					for(Object object : ((LinkedHashMap<String, Object>) plugin.getDescription().getCommands()).keySet()){
						if((String) object != null && ((String) object).length() > 0){
							commands.add((String) object);
						}
					}
				}
			}
			if(args.length == 1){
				if(Util.isValidInt(args[0]) && Integer.parseInt(args[0]) > 0){
					page = Integer.parseInt(args[0]);
				}else{
					if(V.plugin.getServer().getPluginCommand(args[0]) != null){
						sender.sendMessage(V.plugin.getServer().getPluginCommand(args[0]).getUsage().replace("<command>", args[0]));
						if(V.plugin.getServer().getPluginCommand(args[0]).getDescription() != null && V.plugin.getServer().getPluginCommand(args[0]).getDescription().length() > 0){
							sender.sendMessage(V.plugin.getServer().getPluginCommand(args[0]).getDescription());
						}
						return true;
					}
					return false;
				}
			}
			max = Math.round(commands.size() / V.getInt("per_page"));
			if(commands.size() > max * V.getInt("per_page")){
				max += 1;
			}
			if(page > max){
				Misc.sendString(sender, "invalid_page");
				return false;
			}
			sender.sendMessage("Page " + page + " of " + max + ":");
			for(int i = (page - 1) * V.getInt("per_page"); i < (page - 1) * V.getInt("per_page") + V.getInt("per_page") && i < commands.size(); i++){
				String usage = V.plugin.getServer().getPluginCommand(commands.get(i)).getUsage().replace("<command>", commands.get(i));

				if(usage.length() > 60){
					usage = usage.substring(0, 57) + "...";
				}
				sender.sendMessage(usage);
			}
			return true;
		}
		return false;
	}
}