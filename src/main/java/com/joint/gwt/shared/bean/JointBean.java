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

	private T parent;
	private List<T> children = new ArrayList<>();

	public JointBean() {
	}

	public T getParent() {
		return parent;
	}

	public void setParent(T jointBean) {
		this.parent = jointBean;
	}

	public void addChild(T child) {
		children.add(child);
		/*This is the parent of its child*/
		child.setParent((T) this);
	}

	public void removeChild(T child) {
		children.remove(child);
		child.setParent(null);
	}

	public void removeFromParent() {
		if (parent != null) {
			parent.removeChild((T) this);
		}
	}

	public List<T> getChildren() {
		return children;
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

}
