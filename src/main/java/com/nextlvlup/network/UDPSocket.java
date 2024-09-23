package com.nextlvlup.network;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.Getter;

public class UDPSocket implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7830371960208421585L;
	
	@Getter private byte[] addr;
	@Getter private int port;
	
	public UDPSocket(byte[] addr, int port) {
		this.addr = addr;
		this.port = port;
	}
	
	@Override
	public int hashCode() {
		String hash = new String(this.addr) + ":" + this.port;
		return hash.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof UDPSocket)) return false;
		return this.hashCode() == obj.hashCode();
	}
	
	public InetAddress getAddress() throws UnknownHostException {
		return InetAddress.getByAddress(addr);
	}

}
