package com.joint.gwt.client.ui.element;

import com.joint.gwt.shared.Rect;

/**
 * A JointJS element represented by a Rect
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointElementRect extends JointElement {

	private Rect rect;

	public JointElementRect(float width, float height) {
		this(new Rect(width, height));
	}

	public JointElementRect(Rect rect) {
		super();
		this.rect = rect;
	}

	public Rect getRect() {
		return rect;
	}
	
	public void setRect(Rect rect) {
		this.rect = rect;
	}

}
