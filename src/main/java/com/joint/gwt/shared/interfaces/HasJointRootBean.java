package com.joint.gwt.shared.interfaces;

import com.joint.gwt.shared.bean.JointBean;

/**
 * This interface represents objects that has a root {@link JointBean}
 * 
 * @author Douglas Matheus de Souza
 */
public interface HasJointRootBean<T extends JointBean<T>> {

	public T getRootBean();
	
}
