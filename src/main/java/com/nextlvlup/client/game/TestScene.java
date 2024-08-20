package com.nextlvlup.client.game;

import java.awt.Color;

import com.nextlvlup.client.framework.GameInstance;
import com.nextlvlup.client.framework.StaticGameResource;

public class TestScene {
	
	public TestScene() {
		GameInstance instance = new GameInstance(30);
		TestGameLoop gameloop = new TestGameLoop(instance);
		instance.attachGameLoop(gameloop);
		
		Character character = new Character(instance);
		instance.add(character);
		
		
		StaticGameResource platform = new StaticGameResource();
		platform.setBackground(Color.RED);
		platform.setOpaque(true);
		platform.setLocation(0, 300);
		platform.setSize(400, 50);
		instance.add(platform);
		
		gameloop.register(character);
	}

}
