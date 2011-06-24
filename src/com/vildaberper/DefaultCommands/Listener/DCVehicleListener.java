package com.vildaberper.DefaultCommands.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

import com.vildaberper.DefaultCommands.Misc;
import com.vildaberper.DefaultCommands.Class.DCPortal;

public class DCVehicleListener extends VehicleListener{
	@Override
	public void onVehicleMove(VehicleMoveEvent event){
		if(event.getVehicle().getPassenger() != null && event.getVehicle().getPassenger() instanceof Player){
			if(event.getFrom().getBlock() != event.getTo().getBlock()){
				DCPortal dcportal = Misc.getPortal(event.getTo());

				if(dcportal != null && dcportal.getTarget((Player) event.getVehicle().getPassenger()) != null){
					event.getVehicle().teleport(dcportal.getTarget((Player) event.getVehicle().getPassenger()));
				}
			}
		}
	}
}