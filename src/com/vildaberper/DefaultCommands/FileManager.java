package com.vildaberper.DefaultCommands;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

public class FileManager{
	@SuppressWarnings("deprecation")
	public static List<String> readTextFile(File target){
		try{
			List<String> rows = new LinkedList<String>();
			FileInputStream fis = new FileInputStream(target);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);

			while(dis.available() != 0){
				rows.add(dis.readLine());
			}
			fis.close();
			bis.close();
			dis.close();
			return rows;
		}catch(Exception e){
			return null;
		}
	}

	public static void downloadFile(String url, File target){
		OutputStream out = null;
		URLConnection conn = null;
		InputStream in = null;
		try{
			URL _url = new URL(url);
			byte[] buffer = new byte[1024];
			int numRead;

			out = new BufferedOutputStream(new FileOutputStream(target));
			conn = _url.openConnection();
			in = conn.getInputStream();
			while((numRead = in.read(buffer)) != -1){
				out.write(buffer, 0, numRead);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(in != null){
					in.close();
				}
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void deleteDirectory(File directory){
		if(directory.exists()){
			File[] files = directory.listFiles();

			if(files != null){
				for(int i = 0; i < files.length; i++){
					if(files[i].isDirectory()){
						deleteDirectory(files[i]);
					}else{
						files[i].delete();
					}
				}
			}
			directory.delete();
		}
	}
}