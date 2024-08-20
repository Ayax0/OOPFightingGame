package com.nextlvlup.client.framework;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameResource extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -824068727445043912L;
	private Image texture;
	
	public void setTexture(String path) {
		this.texture = new ImageIcon(getClass().getResource("/com/nextlvlup/teko/assets/" + path)).getImage();
	}
	
	private void drawTexture() {
		if(this.texture == null) return;
		
		ImageIcon icon = new ImageIcon(texture.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		this.setIcon(icon);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.drawTexture();
	}
	
	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		this.drawTexture();
	}

}
