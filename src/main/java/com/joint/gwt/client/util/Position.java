package com.joint.gwt.client.util;

/**
 * This class represents a xy position relative to a view
 * 
 * @author Douglas Matheus de Souza
 */
public class Position {

	private int x;
	private int y;

	private Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static final Position create(int x, int y) {
		return new Position(x, y);
	}
}