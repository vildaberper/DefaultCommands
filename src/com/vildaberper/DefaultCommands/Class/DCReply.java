package com.vildaberper.DefaultCommands.Class;

public class DCReply{
	private String sender;
	private String reciever;

	public DCReply(String sender, String reciever){
		this.sender = sender;
		this.reciever = reciever;
	}

	public void setSender(String sender){
		this.sender = sender;
	}
	public String getSender(){
		return sender;
	}
	public void setReciever(String reciever){
		this.reciever = reciever;
	}
	public String getReciever(){
		return reciever;
	}
}