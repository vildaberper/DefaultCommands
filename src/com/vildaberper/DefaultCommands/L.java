package com.vildaberper.DefaultCommands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class L{
	private static File file = null;

	public static void initializeLog(){
		File dir = V.plugin.getDataFolder();

		file = new File(dir, V.plugin.getDescription().getName() + ".log");
		if(!file.exists()){
			try{
				dir.mkdir();
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public static void log(String string){
		try{
			FileWriter fw = new FileWriter(file, true);

			fw.write("[" + Util.getDateTime() + "] " + Util.removeColor(string) + System.getProperty("line.separator"));
			fw.flush();
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}