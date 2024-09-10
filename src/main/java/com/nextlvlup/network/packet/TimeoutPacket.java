package com.nextlvlup.network.packet;

import java.io.Serializable;

import com.nextlvlup.network.UDPSocket;

import lombok.Getter;

public class TimeoutPacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -814495609355845900L;
	
	@Getter UDPSocket socket;
	
	public TimeoutPacket(UDPSocket socket) {
		this.socket = socket;
	}

}
