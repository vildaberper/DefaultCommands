package com.vildaberper.DefaultCommands.Class;

public class DCConfiguration{
	private String configuration;
	private Object value;

	public DCConfiguration(String configuration, Object value){
		this.configuration = configuration;
		this.value = value;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Object getValue() {
		return value;
	}
}