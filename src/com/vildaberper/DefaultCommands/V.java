package com.vildaberper.DefaultCommands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Item;
import org.bukkit.plugin.Plugin;

import com.vildaberper.DefaultCommands.Class.DCAfkPlayer;
import com.vildaberper.DefaultCommands.Class.DCArmor;
import com.vildaberper.DefaultCommands.Class.DCBan;
import com.vildaberper.DefaultCommands.Class.DCBorder;
import com.vildaberper.DefaultCommands.Class.DCCommand;
import com.vildaberper.DefaultCommands.Class.DCConfiguration;
import com.vildaberper.DefaultCommands.Class.DCHome;
import com.vildaberper.DefaultCommands.Class.DCHover;
import com.vildaberper.DefaultCommands.Class.DCInventoryPlayer;
import com.vildaberper.DefaultCommands.Class.DCItem;
import com.vildaberper.DefaultCommands.Class.DCKit;
import com.vildaberper.DefaultCommands.Class.DCPlayer;
import com.vildaberper.DefaultCommands.Class.DCPortal;
import com.vildaberper.DefaultCommands.Class.DCReply;
import com.vildaberper.DefaultCommands.Class.DCSelection;
import com.vildaberper.DefaultCommands.Class.DCString;
import com.vildaberper.DefaultCommands.Class.DCTask;
import com.vildaberper.DefaultCommands.Class.DCTeleport;
import com.vildaberper.DefaultCommands.Class.DCUndo;
import com.vildaberper.DefaultCommands.Class.DCWarp;
import com.vildaberper.DefaultCommands.Class.DCWorld;

public class V{
	public static Plugin plugin = null;

	public static List<DCConfiguration> configuration = new LinkedList<DCConfiguration>();

	public static List<DCWarp> warps = new LinkedList<DCWarp>();
	public static List<DCHome> homes = new LinkedList<DCHome>();
	public static List<DCWorld> worlds = new LinkedList<DCWorld>();
	public static List<DCCommand> commands = new LinkedList<DCCommand>();
	public static List<DCString> strings = new LinkedList<DCString>();
	public static List<DCItem> items = new LinkedList<DCItem>();
	public static List<DCString> names = new LinkedList<DCString>();
	public static List<DCPlayer> players = new LinkedList<DCPlayer>();
	public static List<DCString> last_commands = new LinkedList<DCString>();
	public static List<Item> nopickups = new LinkedList<Item>();
	public static List<DCTask> removefloats = new LinkedList<DCTask>();
	public static List<DCReply> replies = new LinkedList<DCReply>();
	public static List<DCPortal> portals = new LinkedList<DCPortal>();
	public static List<DCSelection> selections = new LinkedList<DCSelection>();
	public static List<DCUndo> undos = new LinkedList<DCUndo>();
	public static List<DCInventoryPlayer> inventories = new LinkedList<DCInventoryPlayer>();
	public static List<DCArmor> armors = new LinkedList<DCArmor>();
	public static List<DCKit> kits = new LinkedList<DCKit>();
	public static List<DCAfkPlayer> afkplayers = new LinkedList<DCAfkPlayer>();
	public static List<DCBan> bans = new LinkedList<DCBan>();
	public static List<DCHover> hovers = new LinkedList<DCHover>();
	public static List<DCTeleport> teleports = new LinkedList<DCTeleport>();
	public static List<DCBorder> borders = new LinkedList<DCBorder>();

	public static List<Integer>
	god = new LinkedList<Integer>(),
	fly = new LinkedList<Integer>(),
	creative = new LinkedList<Integer>(),
	ignoremob = new LinkedList<Integer>(),
	nopickup = new LinkedList<Integer>(),
	instakill = new LinkedList<Integer>(),
	afk = new LinkedList<Integer>();

	public static List<String>
	hide = new LinkedList<String>(),
	mute = new LinkedList<String>(),
	freeze = new LinkedList<String>(),
	whitelist_ = new LinkedList<String>();

	public static int targetmax = 200;

	public static double afk_move_min = 0.5;

	public static int sync_time_id = 0;

	public static boolean return_ = true;

	public static Object getObject(String configuration){
		for(DCConfiguration config : V.configuration){
			if(config.getConfiguration().equals(configuration)){
				return config.getValue();
			}
		}
		return null;
	}
	public static boolean getBoolean(String configuration){
		return (Boolean) getObject(configuration);
	}
	public static int getInt(String configuration){
		return (Integer) getObject(configuration);
	}
	public static double getDouble(String configuration){
		try{
			return (Double) getObject(configuration);
		}catch(Exception e){
			return Double.parseDouble(Integer.toString(getInt(configuration)));
		}
	}
	public static String getString(String configuration){
		return (String) getObject(configuration);
	}
	@SuppressWarnings("unchecked")
	public static List<String> getStringList(String configuration){
		return (List<String>) getObject(configuration);
	}
	public static DCConfiguration getConfiguration(String configuration){
		for(DCConfiguration config : V.configuration){
			if(config.getConfiguration().equals(configuration)){
				return config;
			}
		}
		return null;
	}
	public static void setConfiguration(String configuration, Object value){
		while(getConfiguration(configuration) != null){
			V.configuration.remove(getConfiguration(configuration));
		}
		V.configuration.add(new DCConfiguration(configuration, value));
	}
}