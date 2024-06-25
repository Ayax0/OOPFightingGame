package com.nextlvlup.teko.network;

import java.net.InetAddress;

import com.nextlvlup.packet.PlayerJoinPacket;

public class PlayerJoinListener extends PacketListener {

	public PlayerJoinListener(UDPClient client) {
		super(client);
	}

	@Override
	public void handler(Object obj, InetAddress address, int port) {
		if(!(obj instanceof PlayerJoinPacket)) return;
		
		PlayerJoinPacket packet = (PlayerJoinPacket) obj;
		System.out.println("Player " + packet.getPlayer().getName() + " joined with uuid " + packet.getPlayer().getUuid().toString());
	}

}
