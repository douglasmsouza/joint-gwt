package com.joint.gwt.shared.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a JointElement as a bean for client-server operations
 * 
 * @author Douglas Matheus de Souza
 */
public abstract class JointBean<T extends JointBean<T>> implements Serializable {

	private List<T> children = new ArrayList<>();

	public JointBean() {
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
