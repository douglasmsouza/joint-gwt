package com.joint.gwt.client.widget.graph.paper;

import com.joint.gwt.client.widget.graph.JointGraph;
import com.joint.gwt.client.widget.graph.member.JointMember;

public interface JointPaperListener {

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
