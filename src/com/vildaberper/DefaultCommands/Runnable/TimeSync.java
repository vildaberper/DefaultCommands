package com.vildaberper.DefaultCommands.Runnable;

import org.bukkit.World;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.V;
import com.vildaberper.DefaultCommands.Class.DCWorld;

public class TimeSync implements Runnable{
	@Override
	public void run() {
		World world = null;

		for(DCWorld dcworld : V.worlds){
			if(world == null && dcworld.getBoolean("sync_time")){
				world = V.plugin.getServer().getWorld(dcworld.getName());
			}
			if(world != null){
				for(DCWorld dcw : V.worlds){
					if(!dcw.equals(Misc.getConfig(world)) && dcw.getBoolean("sync_time")){
						V.plugin.getServer().getWorld(dcw.getName()).setTime(world.getTime());
					}
				}
				return;
			}
		}
	}
}