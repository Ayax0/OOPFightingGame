package com.nextlvlup.network.packet;

import java.io.Serializable;

import javax.vecmath.Vector2d;

import com.nextlvlup.network.base.Player;

import lombok.Getter;

public class PlayerMovePacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8525002348203863511L;
	
	@Getter Player player;
	@Getter int x;
	@Getter int y;
	
	public PlayerMovePacket(Player player, int x, int y) {
		this.player = player;
		this.x = x;
		this.y = y;
	}

}
