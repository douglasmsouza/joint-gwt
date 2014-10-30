package com.joint.gwt.client.ui.element;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The base JointJS element
 * 
 * @author Douglas Matheus de Souza
 */
public class JointElementJS extends JavaScriptObject {

	protected JointElementJS() {
	}

	/**
	 * Sets the value of an attribute
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native void setAttr(String name, Object value)/*-{
		this.attr(name, value);
	}-*/;

	/**
	 * Returns the value of an attribute as {@link String}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native String getAttrString(String name)/*-{
		return this.attr(name);
	}-*/;

	/**
	 * Returns the value of an attribute as {@link Integer}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native int getAttrInt(String name)/*-{
		return this.attr(name);
	}-*/;

	/**
	 * Sets the value of a property
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native void setProp(String name, Object value)/*-{
		this.prop(name, value);
	}-*/;

	/**
	 * Returns the value of a property as {@link Object}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native Object getProp(String name)/*-{
		return this.prop(name);
	}-*/;

	/**
	 * Returns the value of a property as {@link String}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native String getPropString(String name)/*-{
		return this.prop(name);
	}-*/;

	/**
	 * Returns the value of a property as {@link Integer}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native int getPropInt(String name)/*-{
		return this.prop(name);
	}-*/;

	/**
	 * Returns the XY position of this element
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native float[] getXY()/*-{
		var box = this.getBBox();
		return [box.x, box.y];
	}-*/;
}
