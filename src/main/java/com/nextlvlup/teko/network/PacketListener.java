package com.nextlvlup.teko.network;

import java.net.InetAddress;

public abstract class PacketListener {
	
	protected UDPClient client;
	
	public PacketListener(UDPClient client) {
		this.client = client;
		this.client.addPacketListener(this);
	}
	
	public abstract void handler(Object obj, InetAddress address, int port);

}
