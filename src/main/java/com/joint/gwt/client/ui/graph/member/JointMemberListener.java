package com.joint.gwt.client.ui.graph.member;

import com.joint.gwt.client.ui.graph.JointGraph;

public interface JointMemberListener {

	/**
	 * Handles the mouse down event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerDown(JointGraph graph, JointMember member, int x, int y);

	/**
	 * Handles the mouse move event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerMove(JointGraph graph, JointMember member, int x, int y);

	/**
	 * Handles the mouse up event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerUp(JointGraph graph, JointMember member);

	/**
	 * Handles the double click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onDblClick(JointGraph graph, JointMember member, int x, int y);

	/**
	 * Handles the single click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onClick(JointGraph graph, JointMember member, int x, int y);

}