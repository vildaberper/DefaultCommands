package com.vildaberper.DefaultCommands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.block.Block;

import com.vildaberper.DefaultCommands.Class.DCIdData;

public class Edit{
	public static List<Block> getWalls(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z1));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z2));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x1, i2, i3));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x2, i2, i3));
			}
		}
		return blocks;
	}

	public static List<Block> getReplace(Block block1, Block block2, List<DCIdData> replace){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				for(int i3 = z1; i3 <= z2; i3++){
					for(DCIdData dciddata : replace){
						if(block1.getWorld().getBlockAt(i1, i2, i3).getTypeId() == dciddata.getId() && block1.getWorld().getBlockAt(i1, i2, i3).getData() == dciddata.getData()){
							blocks.add(block1.getWorld().getBlockAt(i1, i2, i3));
						}
					}
				}
			}
		}
		return blocks;
	}

	public static List<Block> getCuboid(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				for(int i3 = z1; i3 <= z2; i3++){
					blocks.add(block1.getWorld().getBlockAt(i1, i2, i3));
				}
			}
		}
		return blocks;
	}

	public static List<Block> getCuboidwireframe(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y1, z1));
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y1, z2));
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y2, z1));
		}
		for(int i1 = x1; i1 <= x2; i1++){
			blocks.add(block1.getWorld().getBlockAt(i1, y2, z2));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x1, i2, z1));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x2, i2, z1));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x1, i2, z2));
		}
		for(int i2 = y1; i2 <= y2; i2++){
			blocks.add(block1.getWorld().getBlockAt(x2, i2, z2));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x1, y1, i3));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x1, y2, i3));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x2, y1, i3));
		}
		for(int i3 = z1; i3 <= z2; i3++){
			blocks.add(block1.getWorld().getBlockAt(x2, y2, i3));
		}
		return blocks;
	}

	public static List<Block> getCuboidhollow(Block block1, Block block2){
		List<Block> blocks = new LinkedList<Block>();
		int x1, x2, y1, y2, z1, z2;

		if(block1.getX() < block2.getX()){
			x1 = block1.getX();
			x2 = block2.getX();
		}else{
			x2 = block1.getX();
			x1 = block2.getX();
		}
		if(block1.getY() < block2.getY()){
			y1 = block1.getY();
			y2 = block2.getY();
		}else{
			y2 = block1.getY();
			y1 = block2.getY();
		}
		if(block1.getZ() < block2.getZ()){
			z1 = block1.getZ();
			z2 = block2.getZ();
		}else{
			z2 = block1.getZ();
			z1 = block2.getZ();
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z1));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i2 = y1; i2 <= y2; i2++){
				blocks.add(block1.getWorld().getBlockAt(i1, i2, z2));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x1, i2, i3));
			}
		}
		for(int i2 = y1; i2 <= y2; i2++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(x2, i2, i3));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(i1, y1, i3));
			}
		}
		for(int i1 = x1; i1 <= x2; i1++){
			for(int i3 = z1; i3 <= z2; i3++){
				blocks.add(block1.getWorld().getBlockAt(i1, y2, i3));
			}
		}
		return blocks;
	}

	public static List<Block> getSpherehollow(Block block1, int radius){
		List<Block> blocks = new LinkedList<Block>();
		double xi = block1.getLocation().getX() + 0.5;
		double yi = block1.getLocation().getY() + 0.5;
		double zi = block1.getLocation().getZ() + 0.5;

		for(int v1 = 0; v1 <= 90; v1++){
			double y = Math.sin(Math.PI / 180 * v1) * radius;
			double r = Math.cos(Math.PI / 180 * v1) * radius;

			if(v1 == 90){
				r = 0;
			}
			for(int v2 = 0; v2 <= 90; v2++){
				double x = Math.sin(Math.PI / 180 * v2) * r;
				double z = Math.cos(Math.PI / 180 * v2) * r;

				if(v2 == 90){
					z = 0;
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi + z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi + z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi + z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi + y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi + x), (int) (yi - y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi - z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi + y), (int) (zi - z)));
				}
				if(!blocks.contains(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi + z)))){
					blocks.add(block1.getWorld().getBlockAt((int) (xi - x), (int) (yi - y), (int) (zi + z)));
				}
			}
		}
		return blocks;
	}
}