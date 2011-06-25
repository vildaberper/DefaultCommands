package com.vildaberper.DefaultCommands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.config.Configuration;

import com.vildaberper.DefaultCommands.Class.DCCommand;
import com.vildaberper.DefaultCommands.Class.DCConfiguration;
import com.vildaberper.DefaultCommands.Class.DCHome;
import com.vildaberper.DefaultCommands.Class.DCInventoryPlayer;
import com.vildaberper.DefaultCommands.Class.DCItem;
import com.vildaberper.DefaultCommands.Class.DCKit;
import com.vildaberper.DefaultCommands.Class.DCPlayer;
import com.vildaberper.DefaultCommands.Class.DCPortal;
import com.vildaberper.DefaultCommands.Class.DCString;
import com.vildaberper.DefaultCommands.Class.DCWarp;
import com.vildaberper.DefaultCommands.Class.DCWorld;
import com.vildaberper.DefaultCommands.Runnable.TimeSync;

public class SaveLoad{
	public static void saveAll(){
		saveDefaultCommands();
		saveConfig();
		saveCommands();
		saveStrings();
		saveItems();
		saveNames();
		savePlayers();
		saveHomes();
		saveWarps();
		saveInventory();
		savePortals();
		saveKits();
		saveWhitelist();
	}

	public static void loadAll(){
		loadDefaultCommands();
		loadConfig();
		loadCommands();
		loadStrings();
		loadItems();
		loadNames();
		loadPlayers();
		loadHomes();
		loadWarps();
		loadInventory();
		loadPortals();
		loadKits();
		loadWhitelist();
	}

	public static void loadWhitelist(){
		Configuration w = new Configuration(new File(V.plugin.getDataFolder(), "Whitelist.yml"));

		w.load();
		for(String s : w.getStringList("Whitelist", new LinkedList<String>())){
			if(!V.whitelist_.contains(s)){
				V.whitelist_.add(s);
			}
		}
	}

	public static void saveWhitelist(){
		Configuration w = new Configuration(new File(V.plugin.getDataFolder(), "Whitelist.yml"));

		w.setProperty("Whitelist", V.whitelist_);
		w.save();
	}

	public static void loadKits(){
		boolean first = !new File(V.plugin.getDataFolder(), "Kits.yml").exists();
		Configuration k = new Configuration(new File(V.plugin.getDataFolder(), "Kits.yml"));

		V.kits.clear();
		k.load();
		if(k.getKeys(null) != null){
			for(String name : k.getKeys(null)){
				List<ItemStack> items = new LinkedList<ItemStack>();

				for(String item : k.getStringList(name, new LinkedList<String>())){
					String amount = "1";

					if(item.split(" ").length >= 2){
						amount = item.split(" ")[1];
					}
					for(ItemStack itemstack : Misc.getItemStacks(item.split(" ")[0], amount)){
						items.add(itemstack);
					}
				}
				V.kits.add(new DCKit(name, items));
			}
		}
		if(first){
			Reset.resetKits();
		}
	}

	public static void saveKits(){
		Configuration k = new Configuration(new File(V.plugin.getDataFolder(), "Kits.yml"));

		for(DCKit dckit : V.kits){
			List<String> items = new LinkedList<String>();

			for(ItemStack itemstack : dckit.getItems()){
				items.add(itemstack.getTypeId() + ":" + itemstack.getDurability() + " " + itemstack.getAmount());
			}
			k.setProperty(dckit.getName(), items);
		}
		k.save();
	}

	public static void loadPortals(){
		Configuration p = new Configuration(new File(V.plugin.getDataFolder(), "Portals.yml"));

		V.portals.clear();
		p.load();
		if(p.getKeys(null) != null){
			for(String s : p.getKeys(null)){
				if(V.plugin.getServer().getWorld(p.getString(s + ".W")) != null){
					V.portals.add(
							new DCPortal(
									s,
									V.plugin.getServer().getWorld(p.getString(s + ".W")).getBlockAt(
											Integer.parseInt(p.getString(s + ".B1").split(":")[0]),
											Integer.parseInt(p.getString(s + ".B1").split(":")[1]),
											Integer.parseInt(p.getString(s + ".B1").split(":")[2])
									),
									V.plugin.getServer().getWorld(p.getString(s + ".W")).getBlockAt(
											Integer.parseInt(p.getString(s + ".B2").split(":")[0]),
											Integer.parseInt(p.getString(s + ".B2").split(":")[1]),
											Integer.parseInt(p.getString(s + ".B2").split(":")[2])
									),
									p.getString(s + ".T")
							)
					);
				}
			}
		}
	}

	public static void savePortals(){
		Configuration p = new Configuration(new File(V.plugin.getDataFolder(), "Portals.yml"));

		for(DCPortal dcp : V.portals){
			p.setProperty(dcp.getName() + ".B1", dcp.getBlock1().getX() + ":" + dcp.getBlock1().getY() + ":" + dcp.getBlock1().getZ());
			p.setProperty(dcp.getName() + ".B2", dcp.getBlock2().getX() + ":" + dcp.getBlock2().getY() + ":" + dcp.getBlock2().getZ());
			p.setProperty(dcp.getName() + ".T", dcp.getTarget());
			p.setProperty(dcp.getName() + ".W", dcp.getWorld());
		}
		p.save();
	}

	public static void loadInventory(){
		for(DCWorld dcworld : V.worlds){
			if(dcworld.getBoolean("separate_inventory")){
				File dir = new File(V.plugin.getDataFolder(), "Worlds" + File.separator + dcworld.getName() + File.separator + "Inventory");
				List<DCInventoryPlayer> dcinventory = new LinkedList<DCInventoryPlayer>();

				dcworld.setInventory(new LinkedList<DCInventoryPlayer>());
				if(dir.listFiles() != null){
					for(File file : dir.listFiles()){
						try{
							if(file.exists()){
								FileReader fr = new FileReader(file);
								BufferedReader br = new BufferedReader(fr);

								dcinventory.add(new DCInventoryPlayer(file.getName().replace(".data", ""), Misc.getItemStackFromString(br.readLine())));
								br.close();
							}
						}catch(Exception e){
							System.out.println("Failed to load inventory: " + file.getAbsolutePath());
						}
					}
				}
				dcworld.setInventory(dcinventory);
			}
		}
		File dir = new File(V.plugin.getDataFolder(), "Inventory");
		List<DCInventoryPlayer> inventory = new LinkedList<DCInventoryPlayer>();
		V.inventories.clear();
		if(dir.listFiles() != null){
			for(File file : dir.listFiles()){
				try{
					if(file.exists()){
						FileReader fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);

						inventory.add(new DCInventoryPlayer(file.getName().replace(".data", ""), Misc.getItemStackFromString(br.readLine())));
						br.close();
					}
				}catch(Exception e){
					System.out.println("Failed to load inventory: " + file.getAbsolutePath());
				}
			}
		}
		V.inventories = inventory;

	}

	public static void saveInventory(){
		if(!new File(V.plugin.getDataFolder(), "Worlds").exists()){
			new File(V.plugin.getDataFolder(), "Worlds").mkdir();
		}
		for(DCWorld dcworld : V.worlds){
			if(dcworld.getBoolean("separate_inventory")){
				File dir = new File(V.plugin.getDataFolder(), "Worlds" + File.separator + dcworld.getName() + File.separator + "Inventory");

				dir.mkdir();
				for(DCInventoryPlayer dcinventory : dcworld.getInventory()){
					File file = new File(dir, dcinventory.getName() + ".data");

					try{
						if(!file.exists()){
							file.createNewFile();
						}
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);

						bw.flush();
						bw.write(Misc.getStringFromItemStack(Util.convertItemStack(dcinventory.getContents())));
						bw.close();
					}catch(Exception e){
						System.out.println("Failed to save inventory: " + file.getAbsolutePath());
						e.printStackTrace();
					}
				}
			}
		}
		File dir = new File(V.plugin.getDataFolder(), "Inventory");

		dir.mkdir();
		for(DCInventoryPlayer dcinventory : V.inventories){
			File file = new File(dir, dcinventory.getName() + ".data");

			try{
				if(!file.exists()){
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.flush();
				bw.write(Misc.getStringFromItemStack(Util.convertItemStack(dcinventory.getContents())));
				bw.close();
			}catch(Exception e){
				System.out.println("Failed to save inventory: " + file.getAbsolutePath());
				e.printStackTrace();
			}
		}
	}

	public static void loadDefaultCommands(){
		Configuration dc = new Configuration(new File(V.plugin.getDataFolder(), "DefaultCommands.yml"));

		V.plugin.getServer().getScheduler().cancelTask(V.sync_time_id);
		dc.load();
		V.per_page = dc.getInt("per_page", V.per_page);
		V.give = dc.getString("give", V.give);
		V.chat = dc.getString("chat", V.chat);
		V.all = dc.getString("all", V.all);
		V.better_chat = dc.getBoolean("better_chat", V.better_chat);
		V.timezone = dc.getString("timezone", V.timezone);
		V.console_name = dc.getString("console_name", V.console_name);
		V.sync_time = dc.getInt("sync_time", V.sync_time);
		V.afk_time = dc.getInt("afk_time", V.afk_time);
		V.afk_kick_time = dc.getInt("afk_kick_time", V.afk_kick_time);
		V.sync_inventory = dc.getInt("sync_inventory", V.sync_inventory);
		V.save_config = dc.getInt("save_config", V.save_config);
		V.unknown_command = dc.getBoolean("unknown_command", V.unknown_command);
		V.better_fence = dc.getBoolean("better_fence", V.better_fence);
		V.better_pumpkin = dc.getBoolean("better_pumpkin", V.better_pumpkin);
		V.play_message_sound = dc.getBoolean("play_message_sound", V.play_message_sound);
		V.show_teleport_smoke = dc.getBoolean("show_teleport_smoke", V.show_teleport_smoke);
		V.whitelist = dc.getBoolean("whitelist", V.whitelist);
		V.whitelist_kick = dc.getBoolean("whitelist_kick", V.whitelist_kick);
		V.selection_tool = dc.getInt("selection_tool", V.selection_tool);
		V.sync_time_id = V.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(V.plugin, new TimeSync(), (20 * V.sync_time), (20 * V.sync_time));
	}

	public static void saveDefaultCommands(){
		Configuration dc = new Configuration(new File(V.plugin.getDataFolder(), "DefaultCommands.yml"));
		List<DCConfiguration> dcc = new LinkedList<DCConfiguration>();

		dcc.add(new DCConfiguration("per_page", V.per_page));
		dcc.add(new DCConfiguration("give", V.give));
		dcc.add(new DCConfiguration("chat", V.chat));
		dcc.add(new DCConfiguration("all", V.all));
		dcc.add(new DCConfiguration("timezone", V.timezone));
		dcc.add(new DCConfiguration("console_name", V.console_name));
		dcc.add(new DCConfiguration("sync_time", V.sync_time));
		dcc.add(new DCConfiguration("afk_time", V.afk_time));
		dcc.add(new DCConfiguration("afk_kick_time", V.afk_kick_time));
		dcc.add(new DCConfiguration("sync_inventory", V.sync_inventory));
		dcc.add(new DCConfiguration("save_config", V.save_config));
		dcc.add(new DCConfiguration("unknown_command", V.unknown_command));
		dcc.add(new DCConfiguration("better_chat", V.better_chat));
		dcc.add(new DCConfiguration("better_fence", V.better_fence));
		dcc.add(new DCConfiguration("better_pumpkin", V.better_pumpkin));
		dcc.add(new DCConfiguration("play_message_sound", V.play_message_sound));
		dcc.add(new DCConfiguration("show_teleport_smoke", V.show_teleport_smoke));
		dcc.add(new DCConfiguration("whitelist", V.whitelist));
		dcc.add(new DCConfiguration("whitelist_kick", V.whitelist_kick));
		dcc.add(new DCConfiguration("selection_tool", V.selection_tool));
		dc.save();
		for(DCConfiguration dcconf : dcc){
			dc.load();
			dc.setProperty(dcconf.getConfiguration(), dcconf.getValue());
			dc.save();
		}
	}

	public static void loadConfig(){
		File[] files = new File(V.plugin.getDataFolder(), "Worlds").listFiles();
		Configuration config = null;

		for(DCWorld dcworld : V.worlds){
			if(dcworld.getBoolean("magic_sheep")){
				V.plugin.getServer().getScheduler().cancelTask(dcworld.getRunnablesheep());
			}
			if(dcworld.getBoolean("hungry_cows")){
				V.plugin.getServer().getScheduler().cancelTask(dcworld.getRunnablepoop());
			}
			if(dcworld.getBoolean("auto_heal")){
				V.plugin.getServer().getScheduler().cancelTask(dcworld.getRunnableheal());
			}
		}
		V.worlds.clear();
		for(World world : V.plugin.getServer().getWorlds()){
			Misc.addWorld(world.getName(), world.getEnvironment());
		}
		if(files != null){
			for(int i = 0; i < files.length; i++){
				config = new Configuration(new File(V.plugin.getDataFolder(), "Worlds" + File.separator + files[i].getName() + File.separator + "Config.yml"));
				config.load();

				Environment e = Util.getEnvironment(config.getString("enviroment", "NORMAL"));
				String s = null;

				if(e == null){
					e = V.plugin.getServer().getWorld(files[i].getName()).getEnvironment();
				}
				if(config.getString("seed", null) != null){
					s = config.getString("seed");
				}
				if(s != null){
					Misc.addWorld(files[i].getName(), e, Long.parseLong(s));
				}else{
					Misc.addWorld(files[i].getName(), e);
				}
				if(config.getKeys(null) != null){
					for(String configuration : config.getKeys(null)){
						Misc.setConfig(files[i].getName(), configuration, config.getProperty(configuration));
					}
				}
				Perm.setupPermissions(files[i].getName());
			}
		}
		for(DCWorld dcworld : V.worlds){
			if(dcworld.getBoolean("magic_sheep")){
				dcworld.setRunnablesheep(V.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(V.plugin, dcworld.new RunnableSheep(), 0, (long) (20 * dcworld.getInt("magic_sheep_interval"))));
			}
			if(dcworld.getBoolean("hungry_cows")){
				dcworld.setRunnablepoop(V.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(V.plugin, dcworld.new RunnablePoop(), 0, (long) (20 * dcworld.getInt("hungry_cows_interval"))));
			}
			if(dcworld.getBoolean("auto_heal")){
				dcworld.setRunnableheal(V.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(V.plugin, dcworld.new RunnableHeal(), 0, (long) (20 * dcworld.getInt("auto_heal_interval"))));
			}
		}
	}

	public static void saveConfig(){
		Configuration config = null;

		if(!new File(V.plugin.getDataFolder(), "Worlds").exists()){
			new File(V.plugin.getDataFolder(), "Worlds").mkdir();
		}
		for(DCWorld dcworld : V.worlds){
			config = new Configuration(new File(V.plugin.getDataFolder(), "Worlds" + File.separator + dcworld.getName() + File.separator + "Config.yml"));
			config.setProperty("enviroment", dcworld.getEnviroment().toString());
			config.setProperty("seed", Long.toString(dcworld.getSeed()));
			config.save();
			for(DCConfiguration dcconfig : dcworld.getConfiguration()){
				config.load();
				config.setProperty(dcconfig.getConfiguration(), dcconfig.getValue());
				config.save();
			}
		}
	}

	public static void loadCommands(){
		Configuration commands = new Configuration(new File(V.plugin.getDataFolder(), "Commands.yml"));

		commands.load();
		Reset.resetCommands();
		if(commands.getKeys(null).size() > 0){
			for(String key : commands.getKeys(null)){
				if(commands.getStringList(key, null) != null){
					Misc.setAlias(key, commands.getStringList(key, new LinkedList<String>()));
				}
			}
		}
	}

	public static void saveCommands(){
		Configuration commands = new Configuration(new File(V.plugin.getDataFolder(), "Commands.yml"));

		commands.save();
		for(DCCommand dcc : V.commands){
			commands.load();
			commands.setProperty(dcc.getCommand(), dcc.getAlias());
			commands.save();
		}
	}

	public static void loadStrings(){
		Configuration strings = new Configuration(new File(V.plugin.getDataFolder(), "Strings.yml"));

		strings.load();
		Reset.resetStrings();
		if(strings.getKeys(null) != null){
			for(String key : strings.getKeys(null)){
				Misc.setString(key, strings.getString(key, ""));
			}
		}
	}

	public static void saveStrings(){
		Configuration strings = new Configuration(new File(V.plugin.getDataFolder(), "Strings.yml"));

		strings.save();
		for(DCString dcstring : V.strings){
			strings.load();
			strings.setProperty(dcstring.getId(), dcstring.getValue());
			strings.save();
		}
	}

	public static void loadItems(){
		File file = new File(V.plugin.getDataFolder(), "Items.yml");

		if(!file.exists()){
			Reset.resetItems();
		}else{
			Configuration items = new Configuration(new File(V.plugin.getDataFolder(), "Items.yml"));

			V.items.clear();
			items.load();
			for(String key : items.getKeys(null)){
				Misc.setItemAlias(key, items.getStringList(key, new LinkedList<String>()));
			}
		}
	}

	public static void saveItems(){
		Configuration items = new Configuration(new File(V.plugin.getDataFolder(), "Items.yml"));

		items.save();
		for(DCItem dcitem : V.items){
			items.load();
			items.setProperty(dcitem.getId(), dcitem.getAlias());
			items.save();
		}
	}

	public static void loadNames(){
		Configuration names = new Configuration(new File(V.plugin.getDataFolder(), "Names.yml"));

		names.load();
		V.names.clear();
		if(names.getKeys(null) != null){
			for(String key : names.getKeys(null)){
				Misc.setName(key, names.getString(key, ""));
			}
		}
	}

	public static void saveNames(){
		Configuration names = new Configuration(new File(V.plugin.getDataFolder(), "Names.yml"));

		names.save();
		for(DCString dcstring : V.names){
			names.load();
			names.setProperty(dcstring.getId(), dcstring.getValue());
			names.save();
		}
	}

	public static void loadPlayers(){
		Configuration players = new Configuration(new File(V.plugin.getDataFolder(), "Players.yml"));

		players.load();
		V.players.clear();
		if(players.getKeys(null) != null){
			for(String key : players.getKeys(null)){
				Misc.setPlayer(new DCPlayer(key, players.getString(key, "").split(" ")[0], players.getString(key, "").split(" ")[1] + " " + players.getString(key, "").split(" ")[2]));
			}
		}
	}

	public static void savePlayers(){
		Configuration players = new Configuration(new File(V.plugin.getDataFolder(), "Players.yml"));

		players.save();
		for(DCPlayer dcplayer : V.players){
			players.load();
			players.setProperty(dcplayer.getName(), dcplayer.getIp() + " " + dcplayer.getDate() + " " + dcplayer.getTime());
			players.save();
		}
	}

	public static void loadHomes(){
		Configuration homes = new Configuration(new File(V.plugin.getDataFolder(), "Homes.yml"));

		homes.load();
		V.homes.clear();
		if(homes.getKeys(null) != null){
			for(String key : homes.getKeys(null)){
				Misc.setHome(
						key,
						new Location(
								V.plugin.getServer().getWorld(homes.getString(key + ".W", "")),
								homes.getDouble(key + ".X", 0),
								homes.getDouble(key + ".Y", 0),
								homes.getDouble(key + ".Z", 0),
								(float) homes.getDouble(key + ".O", 0),
								(float) homes.getDouble(key + ".P", 0)
						)
				);
			}
		}
	}

	public static void saveHomes(){
		Configuration homes = new Configuration(new File(V.plugin.getDataFolder(), "Homes.yml"));

		homes.save();
		for(DCHome dchome : V.homes){
			homes.load();
			homes.setProperty(dchome.getName() + ".W", dchome.getWorld());
			homes.setProperty(dchome.getName() + ".X", dchome.getX());
			homes.setProperty(dchome.getName() + ".Y", dchome.getY());
			homes.setProperty(dchome.getName() + ".Z", dchome.getZ());
			homes.setProperty(dchome.getName() + ".O", dchome.getYaw());
			homes.setProperty(dchome.getName() + ".P", dchome.getPitch());
			homes.save();
		}
	}

	public static void loadWarps(){
		Configuration warps = new Configuration(new File(V.plugin.getDataFolder(), "Warps.yml"));

		warps.load();
		V.warps.clear();
		if(warps.getKeys(null) != null){
			for(String key : warps.getKeys(null)){
				Misc.setWarp(
						key,
						new Location(
								V.plugin.getServer().getWorld(warps.getString(key + ".W", "")),
								warps.getDouble(key + ".X", 0),
								warps.getDouble(key + ".Y", 0),
								warps.getDouble(key + ".Z", 0),
								(float) warps.getDouble(key + ".O", 0),
								(float) warps.getDouble(key + ".P", 0)
						)
				);
			}
		}
	}

	public static void saveWarps(){
		Configuration warps = new Configuration(new File(V.plugin.getDataFolder(), "Warps.yml"));

		warps.save();
		for(DCWarp dcwarp : V.warps){
			warps.load();
			warps.setProperty(dcwarp.getName() + ".W", dcwarp.getWorld());
			warps.setProperty(dcwarp.getName() + ".X", dcwarp.getX());
			warps.setProperty(dcwarp.getName() + ".Y", dcwarp.getY());
			warps.setProperty(dcwarp.getName() + ".Z", dcwarp.getZ());
			warps.setProperty(dcwarp.getName() + ".O", dcwarp.getYaw());
			warps.setProperty(dcwarp.getName() + ".P", dcwarp.getPitch());
			warps.save();
		}
	}
}