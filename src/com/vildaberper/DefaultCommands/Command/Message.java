package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class Message{
	public static boolean message(CommandSender sender, Command command, String label, String[] args){
		if(args.length >= 2){
			if(Misc.getPlayers(sender, args[0]).size() != 0){
				String message = args[1];

				for(int i = 2; i < args.length; i++){
					message += " " + args[i];
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() != 1 && !Perm.hasPermission((Player) sender, "dc.message.all")){
					return V.return_;
				}
				if(sender instanceof Player && Misc.getPlayers(sender, args[0]).size() == 1){
					if(Misc.getPlayers(sender, args[0]).get(0) == (Player) sender && !Perm.hasPermission((Player) sender, "dc.message.self")){
						return V.return_;
					}else if(Misc.getPlayers(sender, args[0]).get(0) != (Player) sender && !Perm.hasPermission((Player) sender, "dc.message.other")){
						return V.return_;
					}
				}
				for(Player player : Misc.getPlayers(sender, args[0])){
					if(V.getBoolean("play_message_sound")){
						Misc.playMessageSound(player);
					}
					Misc.sendMessage(player, Misc.getString("message_recieved").replace("<player>", Misc.getSenderName(sender)).replace("<message>", message));
					Misc.setReply(((Player) sender).getName(), player.getName());
				}
				Misc.sendMessage(sender, Misc.getString("message_sent").replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))).replace("<message>", message));
				L.log(Misc.getString("c_message").replace("<player>", Misc.getSenderName(sender)).replace("<players>", Misc.getPlayerNames(Misc.getPlayers(sender, args[0]))).replace("<message>", message));
				return true;
			}else{
				Misc.sendString(sender, "invalid_player");
			}
		}
		return false;
	}
}