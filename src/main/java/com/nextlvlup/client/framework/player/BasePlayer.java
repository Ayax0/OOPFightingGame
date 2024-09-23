package com.nextlvlup.client.framework.player;

import java.util.ArrayList;

import com.nextlvlup.client.framework.DynamicGameResource;
import com.nextlvlup.client.framework.StaticGameResource;
import com.nextlvlup.network.base.Player;

import lombok.Getter;
import lombok.Setter;

public class BasePlayer extends DynamicGameResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4653425014477548053L;
	@Getter @Setter private int x;
	@Getter @Setter private int y;

	@Getter Player player;
	
	public BasePlayer(Player player) {
		this.player = player;
		
		this.setTexture("char/basic.png");
		this.setSize(60, 80);
		this.setLocation(10, 10);
	}

	@Override
	public void update(ArrayList<StaticGameResource> staticResources) {
		this.setLocation(x, y);
	}
	
}
