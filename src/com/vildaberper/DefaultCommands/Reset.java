package com.vildaberper.DefaultCommands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import com.vildaberper.DefaultCommands.Class.DCConfiguration;
import com.vildaberper.DefaultCommands.Class.DCItem;
import com.vildaberper.DefaultCommands.Class.DCKit;
import com.vildaberper.DefaultCommands.Class.DCString;

public class Reset{
	public static void resetCommands(){
		V.commands.clear();
		Misc.addAlias("dcgod;dcignoremob;dcnopickup;dchide", "invisible");
		Misc.addAlias("dcfly;dcgod;dcignoremob;dcnopickup;dcinstakill", "admin");
		Misc.addAlias("dccreate", "create");
		Misc.addAlias("dcjoin", "join");
		Misc.addAlias("dcjoin", "j");
		Misc.addAlias("dcworlds", "worlds");
		Misc.addAlias("dcspawnmob", "spawnmob");
		Misc.addAlias("dcspawnmob", "sm");
		Misc.addAlias("dchelp", "help");
		Misc.addAlias("dcgive", "give");
		Misc.addAlias("dcgive", "item");
		Misc.addAlias("dcgive", "i");
		Misc.addAlias("dcgod", "god");
		Misc.addAlias("dcfly", "fly");
		Misc.addAlias("dcname", "name");
		Misc.addAlias("dcwho", "who");
		Misc.addAlias("dcwho", "info");
		Misc.addAlias("dcsetspawn", "setspawn");
		Misc.addAlias("dcspawn", "spawn");
		Misc.addAlias("dcstack", "stack");
		Misc.addAlias("dcstack", "s");
		Misc.addAlias("dcstack 0", "remove");
		Misc.addAlias("dcteleport", "teleport");
		Misc.addAlias("dcteleport", "tp");
		Misc.addAlias("dcteleporthere", "teleporthere");
		Misc.addAlias("dcteleporthere", "tph");
		Misc.addAlias("dchome", "home");
		Misc.addAlias("dcsethome", "sethome");
		Misc.addAlias("dcwarp", "warp");
		Misc.addAlias("dcsetwarp", "setwarp");
		Misc.addAlias("dcdelwarp", "delwarp");
		Misc.addAlias("dcwarps", "warps");
		Misc.addAlias("dccreative", "creative");
		Misc.addAlias("dchealth", "health");
		Misc.addAlias("dchealth", "heal");
		Misc.addAlias("dchealth", "h");
		Misc.addAlias("dckill", "kill");
		Misc.addAlias("dctime", "time");
		Misc.addAlias("dctime 0", "time morning");
		Misc.addAlias("dctime 6000", "time day");
		Misc.addAlias("dctime 12500", "time sunset");
		Misc.addAlias("dctime 15000", "time night");
		Misc.addAlias("dctime 22500", "time sunrise");
		Misc.addAlias("dcinventory", "inventory");
		Misc.addAlias("dcinventory", "inv");
		Misc.addAlias("dckillnear", "killnear");
		Misc.addAlias("dcclearnear", "clearnear");
		Misc.addAlias("dcjump", "jump");
		Misc.addAlias("dcdisconnect", "disconnect");
		Misc.addAlias("dcdisconnect", "exit");
		Misc.addAlias("dcdisconnect", "bye");
		Misc.addAlias("dcrepeat", "repeat");
		Misc.addAlias("dcrepeat", "rep");
		Misc.addAlias("dconline", "online");
		Misc.addAlias("dconline", "list");
		Misc.addAlias("dconline", "players");
		Misc.addAlias("dconline", "o");
		Misc.addAlias("dcweather", "weather");
		Misc.addAlias("dcignoremob", "ignoremob");
		Misc.addAlias("dcnopickup", "nopickup");
		Misc.addAlias("dcinstakill", "instakill");
		Misc.addAlias("dcfreeze", "freeze");
		Misc.addAlias("dchide", "hide");
		Misc.addAlias("dcreset", "reset");
		Misc.addAlias("dcmessage", "message");
		Misc.addAlias("dcmessage", "tell");
		Misc.addAlias("dcmessage", "msg");
		Misc.addAlias("dcmessage *", "say");
		Misc.addAlias("dcmessage *", "broadcast");
		Misc.addAlias("dcrepair", "repair");
		Misc.addAlias("dcmute", "mute");
		Misc.addAlias("dcremovefloat", "removefloat");
		Misc.addAlias("dcremovefloat", "rf");
		Misc.addAlias("dcremovefloat 17 18", "deltree");
		Misc.addAlias("dcremovefloat -14 -15 -16 -21 -56 -73 -74", "automine");
		Misc.addAlias("dcreply", "reply");
		Misc.addAlias("dcfire 120", "fire");
		Misc.addAlias("dcfire 0", "stopfire");
		Misc.addAlias("dcsetportal", "setportal");
		Misc.addAlias("dcdelportal", "delportal");
		Misc.addAlias("dccuboid", "cuboid");
		Misc.addAlias("dccuboid", "c");
		Misc.addAlias("dcundo", "undo");
		Misc.addAlias("dcreplace", "replace");
		Misc.addAlias("dccuboidwireframe", "cuboidwireframe");
		Misc.addAlias("dccuboidwireframe", "cw");
		Misc.addAlias("dccuboidhollow", "cuboidhollow");
		Misc.addAlias("dccuboidhollow", "ch");
		Misc.addAlias("dcspherehollow", "spherehollow");
		Misc.addAlias("dcwalls", "walls");
		Misc.addAlias("dcping", "ping");
		Misc.addAlias("dcextinguish", "extinguish");
		Misc.addAlias("dcextinguish", "ext");
		Misc.addAlias("dcmotd", "motd");
		Misc.addAlias("dckit", "kit");
		Misc.addAlias("dckits", "kits");
		Misc.addAlias("dcdelworld", "delworld");
		Misc.addAlias("dcportals", "portals");
		Misc.addAlias("dcclearinventory", "clearinventory");
		Misc.addAlias("dcclearinventory", "clearinv");
		Misc.addAlias("dcteleportposition", "teleportposition");
		Misc.addAlias("dcteleportposition", "tpp");
		Misc.addAlias("dcteleportposition", "goto");
		Misc.addAlias("dcafk", "afk");
		Misc.addAlias("dclightning", "lightning");
		Misc.addAlias("dclightning", "thunder");
		Misc.addAlias("dcwhitelist", "whitelist");
		Misc.addAlias("dcwhitelists", "whitelists");
	}

	public static List<DCConfiguration> resetDCConfiguration(String world){
		List<DCConfiguration> config = new LinkedList<DCConfiguration>();
		List<String> permissions = new LinkedList<String>();
		Location spawn = V.plugin.getServer().getWorld(world).getSpawnLocation();

		config.add(new DCConfiguration("spawn", spawn.getX() + " " + spawn.getY() + " " + spawn.getZ() + " " + spawn.getYaw() + " " + spawn.getPitch()));
		/*
		 * General
		 */
		config.add(new DCConfiguration("copy", null));
		config.add(new DCConfiguration("op_permissions", false));
		config.add(new DCConfiguration("creative", false));
		config.add(new DCConfiguration("separate_inventory", false));
		config.add(new DCConfiguration("hungry_cows", false));
		config.add(new DCConfiguration("hungry_cows_rate", 0.2));
		config.add(new DCConfiguration("hungry_cows_interval", 10));
		config.add(new DCConfiguration("poop_despawn_time", 20));
		config.add(new DCConfiguration("magic_sheep", false));
		config.add(new DCConfiguration("magic_sheep_rate", 0.35));
		config.add(new DCConfiguration("magic_sheep_interval", 15));
		config.add(new DCConfiguration("blood", false));
		config.add(new DCConfiguration("blood_despawn_time", 0.5));
		config.add(new DCConfiguration("blood_level", 0.5));
		config.add(new DCConfiguration("auto_heal", false));
		config.add(new DCConfiguration("auto_heal_amount", 1));
		config.add(new DCConfiguration("auto_heal_interval", 5));
		config.add(new DCConfiguration("home_on_death", false));
		config.add(new DCConfiguration("sync_time", false));
		config.add(new DCConfiguration("weather_change", true));
		config.add(new DCConfiguration("saddled_pig_drop_saddle", false));
		config.add(new DCConfiguration("leaf_decay", true));
		config.add(new DCConfiguration("leaf_drop_apple", false));
		config.add(new DCConfiguration("leaf_drop_apple_rate", 0.01));
		config.add(new DCConfiguration("leaf_drop_sapling", true));
		config.add(new DCConfiguration("leaf_drop_sapling_rate", 0.03));
		/*
		 * Friendly mobs
		 */
		config.add(new DCConfiguration("friendly_creeper", false));
		config.add(new DCConfiguration("friendly_ghast", false));
		config.add(new DCConfiguration("friendly_giant", false));
		config.add(new DCConfiguration("friendly_monster", false));
		config.add(new DCConfiguration("friendly_pigzombie", false));
		config.add(new DCConfiguration("friendly_skeleton", false));
		config.add(new DCConfiguration("friendly_slime", false));
		config.add(new DCConfiguration("friendly_spider", false));
		config.add(new DCConfiguration("friendly_zombie", false));
		/*
		 * Fire
		 */
		config.add(new DCConfiguration("block_fire_flint_and_steel", false));
		config.add(new DCConfiguration("block_fire_lava", false));
		config.add(new DCConfiguration("block_fire_lightning", false));
		config.add(new DCConfiguration("block_fire_spread", false));
		/*
		 * Block damage
		 */
		config.add(new DCConfiguration("block_tnt_explosion_block_damage", false));
		config.add(new DCConfiguration("block_creeper_explosion_block_damage", false));
		config.add(new DCConfiguration("block_fireball_explosion_block_damage", false));
		/*
		 * Player damage
		 */
		config.add(new DCConfiguration("block_tnt_explosion_player_damage", false));
		config.add(new DCConfiguration("block_creeper_explosion_player_damage", false));
		config.add(new DCConfiguration("block_fireball_explosion_player_damage", false));
		config.add(new DCConfiguration("block_drowning_player_damage", false));
		config.add(new DCConfiguration("block_fall_player_damage", false));
		config.add(new DCConfiguration("block_fire_player_damage", false));
		config.add(new DCConfiguration("block_lava_player_damage", false));
		config.add(new DCConfiguration("block_suffocation_player_damage", false));
		config.add(new DCConfiguration("block_void_player_damage", false));
		config.add(new DCConfiguration("block_player_player_damage", false));
		config.add(new DCConfiguration("block_arrow_player_damage", false));
		config.add(new DCConfiguration("block_mob_player_damage", false));
		/*
		 * Spawn
		 */
		config.add(new DCConfiguration("block_chicken_spawn", false));
		config.add(new DCConfiguration("block_cow_spawn", false));
		config.add(new DCConfiguration("block_creeper_spawn", false));
		config.add(new DCConfiguration("block_ghast_spawn", false));
		config.add(new DCConfiguration("block_giant_spawn", false));
		config.add(new DCConfiguration("block_monster_spawn", false));
		config.add(new DCConfiguration("block_pig_spawn", false));
		config.add(new DCConfiguration("block_pigzombie_spawn", false));
		config.add(new DCConfiguration("block_sheep_spawn", false));
		config.add(new DCConfiguration("block_skeleton_spawn", false));
		config.add(new DCConfiguration("block_slime_spawn", false));
		config.add(new DCConfiguration("block_spider_spawn", false));
		config.add(new DCConfiguration("block_squid_spawn", false));
		config.add(new DCConfiguration("block_wolf_spawn", false));
		config.add(new DCConfiguration("block_zombie_spawn", false));
		/*
		 * Permissions
		 */
		permissions.add("dc.do.*");
		permissions.add("dc.help");
		permissions.add("dc.who");
		permissions.add("dc.worlds");
		permissions.add("dc.create");
		permissions.add("dc.setspawn.*");
		permissions.add("dc.spawnmob.*");
		permissions.add("dc.join.self.*");
		permissions.add("dc.join.other.*");
		permissions.add("dc.join.all.*");
		permissions.add("dc.fly.self");
		permissions.add("dc.fly.other");
		permissions.add("dc.fly.all");
		permissions.add("dc.god.self");
		permissions.add("dc.god.other");
		permissions.add("dc.god.all");
		permissions.add("dc.name.self");
		permissions.add("dc.name.other");
		permissions.add("dc.name.all");
		permissions.add("dc.give.self");
		permissions.add("dc.give.other");
		permissions.add("dc.give.all");
		permissions.add("dc.spawn.self.*");
		permissions.add("dc.spawn.other.*");
		permissions.add("dc.spawn.all.*");
		permissions.add("dc.stack");
		permissions.add("dc.remove");
		permissions.add("dc.teleport.self.*");
		permissions.add("dc.teleport.other.*");
		permissions.add("dc.teleport.all.*");
		permissions.add("dc.home.self");
		permissions.add("dc.home.other");
		permissions.add("dc.home.all");
		permissions.add("dc.sethome.self");
		permissions.add("dc.sethome.other");
		permissions.add("dc.sethome.all");
		permissions.add("dc.warp.self.*");
		permissions.add("dc.warp.other.*");
		permissions.add("dc.warp.all.*");
		permissions.add("dc.setwarp");
		permissions.add("dc.delwarp");
		permissions.add("dc.warps");
		permissions.add("dc.creative.self");
		permissions.add("dc.creative.other");
		permissions.add("dc.creative.all");
		permissions.add("dc.health.self");
		permissions.add("dc.health.other");
		permissions.add("dc.health.all");
		permissions.add("dc.time.*");
		permissions.add("dc.inventory");
		permissions.add("dc.killnear.*");
		permissions.add("dc.clearnear.*");
		permissions.add("dc.jump.*");
		permissions.add("dc.disconnect");
		permissions.add("dc.dc");
		permissions.add("dc.flint_and_steel.*");
		permissions.add("dc.repeat");
		permissions.add("dc.online");
		permissions.add("dc.weather.*");
		permissions.add("dc.ignoremob.self");
		permissions.add("dc.ignoremob.other");
		permissions.add("dc.ignoremob.all");
		permissions.add("dc.nopickup.self");
		permissions.add("dc.nopickup.other");
		permissions.add("dc.nopickup.all");
		permissions.add("dc.instakill.self");
		permissions.add("dc.instakill.other");
		permissions.add("dc.instakill.all");
		permissions.add("dc.freeze.self");
		permissions.add("dc.freeze.other");
		permissions.add("dc.freeze.all");
		permissions.add("dc.hide.self");
		permissions.add("dc.hide.other");
		permissions.add("dc.hide.all");
		permissions.add("dc.hide.see");
		permissions.add("dc.reset.self");
		permissions.add("dc.reset.other");
		permissions.add("dc.reset.all");
		permissions.add("dc.message.self");
		permissions.add("dc.message.other");
		permissions.add("dc.message.all");
		permissions.add("dc.repair");
		permissions.add("dc.mount.animal.*");
		permissions.add("dc.mount.monster.*");
		permissions.add("dc.mount.player.*");
		permissions.add("dc.mute.self");
		permissions.add("dc.mute.other");
		permissions.add("dc.mute.all");
		permissions.add("dc.fire.self");
		permissions.add("dc.fire.other");
		permissions.add("dc.fire.all");
		permissions.add("dc.select");
		permissions.add("dc.portal.*");
		permissions.add("dc.setportal");
		permissions.add("dc.delportal");
		permissions.add("dc.cuboid");
		permissions.add("dc.undo");
		permissions.add("dc.replace");
		permissions.add("dc.cuboidwireframe");
		permissions.add("dc.cuboidhollow");
		permissions.add("dc.spherehollow");
		permissions.add("dc.walls");
		permissions.add("dc.extinguish");
		permissions.add("dc.motd");
		permissions.add("dc.kit.self.*");
		permissions.add("dc.kit.other.*");
		permissions.add("dc.kit.all.*");
		permissions.add("dc.kits");
		permissions.add("dc.delworld*");
		permissions.add("dc.portals");
		permissions.add("dc.clearinventory.self");
		permissions.add("dc.clearinventory.other");
		permissions.add("dc.clearinventory.all");
		permissions.add("dc.teleportposition.self");
		permissions.add("dc.teleportposition.other");
		permissions.add("dc.teleportposition.all");
		permissions.add("dc.afk.self");
		permissions.add("dc.afk.other");
		permissions.add("dc.afk.all");
		permissions.add("dc.lightning");
		permissions.add("dc.whitelist.add");
		permissions.add("dc.whitelist.remove");
		permissions.add("dc.whitelists");
		config.add(new DCConfiguration("permissions", permissions));
		return config;
	}

	public static void resetStrings(){
		V.strings.clear();
		V.strings.add(new DCString("unknown_command", "&4Unknown command."));
		V.strings.add(new DCString("not_permission", "&4You do not have permission to do that."));
		V.strings.add(new DCString("connect_message", "&7<player>&2 connected from &7<ip>&2, joined &7<world>&2."));
		V.strings.add(new DCString("connect_message_first_time", "&7<player>&2 connected for the first time from &7<ip>&2, joined &7<world>&2."));
		V.strings.add(new DCString("disconnect_message", "&7<player>&2 disconnected."));
		V.strings.add(new DCString("join_world", "&7<player>&2 joined &7<world>&2."));
		V.strings.add(new DCString("muted", "&4You are muted!"));
		V.strings.add(new DCString("afk_kick", "&4You were kicked for beeing idle too long!"));
		V.strings.add(new DCString("not_console", "&4You cannot do that as console."));
		V.strings.add(new DCString("frozen", "&4You cannot do that while you are frozen."));
		V.strings.add(new DCString("online", "&2Connected players (&7<online>&2/&7<max>&2):<br><players>&2."));
		V.strings.add(new DCString("who_online", "&7<prefix><player><suffix>&2:<br>&2Online now from &7<ip>&2.<br>&2Location: &7<world> <x> <y> <z><br>&2Hp: &7[<hp>&7]<br>&2Group: &7<group>"));
		V.strings.add(new DCString("who_offline", "&7<player>&2:<br>&2Last seen &7<date> <time>&2 from &7<ip>&2."));
		V.strings.add(new DCString("message_sent", "&2To &7<players>&2: &7<message>"));
		V.strings.add(new DCString("message_recieved", "&2From &7<player>&2: &7<message>"));
		V.strings.add(new DCString("whitelist", "&4You are not on the whitelist!"));
		V.strings.add(new DCString("motd", "&2Welcome to the server, &7<player>&2. Connected players (&7<online>&2/&7<max>&2):<br>&7<players>&2.<br>&2Enjoy your stay!"));
		V.strings.add(new DCString("invalid_player", "&4Invalid player(s)."));
		V.strings.add(new DCString("invalid_world", "&4Invalid world."));
		V.strings.add(new DCString("invalid_page", "&4Invalid page."));
		V.strings.add(new DCString("invalid_item", "&4Invalid item."));
		V.strings.add(new DCString("invalid_amount", "&4Invalid amount."));
		V.strings.add(new DCString("invalid_home", "&4Invalid home."));
		V.strings.add(new DCString("invalid_warp", "&4Invalid warp."));
		V.strings.add(new DCString("invalid_portal", "&4Invalid portal."));
		V.strings.add(new DCString("invalid_health", "&4Invalid health."));
		V.strings.add(new DCString("invalid_time", "&4Invalid time."));
		V.strings.add(new DCString("invalid_distance", "&4Invalid distance."));
		V.strings.add(new DCString("invalid_weather", "&4Invalid weather."));
		V.strings.add(new DCString("invalid_duration", "&4Invalid duration."));
		V.strings.add(new DCString("invalid_selection", "&4Invalid selection."));
		V.strings.add(new DCString("invalid_kit", "&4Invalid kit."));
		V.strings.add(new DCString("invalid_environment", "&4Invalid environment."));
		V.strings.add(new DCString("invalid_coordinate", "&4Invalid coordinate."));
		V.strings.add(new DCString("c_create", "&7<player>&2 created &7<world>&2, &7<enviroment>&2."));
		V.strings.add(new DCString("c_fly", "&7<player>&2 <status>d fly for &7<players>&2."));
		V.strings.add(new DCString("c_god", "&7<player>&2 <status>d god for &7<players>&2."));
		V.strings.add(new DCString("c_join", "&7<player>&2 made &7<players>&2 join &7<world>&2."));
		V.strings.add(new DCString("c_name", "&7<player>&2 changed the name of &7<players>&2 to &7<name>&2."));
		V.strings.add(new DCString("c_setspawn", "&7<player>&2 set the spawn for &7<world>&2."));
		V.strings.add(new DCString("c_spawn", "&7<player>&2 returned &7<players>&2 to spawn."));
		V.strings.add(new DCString("c_spawnmob", "&7<player>&2 spawned &7<entities>&2 in &7<world>&2."));
		V.strings.add(new DCString("c_give", "&7<player>&2 gave &7<players> <amount> <item>&2."));
		V.strings.add(new DCString("c_stack", "&7<player>&2 stacked &7<item>&2 to &7<amount>&2."));
		V.strings.add(new DCString("c_teleport", "&7<player>&2 made &7<players>&2 teleport to &7<target>&2."));
		V.strings.add(new DCString("c_home", "&7<player>&2 made &7<players>&2 return home."));
		V.strings.add(new DCString("c_sethome", "&7<player>&2 set home for &7<players>&2."));
		V.strings.add(new DCString("c_warp", "&7<player>&2 made &7<players>&2 warp to &7<warp>&2."));
		V.strings.add(new DCString("c_setwarp", "&7<player>&2 set warp &7<warp>&2."));
		V.strings.add(new DCString("c_delwarp", "&7<player>&2 removed warp &7<warp>&2."));
		V.strings.add(new DCString("c_creative", "&7<player>&2 <status>d creative for &7<players>&2."));
		V.strings.add(new DCString("c_health", "&7<player>&2 set the health of &7<players>&2 to &7<hp>&2."));
		V.strings.add(new DCString("c_time", "&7<player>&2 set time in &7<world>&2 to &7<time>&2."));
		V.strings.add(new DCString("c_killnear", "&7<player>&2 killed &7<amount>&2 mobs in &7<world>&2."));
		V.strings.add(new DCString("c_clearnear", "&7<player>&2 cleared &7<amount>&2 items in &7<world>&2."));
		V.strings.add(new DCString("c_disconnect_message", "Bye <player>."));
		V.strings.add(new DCString("c_weather", "&7<player>&2 set the weather in &7<world>&2 to &7<weather>&2."));
		V.strings.add(new DCString("c_ignoremob", "&7<player>&2 <status>d ignore mob for &7<players>&2."));
		V.strings.add(new DCString("c_nopickup", "&7<player>&2 <status>d no pickup for &7<players>&2."));
		V.strings.add(new DCString("c_instakill", "&7<player>&2 <status>d instant kill for &7<players>&2."));
		V.strings.add(new DCString("c_freeze", "&7<player>&2 <status>d freeze for &7<players>&2."));
		V.strings.add(new DCString("c_hide", "&7<player>&2 <status>d hide for &7<players>&2."));
		V.strings.add(new DCString("c_reset", "&7<player>&2 resetted &7<players>&2."));
		V.strings.add(new DCString("c_message", "&7<player>&2 -> &7<players>&2: &7<message>"));
		V.strings.add(new DCString("c_repair", "&7<player>&2 repaired one &7<item>&2."));
		V.strings.add(new DCString("item_in_hand", "&4You must hold an item in your hand!"));
		V.strings.add(new DCString("c_mute", "&7<player>&2 <status>d mute for &7<players>&2."));
		V.strings.add(new DCString("c_fire", "&7<player>&2 set &7<players>&2 on fire for &7<duration>&2 seconds."));
		V.strings.add(new DCString("c_kit", "&7<player>&2 gave &7<players>&2 the kit &7<kit>&2."));
		V.strings.add(new DCString("c_delworld", "&7<player>&2 removed world &7<world>&2."));
		V.strings.add(new DCString("c_clearinventory", "&7<player>&2 cleared the inventory of &7<players>&2."));
		V.strings.add(new DCString("c_teleportposition", "&7<player>&2 teleported &7<players>&2 to (&7<position>&2)."));
		V.strings.add(new DCString("c_afk", "&7<player>&2 <status>d afk for &7<players>&2."));
	}

	public static void resetItems(){
		List<String> alias = new LinkedList<String>();

		V.items.clear();
		alias.add("white");
		V.items.add(new DCItem("35:0", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("orange");
		V.items.add(new DCItem("35:1", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("magents");
		V.items.add(new DCItem("35:2", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("light_blue");
		V.items.add(new DCItem("35:3", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("yellow");
		V.items.add(new DCItem("35:4", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("lime_green");
		V.items.add(new DCItem("35:5", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("pink");
		V.items.add(new DCItem("35:6", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("gray");
		V.items.add(new DCItem("35:7", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("light_gray");
		V.items.add(new DCItem("35:8", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("cyan");
		V.items.add(new DCItem("35:9", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("purple");
		V.items.add(new DCItem("35:10", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("blue");
		V.items.add(new DCItem("35:11", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("brown");
		V.items.add(new DCItem("35:12", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("green");
		V.items.add(new DCItem("35:13", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("red");
		V.items.add(new DCItem("35:14", new LinkedList<String>(alias)));
		alias.clear();
		alias.add("black");
		V.items.add(new DCItem("35:15", new LinkedList<String>(alias)));
	}

	public static void resetKits(){
		List<ItemStack> items = new LinkedList<ItemStack>();

		V.kits.clear();
		items.add(new ItemStack(268, 1));
		items.add(new ItemStack(269, 1));
		items.add(new ItemStack(270, 1));
		items.add(new ItemStack(271, 1));
		V.kits.add(new DCKit("starter", items));
	}

	public static void resetNopickup(){
		for(Item item : V.nopickups){
			item.remove();
		}
		V.nopickups.clear();
	}

	public static void resetRemovefloat(){
		for(int i = V.removefloats.size() - 1; i >= 0; i--){
			V.plugin.getServer().getScheduler().cancelTask(V.removefloats.get(i).getTask());
			V.removefloats.remove(i);
		}
	}
}