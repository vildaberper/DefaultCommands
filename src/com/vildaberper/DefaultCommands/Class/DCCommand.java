package com.vildaberper.DefaultCommands.Class;

import java.util.List;

public class DCCommand{
	private String command;
	private List<String> alias;

	public DCCommand(String command, List<String> alias){
		this.command = command;
		this.alias = alias;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCommand() {
		return command;
	}
	public void setAlias(List<String> alias) {
		this.alias = alias;
	}
	public List<String> getAlias() {
		return alias;
	}
}