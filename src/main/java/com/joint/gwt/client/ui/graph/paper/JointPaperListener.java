package com.joint.gwt.client.ui.graph.paper;

import com.joint.gwt.client.ui.graph.JointGraph;
import com.joint.gwt.shared.Position;
import com.joint.gwt.shared.bean.JointBean;

/**
 * Handles events on graph's paper
 * 
 * @author Douglas Matheus de Souza
 */
public interface JointPaperListener<T extends JointBean<T>> {

	/**
	 * Handles the mouse down event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onPointerDown(JointGraph<T> graph, Position graphPosition, Position pagePosition);

	/**
	 * Handles the double click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onDblClick(JointGraph<T> graph, Position graphPosition, Position pagePosition);

	/**
	 * Handles the single click event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	void onClick(JointGraph<T> graph, Position graphPosition, Position pagePosition);

}
