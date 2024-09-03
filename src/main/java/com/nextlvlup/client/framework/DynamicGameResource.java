package com.nextlvlup.client.framework;

import java.util.ArrayList;

public abstract class DynamicGameResource extends GameResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8293648901749509207L;
	
	public abstract void update(ArrayList<StaticGameResource> staticResources);

}
