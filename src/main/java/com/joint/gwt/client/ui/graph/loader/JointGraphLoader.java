package com.joint.gwt.client.ui.graph.loader;

import java.util.List;

import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.shared.Point;
import com.joint.gwt.shared.Rect;
import com.joint.gwt.shared.bean.JointBean;

/**
 * A rect calculator for a JointJS element
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointGraphLoader<T extends JointBean<T>> {

	public abstract Rect calculateRect(T element);

	public Point getInitialPosition(JointMember<T> jointMember) {
		return null;
	}

	public List<Point> getVertices(JointMember<T> jointMember) {
		return null;
	}

}
