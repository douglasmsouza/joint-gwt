package com.joint.gwt.client.widget.graph.member;

import com.google.gwt.core.client.JavaScriptObject;

public class JointMember extends JavaScriptObject {

	public static final native JointMember createMember(JointMemberOptions options)/*-{
		return new $wnd.joint.shapes.org.Member({
			attrs : {
				'.card' : {
					fill : options["fillColor"],
					stroke : options["strokeColor"]
				},
				'.rank' : {
					text : options["rank"]
				},
				'.name' : {
					text : options["name"]
				}
			}
		});
	}-*/;

	protected JointMember() {
	}

}
