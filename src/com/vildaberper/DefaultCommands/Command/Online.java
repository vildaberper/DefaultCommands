package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Online implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(args.length == 0){
			if(sender instanceof Player && !Perm.hasPermission((Player) sender, "dc.online")){
				return V.return_;
			}
			Misc.sendMessage(
					sender,
					Misc.getColoredString("online")
					.replace("<online>", Integer.toString(Misc.getPlayers(sender, V.getString("all")).size()))
					.replace("<max>", Integer.toString(V.plugin.getServer().getMaxPlayers()))
					.replace("<players>", Misc.getPlayerList(sender).toString().substring(1, Misc.getPlayerList(sender).toString().length() - 1)).replace(",", "&2,")
					);
			return true;
		}
		return false;
	}
}