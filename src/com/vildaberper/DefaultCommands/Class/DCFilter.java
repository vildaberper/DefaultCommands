package com.vildaberper.DefaultCommands.Class;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.V;

public class DCFilter implements Filter{
	@Override
	public boolean isLoggable(LogRecord arg0){
		if(arg0.getMessage() != null){
			if(V.block_cant_keep_up && arg0.getMessage().equals("Can't keep up! Did the system time change, or is the server overloaded?")){
				return false;
			}
			if(arg0.getMessage().endsWith(" was kicked for floating too long!")){
				if(V.plugin.getServer().getPlayer(arg0.getMessage().split(" ")[0]) != null
						&& Perm.hasPermission(
								V.plugin.getServer().getPlayer(arg0.getMessage().split(" ")[0]),
								"dc.allowflight." + V.plugin.getServer().getPlayer(arg0.getMessage().split(" ")[0]).getWorld().getName()
								)){
					return false;
				}
			}
		}
		return true;
	}
}