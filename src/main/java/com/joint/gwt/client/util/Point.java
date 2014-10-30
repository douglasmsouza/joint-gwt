package com.joint.gwt.client.util;

/**
 * This class represents a xy point relative to a view
 * 
 * @author Douglas Matheus de Souza
 */
public class Point {

	private float x;
	private float y;

	private Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public static final Point create(float x, float y) {
		return new Point(x, y);
	}

	@Override
	public String toString() {
		return "Point(" + x + "," + y + ")";
	}
}
