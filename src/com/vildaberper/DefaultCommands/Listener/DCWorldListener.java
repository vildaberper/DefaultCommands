package com.vildaberper.DefaultCommands.Listener;

import org.bukkit.event.world.WorldListener;
import org.bukkit.event.world.WorldLoadEvent;

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
}