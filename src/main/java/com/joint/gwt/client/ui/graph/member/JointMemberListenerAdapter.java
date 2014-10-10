package com.joint.gwt.client.ui.graph.member;

import com.joint.gwt.client.ui.graph.JointGraph;

@SuppressWarnings("rawtypes")
public class JointMemberListenerAdapter<T extends JointMember> implements JointMemberListener<T> {

	@Override
	public void onPointerDown(JointGraph<T> graph, T member, int x, int y) {
	}

	@Override
	public void onPointerMove(JointGraph<T> graph, T member, int x, int y) {
	}

	@Override
	public void onPointerUp(JointGraph<T> graph, T member) {
	}

	@Override
	public void onDblClick(JointGraph<T> graph, T member, int x, int y) {
	}

	@Override
	public void onClick(JointGraph<T> graph, T member, int x, int y) {
	}

}
