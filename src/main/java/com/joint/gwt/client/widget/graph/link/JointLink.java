package com.joint.gwt.client.widget.graph.link;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class JointLink {

	public static final native JavaScriptObject createLink(Element source, Element target)/*-{
		return new $wnd.joint.shapes.org.Arrow({
			source : {
				id : source.id
			},
			target : {
				id : target.id
			}
		});
	}-*/;
}
