package com.nextlvlup.client.game;

import com.nextlvlup.client.framework.DynamicGameResource;
import com.nextlvlup.client.framework.GameInstance;
import com.nextlvlup.client.framework.GameLoop;

public class TestGameLoop extends GameLoop {

	public TestGameLoop(GameInstance instance) {
		super(instance);
	}

	@Override
	public void run() {
		for(DynamicGameResource resource : resources) {
			resource.update();
		}
	}

}
