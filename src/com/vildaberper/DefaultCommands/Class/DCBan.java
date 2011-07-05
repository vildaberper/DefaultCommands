package com.vildaberper.DefaultCommands.Class;

public class DCBan{
	private String player;
	private String message;

	public DCBan(String player, String message){
		setPlayer(player);
		setMessage(message);
	}

	public void setPlayer(String player){
		this.player = player;
	}
	public String getPlayer(){
		return player;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
}