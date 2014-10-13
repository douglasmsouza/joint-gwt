package com.joint.gwt.client.ui.element;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * An abstract class that provides {@link JavaScriptObject} creation for JointJS
 * elements
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointElement {

	private JointElementJS js;

	protected abstract JointElementJS createJavaScriptObject();

	public int[] getXY() {
		return js.getXY();
	}

	public JointElementJS getJS() {
		if (js == null)
			js = createJavaScriptObject();
		//
		return js;
	}

	public JointElement getInstance() {
		return this;
	}

}
