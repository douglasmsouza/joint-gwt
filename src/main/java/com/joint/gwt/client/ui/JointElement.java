package com.joint.gwt.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.joint.gwt.client.ui.graph.member.JointMember;

/**
 * The base JointJS element
 * 
 * @author Douglas Matheus de Souza
 */
public class JointElement extends JavaScriptObject {

	protected JointElement() {
	}

	/**
	 * Sets the value of an attribute
	 * 
	 * @author Douglas Matheus de Souza
	 */
	protected final native void setAttr(String name, Object value)/*-{
		this.attr(name, value);
	}-*/;

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
	 * @author Douglas Matheus de Souza em 02/10/2014
	 */
	public final native int[] getXY()/*-{
		var box = this.getBBox();
		return [box.x, box.y];
	}-*/;
}
