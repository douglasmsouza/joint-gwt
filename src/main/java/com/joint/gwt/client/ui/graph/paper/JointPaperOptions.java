package com.joint.gwt.client.ui.graph.paper;

import com.google.gwt.core.client.JavaScriptObject;

public final class JointPaperOptions extends JavaScriptObject {

	public static native JointPaperOptions createOptions()/*-{
		return {};
	}-*/;

	protected JointPaperOptions() {
	}

	public native void setWidth(Object width)/*-{
		this.width = width;
	}-*/;

	public native void setHeight(Object height)/*-{
		this.height = height;
	}-*/;

	public native void setGridSize(int gridSize)/*-{
		this.gridSize = gridSize;
	}-*/;

	public native void setPerpendicularLinks(boolean perpendicularLinks)/*-{
		this.perpendicularLinks = perpendicularLinks;
	}-*/;

	public native void setScrollerWidth(Object scrollerWidth)/*-{
		this.scrollerWidth = scrollerWidth;
	}-*/;

	public native void setScrollerHeight(Object scrollerHeight)/*-{
		this.scrollerHeight = scrollerHeight;
	}-*/;

}
