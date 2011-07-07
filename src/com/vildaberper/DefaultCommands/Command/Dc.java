package com.vildaberper.DefaultCommands.Command;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.FileManager;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Reset;
import com.vildaberper.DefaultCommands.SaveLoad;
import com.vildaberper.DefaultCommands.V;
import com.vildaberper.DefaultCommands.Class.DCHome;
import com.vildaberper.DefaultCommands.Class.DCPlayer;
import com.vildaberper.DefaultCommands.Class.DCString;
import com.vildaberper.DefaultCommands.Class.DCWarp;
import com.vildaberper.DefaultCommands.Class.DCWorld;

public class Dc{
	@SuppressWarnings("unchecked")
	public static boolean dc(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.dc")){
			return false;
		}
		if(args.length == 0){
			sender.sendMessage("load/save/reload/reset/get/check/convert/update/updatedev");
		}else if(args.length == 1){
			if(args[0].equalsIgnoreCase("load")){
				SaveLoad.loadAll();
				sender.sendMessage("Loaded.");
				return true;
			}else if(args[0].equalsIgnoreCase("save")){
				SaveLoad.saveAll();
				sender.sendMessage("Saved.");
				return true;
			}else if(args[0].equalsIgnoreCase("reload")){
				SaveLoad.loadAll();
				SaveLoad.saveAll();
				sender.sendMessage("Reloaded.");
				return true;
			}else if(args[0].equalsIgnoreCase("get")){
				int count = 0;

				for(World world : V.plugin.getServer().getWorlds()){
					if(Misc.getConfig(world) == null){
						count++;
						Misc.addWorld(world.getName(), world.getEnvironment());
					}
				}
				sender.sendMessage("Found " + count + " worlds.");
				return true;
			}else if(args[0].equalsIgnoreCase("reset")){
				sender.sendMessage("homes/warps/names/players/config/commands/strings/items/kits/portals/whitelist/bans");
			}else if(args[0].equalsIgnoreCase("check")){
				String latest = Misc.getLatestVersion(), current = V.plugin.getDescription().getVersion();
				if(current.equals(latest)){
					sender.sendMessage("There is no new update availiable (" + current + ").");
				}else{
					sender.sendMessage("There is an update availiable (" + latest + "), currently running (" + current + "). Type '/dc update' to update (the server will reload).");
				}
				return true;
			}else if(args[0].equalsIgnoreCase("update")){
				String latest = Misc.getLatestVersion(), current = V.plugin.getDescription().getVersion();
				if(current.equals(latest)){
					sender.sendMessage("There is no new update availiable (" + current + ").");
				}else{
					V.plugin.getServer().getPluginManager().disablePlugin(V.plugin);
					FileManager.downloadFile("http://dl.dropbox.com/u/19411765/Release/DefaultCommands/Latest/DefaultCommands.jar", new File(new File("plugins"), "DefaultCommands.jar"));
					sender.sendMessage("Successfully updated to the latest version (" + latest + "). Reloading server.");
					V.plugin.getServer().reload();
				}
				return true;
			}else if(args[0].equalsIgnoreCase("updatedev")){
				V.plugin.getServer().getPluginManager().disablePlugin(V.plugin);
				FileManager.downloadFile("http://dl.dropbox.com/u/19411765/Release/DefaultCommands/Development/DefaultCommands.jar", new File(new File("plugins"), "DefaultCommands.jar"));
				sender.sendMessage("Successfully updated to the latest development build. Reloading server.");
				V.plugin.getServer().reload();
				return true;
			}else if(args[0].equalsIgnoreCase("convert")){
				sender.sendMessage("MyWarp");
			}else{
				sender.sendMessage("load/save/reload/reset/get/check/convert/update/updatedev");
			}
		}else if(args.length == 2){
			if(args[0].equalsIgnoreCase("reset")){
				if(args[1].equalsIgnoreCase("homes")){
					V.homes = new LinkedList<DCHome>();
					sender.sendMessage("Resetted homes.");
					return true;
				}else if(args[1].equalsIgnoreCase("warps")){
					V.warps = new LinkedList<DCWarp>();
					sender.sendMessage("Resetted warps.");
					return true;
				}else if(args[1].equalsIgnoreCase("names")){
					for(Player player : V.plugin.getServer().getOnlinePlayers()){
						player.setDisplayName(player.getName());
					}
					V.names = new LinkedList<DCString>();
					sender.sendMessage("Resetted names.");
					return true;
				}else if(args[1].equalsIgnoreCase("players")){
					V.players = new LinkedList<DCPlayer>();
					sender.sendMessage("Resetted players.");
					return true;
				}else if(args[1].equalsIgnoreCase("config")){
					for(DCWorld dcworld : V.worlds){
						dcworld.setConfiguration(Reset.resetDCConfiguration(dcworld.getName()));
						Perm.setupPermissions(dcworld.getName());
					}
					sender.sendMessage("Resetted config.");
					return true;
				}else if(args[1].equalsIgnoreCase("commands")){
					Reset.resetCommands();
					sender.sendMessage("Resetted commands.");
					return true;
				}else if(args[1].equalsIgnoreCase("strings")){
					Reset.resetStrings();
					sender.sendMessage("Resetted strings.");
					return true;
				}else if(args[1].equalsIgnoreCase("items")){
					Reset.resetItems();
					sender.sendMessage("Resetted items.");
					return true;
				}else if(args[1].equalsIgnoreCase("kits")){
					Reset.resetKits();
					sender.sendMessage("Resetted kits.");
					return true;
				}else if(args[1].equalsIgnoreCase("portals")){
					V.portals.clear();
					sender.sendMessage("Resetted portals.");
					return true;
				}else if(args[1].equalsIgnoreCase("whitelist")){
					V.whitelist_.clear();
					sender.sendMessage("Resetted whitelist.");
					return true;
				}else if(args[1].equalsIgnoreCase("bans")){
					V.bans.clear();
					sender.sendMessage("Resetted bans.");
					return true;
				}else{
					sender.sendMessage("homes/warps/names/players/config/commands/strings/items/kits/portals/whitelist/bans");
				}
			}else if(args[0].equalsIgnoreCase("convert")){
				if(args[1].equalsIgnoreCase("MyWarp")){
					if(V.plugin.getServer().getPluginManager().getPlugin("MyWarp") == null){
						sender.sendMessage("Could not find plugin MyWarp!");
					}else{
						try{
							HashMap<String, me.taylorkelly.mywarp.Warp> warps = new HashMap<String, me.taylorkelly.mywarp.Warp>();
							Method m;
							int count = 0;
							String s = "s";

							m = me.taylorkelly.mywarp.WarpDataSource.class.getDeclaredMethod("getMap");
							m.setAccessible(true);
							warps = (HashMap<String, me.taylorkelly.mywarp.Warp>) m.invoke(warps);
							for(String name : warps.keySet()){
								count++;
								Misc.setWarp(name,
										new Location(
												V.plugin.getServer().getWorld(warps.get(name).world),
												warps.get(name).x,
												warps.get(name).y,
												warps.get(name).z,
												warps.get(name).yaw,
												warps.get(name).pitch
										)
								);
							}
							if(count == 1){
								s = "";
							}
							sender.sendMessage("Converted " + count + " warp" + s + ".");
							return true;
						}catch(Exception e){
							sender.sendMessage("Failed to convert warps from MyWarp!");
						}
					}
				}else{
					sender.sendMessage("MyWarp");
				}
			}
		}
		return false;
	}
}