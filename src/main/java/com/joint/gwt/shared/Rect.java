package com.joint.gwt.shared;

import java.io.Serializable;

/**
 * This class represents a basic Rect
 * 
 * @author Douglas Matheus de Souza
 */
public class Rect implements Serializable {

	private float width;
	private float height;

	protected Rect() {
	}

	public Rect(float width, float height) {
		super();
		this.width = width;
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
