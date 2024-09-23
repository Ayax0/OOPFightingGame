package com.nextlvlup.client.framework;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.nextlvlup.client.network.UDPClient;
import lombok.Getter;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2431403290509854638L;
	private JLayeredPane rootPane = new JLayeredPane();
	
	@Getter private UDPClient client;
	
	private ArrayList<StaticGameResource> staticResources = new ArrayList<StaticGameResource>();
	private ArrayList<DynamicGameResource> dynamicResources = new ArrayList<DynamicGameResource>();
	
	private ArrayList<DynamicGameResource> dynamicResourceStash = new ArrayList<DynamicGameResource>();
	
	public GameFrame() {
		this.setBounds(0, 0, 800, 600);
		this.setContentPane(rootPane);
		rootPane.setLayout(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void start() {
		new Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				for(DynamicGameResource resource : dynamicResources) {
					resource.update(staticResources);
				}
				
				if(dynamicResourceStash.size() > 0) {
					dynamicResources.addAll(dynamicResourceStash);
					dynamicResourceStash.clear();
				}
			}
		}, 0L, 1000L / 30);
	}
	
	public ArrayList<StaticGameResource> getStaticResources() {
		return staticResources;
	}
	
	@Override
	public Component add(Component comp) {
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		if(comp instanceof DynamicGameResource)
			dynamicResourceStash.add((DynamicGameResource) comp);
		
		return super.add(comp);
	}
	
	@Override
	public Component add(Component comp, int index) {
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		if(comp instanceof DynamicGameResource)
			dynamicResourceStash.add((DynamicGameResource) comp);
		
		return super.add(comp, index);
	}
	
	@Override
	public void add(Component comp, Object constraints) {
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		if(comp instanceof DynamicGameResource)
			dynamicResourceStash.add((DynamicGameResource) comp);
		
		super.add(comp, constraints);
	}
	
	@Override
	public void add(Component comp, Object constraints, int index) {
		if(comp instanceof StaticGameResource) 
			staticResources.add((StaticGameResource) comp);
		
		if(comp instanceof DynamicGameResource)
			dynamicResourceStash.add((DynamicGameResource) comp);
		
		super.add(comp, constraints, index);
	}

}
