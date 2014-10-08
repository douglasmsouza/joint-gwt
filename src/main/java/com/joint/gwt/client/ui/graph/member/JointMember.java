package com.joint.gwt.client.ui.graph.member;

import com.joint.gwt.client.ui.JointElement;

public final class JointMember extends JointElement {

	private static final String ID_PROPERTY = "id";
	private static final String PARENT_MEMBER_PROPERTY = "parentMember";

	public static native JointMember createMember(JointMemberOptions options)/*-{
		return new $wnd.joint.shapes.org.Member({
			attrs : {
				".card" : {
					fill : options.fillColor,
					stroke : options.strokeColor,
					"stroke-width": options.strokeWidth
				},
				".rank" : {
					text : options.rank,
					"font-size" : options.rankTextOptions.fontSize,
					"font-weight" : options.rankTextOptions.fontWeight,
					"text-decoration" : options.rankTextOptions.textDecoration,
					"text-anchor" : options.rankTextOptions.textAnchor,
					"ref-x" : options.rankTextOptions.positionX,
					"ref-y" : options.rankTextOptions.positionY
				},
				".name" : {
					text : options.name,
					"font-size" : options.nameTextOptions.fontSize,
					"font-weight" : options.nameTextOptions.fontWeight,
					"text-decoration" : options.nameTextOptions.textDecoration,
					"text-anchor" : options.nameTextOptions.textAnchor,
					"ref-x" : options.nameTextOptions.positionX,
					"ref-y" : options.nameTextOptions.positionY
				},
				image : {
					width : options.imageWidth,
					height : options.imageHeight
				}
			},
			size : {
				width : options.width,
				height : options.height
			}
		});
	}-*/;

	protected JointMember() {
	}

	public void setId(int id) {
		super.setProp(ID_PROPERTY, id);
	}

	public int getId() {
		return super.getPropInt(ID_PROPERTY);
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

	public void setParentMember(JointMember parentMember) {
		super.setProp(PARENT_MEMBER_PROPERTY, parentMember);
	}

	public JointMember getParentMember() {
		return (JointMember) super.getProp(PARENT_MEMBER_PROPERTY);
	}
}
