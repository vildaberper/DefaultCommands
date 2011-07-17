package com.vildaberper.DefaultCommands.Class;

public class DCInteger{
	private int integer = 0;

	public DCInteger(int integer){
		this.integer = integer;
	}

	public void setInteger(int integer){
		this.integer = integer;
	}
	public int getInteger(){
		return integer;
	}

	public void add(int amount){
		integer += amount;
	}
	public void subtract(int amount){
		integer -= amount;
	}
}