package com.nextlvlup.teko.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.vecmath.Vector2d;

import com.nextlvlup.base.Player;
import com.nextlvlup.packet.PlayerMovePacket;
import com.nextlvlup.teko.framework.GameInstance;
import com.nextlvlup.teko.framework.PhysicGameResource;

import lombok.Getter;

public class Character extends PhysicGameResource {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2677108534728790704L;
	private GameInstance instance;
	
	@Getter private Player player = new Player("SomePlayer");
	
	public Character(GameInstance instance) {
		super(instance);
		
		this.instance = instance;
		this.setTexture("char/basic.png");
		this.setSize(60, 80);
		
		instance.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == 'w') {
					vector.setY(-5);
				}
				if(e.getKeyChar() == 's') {
					vector.setY(5);
				}
				if(e.getKeyChar() == 'a') {
					vector.setX(-5);
				}
				if(e.getKeyChar() == 'd') {
					vector.setX(5);
				}
				super.keyPressed(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 'w' || e.getKeyChar() == 's') {
					vector.setY(0);
				}
				if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
					vector.setX(0);
				}
				super.keyReleased(e);
			}
		});
	}
	
	@Override
	public void update() {
		super.update();
		
		Vector2d location = new Vector2d();
		location.setX(this.getX());
		location.setY(this.getY());
		instance.getClient().sendPacket(new PlayerMovePacket(player, location));
	}

}
