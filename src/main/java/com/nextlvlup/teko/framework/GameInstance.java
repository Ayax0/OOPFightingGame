package com.nextlvlup.teko.framework;

import java.awt.Component;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.nextlvlup.base.Player;
import com.nextlvlup.packet.PlayerJoinPacket;
import com.nextlvlup.teko.network.PlayerJoinListener;
import com.nextlvlup.teko.network.UDPClient;

import lombok.Getter;

public class GameInstance extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2431403290509854638L;
	private int framerate;
	private JLayeredPane rootPane = new JLayeredPane();
	
	@Getter private UDPClient client;
	
	private ArrayList<StaticGameResource> staticResources = new ArrayList<StaticGameResource>();
	
	public GameInstance(int framerate) {
		this.framerate = framerate;
		
		this.setBounds(0, 0, 800, 600);
		this.setContentPane(rootPane);
		rootPane.setLayout(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try {
			client = new UDPClient();
			client.start();
			
			new PlayerJoinListener(client);
			
			client.sendPacket(new PlayerJoinPacket(new Player("SomePlayer")));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void attachGameLoop(GameLoop gameloop) {
		new Timer().scheduleAtFixedRate(gameloop, 0L, 1000L / this.framerate);
	}
	
	public ArrayList<StaticGameResource> getStaticResources() {
		return staticResources;
	}
	
	@Override
	public Component add(Component comp) {
		System.out.println(comp instanceof StaticGameResource);
		
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		return super.add(comp);
	}
	
	@Override
	public Component add(Component comp, int index) {
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		return super.add(comp, index);
	}
	
	@Override
	public void add(Component comp, Object constraints) {
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		super.add(comp, constraints);
	}
	
	@Override
	public void add(Component comp, Object constraints, int index) {
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		super.add(comp, constraints, index);
	}

}
