package com.joint.gwt.shared;

import java.io.Serializable;

/**
 * This class represents a xy point relative to a view
 * 
 * @author Douglas Matheus de Souza
 */
public class Point implements Serializable {

	private float x;
	private float y;

	protected Point() {
	}

	protected Point(float x, float y) {
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

	public static Point create(float x, float y) {
		return new Point(x, y);
	}
}
