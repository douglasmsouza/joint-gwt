package com.joint.gwt.client.widget.graph.member;

import com.google.gwt.core.client.JavaScriptObject;

public class JointMemberOptions extends JavaScriptObject {

	public static final native JointMemberOptions createOptions()/*-{
		return {};
	}-*/;

	protected JointMemberOptions() {
	}

	public final native void setName(String name)/*-{
		this.name = name;
	}-*/;

	public final native void setRank(String rank)/*-{
		this.rank = rank;
	}-*/;

}
