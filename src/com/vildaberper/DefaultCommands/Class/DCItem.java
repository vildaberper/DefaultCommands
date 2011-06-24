package com.vildaberper.DefaultCommands.Class;

import java.util.List;

public class DCItem{
	private String id;
	private List<String> alias;

	public DCItem(String id, List<String> alias){
		setId(id);
		this.alias = alias;
	}

	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setAlias(List<String> alias){
		this.alias = alias;
	}
	public List<String> getAlias(){
		return alias;
	}
}