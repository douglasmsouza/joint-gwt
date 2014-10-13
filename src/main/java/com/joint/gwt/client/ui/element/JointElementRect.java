package com.joint.gwt.client.ui.element;

import com.joint.gwt.client.util.Rect;

/**
 * A JointJS element represented by a Rect
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointElementRect extends JointElement {

	private Rect rect;

	public JointElementRect(float width, float height) {
		super();
		this.rect = new Rect(width, height);
	}

	public Rect getRect() {
		return rect;
	}

}
