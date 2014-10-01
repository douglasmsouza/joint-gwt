package com.joint.gwt.client.widget.graph.member;

import com.google.gwt.core.client.JavaScriptObject;

public class JointMember extends JavaScriptObject {

	public static final native JointMember createMember(JointMemberOptions options)/*-{
		return new $wnd.joint.shapes.org.Member({
			attrs : {
				'.card' : {
					fill : "#F1C40F",
					stroke : "gray"
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
