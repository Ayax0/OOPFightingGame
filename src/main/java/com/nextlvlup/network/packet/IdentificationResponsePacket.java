package com.nextlvlup.network.packet;

import java.util.UUID;

import com.nextlvlup.network.base.Player;

public class IdentificationResponsePacket extends PlayerJoinPacket {
	
	private static final long serialVersionUID = 6134916954890883050L;
	
	private String uuid;

	public IdentificationResponsePacket(UUID uuid, Player player) {
		super(player);
		
		this.uuid = uuid.toString();
	}
	
	public UUID getUUID() {
		return UUID.fromString(uuid);
	}

}
