package com.joint.gwt.client.ui.graph.member;

import com.joint.gwt.client.ui.graph.JointGraph;
import com.joint.gwt.client.util.Position;

@SuppressWarnings("rawtypes")
public class JointMemberListenerAdapter<T extends JointMember> implements JointMemberListener<T> {

	@Override
	public void onPointerDown(JointGraph<T> graph, T member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onPointerMove(JointGraph<T> graph, T member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onPointerUp(JointGraph<T> graph, T member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onDblClick(JointGraph<T> graph, T member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onClick(JointGraph<T> graph, T member, Position graphPosition, Position pagePosition) {
	}

}
