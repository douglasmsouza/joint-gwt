package com.joint.gwt.client.ui.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.joint.gwt.client.ui.element.view.JointElementView;
import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.client.ui.graph.member.JointMemberListener;
import com.joint.gwt.client.ui.graph.paper.JointPaperOptions;
import com.joint.gwt.client.util.Position;

/**
 * An implementation of joint.dia.Graph of JointJS library
 * 
 * @param <T> type of user IDs of each member
 * 
 * @author Douglas Matheus de Souza
 */
@SuppressWarnings("rawtypes")
public class JointGraph<T extends JointMember> extends Composite implements Iterable<T> {

	private JavaScriptObject graphJS;
	private JavaScriptObject paperJS;
	private JavaScriptObject paperScrollerJS;
	private Map<JavaScriptObject, T> members = new HashMap<JavaScriptObject, T>();

	private float graphScale = 1;

	public JointGraph(final JointPaperOptions paperOptions) {
		this(paperOptions, null);
	}

	public JointGraph(final JointPaperOptions paperOptions, final T rootMember) {
		initWidget(new SimplePanel());

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				initGraphJS(getElement().getId(), paperOptions, rootMember);
			}
		});
	}

	/**
	 * Initialize and bind the graph into the container identified by the
	 * <b>containerId</b>
	 * 
	 * @param containerId DOM id of the graph container
	 * @param paperOptions options to create the graph's viewport
	 * @param rootMember the root member of the graph
	 * @author Douglas Matheus de Souza
	 */
	private native void initGraphJS(String containerId, JointPaperOptions paperOptions, T rootMember)/*-{
		//Creates the graph
		var graph = new $wnd.joint.dia.Graph;
		//Creates the paperScroller
		var paperScroller = new $wnd.joint.ui.PaperScroller();
		//Creates the paper
		paperOptions["model"] = graph;
		paperOptions["el"] = paperScroller.el;
		var paper = new $wnd.joint.dia.Paper(paperOptions);
		//Sets the scroller options
		paperScroller.options = {
			paper : paper,
			autoResizePaper : true
		};
		paperScroller.$el.css({
			width : paperOptions.scrollerWidth,
			height : paperOptions.scrollerHeight
		});
		//Initiate panning when the user grabs the blank area of the paper.
		paper.on('blank:pointerdown', paperScroller.startPanning);
		//Bind the paper into the container
		var containerElement = $doc.getElementById(containerId);
		containerElement.appendChild(paperScroller.render().el);
		//
		this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS = graph;
		this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS = paper;
		this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS = paperScroller;
		//
		if (rootMember != null) {
			this.@com.joint.gwt.client.ui.graph.JointGraph::addMember(Lcom/joint/gwt/client/ui/graph/member/JointMember;Lcom/joint/gwt/client/ui/graph/member/JointMember;)(rootMember);
		}
	}-*/;

	/**
	 * Sets the element view of the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void setElementView(final JointElementView<T> elementView) {
		if (isAttached()) {
			setElementViewRendered(elementView);
		} else {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				public void execute() {
					setElementViewRendered(elementView);
				}
			});
		}
	}

	/**
	 * Sets the element view of the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native void setElementViewRendered(JointElementView<T> elementView)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		var paperJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		paperJS.options.elementView = $wnd.joint.dia.ElementView
				.extend({
					template : elementView.@com.joint.gwt.client.ui.element.view.JointElementView::createTemplate()(),
					initialize : function() {
						var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getJointMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(this.model);
						elementView.@com.joint.gwt.client.ui.element.view.JointElementView::initialize(Lcom/joint/gwt/client/ui/element/JointElement;)(javaMember);
					}
				});
	}-*/;

	/**
	 * Add a listener to the graph
	 * 
	 * @param listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addListener(final JointMemberListener<T> listener) {
		if (isAttached()) {
			addListenerRendered(listener);
		} else {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				public void execute() {
					addListenerRendered(listener);
				}
			});
		}
	}

	/**
	 * Add a listener to the graph
	 * 
	 * @param listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private final native void addListenerRendered(JointMemberListener<T> listener)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		var paper = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		//
		paper
				.on(
						'cell:pointerdown',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointermove',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointerup',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointerdblclick',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointerclick',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
	}-*/;

	private T getJointMemberFromJS(JavaScriptObject javaScriptObject) {
		return members.get(javaScriptObject);
	}

	/**
	 * Fires an paper event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native void fireEventJS(JavaScriptObject model, JavaScriptObject event, JointMemberListener<T> listener)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		if (@com.joint.gwt.client.ui.graph.JointGraph::isMember(Lcom/google/gwt/core/client/JavaScriptObject;)(model)) {
			var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getJointMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(model);
			var graphPosition = @com.joint.gwt.client.util.Position::create(II)(event.offsetX,event.offsetY);
			var pagePosition = @com.joint.gwt.client.util.Position::create(II)(event.pageX,event.pageY);
			graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireEvent(Ljava/lang/String;Lcom/joint/gwt/client/ui/graph/member/JointMember;Lcom/joint/gwt/client/util/Position;Lcom/joint/gwt/client/util/Position;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(event.type,javaMember,graphPosition,pagePosition,listener);
		}
	}-*/;

	/**
	 * Fires an paper event
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private void fireEvent(String event, T member, Position graphPosition, Position pagePosition, JointMemberListener<T> listener) {
		switch (event) {
		case BrowserEvents.MOUSEDOWN:
			listener.onPointerDown(this, member, graphPosition, pagePosition);
			break;
		case BrowserEvents.MOUSEMOVE:
			listener.onPointerMove(this, member, graphPosition, pagePosition);
			break;
		case BrowserEvents.MOUSEUP:
			listener.onPointerUp(this, member, graphPosition, pagePosition);
			break;
		case BrowserEvents.DBLCLICK:
			listener.onDblClick(this, member, graphPosition, pagePosition);
			break;
		case BrowserEvents.CLICK:
			listener.onClick(this, member, graphPosition, pagePosition);
			break;
		}
	}

	private static final native boolean isMember(JavaScriptObject el)/*-{
		return el instanceof $wnd.joint.shapes.org.Member;
	}-*/;

	/**
	 * Add a new member to the graph
	 * 
	 * @param newMember the new member
	 * @param parentMember parent member to link
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addMember(T newMember, T parentMember) {
		newMember.setParentMember(parentMember);
		this.members.put(newMember.getJS(), newMember);
		addMemberJS(newMember, parentMember);
	}

	private native void addMemberJS(T newMember, T parentMember)/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		var newMemberJS = newMember.@com.joint.gwt.client.ui.graph.member.JointMember::getJS()();
		graph.addCell(newMemberJS);
		//
		if (parentMember != null) {
			link = @com.joint.gwt.client.ui.graph.link.JointLink::createLink(Lcom/joint/gwt/client/ui/element/JointElement;Lcom/joint/gwt/client/ui/element/JointElement;)(parentMember,newMember);
			graph.addCell(link);
		}
		//
		this.@com.joint.gwt.client.ui.graph.JointGraph::redraw()();
	}-*/;

	/**
	 * Redraw the graph and recalculate the positions of its members
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native Element redraw()/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		$wnd.joint.layout.DirectedGraph.layout(graph, {
			setLinkVertices : true,
			nodeSep : 50
		});
	}-*/;

	/**
	 * Scale the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void scale(float scaleValue) {
		float newGraphScale = graphScale + scaleValue;
		if (newGraphScale > 0) {
			this.graphScale = newGraphScale;
			scaleJS(graphScale);
		}
	};

	private native void scaleJS(float scaleValue)/*-{
		var paper = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		paper.scale(scaleValue, scaleValue);
	}-*/;

	/**
	 * Scale the graph by 0.1
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void zoomIn() {
		scale(0.1f);
	}

	/**
	 * Scale the graph by -0.1
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void zoomOut() {
		scale(-0.1f);
	}

	/**
	 * Scale the graph by its real size
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void zoomReal() {
		scale(1 - graphScale);
	}

	/**
	 * Fits the graph to its content
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void fitToContent()/*-{
		var paper = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		paper.fitToContent();
	}-*/;

	/**
	 * Clear the graph removing all its members
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native void clear()/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		graph.clear();
	}-*/;

	/**
	 * Returns the list of members, excluding the links
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public Collection<T> getMembers() {
		return members.values();
	}

	/**
	 * Returns the graph as a JSON object
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public final native JSONObject toJSON()/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		return new @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(graph.toJSON());
	}-*/;

	private JointGraph<T> getInstance() {
		return this;
	}

	public Iterator<T> iterator() {
		return members.values().iterator();
	}

}
