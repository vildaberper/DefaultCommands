package com.vildaberper.DefaultCommands.Runnable;

import com.vildaberper.DefaultCommands.SaveLoad;

public class ConfigSave implements Runnable{
	@Override
	public void run(){
		SaveLoad.saveAll();
	}
}