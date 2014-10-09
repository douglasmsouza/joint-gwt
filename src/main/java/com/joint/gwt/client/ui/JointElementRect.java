package com.joint.gwt.client.ui;

import com.joint.gwt.client.util.Rect;

/**
 * A JointJS element represented by a Rect
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointElementRect extends JointElement {

	private Rect rect;

	public JointElementRect(int width, int height) {
		super();
		this.rect = new Rect(width, height);
	}

	public Rect getRect() {
		return rect;
	}

}
