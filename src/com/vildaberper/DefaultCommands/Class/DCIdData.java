package com.vildaberper.DefaultCommands.Class;

public class DCIdData{
	private int id;
	private byte data;

	public DCIdData(int id, byte data){
		this.id = id;
		this.data = data;
	}

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setData(byte data){
		this.data = data;
	}
	public byte getData(){
		return data;
	}
}