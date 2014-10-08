package com.joint.gwt.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * An abstract class that provides {@link JavaScriptObject} creation
 * 
 * @author Douglas Matheus de Souza em 08/10/2014
 */
public abstract class JointElement {

	private JointElementJS js = createJavaScriptObject();

	protected abstract JointElementJS createJavaScriptObject();

	public int[] getXY() {
		return js.getXY();
	}

	public JointElementJS getJS() {
		return js;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JavaScriptObject)) {
			return false;
		}

		return js.equals(obj);
	}

}
