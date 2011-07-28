package com.vildaberper.DefaultCommands.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Perm;
import com.vildaberper.DefaultCommands.Util;
import com.vildaberper.DefaultCommands.V;
import com.vildaberper.DefaultCommands.Class.DCInteger;

public class DCBlockListener extends BlockListener{
	@Override
	public void onBlockPhysics(BlockPhysicsEvent event){
		if(event.isCancelled()){
			return;
		}
		if(Misc.isRemoveFloat(event.getBlock().getWorld())){
			event.setCancelled(true);
			return;
		}
	}

	@Override
	public void onBlockDamage(BlockDamageEvent event){
		if(event.isCancelled()){
			return;
		}
		if(Misc.getConfig(event.getBlock()).getBoolean("creative") || Misc.isCreative(event.getPlayer().getEntityId())){
			event.setInstaBreak(true);
		}
	}

	@Override
	public void onLeavesDecay(LeavesDecayEvent event){
		if(event.isCancelled()){
			return;
		}
		event.setCancelled(true);
		if(Misc.isRemoveFloat(event.getBlock().getWorld())){
			return;
		}
		Misc.leafDestroy(event.getBlock(), false);
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event){
		boolean log = event.getBlock().getType().equals(Material.LOG);

		Misc.afkMove(event.getPlayer());
		if(event.isCancelled()){
			return;
		}
		if(!Perm.hasPermission(event.getPlayer(), "dc.do." + event.getPlayer().getWorld().getName())){
			event.setCancelled(true);
			return;
		}
		if(Misc.getConfig(event.getBlock()).getBoolean("creative") || Misc.isCreative(event.getPlayer().getEntityId())){
			if(event.getBlock().getType().equals(Material.LEAVES)){
				event.setCancelled(true);
				Misc.leafDestroyCreative(event.getPlayer(), event.getBlock());
				return;
			}else if(event.getBlock().getType().equals(Material.LONG_GRASS)){
				event.setCancelled(true);
				Misc.longGrassDestroyCreative(event.getPlayer(), event.getBlock());
				return;
			}
			if(!event.getBlock().getType().equals(Material.DEAD_BUSH)){
				ItemStack drop = Util.getDrop(event.getBlock());

				if(drop != null){
					Util.addIfNotInInventory(event.getPlayer(), drop);
				}
			}
			event.getBlock().setType(Material.AIR);
			event.setCancelled(true);
		}else{
			if(event.getBlock().getType().equals(Material.LEAVES)){
				event.setCancelled(true);
				Misc.leafDestroy(event.getBlock(), event.getPlayer().getItemInHand().getType().equals(Material.SHEARS));
			}else if(event.getBlock().getType().equals(Material.LONG_GRASS)){
				event.setCancelled(true);
				Misc.longGrassDestroy(event.getBlock());
			}
		}
		if(log && Misc.getConfig(event.getBlock()).getBoolean("wood_gravity")){
			final Block block = event.getBlock();
			final DCInteger i = new DCInteger(0);
			final DCInteger id = new DCInteger(0);

			id.setInteger(
					V.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(
							V.plugin,
							new Runnable(){
								@Override
								public void run(){
									i.add(1);
									if(block.getWorld().getBlockAt(block.getX(), block.getY() + i.getInteger(), block.getZ()).getType().equals(Material.LOG)){
										byte data = block.getWorld().getBlockAt(block.getX(), block.getY() + i.getInteger(), block.getZ()).getData();

										block.getWorld().getBlockAt(block.getX(), block.getY() + i.getInteger(), block.getZ()).setType(Material.AIR);
										block.getWorld().getBlockAt(block.getX(), block.getY() + i.getInteger() - 1, block.getZ()).setType(Material.LOG);
										block.getWorld().getBlockAt(block.getX(), block.getY() + i.getInteger() - 1, block.getZ()).setData(data);
									}else{
										V.plugin.getServer().getScheduler().cancelTask(id.getInteger());
									}
								}
							},
							3,
							3
					)
			);
		}
	}

	@Override
	public void onBlockCanBuild(BlockCanBuildEvent event){
		if(event.getMaterial().equals(Material.FENCE) && V.better_fence){
			for(Entity entity : event.getBlock().getWorld().getEntities()){
				if(event.getBlock().equals(entity.getLocation().getBlock()) || event.getBlock().equals(entity.getLocation().getBlock().getRelative(BlockFace.UP))){
					return;
				}
			}
			event.setBuildable(true);
		}else if(event.getMaterial().equals(Material.PUMPKIN) && V.better_pumpkin){
			for(Entity entity : event.getBlock().getWorld().getEntities()){
				if(event.getBlock().equals(entity.getLocation().getBlock()) || event.getBlock().equals(entity.getLocation().getBlock().getRelative(BlockFace.UP))){
					return;
				}
			}
			event.setBuildable(true);
		}
	}

	@Override
	public void onBlockPlace(BlockPlaceEvent event){
		Misc.afkMove(event.getPlayer());
		if(event.isCancelled()){
			return;
		}
		if(!Perm.hasPermission(event.getPlayer(), "dc.do." + event.getPlayer().getWorld().getName())){
			event.setCancelled(true);
			return;
		}
		if(Misc.isCreative(event.getPlayer().getEntityId()) || Misc.getConfig(event.getBlock()).getBoolean("creative")){
			event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() + 1);
		}
	}

	@Override
	public void onBlockIgnite(BlockIgniteEvent event){
		if(event.isCancelled()){
			return;
		}
		if(event.getCause().equals(IgniteCause.LAVA)){
			if(Misc.getConfig(event.getBlock()).getBoolean("block_fire_lava")){
				event.setCancelled(true);
				return;
			}
		}else if(event.getCause().equals(IgniteCause.LIGHTNING)){
			if(Misc.getConfig(event.getBlock()).getBoolean("block_fire_lightning")){
				event.setCancelled(true);
				return;
			}
		}else if(event.getCause().equals(IgniteCause.SPREAD)){
			if(Misc.getConfig(event.getBlock()).getBoolean("block_fire_spread")){
				for(BlockFace bf : BlockFace.values()){
					if(event.getBlock().getRelative(bf).getType().equals(Material.FIRE)){
						event.getBlock().getRelative(bf).setType(Material.AIR);
					}
				}
				event.setCancelled(true);
				return;
			}
		}
	}
}