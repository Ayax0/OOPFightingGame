package com.nextlvlup.teko.framework;

import java.util.ArrayList;
import java.util.TimerTask;

public abstract class GameLoop extends TimerTask {
	
	private GameInstance instance;
	protected ArrayList<DynamicGameResource> resources = new ArrayList<DynamicGameResource>();
	
	public GameLoop(GameInstance instance) {
		this.instance = instance;
	}
	
	public void register(DynamicGameResource resource) {
		this.resources.add(resource);
	}

}
