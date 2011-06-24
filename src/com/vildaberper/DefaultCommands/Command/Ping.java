package com.vildaberper.DefaultCommands.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Ping{
	public static boolean ping(CommandSender sender, Command command, String label, String[] args){
		sender.sendMessage("Pong");
		return true;
	}
}