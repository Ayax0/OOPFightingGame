package com.nextlvlup.client.framework;

import java.util.ArrayList;
import java.util.TimerTask;

public abstract class GameLoop extends TimerTask {
	
	private GameFrame instance;
	protected ArrayList<DynamicGameResource> resources = new ArrayList<DynamicGameResource>();
	
	public GameLoop(GameFrame instance) {
		this.instance = instance;
	}
	
	public void register(DynamicGameResource resource) {
		this.resources.add(resource);
	}

}
