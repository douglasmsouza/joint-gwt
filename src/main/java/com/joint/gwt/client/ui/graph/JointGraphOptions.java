package com.joint.gwt.client.ui.graph;

import com.google.gwt.core.client.JavaScriptObject;
import com.joint.gwt.client.ui.graph.link.JointLinkRouter;

public final class JointGraphOptions extends JavaScriptObject {

	public static native JointGraphOptions createOptions()/*-{
		return {};
	}-*/;

	protected JointGraphOptions() {
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

	public native void setAllowSelection(boolean allowSelection)/*-{
		this.allowSelection = allowSelection;
	}-*/;

	public native void setAutoResizePaper(boolean autoResizePaper)/*-{
		this.autoResizePaper = autoResizePaper;
	}-*/;

}
