package com.joint.gwt.client.constants;

import com.google.gwt.dom.client.Style.HasCssName;

public enum TextAnchor implements HasCssName {

	START, MIDDLE, END;

	public String getCssName() {
		return name().toLowerCase();
	}

}
