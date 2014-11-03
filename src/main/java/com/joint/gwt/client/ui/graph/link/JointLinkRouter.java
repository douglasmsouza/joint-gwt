package com.joint.gwt.client.ui.graph.link;

public enum JointLinkRouter {

	MANHATTAN, METRO, ORTHOGONAL;

	public String getRouter() {
		return name().toLowerCase();
	}
}
