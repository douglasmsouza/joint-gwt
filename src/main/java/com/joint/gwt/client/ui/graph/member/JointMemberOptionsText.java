package com.joint.gwt.client.ui.graph.member;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.joint.gwt.client.constants.TextAnchor;

public final class JointMemberOptionsText extends JavaScriptObject {

	public static native JointMemberOptionsText createOptions()/*-{
		return {};
	}-*/;

	protected JointMemberOptionsText() {
	}

	public native void setFontSize(int fontSize)/*-{
		this.fontSize = fontSize;
	}-*/;

	public native void setFontWeight(FontWeight fontWeight)/*-{
		this.fontWeight = fontWeight.@com.google.gwt.dom.client.Style.FontWeight::getCssName()();
	}-*/;

	public native void setTextDecoration(TextDecoration textDecoration)/*-{
		this.textDecoration = textDecoration.@com.google.gwt.dom.client.Style.TextDecoration::getCssName()();
	}-*/;

	public native void setTextAnchor(TextAnchor textAnchor)/*-{
		this.textAnchor = textAnchor.@com.joint.gwt.client.constants.TextAnchor::getCssName()();
	}-*/;

	public native void setPositionX(float position)/*-{
		this.positionX = position;
	}-*/;

	public native void setPositionY(float position)/*-{
		this.positionY = position;
	}-*/;

}
