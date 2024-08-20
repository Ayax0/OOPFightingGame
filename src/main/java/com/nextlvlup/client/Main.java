package com.nextlvlup.client;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nextlvlup.client.network.UDPClient;
import com.nextlvlup.network.base.Player;
import com.nextlvlup.network.packet.PlayerJoinPacket;

public class Main {
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		// new TestScene();
		
		try {
			UDPClient client = new UDPClient();
			client.start();
			
			client.sendPacket(new PlayerJoinPacket(new Player("test")));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
