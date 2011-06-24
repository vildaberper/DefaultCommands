package com.vildaberper.DefaultCommands.Class;

public class DCPlayer{
	private String name;
	private String ip;
	private String date;
	private String time;

	public DCPlayer(String name, String ip, String datetime){
		setName(name);
		setIp(ip);
		setDate(datetime.split(" ")[0]);
		setTime(datetime.split(" ")[1]);
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getIp(){
		return ip;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return date;
	}
	public void setTime(String time){
		this.time = time;
	}
	public String getTime(){
		return time;
	}
}