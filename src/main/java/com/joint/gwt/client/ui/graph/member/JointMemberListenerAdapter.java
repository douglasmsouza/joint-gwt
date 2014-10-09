package com.joint.gwt.client.ui.graph.member;

import java.io.Serializable;

import com.joint.gwt.client.ui.graph.JointGraph;

public class JointMemberListenerAdapter<T extends Serializable> implements JointMemberListener<T> {

	@Override
	public void onPointerDown(JointGraph<T> graph, JointMember<T> member, int x, int y) {
	}

	@Override
	public void onPointerMove(JointGraph<T> graph, JointMember<T> member, int x, int y) {
	}

	@Override
	public void onPointerUp(JointGraph<T> graph, JointMember<T> member) {
	}

	@Override
	public void onDblClick(JointGraph<T> graph, JointMember<T> member, int x, int y) {
	}

	@Override
	public void onClick(JointGraph<T> graph, JointMember<T> member, int x, int y) {
	}

}
