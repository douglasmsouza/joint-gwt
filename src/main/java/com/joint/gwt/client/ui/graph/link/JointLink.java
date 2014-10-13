package com.joint.gwt.client.ui.graph.link;

import com.google.gwt.core.client.JavaScriptObject;
import com.joint.gwt.client.ui.element.JointElement;

public class JointLink {

	public static final native JavaScriptObject createLink(JointElement source, JointElement target)/*-{
		var sourceJS = source.@com.joint.gwt.client.ui.element.JointElement::getJS()();
		var targetJS = target.@com.joint.gwt.client.ui.element.JointElement::getJS()();
		//
		return new $wnd.joint.shapes.org.Arrow({
			source : {
				id : sourceJS.id
			},
			target : {
				id : targetJS.id
			},
			smooth : false
		});
	}-*/;
}
