package com.nextlvlup.network.packet;

import java.io.Serializable;

import lombok.Getter;

public class TestPacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8471682078162407741L;
	
	@Getter private String message;
	
	public TestPacket(String message) {
		this.message = message;
	}

}
