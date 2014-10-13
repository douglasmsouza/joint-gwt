package com.joint.gwt.shared.ui.graph.member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a JointMember as a bean for client-server operations
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointMemberBean<T extends JointMemberBean<T>> implements Serializable {

	private List<T> children = new ArrayList<>();

	public JointMemberBean() {
	}

	public void addChild(T child) {
		children.add(child);
	}

	public List<T> getChildren() {
		return children;
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

}
