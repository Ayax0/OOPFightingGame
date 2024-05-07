package com.nextlvlup.teko.game;

import com.nextlvlup.teko.framework.DynamicGameResource;
import com.nextlvlup.teko.framework.GameInstance;
import com.nextlvlup.teko.framework.GameLoop;

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
