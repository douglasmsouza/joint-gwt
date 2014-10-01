package com.joint.gwt.client.widget.graph.paper;

import com.google.gwt.core.client.JavaScriptObject;

public class JointPaperOptions extends JavaScriptObject {

	public static final native JointPaperOptions createOptions()/*-{
		return {};
	}-*/;

	protected JointPaperOptions() {
	}

	public final native void setWidth(int width)/*-{
		this.width = width;
	}-*/;

	public final native void setHeight(int height)/*-{
		this.height = height;
	}-*/;

	public final native void setGridSize(int gridSize)/*-{
		this.gridSize = gridSize;
	}-*/;

	public final native void setPerpendicularLinks(boolean perpendicularLinks)/*-{
		this.perpendicularLinks = perpendicularLinks;
	}-*/;

}
