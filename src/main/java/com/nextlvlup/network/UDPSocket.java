package com.nextlvlup.network;

import java.net.InetAddress;

import lombok.Getter;

public class UDPSocket {
	
	@Getter private InetAddress address;
	@Getter private int port;
	
	public UDPSocket(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

}
