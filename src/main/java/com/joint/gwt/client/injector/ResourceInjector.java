package com.joint.gwt.client.injector;

import com.google.gwt.resources.client.TextResource;
import com.joint.gwt.client.injector.resources.Resources;

/**
 * Responsible for injecting the resources within the application
 * @author andrezimmermmann
 *
 */
public class ResourceInjector {

    private static boolean configured;

    public static void configure(Resources resources) {
        if (!configured) {
            injectJs(resources.joinAllMin());
            injectJs(resources.directedGraphTransform());
            configured = true;
        }
    }

    private static void injectJs(TextResource r) {
        JavaScriptInjector.inject(r.getText());
    }

    private ResourceInjector() {
    }

}
