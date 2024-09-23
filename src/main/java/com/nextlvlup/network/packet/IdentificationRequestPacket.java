package com.nextlvlup.network.packet;

import java.io.Serializable;

import lombok.Getter;

public class IdentificationRequestPacket implements Serializable {

	private static final long serialVersionUID = 5320273183347904441L;
	
	@Getter private byte[] addr;
	@Getter private int port;
	
	public IdentificationRequestPacket(byte[] addr, int port) {
		this.addr = addr;
		this.port = port;
	}

}
