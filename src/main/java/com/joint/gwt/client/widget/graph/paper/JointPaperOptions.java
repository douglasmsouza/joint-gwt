package com.joint.gwt.client.widget.graph.paper;

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

	public native void setGridSize(int gridSize)/*-{
		this.gridSize = gridSize;
	}-*/;

	public native void setPerpendicularLinks(boolean perpendicularLinks)/*-{
		this.perpendicularLinks = perpendicularLinks;
	}-*/;

}
