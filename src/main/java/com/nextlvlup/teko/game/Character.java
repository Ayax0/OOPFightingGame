package com.nextlvlup.teko.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.nextlvlup.teko.framework.DynamicGameResource;
import com.nextlvlup.teko.framework.GameInstance;

public class Character extends DynamicGameResource {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2677108534728790704L;
	int xVec = 0;
	int yVec = 0;
	
	public Character(GameInstance instance) {
		this.setTexture("test.png");
		this.setSize(100, 100);
		
		instance.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == 'w') {
					yVec = -1;
				}
				if(e.getKeyChar() == 's') {
					yVec = 1;
				}
				if(e.getKeyChar() == 'a') {
					xVec = -1;
				}
				if(e.getKeyChar() == 'd') {
					xVec = 1;
				}
				super.keyPressed(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 'w') {
					yVec = 0;
				}
				if(e.getKeyChar() == 's') {
					yVec = 0;
				}
				if(e.getKeyChar() == 'a') {
					xVec = 0;
				}
				if(e.getKeyChar() == 'd') {
					xVec = 0;
				}
				super.keyReleased(e);
			}
		});
	}

	@Override
	public void update() {
		this.setLocation(this.getX() + xVec * 5, this.getY() + yVec * 5);
	}

}
