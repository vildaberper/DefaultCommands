package com.vildaberper.DefaultCommands.Runnable;

import org.bukkit.entity.Player;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.SaveLoad;
import com.vildaberper.DefaultCommands.V;

public class ArmorSave implements Runnable{
	@Override
	public void run(){
		for(Player player : V.plugin.getServer().getOnlinePlayers()){
			Misc.setArmor(player.getName(), player.getInventory().getArmorContents(), player.getWorld().getName());
		}
		SaveLoad.saveArmor();
	}
}