package com.joint.gwt.client.ui.graph.member;

import java.io.Serializable;

import com.joint.gwt.client.ui.graph.JointGraph;

public interface JointMemberListener<T extends Serializable> {

	/**
	 * Handles the mouse down event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerDown(JointGraph<T> graph, JointMember<T> member, int x, int y);

	/**
	 * Handles the mouse move event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerMove(JointGraph<T> graph, JointMember<T> member, int x, int y);

	/**
	 * Handles the mouse up event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerUp(JointGraph<T> graph, JointMember<T> member);

	/**
	 * Handles the double click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onDblClick(JointGraph<T> graph, JointMember<T> member, int x, int y);

	/**
	 * Handles the single click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onClick(JointGraph<T> graph, JointMember<T> member, int x, int y);

}
