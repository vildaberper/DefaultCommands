package com.vildaberper.DefaultCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

import com.vildaberper.DefaultCommands.Class.DCAfkPlayer;
import com.vildaberper.DefaultCommands.Class.DCFilter;
import com.vildaberper.DefaultCommands.Class.DCPlayer;
import com.vildaberper.DefaultCommands.Listener.DCBlockListener;
import com.vildaberper.DefaultCommands.Listener.DCEntityListener;
import com.vildaberper.DefaultCommands.Listener.DCPlayerListener;
import com.vildaberper.DefaultCommands.Listener.DCVehicleListener;
import com.vildaberper.DefaultCommands.Listener.DCWeatherListener;
import com.vildaberper.DefaultCommands.Listener.DCWorldListener;
import com.vildaberper.DefaultCommands.Runnable.AfkCheck;

public class DefaultCommands extends JavaPlugin{
	private final DCBlockListener blockListener = new DCBlockListener();
	private final DCWorldListener worldListener = new DCWorldListener();
	private final DCPlayerListener playerListener = new DCPlayerListener();
	private final DCEntityListener entityListener = new DCEntityListener();
	private final DCWeatherListener weatherListener = new DCWeatherListener();
	private final DCVehicleListener vehicleListener = new DCVehicleListener();

	@Override
	public void onDisable(){
		for(Player player : getServer().getOnlinePlayers()){
			if(!Misc.isHide(player.getName())){
				Misc.setPlayer(new DCPlayer(player.getName(), Util.getIp(player), Util.getDateTime()));
			}
			Misc.setInventory(player.getName(), player.getInventory().getContents(), player.getWorld().getName());
			Misc.setArmor(player.getName(), player.getInventory().getArmorContents(), player.getWorld().getName());
		}
		SaveLoad.saveAll();

		// Task
		V.plugin.getServer().getScheduler().cancelTasks(this);

		Reset.resetNopickup();
		L.log("Disabled " + getDescription().getName() + " " + getDescription().getVersion() + ".");
		System.out.println(getDescription().getName() + " " + getDescription().getVersion() + " is disabled.");
	}

	@Override
	public void onEnable(){
		L.initializeLog();
		SaveLoad.loadAll();

		for(Player player : getServer().getOnlinePlayers()){
			Misc.setAfkPlayer(player.getEntityId(), new DCAfkPlayer(player.getEntityId(), player.getLocation()));
		}

		getServer().getLogger().setFilter(new DCFilter());

		getCommand("dcgive").setUsage("/<command> " + V.getString("give").replace("item", "<item>[:data]").replace("amount", "[amount]").replace("target", "[player]"));

		// VehicleListener
		getServer().getPluginManager().registerEvent(Type.VEHICLE_MOVE, vehicleListener, Priority.High, this);

		// WeatherListener
		getServer().getPluginManager().registerEvent(Type.WEATHER_CHANGE, weatherListener, Priority.High, this);

		// WorldListener
		getServer().getPluginManager().registerEvent(Type.WORLD_LOAD, worldListener, Priority.Monitor, this);
		getServer().getPluginManager().registerEvent(Type.WORLD_SAVE, worldListener, Priority.Monitor, this);

		// BlockListener
		getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, blockListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, blockListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_DAMAGE, blockListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_IGNITE, blockListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.LEAVES_DECAY, blockListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_PHYSICS, blockListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_CANBUILD, blockListener, Priority.High, this);

		// EntityListener
		getServer().getPluginManager().registerEvent(Type.ENTITY_DEATH, entityListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.ENTITY_TARGET, entityListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.ENTITY_DAMAGE, entityListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.CREATURE_SPAWN, entityListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.ENTITY_EXPLODE, entityListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.ENDERMAN_PICKUP, entityListener, Priority.High, this);


		// PlayerListener
		getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_KICK, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_LOGIN, playerListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_BED_ENTER, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_DROP_ITEM, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_TELEPORT, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_PICKUP_ITEM, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_TOGGLE_SNEAK, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT_ENTITY, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Lowest, this);

		// Task
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new AfkCheck(), 20 * 60, 20 * 60);

		L.log("Enabled " + getDescription().getName() + " " + getDescription().getVersion() + ".");
		System.out.println(getDescription().getName() + " " + getDescription().getVersion() + " is enabled.");
	}

	@Override
	public void onLoad(){
		V.plugin = this;
	}

	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args){
		try{
			String c = command.getName();

			if(!c.equalsIgnoreCase("dc")){
				c = c.substring(2);
			}
			return ((CommandExecutor) Class.forName("com.vildaberper.DefaultCommands.Command." +c.toUpperCase().charAt(0) + c.toLowerCase().substring(1)).getConstructor().newInstance()).onCommand(sender, command, label, args);
		}catch(Exception e){
			sender.sendMessage("Something went wrong (" + e.getClass() + "): " + e.getMessage());
		}
		return false;
	}
}