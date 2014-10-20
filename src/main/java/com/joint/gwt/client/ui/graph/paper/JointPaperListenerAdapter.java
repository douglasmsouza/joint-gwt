package com.joint.gwt.client.ui.graph.paper;

import com.joint.gwt.client.ui.graph.JointGraph;
import com.joint.gwt.client.util.Position;
import com.joint.gwt.shared.bean.JointBean;

/**
 * Handles events on graph's paper
 * 
 * @author Douglas Matheus de Souza
 */
public class JointPaperListenerAdapter<T extends JointBean<T>> implements JointPaperListener<T> {

	@Override
	public void onPointerDown(JointGraph<T> graph, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onDblClick(JointGraph<T> graph, Position graphPosition, Position pagePosition) {
	}

	@Override
	public void onClick(JointGraph<T> graph, Position graphPosition, Position pagePosition) {
	}

}
