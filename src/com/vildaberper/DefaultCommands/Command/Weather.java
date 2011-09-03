package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Weather{
	public static boolean weather(CommandSender sender, Command command, String label, final String[] args){
		if(args.length == 1 && sender instanceof Player){
			((Player) sender).chat("/dcweather " + args[0] + " 120 " + ((Player) sender).getWorld().getName());
			return true;
		}else if(args.length == 2 && sender instanceof Player){
			((Player) sender).chat("/dcweather " + args[0] + " " + args[1] + " " + ((Player) sender).getWorld().getName());
			return true;
		}else if(args.length == 3){
			if(V.plugin.getServer().getWorld(args[2]) != null){
				final boolean weatherchange = Misc.getConfig(args[2]).getBoolean("weather_change");

				if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.weather." + V.plugin.getServer().getWorld(args[2]).getName())){
					return V.return_;
				}
				if(Util.isValidInt(args[1]) && Integer.parseInt(args[1]) > 0){
					Misc.setConfig(args[2], "weather_change", true);
					if(args[0].equalsIgnoreCase("rain")){
						V.plugin.getServer().getWorld(args[2]).setStorm(true);
						V.plugin.getServer().getWorld(args[2]).setThundering(false);
						V.plugin.getServer().getWorld(args[2]).setWeatherDuration(20 * Integer.parseInt(args[1]));
						Misc.setConfig(args[2], "weather_change", weatherchange);
					}else if(args[0].equalsIgnoreCase("storm") || args[0].equalsIgnoreCase("thunder")){
						V.plugin.getServer().getWorld(args[2]).setStorm(true);
						V.plugin.getServer().getWorld(args[2]).setThundering(true);
						V.plugin.getServer().getWorld(args[2]).setThunderDuration(20 * Integer.parseInt(args[1]));
						Misc.setConfig(args[2], "weather_change", weatherchange);
					}else if(args[0].equalsIgnoreCase("none")){
						V.plugin.getServer().getWorld(args[2]).setStorm(true);
						V.plugin.getServer().getWorld(args[2]).setThundering(true);
						V.plugin.getServer().getScheduler().scheduleSyncDelayedTask(
								V.plugin,
								new Runnable(){
									@Override
									public void run(){
										V.plugin.getServer().getWorld(args[2]).setStorm(false);
										V.plugin.getServer().getWorld(args[2]).setThundering(false);
										Misc.setConfig(args[2], "weather_change", weatherchange);
									}
								},
								20
						);
					}else{
						Misc.sendString(sender, "invalid_weather");
						return false;
					}
					Misc.sendMessage(sender, Misc.getColoredString("c_weather").replace("<player>", Misc.getSenderName(sender)).replace("<world>", V.plugin.getServer().getWorld(args[2]).getName()).replace("<weather>", args[0].toLowerCase()));
					L.log(Misc.getColoredString("c_weather").replace("<player>", Misc.getSenderName(sender)).replace("<world>", V.plugin.getServer().getWorld(args[2]).getName()).replace("<weather>", args[0].toLowerCase()));
					return true;
				}else{
					Misc.sendString(sender, "invalid_duration");
				}
			}else{
				Misc.sendString(sender, "invalid_world");
			}
		}
		return false;
	}
}