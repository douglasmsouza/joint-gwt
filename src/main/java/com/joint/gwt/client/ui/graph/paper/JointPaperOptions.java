package com.joint.gwt.client.ui.graph.paper;

import com.google.gwt.core.client.JavaScriptObject;

public final class JointPaperOptions extends JavaScriptObject {

	public static native JointPaperOptions createOptions()/*-{
		return {};
	}-*/;

	protected JointPaperOptions() {
	}

	public native void setWidth(int width)/*-{
		this.width = width;
	}-*/;

	public native void setHeight(int height)/*-{
		this.height = height;
	}-*/;

	public native int getWidth()/*-{
		return this.width;
	}-*/;

	public native int getHeight()/*-{
		return this.height;
	}-*/;

	public native void setGridSize(int gridSize)/*-{
		this.gridSize = gridSize;
	}-*/;

	public native void setPerpendicularLinks(boolean perpendicularLinks)/*-{
		this.perpendicularLinks = perpendicularLinks;
	}-*/;

	public native void setScrollerWidth(int scrollerWidth)/*-{
		this.scrollerWidth = scrollerWidth;
	}-*/;

	public native void setScrollerHeight(int scrollerHeight)/*-{
		this.scrollerHeight = scrollerHeight;
	}-*/;

}
