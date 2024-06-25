package com.nextlvlup.packet;

import java.io.Serializable;

import javax.vecmath.Vector2d;

import com.nextlvlup.base.Player;

import lombok.Getter;

public class PlayerMovePacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8525002348203863511L;
	
	@Getter Player player;
	@Getter Vector2d location;
	
	public PlayerMovePacket(Player player, Vector2d location) {
		this.player = player;
		this.location = location;
	}

}
