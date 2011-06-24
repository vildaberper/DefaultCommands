package com.vildaberper.DefaultCommands.Listener;

import org.bukkit.Material;
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
import com.vildaberper.DefaultCommands.V;

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
		if(Misc.getConfig(event.getBlock()).getBoolean("leaf_decay")){
			if(Misc.getConfig(event.getBlock()).getBoolean("leaf_drop_apple") && Math.random() <= Misc.getConfig(event.getBlock()).getDouble("leaf_drop_apple_rate")){
				event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			if(Misc.getConfig(event.getBlock()).getBoolean("leaf_drop_sapling") && Math.random() <= Misc.getConfig(event.getBlock()).getDouble("leaf_drop_sapling_rate")){
				if(event.getBlock().getData() == (byte) 1 || event.getBlock().getData() == (byte) 9){
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.SAPLING, 1, (short) 1, Byte.parseByte("1")));
				}else if(event.getBlock().getData() == (byte) 2 || event.getBlock().getData() == (byte) 10){
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.SAPLING, 1, (short) 1, Byte.parseByte("2")));
				}else{
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.SAPLING, 1, (short) 1, Byte.parseByte("0")));
				}
			}
			event.getBlock().setType(Material.AIR);
		}
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event){
		if(event.isCancelled()){
			return;
		}
		if(!Perm.hasPermission(event.getPlayer(), "dc.do." + event.getPlayer().getWorld().getName())){
			event.setCancelled(true);
			return;
		}
		if(event.getBlock().getType().equals(Material.LEAVES)){
			event.setCancelled(true);
			if(Misc.getConfig(event.getBlock()).getBoolean("leaf_drop_apple") && Math.random() <= Misc.getConfig(event.getBlock()).getDouble("leaf_drop_apple_rate")){
				event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			if(Misc.getConfig(event.getBlock()).getBoolean("leaf_drop_sapling") && Math.random() <= Misc.getConfig(event.getBlock()).getDouble("leaf_drop_sapling_rate")){
				if(event.getBlock().getData() == (byte) 1 || event.getBlock().getData() == (byte) 9){
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.SAPLING, 1, (short) 1, Byte.parseByte("1")));
				}else if(event.getBlock().getData() == (byte) 2 || event.getBlock().getData() == (byte) 10){
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.SAPLING, 1, (short) 1, Byte.parseByte("2")));
				}else{
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.SAPLING, 1, (short) 1, Byte.parseByte("0")));
				}
			}
			event.getBlock().setType(Material.AIR);
		}
		if(Misc.getConfig(event.getBlock()).getBoolean("creative") || Misc.isCreative(event.getPlayer().getEntityId())){
			boolean add = true;

			if(event.getBlock().getTypeId() != 18){
				for(ItemStack is : event.getPlayer().getInventory().getContents()){
					if(is != null && is.getType().equals(event.getBlock().getType()) && (":6:17:35:43:44:".indexOf(":" + event.getBlock().getTypeId() + ":") == -1 || (":6:17:35:43:44:".indexOf(":" + event.getBlock().getTypeId() + ":") != -1 && is.getData().equals(event.getBlock().getData())))){
						add = false;
						break;
					}
				}
				if(add){
					event.getPlayer().getInventory().addItem(new ItemStack(event.getBlock().getType(), 1, (short) 0, event.getBlock().getData()));
				}
			}
			event.getBlock().setType(Material.AIR);
		}
	}

	@Override
	public void onBlockCanBuild(BlockCanBuildEvent event){
		if(event.getMaterial().equals(Material.FENCE) && V.better_fence){
			for(Entity entity : event.getBlock().getWorld().getEntities()){
				if(event.getBlock().equals(entity.getLocation().getBlock()) || event.getBlock().equals(entity.getLocation().getBlock().getFace(BlockFace.UP))){
					return;
				}
			}
			event.setBuildable(true);
		}else if(event.getMaterial().equals(Material.PUMPKIN) && V.better_pumpkin){
			for(Entity entity : event.getBlock().getWorld().getEntities()){
				if(event.getBlock().equals(entity.getLocation().getBlock()) || event.getBlock().equals(entity.getLocation().getBlock().getFace(BlockFace.UP))){
					return;
				}
			}
			event.setBuildable(true);
		}
	}

	@Override
	public void onBlockPlace(BlockPlaceEvent event){
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
					if(event.getBlock().getFace(bf).getType().equals(Material.FIRE)){
						event.getBlock().getFace(bf).setType(Material.AIR);
					}
				}
				event.setCancelled(true);
				return;
			}
		}
	}
}