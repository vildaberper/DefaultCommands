package com.vildaberper.DefaultCommands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Item;
import org.bukkit.plugin.Plugin;

import com.vildaberper.DefaultCommands.Class.DCAfkPlayer;
import com.vildaberper.DefaultCommands.Class.DCArmor;
import com.vildaberper.DefaultCommands.Class.DCBan;
import com.vildaberper.DefaultCommands.Class.DCCommand;
import com.vildaberper.DefaultCommands.Class.DCHome;
import com.vildaberper.DefaultCommands.Class.DCInventoryPlayer;
import com.vildaberper.DefaultCommands.Class.DCItem;
import com.vildaberper.DefaultCommands.Class.DCKit;
import com.vildaberper.DefaultCommands.Class.DCPlayer;
import com.vildaberper.DefaultCommands.Class.DCPortal;
import com.vildaberper.DefaultCommands.Class.DCReply;
import com.vildaberper.DefaultCommands.Class.DCSelection;
import com.vildaberper.DefaultCommands.Class.DCString;
import com.vildaberper.DefaultCommands.Class.DCTask;
import com.vildaberper.DefaultCommands.Class.DCUndo;
import com.vildaberper.DefaultCommands.Class.DCWarp;
import com.vildaberper.DefaultCommands.Class.DCWorld;

public class V{
	public static Plugin plugin = null;

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

	/*
	 * Configuration
	 */
	public static int
	per_page = 9,
	sync_time = 30,
	sync_inventory = 30,
	sync_armor = 30,
	selection_tool = 280,
	afk_time = 5,
	afk_kick_time = 10,
	save_config = 10;

	public static String
	give = "item amount target",
	all = "*",
	chat = "&7<prefix><player><suffix>&2: &f<message>",
	timezone = "GMT+1",
	console_name = "CONSOLE";

	public static boolean
	better_chat = true,
	unknown_command = true,
	better_fence = true,
	better_pumpkin = true,
	play_message_sound = true,
	show_teleport_smoke = true,
	whitelist = false,
	whitelist_kick = true;
}