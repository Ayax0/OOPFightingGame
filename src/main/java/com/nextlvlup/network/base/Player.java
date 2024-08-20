package com.nextlvlup.network.base;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8484132420791020859L;
	@Getter UUID 	uuid;
	@Getter String 	name;
	
	public Player(String name) {
		this.uuid = UUID.randomUUID();
		this.name = name;
	}
	
	public Player(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

}
