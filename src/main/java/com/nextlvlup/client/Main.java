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
import com.nextlvlup.network.packet.PlayerJoinPacket;
import com.nextlvlup.network.packet.TimeoutPacket;

public class Main {
	
	static HashMap<UDPSocket, BasePlayer> players = new HashMap<>();

	public static void main(String[] args) {
		try {
			UDPClient client = new UDPClient();
			client.start();
			
			GameFrame frame = new GameFrame();
			frame.start();
			
			PhysicGameResource test = new PhysicGameResource();
			test.setTexture("char/basic.png");
			test.setLocation(10, 10);
			test.setSize(60, 80);
			frame.add(test);
			
			frame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					super.keyPressed(e);
					
					// Space Key
					if(e.getKeyCode() == 32)
						test.vector.setY(-10);
					
					// A Key
					if(e.getKeyCode() == 65)
						test.vector.setX(-5);
						
					// D Key
					if(e.getKeyCode() == 68)
						test.vector.setX(5);
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					super.keyReleased(e);
					
					// A Key
					if(e.getKeyCode() == 65 && test.vector.getX() < 0 )
						test.vector.setX(0);
						
					// D Key
					if(e.getKeyCode() == 68 && test.vector.getX() > 0)
						test.vector.setX(0);
				}
			});
			
			StaticGameResource test2 = new StaticGameResource();
			test2.setLocation(10, 300);
			test2.setSize(300, 10);
			test2.setBackground(Color.GREEN);
			test2.setForeground(Color.RED);
			test2.setText("test");
			frame.add(test2);
			
			client.sendPacket(new PlayerJoinPacket(new Player("test")));
			
			client.addPacketListener(PlayerJoinPacket.class, new PacketListener<PlayerJoinPacket>() {
				
				@Override
				public void handler(PlayerJoinPacket obj, UDPSocket socket) {
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
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
