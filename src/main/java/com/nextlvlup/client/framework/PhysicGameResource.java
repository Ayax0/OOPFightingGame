package com.nextlvlup.client.framework;

import java.util.ArrayList;
import java.util.Collection;

import javax.vecmath.Vector2d;

import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.line.Line2D;
import math.geom2d.line.LinearShape2D;

public abstract class PhysicGameResource extends DynamicGameResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7387826990264843880L;
	public Vector2d vector = new Vector2d();

	@Override
	public void update(ArrayList<StaticGameResource> staticResources) {
		Vector2D vect = new Vector2D(vector.getX(), vector.getY());
		Vector2D offset = new Vector2D(0, this.getHeight());
		
		Point2D src = new Point2D(this.getX(), this.getY());
		Point2D dest = src.plus(vect);
		
		// System.out.println("vect: " + vect.x() + " / " + vect.y());
		// System.out.println("src: " + src.x() + " / " + src.y());
		// System.out.println("dest: " + dest.x() + " / " + dest.y());
		
		// Collision Check
		if(vect.y() > 0) {
			for(StaticGameResource resource : staticResources) {
				Box2D box = new Box2D(
					new Point2D(resource.getX(), resource.getY()), 
					resource.getWidth(), 
					resource.getHeight()
				);
				
				
				Line2D trajectory = new Line2D(src.plus(offset), dest.plus(offset));
				
				for(LinearShape2D edge : box.edges()) {
					Collection<Point2D> intersections = trajectory.intersections(edge);
					intersections.removeIf(p -> p.almostEquals(src, 0));
					
					if(intersections.size() > 0) {
						Point2D intersection = intersections.iterator().next();
						
						this.setLocation(
							(int) intersection.minus(offset).x(), 
							(int) intersection.minus(offset).y()
						);
						
						vector.setY(0);
						return;
					}
				}
			}
		}
		
		int destX = (int) dest.x();
		int destY = (int) dest.y();
		
		// if(destX != this.getX() || destY != this.getY())
			this.move();
		
		this.setLocation(destX, destY);
		vector.add(new Vector2d(0, 1));
	}
	
	public abstract void move();

}
