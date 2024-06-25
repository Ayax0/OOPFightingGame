package com.nextlvlup.teko.framework;

import javax.vecmath.Vector2d;

public class PhysicGameResource extends DynamicGameResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7387826990264843880L;
	
	private GameInstance instance;
	public Vector2d vector = new Vector2d();
	
	public PhysicGameResource(GameInstance instance) {
		this.instance = instance;
	}

	@Override
	public void update() {
		Vector2d location = new Vector2d(this.getX(), this.getY());
		location.add(vector);
		
		this.setLocation((int) location.getX(), (int) location.getY());
	}

}
