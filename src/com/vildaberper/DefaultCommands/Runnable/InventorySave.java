package com.vildaberper.DefaultCommands.Runnable;

import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.SaveLoad;
import com.vildaberper.DefaultCommands.V;

public class InventorySave implements Runnable{
	@Override
	public void run(){
		for(Player player : V.plugin.getServer().getOnlinePlayers()){
			Misc.setInventory(player.getName(), player.getInventory().getContents(), player.getWorld().getName());
		}
		SaveLoad.saveInventory();
	}
}