package com.nextlvlup.teko.framework;

import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class GameInstance extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2431403290509854638L;
	private int framerate;
	private JLayeredPane rootPane = new JLayeredPane();
	
	public GameInstance(int framerate) {
		this.framerate = framerate;
		
		this.setBounds(0, 0, 800, 600);
		this.setContentPane(rootPane);
		rootPane.setLayout(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void attachGameLoop(GameLoop gameloop) {
		new Timer().scheduleAtFixedRate(gameloop, 0L, 1000L / this.framerate);
	}

}
