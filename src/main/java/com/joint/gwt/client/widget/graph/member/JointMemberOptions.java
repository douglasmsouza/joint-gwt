package com.joint.gwt.client.widget.graph.member;

import com.google.gwt.core.client.JavaScriptObject;

public class JointMemberOptions extends JavaScriptObject {

	public static final native JointMemberOptions createOptions()/*-{
		return {};
	}-*/;

	protected JointMemberOptions() {
	}

	public final native void setName(String name)/*-{
		this.name = name;
	}-*/;

	public final native String getName()/*-{
		return this.name;
	}-*/;

	public final native void setRank(String rank)/*-{
		this.rank = rank;
	}-*/;

	public final native String getRank()/*-{
		return this.rank;
	}-*/;

	public final native void setFillColor(String fillColor)/*-{
		this.fillColor = fillColor;
	}-*/;

	public final native String getFillColor()/*-{
		return this.fillColor;
	}-*/;

	public final native void setStrokeColor(String strokeColor)/*-{
		this.strokeColor = strokeColor;
	}-*/;

	public final native String getStrokeColor()/*-{
		return this.strokeColor;
	}-*/;

}
