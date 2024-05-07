package com.nextlvlup.teko.game;

import com.nextlvlup.teko.framework.GameInstance;

public class TestScene {
	
	public TestScene() {
		GameInstance instance = new GameInstance(30);
		TestGameLoop gameloop = new TestGameLoop(instance);
		instance.attachGameLoop(gameloop);
		
		Character character = new Character(instance);
		instance.add(character);
		gameloop.register(character);
	}

}
