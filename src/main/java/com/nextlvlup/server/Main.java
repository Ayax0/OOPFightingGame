package com.nextlvlup.server;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

import com.nextlvlup.network.PacketListener;
import com.nextlvlup.network.base.Player;
import com.nextlvlup.network.packet.KeepAlivePacket;
import com.nextlvlup.network.packet.PlayerJoinPacket;
import com.nextlvlup.network.packet.PlayerMovePacket;
import com.nextlvlup.server.network.UDPServer;

public class Main {
	
	static HashMap<String, Player> players = new HashMap<>();

	public static void main(String[] args) {
		try {
			UDPServer server = new UDPServer(5000);
			server.start();
			
			server.addPacketListener(PlayerJoinPacket.class, new PacketListener<PlayerJoinPacket>() {

				@Override
				public void handler(PlayerJoinPacket obj, InetAddress address, int port) {
					System.out.println("player join");
					players.put(address.toString() + ":" + port, obj.getPlayer());
				}
				
			});
			
			server.addPacketListener(PlayerMovePacket.class, new PacketListener<PlayerMovePacket>() {

				@Override
				public void handler(PlayerMovePacket obj, InetAddress address, int port) {
					System.out.println("player move");
				}
				
			});
			
			server.addPacketListener(KeepAlivePacket.class, new PacketListener<KeepAlivePacket>() {

				@Override
				public void handler(KeepAlivePacket obj, InetAddress address, int port) {
					String socket = address.toString() + ":" + port;
					if(players.containsKey(socket)) {
						System.out.println("keep alive from player " + players.get(socket).getUuid());
					} else {
						System.out.println("keep alive from unidentified player");
					}
				}
			});
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
