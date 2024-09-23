package com.nextlvlup.server;

import java.net.SocketException;
import com.nextlvlup.network.PacketListener;
import com.nextlvlup.network.UDPSocket;
import com.nextlvlup.network.packet.PlayerJoinPacket;
import com.nextlvlup.network.packet.PlayerMovePacket;
import com.nextlvlup.server.network.UDPServer;

public class Main {

	public static void main(String[] args) {
		try {
			UDPServer server = new UDPServer(5000);
			server.start();
			
			server.addPacketListener(PlayerJoinPacket.class, new PacketListener<PlayerJoinPacket>() {

				@Override
				public void handler(PlayerJoinPacket obj, UDPSocket socket) {
					System.out.println("player join");
					server.broadcast(obj, socket);
				}
				
			});
			
			server.addPacketListener(PlayerMovePacket.class, new PacketListener<PlayerMovePacket>() {

				@Override
				public void handler(PlayerMovePacket obj, UDPSocket socket) {
					server.broadcast(obj, socket);
				}
				
			});
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
