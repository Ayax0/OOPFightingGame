package com.nextlvlup.network.packet;

import java.io.Serializable;

import lombok.Getter;

public class KeepAlivePacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3349979344210461707L;

	@Getter long timestamp;
	
	public KeepAlivePacket() {
		this.timestamp = System.currentTimeMillis();
	}
	
}
