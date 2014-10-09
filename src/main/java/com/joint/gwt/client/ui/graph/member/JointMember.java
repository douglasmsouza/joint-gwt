package com.joint.gwt.client.ui.graph.member;

import java.io.Serializable;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.joint.gwt.client.constants.TextAnchor;
import com.joint.gwt.client.ui.JointElementJS;
import com.joint.gwt.client.ui.JointElementRect;

/**
 * This class represents a JointJS member
 * 
 * @author Douglas Matheus de Souza
 */
public class JointMember<T extends Serializable> extends JointElementRect {

	private T userId;
	private JointMember<T> parentMember;

	public JointMember(int width, int height) {
		super(width, height);
	}

	public T getUserId() {
		return userId;
	}

	public void setUserId(T userId) {
		this.userId = userId;
	}

	public JointMember<T> getParentMember() {
		return parentMember;
	}

	public void setParentMember(JointMember<T> parentMember) {
		this.parentMember = parentMember;
	}

	public void setName(String name) {
		getJS().setAttr(".name/text", name);
	}

	public String getName() {
		return getJS().getAttrString(".name/text");
	};

	public int getNameFontSize() {
		return getJS().getAttrInt(".name/font-size");
	}

	public void setNameFontSize(int fontSize) {
		getJS().setAttr(".name/font-size", fontSize);
	}

	public FontWeight getNameFontWeight() {
		try {
			return FontWeight.valueOf(getJS().getAttrString(".name/font-weight"));
		} catch (Exception e) {
			return null;
		}
	}

	public void setNameFontWeight(FontWeight fontWeight) {
		getJS().setAttr(".name/font-weight", fontWeight.getCssName());
	}

	public TextDecoration getNameTextDecoration() {
		try {
			return TextDecoration.valueOf(getJS().getAttrString(".name/text-decoration"));
		} catch (Exception e) {
			return null;
		}
	}

	public void setNameTextDecoration(TextDecoration textDecoration) {
		getJS().setAttr(".name/text-decoration", textDecoration.getCssName());
	}

	public TextAnchor getNameTextAnchor() {
		try {
			return TextAnchor.valueOf(getJS().getAttrString(".name/text-anchor"));
		} catch (Exception e) {
			return null;
		}
	}

	public void setNameTextAnchor(TextAnchor textAnchor) {
		getJS().setAttr(".name/text-anchor", textAnchor.getCssName());
	}

	public int getNamePositionX() {
		return getJS().getAttrInt(".name/ref-x");
	}

	public void setNamePositionX(int positionX) {
		getJS().setAttr(".name/ref-x", positionX);
	}

	public int getNamePositionY() {
		return getJS().getAttrInt(".name/ref-y");
	}

	public void setNamePositionY(int positionY) {
		getJS().setAttr(".name/ref-y", positionY);
	}

	public void setNamePosition(int x, int y) {
		setNamePositionX(x);
		setNamePositionY(y);
	}

	public void setRank(String rank) {
		getJS().setAttr(".rank/text", rank);
	}

	public String getRank() {
		return getJS().getAttrString(".rank/text");
	};

	public int getRankFontSize() {
		return getJS().getAttrInt(".rank/font-size");
	}

	public void setRankFontSize(int fontSize) {
		getJS().setAttr(".rank/font-size", fontSize);
	}

	public FontWeight getRankFontWeight() {
		try {
			return FontWeight.valueOf(getJS().getAttrString(".rank/font-weight"));
		} catch (Exception e) {
			return null;
		}
	}

	public void setRankFontWeight(FontWeight fontWeight) {
		getJS().setAttr(".rank/font-weight", fontWeight.getCssName());
	}

	public TextDecoration getRankTextDecoration() {
		try {
			return TextDecoration.valueOf(getJS().getAttrString(".rank/text-decoration"));
		} catch (Exception e) {
			return null;
		}
	}

	public void setRankTextDecoration(TextDecoration textDecoration) {
		getJS().setAttr(".rank/text-decoration", textDecoration.getCssName());
	}

	public TextAnchor getRankTextAnchor() {
		try {
			return TextAnchor.valueOf(getJS().getAttrString(".rank/text-anchor"));
		} catch (Exception e) {
			return null;
		}
	}

	public void setRankTextAnchor(TextAnchor textAnchor) {
		getJS().setAttr(".rank/text-anchor", textAnchor.getCssName());
	}

	public int getRankPositionX() {
		return getJS().getAttrInt(".rank/ref-x");
	}

	public void setRankPositionX(int positionX) {
		getJS().setAttr(".rank/ref-x", positionX);
	}

	public int getRankPositionY() {
		return getJS().getAttrInt(".rank/ref-y");
	}

	public void setRankPositionY(int positionY) {
		getJS().setAttr(".rank/ref-y", positionY);
	}

	public void setRankPosition(int x, int y) {
		setRankPositionX(x);
		setRankPositionY(y);
	}

	public void setFillColor(String color) {
		getJS().setAttr(".card/fill", color);
	}

	public String getFillColor() {
		return getJS().getAttrString(".card/fill");
	}

	public void setStrokeColor(String color) {
		getJS().setAttr(".card/stroke", color);
	}

	public String getStrokeColor() {
		return getJS().getAttrString(".card/stroke");
	}

	public void setStrokeWidth(int width) {
		getJS().setAttr(".card/stroke-width", width);
	}

	public int getStrokeWidth() {
		return getJS().getAttrInt(".card/stroke-width");
	}

	public void setImageWidth(int width) {
		getJS().setAttr("image/width", width);
	}

	public int getImageWidth() {
		return getJS().getAttrInt("image/width");
	}

	public void setImageHeight(int height) {
		getJS().setAttr("image/height", height);
	}

	public int getImageHeight() {
		return getJS().getAttrInt("image/height");
	}

	public void setWidth(int width) {
		getJS().setProp("size/width", width);
	}

	public int getWidth() {
		return getJS().getPropInt("size/width");
	}

	public void setHeight(int height) {
		getJS().setProp("size/height", height);
	}

	public int getHeight() {
		return getJS().getPropInt("size/height");
	}

	@Override
	protected final native JointElementJS createJavaScriptObject()/*-{
		var instance = this.@com.joint.gwt.client.ui.JointElementRect::getRect()();
		return new $wnd.joint.shapes.org.Member({
			size : {
				width : instance.@com.joint.gwt.client.util.Rect::getWidth()(),
				height : instance.@com.joint.gwt.client.util.Rect::getHeight()()
			}
		});
	}-*/;
}
