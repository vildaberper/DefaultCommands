package com.vildaberper.DefaultCommands.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class Who implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.who")){
			return V.return_;
		}
		if(args.length == 0 && sender instanceof Player){
			((Player) sender).chat("/dcwho " + ((Player) sender).getName());
			return true;
		}else if(args.length == 1){
			if(Misc.getPlayer(args[0]) != null){
				String name = Misc.getPlayer(args[0]).getName();
				String send = "";

				if(!Misc.getName(Misc.getPlayer(args[0]).getName()).equals(Misc.getPlayer(args[0]).getName())){
					name += "&2 (&7" + Misc.getName(Misc.getPlayer(args[0]).getName()) + "&2)";
				}
				if(V.plugin.getServer().getPlayer(args[0]) != null && (!(sender instanceof Player) || Perm.hasPermissionSilent((Player) sender, "dc.hide.see") || V.plugin.getServer().getPlayer(args[0]).equals(sender) || !Misc.isHide(V.plugin.getServer().getPlayer(args[0]).getName()))){
					String hp = "";

					for(int i = 0; i < V.plugin.getServer().getPlayer(args[0]).getHealth(); i++){
						hp += "|";
					}
					hp += ChatColor.DARK_GRAY;
					for(int i = 0; i < 20 - V.plugin.getServer().getPlayer(args[0]).getHealth(); i++){
						hp += "|";
					}
					if(V.plugin.getServer().getPlayer(args[0]).getHealth() > 10){
						hp = ChatColor.GREEN + hp;
					}else if(V.plugin.getServer().getPlayer(args[0]).getHealth() <= 10 && V.plugin.getServer().getPlayer(args[0]).getHealth() > 5){
						hp = ChatColor.GOLD + hp;
					}else if(V.plugin.getServer().getPlayer(args[0]).getHealth() <= 5){
						hp = ChatColor.RED + hp;
					}
					send = Misc.getColoredString("who_online")
							.replace("<player>", name)
							.replace("<hp>", hp)
							.replace("<ip>", Misc.getPlayer(args[0]).getIp())
							.replace("<experience>", Integer.toString(V.plugin.getServer().getPlayer(args[0]).getExperience()))
							.replace("<world>", V.plugin.getServer().getPlayer(args[0]).getWorld().getName())
							.replace("<x>", Double.toString(Math.round(V.plugin.getServer().getPlayer(args[0]).getLocation().getX())))
							.replace("<y>", Double.toString(Math.round(V.plugin.getServer().getPlayer(args[0]).getLocation().getY())))
							.replace("<z>", Double.toString(Math.round(V.plugin.getServer().getPlayer(args[0]).getLocation().getZ())));
					if(Perm.getGroupsString(name) != null){
						send = send
								.replace("<group>", Perm.getGroupsString(name).toString().substring(1, Perm.getGroupsString(name).toString().length() - 1));
					}else{
						send = send
								.replace("<group>", "");
					}
					Misc. sendMessage(
							sender,
							Util.replaceColor(send)
							);
					return true;
				}else{
					send = Misc.getColoredString("who_offline")
							.replace("<player>", name)
							.replace("<ip>", Misc.getPlayer(args[0]).getIp())
							.replace("<date>", Misc.getPlayer(args[0]).getDate())
							.replace("<time>", Misc.getPlayer(args[0]).getTime());
					if(Perm.getGroupsString(name) != null){
						send = send
								.replace("<group>", Perm.getGroupsString(name).toString().substring(1, Perm.getGroupsString(name).toString().length() - 1));
					}else{
						send = send
								.replace("<group>", "");
					}
					Misc. sendMessage(
							sender,
							Util.replaceColor(send)
							);
					return true;
				}
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}