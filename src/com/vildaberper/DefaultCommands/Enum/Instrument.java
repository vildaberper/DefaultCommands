package com.vildaberper.DefaultCommands.Enum;

import java.util.HashMap;
import java.util.Map;

public enum Instrument{
	PIANO((byte) 0x0),
	BASS_DRUM((byte) 0x1),
	SNARE_DRUM((byte) 0x2),
	STICKS((byte) 0x3),
	BASS_GUITAR((byte) 0x4);

	private final byte type;
	private final static Map<Byte, Instrument> types = new HashMap<Byte, Instrument>();

	private Instrument(byte type){
		this.type = type;
	}

	public byte getType(){
		return type;
	}

	static{
		for(Instrument instrument : Instrument.values()){
			types.put(instrument.getType(), instrument);
		}
	}
}