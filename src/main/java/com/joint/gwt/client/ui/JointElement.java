package com.joint.gwt.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The base JointJS element
 * 
 * @author Douglas Matheus de Souza
 */
public class JointElement extends JavaScriptObject {

	protected JointElement() {
	}

	/**
	 * Returns the value of an attribute as {@link String}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	protected final native String getAttrString(String name)/*-{
		return this.attr(name);
	}-*/;

	/**
	 * Returns the value of an attribute as {@link Integer}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	protected final native int getAttrInt(String name)/*-{
		return this.attr(name);
	}-*/;

	/**
	 * Sets the value of a property
	 * 
	 * @author Douglas Matheus de Souza
	 */
	protected final native void setProp(String name, Object value)/*-{
		this.prop(name, value);
	}-*/;

	/**
	 * Returns the value of a property as {@link String}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	protected final native String getPropString(String name)/*-{
		return this.prop(name);
	}-*/;

	/**
	 * Returns the value of a property as {@link Integer}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	protected final native int getPropInt(String name)/*-{
		return this.prop(name);
	}-*/;

}
