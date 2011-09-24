package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Ping implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		sender.sendMessage("Pong");
		return true;
	}
}