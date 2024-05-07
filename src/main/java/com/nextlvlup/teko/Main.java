package com.nextlvlup.teko;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nextlvlup.teko.game.TestScene;

public class Main {
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		new TestScene();
	}

}
