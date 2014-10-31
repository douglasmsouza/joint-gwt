package com.joint.gwt.client.util;

import com.joint.gwt.shared.Rect;
import com.joint.gwt.shared.bean.JointBean;

/**
 * Rect calculator for joint elements
 * 
 * @author Douglas Matheus de Souza
 */
public interface RectCalculator<T extends JointBean<T>> {

	public abstract Rect calculateRect(T element);

}
