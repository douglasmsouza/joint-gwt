package com.joint.gwt.client.ui.graph.link;

import java.util.List;

import com.joint.gwt.client.ui.element.JointElement;
import com.joint.gwt.client.ui.element.JointElementJS;
import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.shared.Point;
import com.joint.gwt.shared.bean.JointBean;

/**
 * This class is used to create links between two members
 * 
 * @author Douglas Matheus de Souza
 */
public final class JointLink<T extends JointBean<T>> extends JointElement {

	public JointLink(JointMember<T> source, JointMember<T> target) {
		super();
		setSourceTarget(source, "source");
		setSourceTarget(target, "target");
	}

	private native void setSourceTarget(JointMember<T> member, String sourceOrTarget)/*-{
		var memberId = member.@com.joint.gwt.client.ui.graph.member.JointMember::getId()();
		var thisJS = this.@com.joint.gwt.client.ui.graph.link.JointLink::getJS()();
		thisJS.set(sourceOrTarget, {
			id : memberId
		});
	}-*/;

	public void setStrokeColor(String color) {
		getJS().setAttr(".connection/stroke", color);
	}

	public String getStrokeColor() {
		return getJS().getAttrString(".connection/stroke");
	}

	public void setStrokeWidth(float width) {
		getJS().setAttr(".connection/stroke-width", width);
	}

	public float getStrokeWidth() {
		return getJS().getAttrInt(".connection/stroke-width");
	}

	public native void setVertices(List<Point> vertices) /*-{
		var verticesArray = [];
		//
		if (vertices != null) {
			var verticesSize = vertices.@java.util.List::size()();
			for (var i = 0; i < verticesSize; i++) {
				var point = vertices.@java.util.List::get(I)(i);
				verticesArray[i] = {
					x : point.@com.joint.gwt.shared.Point::getX()(),
					y : point.@com.joint.gwt.shared.Point::getY()()
				};
			}
		}
		//
		var thisJS = this.@com.joint.gwt.client.ui.graph.link.JointLink::getJS()();
		thisJS.set('vertices', verticesArray);
	}-*/;

	@Override
	protected native JointElementJS createJavaScriptObject() /*-{
		return new $wnd.joint.dia.Link({
			attrs : {
				'.connection' : {
					stroke : '#000',
					'stroke-width' : 0.5
				},
			}
		});
	}-*/;

}
