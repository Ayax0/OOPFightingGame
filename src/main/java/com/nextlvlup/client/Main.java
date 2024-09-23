package com.nextlvlup.client;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import com.nextlvlup.client.framework.GameFrame;
import com.nextlvlup.client.framework.PhysicGameResource;
import com.nextlvlup.client.framework.StaticGameResource;
import com.nextlvlup.client.framework.player.BasePlayer;
import com.nextlvlup.client.network.UDPClient;
import com.nextlvlup.network.PacketListener;
import com.nextlvlup.network.UDPSocket;
import com.nextlvlup.network.base.Player;
import com.nextlvlup.network.packet.IdentificationRequestPacket;
import com.nextlvlup.network.packet.IdentificationResponsePacket;
import com.nextlvlup.network.packet.PlayerJoinPacket;
import com.nextlvlup.network.packet.PlayerMovePacket;
import com.nextlvlup.network.packet.TimeoutPacket;

public class Main {
	
	static HashMap<UDPSocket, BasePlayer> players = new HashMap<>();

	public static void main(String[] args) {
		try {
			UDPClient client = new UDPClient();
			client.start();
			
			GameFrame frame = new GameFrame();
			frame.start();
			
			Player player = new Player("test");
			
			PhysicGameResource character = new PhysicGameResource() {

				private static final long serialVersionUID = -440256921387586272L;

				@Override
				public void move() {
					client.sendPacket(new PlayerMovePacket(player, this.getX(), this.getY()));
				}
				
			};
			
			character.setTexture("char/basic.png");
			character.setLocation(10, 10);
			character.setSize(60, 80);
			frame.add(character);
			
			frame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					super.keyPressed(e);
					
					// Space Key
					if(e.getKeyCode() == 32)
						character.vector.setY(-10);
					
					// A Key
					if(e.getKeyCode() == 65)
						character.vector.setX(-5);
						
					// D Key
					if(e.getKeyCode() == 68)
						character.vector.setX(5);
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					super.keyReleased(e);
					
					// A Key
					if(e.getKeyCode() == 65 && character.vector.getX() < 0 )
						character.vector.setX(0);
						
					// D Key
					if(e.getKeyCode() == 68 && character.vector.getX() > 0)
						character.vector.setX(0);
				}
			});
			
			StaticGameResource test2 = new StaticGameResource();
			test2.setLocation(10, 300);
			test2.setSize(300, 10);
			test2.setBackground(Color.GREEN);
			test2.setForeground(Color.RED);
			test2.setText("test");
			frame.add(test2);
			
			client.sendPacket(new PlayerJoinPacket(player));
			
			client.addPacketListener(PlayerJoinPacket.class, new PacketListener<PlayerJoinPacket>() {
				
				@Override
				public void handler(PlayerJoinPacket obj, UDPSocket socket) {
					if(players.containsKey(socket)) return;
					
					System.out.println("player join on client");
					
					BasePlayer player = new BasePlayer(obj.getPlayer());
					frame.add(player);
					
					players.put(socket, player);
				}
				
			});
			
			client.addPacketListener(TimeoutPacket.class, new PacketListener<TimeoutPacket>() {

				@Override
				public void handler(TimeoutPacket obj, UDPSocket socket) {
					if(players.containsKey(socket)) {
						System.out.println("player timeout on client");
						
						BasePlayer player = players.get(socket);
						frame.remove(player);
						frame.repaint();
						
						players.remove(socket);
					} else {
						System.err.println("unidentified player timeout!");
					}
				}
				
			});
			
			client.addPacketListener(PlayerMovePacket.class, new PacketListener<PlayerMovePacket>() {

				@Override
				public void handler(PlayerMovePacket obj, UDPSocket socket) {
					if(players.containsKey(socket)) {
						BasePlayer player = players.get(socket);
						System.out.println("update player");
						player.setX(obj.getX());
						player.setY(obj.getY());
						frame.repaint();
					}
				}
				
			});
			
			client.addPacketListener(IdentificationRequestPacket.class, new PacketListener<IdentificationRequestPacket>() {

				@Override
				public void handler(IdentificationRequestPacket obj, UDPSocket socket) {
					client.sendPacket(new IdentificationResponsePacket(obj.getUUID(), player));
				}
				
			});
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
