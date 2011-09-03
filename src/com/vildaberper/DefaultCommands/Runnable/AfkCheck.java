package com.vildaberper.DefaultCommands.Runnable;

import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.L;
import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;

public class AfkCheck implements Runnable{
	@Override
	public void run(){
		for(Player player : V.plugin.getServer().getOnlinePlayers()){
			Misc.getAfkPlayer(player.getEntityId()).addTime();
			if(!Misc.isAfk(player.getEntityId())){
				if(
						Util.getDistanceIgnoreY(player.getLocation(), Misc.getAfkPlayer(player.getEntityId()).getLocation()) <= V.afk_move_min * 4
						&& player.getLocation().getYaw() == Misc.getAfkPlayer(player.getEntityId()).getLocation().getYaw()
						&& player.getLocation().getPitch() == Misc.getAfkPlayer(player.getEntityId()).getLocation().getPitch()
						&& Perm.hasPermissionSilent(player, "dc.afk.self")
						){
					if(Misc.getAfkPlayer(player.getEntityId()).getTime() >= V.getInt("afk_time")){
						Misc.setAfk(player.getEntityId(), true);
						L.log(Misc.getSenderCmdMsg("c_afk", player, Misc.getPlayers(player, player.getName()), Misc.isAfk(Misc.getPlayers(player, player.getName()).get(0).getEntityId())));
						Misc.sendMessage(player, Misc.getSenderCmdMsg("c_afk", player, Misc.getPlayers(player, player.getName()), Misc.isAfk(Misc.getPlayers(player, player.getName()).get(0).getEntityId())));
					}
					if(Misc.getAfkPlayer(player.getEntityId()).getTime() >= V.getInt("afk_kick_time")){
						player.kickPlayer(Misc.getColoredString("afk_kick"));
					}
				}else{
					Misc.getAfkPlayer(player.getEntityId()).resetTime();
				}
			}
			Misc.getAfkPlayer(player.getEntityId()).setLocation(player.getLocation());
			if(Misc.getAfkPlayer(player.getEntityId()).getTime() >= V.getInt("afk_kick_time")){
				player.kickPlayer(Misc.getColoredString("afk_kick"));
			}
		}
	}
}