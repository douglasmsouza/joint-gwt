package com.joint.gwt.client.widget.graph.member;

import com.google.gwt.core.client.JavaScriptObject;

public final class JointMemberOptions extends JavaScriptObject {

	public static native JointMemberOptions createOptions()/*-{
		return {};
	}-*/;

	protected JointMemberOptions() {
	}

	public native void setName(String name)/*-{
		this.name = name;
	}-*/;

	public native void setRank(String rank)/*-{
		this.rank = rank;
	}-*/;

	public native void setFillColor(String fillColor)/*-{
		this.fillColor = fillColor;
	}-*/;

	public native void setStrokeColor(String strokeColor)/*-{
		this.strokeColor = strokeColor;
	}-*/;

}
