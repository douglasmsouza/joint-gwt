package com.joint.gwt.shared;

import java.io.Serializable;

/**
 * This class represents a xy position relative to a view
 * 
 * @author Douglas Matheus de Souza
 */
public class Position implements Serializable {

	private int x;
	private int y;

	protected Position() {
	}

	protected Position(int x, int y) {
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