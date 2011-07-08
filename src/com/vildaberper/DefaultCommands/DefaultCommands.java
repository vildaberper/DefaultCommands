package com.vildaberper.DefaultCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

import com.vildaberper.DefaultCommands.Class.DCAfkPlayer;
import com.vildaberper.DefaultCommands.Class.DCPlayer;
import com.vildaberper.DefaultCommands.Class.DCWorld;
import com.vildaberper.DefaultCommands.Command.Afk;
import com.vildaberper.DefaultCommands.Command.Ban;
import com.vildaberper.DefaultCommands.Command.Bans;
import com.vildaberper.DefaultCommands.Command.Clearinventory;
import com.vildaberper.DefaultCommands.Command.Clearnear;
import com.vildaberper.DefaultCommands.Command.Create;
import com.vildaberper.DefaultCommands.Command.Creative;
import com.vildaberper.DefaultCommands.Command.Cuboid;
import com.vildaberper.DefaultCommands.Command.Cuboidhollow;
import com.vildaberper.DefaultCommands.Command.Cuboidwireframe;
import com.vildaberper.DefaultCommands.Command.Dc;
import com.vildaberper.DefaultCommands.Command.Delportal;
import com.vildaberper.DefaultCommands.Command.Delwarp;
import com.vildaberper.DefaultCommands.Command.Delworld;
import com.vildaberper.DefaultCommands.Command.Disconnect;
import com.vildaberper.DefaultCommands.Command.Extinguish;
import com.vildaberper.DefaultCommands.Command.Fire;
import com.vildaberper.DefaultCommands.Command.Fly;
import com.vildaberper.DefaultCommands.Command.Freeze;
import com.vildaberper.DefaultCommands.Command.Give;
import com.vildaberper.DefaultCommands.Command.God;
import com.vildaberper.DefaultCommands.Command.Health;
import com.vildaberper.DefaultCommands.Command.Help;
import com.vildaberper.DefaultCommands.Command.Hide;
import com.vildaberper.DefaultCommands.Command.Home;
import com.vildaberper.DefaultCommands.Command.Ignoremob;
import com.vildaberper.DefaultCommands.Command.Instakill;
import com.vildaberper.DefaultCommands.Command.Inventory;
import com.vildaberper.DefaultCommands.Command.Join;
import com.vildaberper.DefaultCommands.Command.Jump;
import com.vildaberper.DefaultCommands.Command.Kick;
import com.vildaberper.DefaultCommands.Command.Kill;
import com.vildaberper.DefaultCommands.Command.Killnear;
import com.vildaberper.DefaultCommands.Command.Kit;
import com.vildaberper.DefaultCommands.Command.Kits;
import com.vildaberper.DefaultCommands.Command.Lightning;
import com.vildaberper.DefaultCommands.Command.Message;
import com.vildaberper.DefaultCommands.Command.Motd;
import com.vildaberper.DefaultCommands.Command.Mute;
import com.vildaberper.DefaultCommands.Command.Name;
import com.vildaberper.DefaultCommands.Command.Nopickup;
import com.vildaberper.DefaultCommands.Command.Online;
import com.vildaberper.DefaultCommands.Command.Ping;
import com.vildaberper.DefaultCommands.Command.Portals;
import com.vildaberper.DefaultCommands.Command.Removefloat;
import com.vildaberper.DefaultCommands.Command.Repair;
import com.vildaberper.DefaultCommands.Command.Repeat;
import com.vildaberper.DefaultCommands.Command.Replace;
import com.vildaberper.DefaultCommands.Command.Reply;
import com.vildaberper.DefaultCommands.Command.Sethome;
import com.vildaberper.DefaultCommands.Command.Setportal;
import com.vildaberper.DefaultCommands.Command.Setspawn;
import com.vildaberper.DefaultCommands.Command.Setwarp;
import com.vildaberper.DefaultCommands.Command.Spawn;
import com.vildaberper.DefaultCommands.Command.Spawnmob;
import com.vildaberper.DefaultCommands.Command.Spherehollow;
import com.vildaberper.DefaultCommands.Command.Stack;
import com.vildaberper.DefaultCommands.Command.Teleport;
import com.vildaberper.DefaultCommands.Command.Teleporthere;
import com.vildaberper.DefaultCommands.Command.Teleportposition;
import com.vildaberper.DefaultCommands.Command.Time;
import com.vildaberper.DefaultCommands.Command.Top;
import com.vildaberper.DefaultCommands.Command.Unban;
import com.vildaberper.DefaultCommands.Command.Undo;
import com.vildaberper.DefaultCommands.Command.Walls;
import com.vildaberper.DefaultCommands.Command.Warp;
import com.vildaberper.DefaultCommands.Command.Warps;
import com.vildaberper.DefaultCommands.Command.Weather;
import com.vildaberper.DefaultCommands.Command.Whitelist;
import com.vildaberper.DefaultCommands.Command.Whitelists;
import com.vildaberper.DefaultCommands.Command.Who;
import com.vildaberper.DefaultCommands.Command.Worlds;
import com.vildaberper.DefaultCommands.Listener.DCBlockListener;
import com.vildaberper.DefaultCommands.Listener.DCEntityListener;
import com.vildaberper.DefaultCommands.Listener.DCPlayerListener;
import com.vildaberper.DefaultCommands.Listener.DCVehicleListener;
import com.vildaberper.DefaultCommands.Listener.DCWeatherListener;
import com.vildaberper.DefaultCommands.Listener.DCWorldListener;
import com.vildaberper.DefaultCommands.Runnable.AfkCheck;
import com.vildaberper.DefaultCommands.Runnable.ArmorSave;
import com.vildaberper.DefaultCommands.Runnable.ConfigSave;
import com.vildaberper.DefaultCommands.Runnable.InventorySave;

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
		for(DCWorld dcworld : V.worlds){
			Perm.setupPermissions(dcworld.getName());
		}

		for(Player player : getServer().getOnlinePlayers()){
			Misc.setAfkPlayer(player.getEntityId(), new DCAfkPlayer(player.getEntityId(), player.getLocation()));
		}

		getCommand("dcgive").setUsage("/<command> " + V.give.replace("item", "<item>[:data]").replace("amount", "[amount]").replace("target", "[player]"));

		// VehicleListener
		getServer().getPluginManager().registerEvent(Type.VEHICLE_MOVE, vehicleListener, Priority.High, this);

		// WeatherListener
		getServer().getPluginManager().registerEvent(Type.WEATHER_CHANGE, weatherListener, Priority.High, this);

		// WorldListener
		getServer().getPluginManager().registerEvent(Type.WORLD_LOAD, worldListener, Priority.Monitor, this);

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

		// PlayerListener
		getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_LOGIN, playerListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_BED_ENTER, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.High, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_DROP_ITEM, playerListener, Priority.Low, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_TELEPORT, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_PICKUP_ITEM, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT_ENTITY, playerListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Lowest, this);

		// Task
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new InventorySave(), 0, 20 * V.sync_inventory);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new ArmorSave(), 0, 20 * V.sync_armor);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new AfkCheck(), 20 * 60, 20 * 60);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new ConfigSave(), 0, 20 * 60 * V.save_config);

		L.log("Enabled " + getDescription().getName() + " " + getDescription().getVersion() + ".");
		System.out.println(getDescription().getName() + " " + getDescription().getVersion() + " is enabled.");
	}

	@Override
	public void onLoad(){
		V.plugin = this;
	}

	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args){
		if(command.getName().equalsIgnoreCase("dccreate")){
			return Create.create(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcjoin")){
			return Join.join(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcworlds")){
			return Worlds.worlds(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcspawnmob")){
			return Spawnmob.spawnmob(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dchelp")){
			return Help.help(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcgive")){
			return Give.give(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcgod")){
			return God.god(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcfly")){
			return Fly.fly(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcname")){
			return Name.name(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcwho")){
			return Who.who(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcspawn")){
			return Spawn.spawn(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcsetspawn")){
			return Setspawn.setspawn(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcstack")){
			return Stack.stack(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcteleport")){
			return Teleport.teleport(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcteleporthere")){
			return Teleporthere.teleporthere(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dchome")){
			return Home.home(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcsethome")){
			return Sethome.sethome(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcwarp")){
			return Warp.warp(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcsetwarp")){
			return Setwarp.setwarp(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcdelwarp")){
			return Delwarp.delwarp(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcwarps")){
			return Warps.warps(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dccreative")){
			return Creative.creative(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dchealth")){
			return Health.health(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dckill")){
			return Kill.kill(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dctime")){
			return Time.time(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcinventory")){
			return Inventory.inventory(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dckillnear")){
			return Killnear.killnear(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcclearnear")){
			return Clearnear.clearnear(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcjump")){
			return Jump.jump(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcdisconnect")){
			return Disconnect.disconnect(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dc")){
			return Dc.dc(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcrepeat")){
			return Repeat.repeat(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dconline")){
			return Online.online(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcweather")){
			return Weather.weather(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcignoremob")){
			return Ignoremob.ignoremob(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcnopickup")){
			return Nopickup.nopickup(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcinstakill")){
			return Instakill.instakill(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcfreeze")){
			return Freeze.freeze(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dchide")){
			return Hide.hide(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcreset")){
			return com.vildaberper.DefaultCommands.Command.Reset.reset(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcmessage")){
			return Message.message(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcrepair")){
			return Repair.repair(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcmute")){
			return Mute.mute(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcremovefloat")){
			return Removefloat.removefloat(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcreply")){
			return Reply.reply(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcfire")){
			return Fire.fire(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcsetportal")){
			return Setportal.setportal(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcdelportal")){
			return Delportal.delportal(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dccuboid")){
			return Cuboid.cuboid(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcundo")){
			return Undo.undo(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcreplace")){
			return Replace.replace(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dccuboidwireframe")){
			return Cuboidwireframe.cuboidwireframe(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dccuboidhollow")){
			return Cuboidhollow.cuboidhollow(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcspherehollow")){
			return Spherehollow.spherehollow(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcwalls")){
			return Walls.walls(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcping")){
			return Ping.ping(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcextinguish")){
			return Extinguish.extinguish(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcmotd")){
			return Motd.motd(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dckit")){
			return Kit.kit(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dckits")){
			return Kits.kits(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcdelworld")){
			return Delworld.delworld(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcportals")){
			return Portals.portals(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcclearinventory")){
			return Clearinventory.clearinventory(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcteleportposition")){
			return Teleportposition.teleportposition(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcafk")){
			return Afk.afk(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dclightning")){
			return Lightning.lightning(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcwhitelist")){
			return Whitelist.whitelist(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcwhitelists")){
			return Whitelists.whitelists(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcban")){
			return Ban.ban(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcunban")){
			return Unban.unban(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dcbans")){
			return Bans.bans(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dckick")){
			return Kick.kick(sender, command, label, args);
		}else if(command.getName().equalsIgnoreCase("dctop")){
			return Top.top(sender, command, label, args);
		}
		return false;
	}
}