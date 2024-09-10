package com.nextlvlup.client.framework.player;

import com.nextlvlup.client.framework.PhysicGameResource;
import com.nextlvlup.network.base.Player;

import lombok.Getter;

public class BasePlayer extends PhysicGameResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4653425014477548053L;

	@Getter Player player;
	
	public BasePlayer(Player player) {
		this.player = player;
		
		this.setTexture("char/basic.png");
		this.setSize(60, 80);
		this.setLocation(10, 10);
	}
	
}
