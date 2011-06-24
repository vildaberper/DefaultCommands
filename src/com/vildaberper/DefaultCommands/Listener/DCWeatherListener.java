package com.vildaberper.DefaultCommands.Listener;

import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherListener;

import com.vildaberper.DefaultCommands.Misc;

public class DCWeatherListener extends WeatherListener{
	@Override
	public void onWeatherChange(WeatherChangeEvent event){
		if(event.isCancelled()){
			return;
		}
		if(!Misc.getConfig(event.getWorld()).getBoolean("weather_change")){
			event.setCancelled(true);
		}
	}
}