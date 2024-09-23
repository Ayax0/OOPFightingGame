package com.nextlvlup.server;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.nextlvlup.network.PacketListener;
import com.nextlvlup.network.UDPSocket;
import com.nextlvlup.network.packet.IdentificationRequestPacket;
import com.nextlvlup.network.packet.IdentificationResponsePacket;
import com.nextlvlup.network.packet.PlayerJoinPacket;
import com.nextlvlup.network.packet.PlayerMovePacket;
import com.nextlvlup.server.network.UDPServer;

public class Main {

	public static void main(String[] args) {
		try {
			UDPServer server = new UDPServer(5000);
			server.start();
			
			HashMap<UUID, UDPSocket> ident = new HashMap<>();
			
			server.addPacketListener(PlayerJoinPacket.class, new PacketListener<PlayerJoinPacket>() {

				@Override
				public void handler(PlayerJoinPacket obj, UDPSocket socket) {
					System.out.println("player join");
					server.broadcast(obj, socket);
					
					UUID uuid = UUID.randomUUID();
					
					ident.put(uuid, socket);
					server.broadcast(new IdentificationRequestPacket(uuid), socket);
					
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							ident.remove(uuid);
						}
					}, 10000);
				}
				
			});
			
			server.addPacketListener(PlayerMovePacket.class, new PacketListener<PlayerMovePacket>() {

				@Override
				public void handler(PlayerMovePacket obj, UDPSocket socket) {
					server.broadcast(obj, socket);
				}
				
			});
			
			server.addPacketListener(IdentificationResponsePacket.class, new PacketListener<IdentificationResponsePacket>() {

				@Override
				public void handler(IdentificationResponsePacket obj, UDPSocket socket) {
					if(!ident.containsKey(obj.getUUID())) return;
					
					server.sendPacket(ident.get(obj.getUUID()), new PlayerJoinPacket(obj.getPlayer()));
				}
				
			});
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
