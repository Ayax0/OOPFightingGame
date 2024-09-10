package com.nextlvlup.network;

import java.io.Serializable;
import java.net.InetAddress;

public abstract class PacketListener<T extends Serializable> {
	
	public abstract void handler(T obj, UDPSocket socket);

}
