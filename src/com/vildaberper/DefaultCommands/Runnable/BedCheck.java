package com.vildaberper.DefaultCommands.Runnable;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.V;

public class BedCheck implements Runnable{
	@Override
	public void run(){
		for(World world : V.plugin.getServer().getWorlds()){
			boolean change = true;

			for(Player player : world.getPlayers()){
				if(!player.isSleeping() && !player.isSleepingIgnored()){
					change = false;
				}
			}
			if(change){
				Misc.setTime(world.getName(), 0);
			}
		}
	}
}