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

	public float[] getXY() {
		return getJS().getXY();
	}

	public JointElementJS getJS() {
		if (js == null)
			js = createJavaScriptObject();
		//
		return js;
	}

	public final String getId() {
		return getJS().getId();
	};

	public JointElement getInstance() {
		return this;
	}

	public native void toFront() /*-{
		var thisJS = this.@com.joint.gwt.client.ui.element.JointElement::getJS()();
		thisJS.toFront();
	}-*/;

	public native void toBack() /*-{
		var thisJS = this.@com.joint.gwt.client.ui.element.JointElement::getJS()();
		thisJS.toBack();
	}-*/;

}
