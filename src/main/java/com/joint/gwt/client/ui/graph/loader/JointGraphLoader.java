package com.joint.gwt.client.ui.graph.loader;

import java.util.List;

import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.client.util.RectCalculator;
import com.joint.gwt.shared.Point;
import com.joint.gwt.shared.bean.JointBean;

/**
 * A graph loader helper
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointGraphLoader<T extends JointBean<T>> {

	private RectCalculator<T> rectCalculator = createRectCalculator();

	public RectCalculator<T> getRectCalculator() {
		return rectCalculator;
	}

	/**
	 * Calculates the rect of the element that is being added to the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	protected abstract RectCalculator<T> createRectCalculator();

	/**
	 * Return TRUE if should automatically layout the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public abstract boolean isAutoLayout();

	/**
	 * The initial scroll position of the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public float[] getInitialScrollPosition(JointMember<T> rootMember) {
		return null;
	}

	/**
	 * Return the initial XY position of the element in the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public Point getInitialPosition(JointMember<T> jointMember) {
		return null;
	}

	/**
	 * Return the existing points in the connection between a member and its
	 * parent
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public List<Point> getLinkVertices(JointMember<T> jointMember) {
		return null;
	}

}
