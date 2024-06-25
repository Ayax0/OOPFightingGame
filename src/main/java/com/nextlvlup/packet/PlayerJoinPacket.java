package com.nextlvlup.packet;

import java.io.Serializable;
import com.nextlvlup.base.Player;

import lombok.Getter;

public class PlayerJoinPacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7512132922224174494L;
	
	@Getter Player player;
	
	public PlayerJoinPacket(Player player) {
		this.player = player;
	}

}
