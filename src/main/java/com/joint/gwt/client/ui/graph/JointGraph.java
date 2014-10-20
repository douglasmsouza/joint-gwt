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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.joint.gwt.client.ui.element.view.JointElementView;
import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.client.ui.graph.member.JointMemberListener;
import com.joint.gwt.client.ui.graph.member.JointMemberListenerAdapter;
import com.joint.gwt.client.ui.graph.paper.JointPaperOptions;
import com.joint.gwt.client.util.Position;
import com.joint.gwt.client.util.Rect;
import com.joint.gwt.client.util.RectCalculator;
import com.joint.gwt.shared.bean.JointBean;

/**
 * An implementation of joint.dia.Graph of JointJS library
 * 
 * @param <T> type of user beans of each member
 * 
 * @author Douglas Matheus de Souza
 */
public class JointGraph<T extends JointBean<T>> extends Composite implements Iterable<JointMember<T>> {

	private JavaScriptObject graphJS;
	private JavaScriptObject paperJS;
	private JavaScriptObject paperScrollerJS;
	//
	private JointMember<T> rootMember;
	private JointMember<T> selectedMember;
	private Map<JavaScriptObject, JointMember<T>> members = new HashMap<JavaScriptObject, JointMember<T>>();

	private float graphScale = 1;

	public JointGraph(final JointPaperOptions paperOptions) {
		this(paperOptions, null);
	}

	public JointGraph(final JointPaperOptions paperOptions, final T rootMember) {
		initWidget(new SimplePanel());

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				initGraphJS(getElement().getId(), paperOptions, rootMember);
				//
				addListener(new JointMemberListenerAdapter<T>() {
					public void onClick(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition) {
						JointGraph.this.selectedMember = member;
					};
				});
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
		var paperScroller = new $wnd.joint.ui.PaperScroller;
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
			this.@com.joint.gwt.client.ui.graph.JointGraph::addMember(Lcom/joint/gwt/client/ui/graph/member/JointMember;Lcom/joint/gwt/client/ui/graph/member/JointMember;)(rootMember,null);
		}
	}-*/;

	/**
	 * Sets the element view of the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void setElementView(final JointElementView<JointMember<T>> elementView) {
		if (isAttached()) {
			setElementViewAttached(elementView);
		} else {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				public void execute() {
					setElementViewAttached(elementView);
				}
			});
		}
	}

	/**
	 * Sets the element view of the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native void setElementViewAttached(JointElementView<JointMember<T>> elementView)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		var paperJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		paperJS.options.elementView = $wnd.joint.dia.ElementView
				.extend({
					template : elementView.@com.joint.gwt.client.ui.element.view.JointElementView::createTemplate()(),
					initialize : function() {
						//Calls the original function to initialize the element						
						$wnd.joint.dia.ElementView.prototype.initialize.apply(this, arguments);
						//Calls the java listener to initialize the java element
						var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(this.model);
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
			addListenerAttached(listener);
		} else {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				public void execute() {
					addListenerAttached(listener);
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
	private final native void addListenerAttached(JointMemberListener<T> listener)/*-{
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

	private JointMember<T> getMemberFromJS(JavaScriptObject javaScriptObject) {
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
			var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(model);
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
	private void fireEvent(String event, JointMember<T> member, Position graphPosition, Position pagePosition, JointMemberListener<T> listener) {
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
	 * See
	 * {@link JointGraph#addMember(JointMember, JointMember, boolean, boolean)}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addMember(JointMember<T> newMember, JointMember<T> parentMember) {
		addMember(newMember, parentMember, true, true);
	}

	/**
	 * See
	 * {@link JointGraph#addMember(JointMember, JointMember, boolean, boolean)}
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addMember(JointMember<T> newMember, JointMember<T> parentMember, boolean redraw) {
		addMember(newMember, parentMember, true, redraw);
	}

	/**
	 * Add a new member to the graph
	 * 
	 * @param newMember the new member
	 * @param parentMember parent member to link
	 * @param associateParentAndChild true if should add the newMember to the
	 *            parentMember's children list
	 * @param redraw if should redraw the graph after insert the new member
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private void addMember(JointMember<T> newMember, JointMember<T> parentMember, boolean associateParentAndChild, boolean redraw) {
		// Sets the graph's root member
		if (rootMember == null) {
			this.rootMember = newMember;
		}
		// Associates the newMember to the parentMember
		if (parentMember != null && associateParentAndChild) {
			parentMember.addChild(newMember);
		}
		// Maps the java object instance by the JavaScriptObject
		this.members.put(newMember.getJS(), newMember);
		// Draw the member in the graph
		addMemberJS(newMember, parentMember, redraw);
	}

	private native void addMemberJS(JointMember<T> newMember, JointMember<T> parentMember, boolean redraw)/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		var newMemberJS = newMember.@com.joint.gwt.client.ui.graph.member.JointMember::getJS()();
		graph.addCell(newMemberJS);
		//
		if (parentMember != null) {
			link = @com.joint.gwt.client.ui.graph.link.JointLink::createLink(Lcom/joint/gwt/client/ui/element/JointElement;Lcom/joint/gwt/client/ui/element/JointElement;)(parentMember,newMember);
			graph.addCell(link);
		}
		//
		if (redraw) {
			this.@com.joint.gwt.client.ui.graph.JointGraph::redraw()();
		}
	}-*/;

	/**
	 * Loads a graph based on the root member and its children
	 * 
	 * @param the root member
	 * @param the rect calculator for width and height calculation of each
	 *            member
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void load(T rootMember, RectCalculator<T> rectCalculator) {
		/*Clear the graph*/
		clear();
		//
		if (rootMember != null) {
			/*Load the new members*/
			load(rootMember, null, rectCalculator);
			/*Redraw the graph*/
			redraw();
		}
	}

	/**
	 * Loads the graph adding each element's children recursively
	 * 
	 * @author Douglas Matheus de Souza em 14/10/2014
	 */
	private void load(T member, JointMember<T> parentMemberJS, RectCalculator<T> rectCalculator) {
		if (rectCalculator == null)
			throw new IllegalArgumentException("The rectCalculator cannnot be null.");
		/*Calculates the element rect*/
		Rect rect = rectCalculator.calculateRect(member);
		/*Creates the js element*/
		JointMember<T> memberJS = new JointMember<T>(member, rect);
		/*Adds the element into the graph*/
		addMember(memberJS, parentMemberJS, false, false);
		/*Adds the element's children into the graph*/
		for (T childBean : member.getChildren()) {
			load(childBean, memberJS, rectCalculator);
		}
	}

	/**
	 * Redraw the graph and recalculate the positions of its members
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native Element redraw()/*-{
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
	public void clear() {
		this.rootMember = null;
		this.selectedMember = null;
		this.members.clear();
		zoomReal();
		clearJS();
	}

	/**
	 * Clear the graph removing all its members
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private final native void clearJS()/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		graph.resetCells([]);
	}-*/;

	/**
	 * Returns the list of members, excluding the links
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public Collection<JointMember<T>> getMembers() {
		return members.values();
	}

	/**
	 * Removes a member and its links from the graph. Only remove if the member
	 * is a leaf
	 * 
	 * @param member the member to remove from the graph
	 * 
	 * @author Douglas Matheus de Souza em 15/10/2014
	 */
	public void removeLeaf(JointMember<T> member) {
		if (!member.hasChildren()) {
			removeLeafJS(member);
			member.removeFromParent();
			members.remove(member.getJS());
		}
	}

	private native void removeLeafJS(JointMember<T> member)/*-{
		var memberJS = member.@com.joint.gwt.client.ui.graph.member.JointMember::getJS()();
		memberJS.remove();
	}-*/;

	private JointGraph<T> getInstance() {
		return this;
	}

	@Override
	public Iterator<JointMember<T>> iterator() {
		return members.values().iterator();
	}

	public JointMember<T> getRootMember() {
		return rootMember;
	}

	public JointMember<T> getSelectedMember() {
		return selectedMember;
	}

	public boolean isEmpty() {
		return rootMember == null;
	}
}
