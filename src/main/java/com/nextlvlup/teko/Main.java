package com.nextlvlup.teko;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nextlvlup.base.Player;
import com.nextlvlup.packet.PlayerJoinPacket;
import com.nextlvlup.teko.game.TestScene;
import com.nextlvlup.teko.network.PlayerJoinListener;
import com.nextlvlup.teko.network.UDPClient;

public class Main {
	protected static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		new TestScene();
	}

}
