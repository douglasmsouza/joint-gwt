package com.joint.gwt.client.ui.graph.member;

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

	public native void setStrokeWidth(int strokeWidth)/*-{
		this.strokeWidth = strokeWidth;
	}-*/;

	public native void setNameTextOptions(JointMemberOptionsText options)/*-{
		this.nameTextOptions = options;
	}-*/;

	public native void setRankTextOptions(JointMemberOptionsText options)/*-{
		this.rankTextOptions = options;
	}-*/;

	public native void setImageWidth(int imageWidth)/*-{
		this.imageWidth = imageWidth;
	}-*/;

	public native void setImageHeight(int imageHeight)/*-{
		this.imageHeight = imageHeight;
	}-*/;

	public native void setWidth(int width)/*-{
		this.width = width;
	}-*/;

	public native void setHeight(int height)/*-{
		this.height = height;
	}-*/;
}
