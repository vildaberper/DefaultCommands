package com.vildaberper.DefaultCommands.Listener;

import org.bukkit.event.world.WorldListener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.V;
import com.vildaberper.DefaultCommands.Class.DCWorld;

public class DCWorldListener extends WorldListener{
	@Override
	public void onWorldLoad(WorldLoadEvent event){
		if(Misc.getConfig(event.getWorld()) == null){
			V.worlds.add(new DCWorld(event.getWorld().getName(), event.getWorld().getEnvironment(), event.getWorld().getSeed()));
		}
	}

	@Override
	public void onWorldSave(WorldSaveEvent event){
		/*final Player[] players = V.plugin.getServer().getOnlinePlayers();

		V.plugin.getServer().getScheduler().scheduleAsyncDelayedTask(
				V.plugin,
				new Runnable(){
					@Override
					public void run(){
						for(Player player : players){
							Misc.setInventory(player.getName(), player.getInventory().getContents(), player.getWorld().getName());
							Misc.setArmor(player.getName(), player.getInventory().getArmorContents(), player.getWorld().getName());
						}
						SaveLoad.saveAll();
					}
				}
				);*/
	}
}