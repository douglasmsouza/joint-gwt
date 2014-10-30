package com.joint.gwt.client.ui.graph.member;

import com.joint.gwt.client.ui.graph.JointGraph;
import com.joint.gwt.shared.Position;
import com.joint.gwt.shared.bean.JointBean;

public class JointMemberListenerAdapter<T extends JointBean<T>> implements JointMemberListener<T> {

	@Override
	public void onPointerDown(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onPointerMove(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onPointerUp(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onDblClick(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onClick(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition) {
	}

}
