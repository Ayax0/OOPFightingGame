package com.nextlvlup.network.packet;

import java.io.Serializable;
import java.util.UUID;

public class IdentificationRequestPacket implements Serializable {

	private static final long serialVersionUID = 5320273183347904441L;
	
	private String uuid;
	
	public IdentificationRequestPacket(UUID uuid) {
		this.uuid = uuid.toString();
	}
	
	public UUID getUUID() {
		return UUID.fromString(uuid);
	}

}
