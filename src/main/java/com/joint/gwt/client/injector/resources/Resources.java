package com.joint.gwt.client.injector.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Interface that provides the javascript resources.
 *
 * @author andrezimmermann
 */
public interface Resources extends ClientBundle {

    /**
     * Returns the JS file.
     * <p/>
     * Override this method to use your own JS file.
     *
     * @return the JS file
     */
    @Source("js/joint.DirectedGraph.transform.js")
    TextResource directedGraphTransform();

    /**
     * Returns the JS file.
     * <p/>
     * Override this method to use your own JS file.
     *
     * @return the JS file
     */
    @Source("js/joint.all.min.js")
    TextResource joinAllMin();

}
