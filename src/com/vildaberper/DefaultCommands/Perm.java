package com.vildaberper.DefaultCommands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;

import com.platymuus.bukkit.permissions.Group;
import com.platymuus.bukkit.permissions.PermissionsPlugin;

public class Perm{
	public static List<Group> getGroups(String player){
		if(V.plugin.getServer().getPluginManager().getPlugin("PermissionsBukkit") != null && ((PermissionsPlugin) V.plugin.getServer().getPluginManager().getPlugin("PermissionsBukkit")).getPlayerInfo(player) != null){
			return ((PermissionsPlugin) V.plugin.getServer().getPluginManager().getPlugin("PermissionsBukkit")).getPlayerInfo(player).getGroups();
		}
		return null;
	}

	public static List<String> getGroupsString(String player){
		List<Group> groups = getGroups(player);

		if(groups != null){
			List<String> groupsstring = new LinkedList<String>();

			for(Group group : groups){
				groupsstring.add(group.getName());
			}
			return groupsstring;
		}
		return null;
	}

	public static void clearPermissions(String world){
		Misc.getConfig(world).getStringList("permissions").clear();
	}

	public static List<String> getPermissions(String world){
		return Misc.getConfig(world).getStringList("permissions");
	}

	public static String getPermission(String world, int index){
		return Misc.getConfig(world).getStringList("permissions").get(index);
	}

	public static void setPermission(String world, String permission){
		Misc.getConfig(world).getStringList("permissions").add(permission);
	}

	public static int getSize(String world){
		return Misc.getConfig(world).getStringList("permissions").size();
	}

	public static boolean hasPermission(Player player, String node){
		boolean permission = hasPermissionSilent(player, node);

		if(!permission){
			player.sendMessage(Misc.getColoredString("not_permission"));
		}
		return permission;
	}

	public static boolean hasPermissionSilent(Player player, String node){
		if(Misc.getConfig(player.getWorld().getName()).getBoolean("op_permissions")){
			if(player.isOp()){
				return true;
			}
			for(int i = 0; i < getSize(player.getWorld().getName()); i++){
				if(getPermission(player.getWorld().getName(), i).equals(node)){
					return true;
				}
				if(getPermission(player.getWorld().getName(), i).equals("*")){
					return true;
				}
				for(int y = 0; y < node.length(); y++){
					if(node.charAt(y) == '.'){
						if(getPermission(player.getWorld().getName(), i).equals(node.substring(0, y) + ".*")){
							return true;
						}
					}
				}
			}
		}else{
			if(player.hasPermission(node)){
				return true;
			}
			if(player.hasPermission("*")){
				return true;
			}
			for(int y = 0; y < node.length(); y++){
				if(node.charAt(y) == '.'){
					if(player.hasPermission(node.substring(0, y) + ".*")){
						return true;
					}
				}
			}
		}
		return false;
	}
}
