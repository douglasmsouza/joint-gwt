package com.joint.gwt.client.widget.graph.member;

import com.joint.gwt.client.ui.JointElement;

public final class JointMember extends JointElement {

	public static native JointMember createMember(JointMemberOptions options)/*-{
		return new $wnd.joint.shapes.org.Member({
			attrs : {
				'.card' : {
					fill : options.fillColor,
					stroke : options.strokeColor
				},
				'.rank' : {
					text : options.rank,
					'font-size' : options.rankFontSize
				},
				'.name' : {
					text : options.name,
					'font-size' : options.nameFontSize
				}
			}
		});
	}-*/;

	protected JointMember() {
	}

	public void setId(int id) {
		super.setProp("id", id);
	}

	public int getId() {
		return super.getPropInt("id");
	}

	public String getName() {
		return super.getAttrString(".name/text");
	};

	public String getRank() {
		return super.getAttrString(".rank/text");
	};

	public void setFillColor(String color) {
		super.setAttr(".card/fill", color);
	}

	public void setStrokeColor(String color) {
		super.setAttr(".card/stroke", color);
	}

}
