package com.vildaberper.DefaultCommands.Class;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.InventoryPlayer;
import net.minecraft.server.ItemStack;

import com.vildaberper.DefaultCommands.Util;

public class DCInventoryPlayer extends InventoryPlayer implements IInventory{
	public ItemStack[] a = new ItemStack[36];
	public ItemStack[] b = new ItemStack[4];
	public int c = 0;
	public EntityHuman d;
	public boolean e = false, update = true;
	public String name = "";

	public DCInventoryPlayer(String name, EntityHuman entityhuman){
		super(entityhuman);
		this.name = name;
		a = entityhuman.inventory.getContents();
		b = entityhuman.inventory.getArmorContents();
		d = entityhuman;
	}

	public DCInventoryPlayer(String name){
		super(null);
		this.name = name;
		a = new ItemStack[43];
		b = new ItemStack[4];
		d = null;
	}

	public DCInventoryPlayer(String name, ItemStack[] itemstack){
		super(null);
		this.name = name;
		a = itemstack;
		b = new ItemStack[4];
		d = null;
	}

	public DCInventoryPlayer(String name, org.bukkit.inventory.ItemStack[] itemstack){
		super(null);
		this.name = name;
		a = Util.convertItemStack(itemstack);
		b = new ItemStack[4];
		d = null;
	}

	@Override
	public boolean a_(EntityHuman arg0){
		update();
		return true;
	}

	@Override
	public ItemStack[] getContents(){
		return a;
	}

	@Override
	public ItemStack getItem(int arg0){
		return a[arg0];
	}

	@Override
	public int getMaxStackSize(){
		return 64;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public int getSize(){
		return 36;
	}

	@Override
	public void setItem(int arg0, ItemStack arg1){
		a[arg0] = arg1;
		if(d != null && update){
			d.inventory.items[arg0] = arg1;
		}
	}

	@Override
	public void update(){
		if(d != null && update){
			d.inventory.items = a;
			d.inventory.armor = b;
		}
	}
}