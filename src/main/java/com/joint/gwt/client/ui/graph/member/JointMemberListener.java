package com.joint.gwt.client.ui.graph.member;

import com.joint.gwt.client.ui.graph.JointGraph;
import com.joint.gwt.client.util.Position;
import com.joint.gwt.shared.bean.bean.JointBean;

public interface JointMemberListener<T extends JointBean<T>> {

	/**
	 * Handles the mouse down event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerDown(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition);

	/**
	 * Handles the mouse move event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerMove(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition);

	/**
	 * Handles the mouse up event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerUp(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition);

	/**
	 * Handles the double click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onDblClick(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition);

	/**
	 * Handles the single click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onClick(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition);

}
