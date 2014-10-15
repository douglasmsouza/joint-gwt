package com.joint.gwt.client.ui.graph.member;

import java.util.List;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.joint.gwt.client.constants.TextAnchor;
import com.joint.gwt.client.ui.element.JointElementJS;
import com.joint.gwt.client.ui.element.JointElementRect;
import com.joint.gwt.client.util.Rect;
import com.joint.gwt.client.util.RectCalculator;
import com.joint.gwt.shared.bean.JointBean;

/**
 * This class represents a JointJS member
 * 
 * @author Douglas Matheus de Souza
 */
public class JointMember<T extends JointBean<T>> extends JointElementRect {

	private T bean;

	public JointMember(T bean, float width, float height) {
		this(bean, new Rect(width, height));
	}

	public JointMember(T bean, RectCalculator<T> rectCalculator) {
		this(bean, rectCalculator.calculateRect(bean));
	}

	public JointMember(T bean, Rect rect) {
		super(rect);
		this.bean = bean;
	}

	public T getBean() {
		return bean;
	}

	public T getParent() {
		return bean.getParent();
	}

	public void removeFromParent() {
		bean.removeFromParent();
	}

	public void addChild(JointMember<T> child) {
		bean.addChild(child.getBean());
	}

	public List<T> getChildren() {
		return bean.getChildren();
	}

	public boolean hasChildren() {
		return bean.hasChildren();
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

	public void setStrokeRadius(int radius) {
		getJS().setAttr(".card/rx", radius);
		getJS().setAttr(".card/ry", radius);
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

	public void setWidth(float width) {
		getJS().setProp("size/width", width);
	}

	public float getWidth() {
		return getJS().getPropInt("size/width");
	}

	public void setHeight(float height) {
		getJS().setProp("size/height", height);
	}

	public float getHeight() {
		return getJS().getPropInt("size/height");
	}

	@Override
	public void setRect(Rect rect) {
		super.setRect(rect);
		setWidth(rect.getWidth());
		setHeight(rect.getHeight());
	}

	@Override
	protected final native JointElementJS createJavaScriptObject()/*-{
		var instance = this.@com.joint.gwt.client.ui.element.JointElementRect::getRect()();
		return new $wnd.joint.shapes.org.Member({
			size : {
				width : instance.@com.joint.gwt.client.util.Rect::getWidth()(),
				height : instance.@com.joint.gwt.client.util.Rect::getHeight()()
			},
			attrs : {
				'.card' : {
					rx : 0,
					ry : 0
				}
			}
		});
	}-*/;

}
