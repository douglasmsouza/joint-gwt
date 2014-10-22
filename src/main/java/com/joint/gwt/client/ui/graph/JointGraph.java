package com.joint.gwt.client.ui.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.joint.gwt.client.ui.element.JointElement;
import com.joint.gwt.client.ui.element.view.JointElementView;
import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.client.ui.graph.member.JointMemberListener;
import com.joint.gwt.client.ui.graph.member.JointMemberListenerAdapter;
import com.joint.gwt.client.ui.graph.paper.JointPaperListener;
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
	 * Add a listener to the graph's members
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
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireMemberEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointermove',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireMemberEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointerup',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireMemberEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointerdblclick',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireMemberEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
		paper
				.on(
						'cell:pointerclick',
						function(cellView, evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireMemberEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(cellView.model, evt, listener);
						});
	}-*/;

	private JointMember<T> getMemberFromJS(JavaScriptObject javaScriptObject) {
		return members.get(javaScriptObject);
	}

	/**
	 * Fires a member event
	 * 
	 * @param the member object
	 * @param event the event object
	 * @param listener the listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native void fireMemberEventJS(JavaScriptObject model, JavaScriptObject event, JointMemberListener<T> listener)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		if (@com.joint.gwt.client.ui.graph.JointGraph::isMember(Lcom/google/gwt/core/client/JavaScriptObject;)(model)) {
			var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(model);
			var graphPosition = @com.joint.gwt.client.util.Position::create(II)(event.offsetX,event.offsetY);
			var pagePosition = @com.joint.gwt.client.util.Position::create(II)(event.pageX,event.pageY);
			graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireMemberEvent(Ljava/lang/String;Lcom/joint/gwt/client/ui/graph/member/JointMember;Lcom/joint/gwt/client/util/Position;Lcom/joint/gwt/client/util/Position;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(event.type,javaMember,graphPosition,pagePosition,listener);
		}
	}-*/;

	/**
	 * Fires a paper event
	 * 
	 * @param event the event name
	 * @param the member that the event was fired
	 * @param graphPosition the pointer position relative to the graph
	 * @param pagePosition the pointer position relative to the page
	 * @param listener the listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private void fireMemberEvent(String event, JointMember<T> member, Position graphPosition, Position pagePosition, JointMemberListener<T> listener) {
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
	 * Add a listener to the paper blank area
	 * 
	 * @param listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addListener(final JointPaperListener<T> listener) {
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
	 * Add a listener to the paper blank area
	 * 
	 * @param listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private final native void addListenerAttached(JointPaperListener<T> listener)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		var paper = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		//
		paper
				.on(
						'blank:pointerdown',
						function(evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::firePaperBlankAreaEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/paper/JointPaperListener;)(evt, listener);
						});
		//
		paper
				.on(
						'blank:pointerdblclick',
						function(evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::firePaperBlankAreaEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/paper/JointPaperListener;)(evt, listener);
						});
		//
		paper
				.on(
						'blank:pointerclick',
						function(evt) {
							graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::firePaperBlankAreaEventJS(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/joint/gwt/client/ui/graph/paper/JointPaperListener;)(evt, listener);
						});
	}-*/;

	/**
	 * Fires a paper blank area listener
	 * 
	 * @param event the event object
	 * @param listener the listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native void firePaperBlankAreaEventJS(JavaScriptObject event, JointPaperListener<T> listener)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		var graphPosition = @com.joint.gwt.client.util.Position::create(II)(event.offsetX,event.offsetY);
		var pagePosition = @com.joint.gwt.client.util.Position::create(II)(event.pageX,event.pageY);
		graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::firePaperBlankAreaEvent(Ljava/lang/String;Lcom/joint/gwt/client/util/Position;Lcom/joint/gwt/client/util/Position;Lcom/joint/gwt/client/ui/graph/paper/JointPaperListener;)(event.type,graphPosition,pagePosition,listener);
	}-*/;

	/**
	 * Fires a paper blank area listener
	 * 
	 * @param event the event name
	 * @param graphPosition the pointer position relative to the graph
	 * @param pagePosition the pointer position relative to the page
	 * @param listener the listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private void firePaperBlankAreaEvent(String event, Position graphPosition, Position pagePosition, JointPaperListener<T> listener) {
		switch (event) {
		case BrowserEvents.MOUSEDOWN:
			listener.onPointerDown(this, graphPosition, pagePosition);
			break;
		case BrowserEvents.DBLCLICK:
			listener.onDblClick(this, graphPosition, pagePosition);
			break;
		case BrowserEvents.CLICK:
			listener.onClick(this, graphPosition, pagePosition);
			break;
		}
	}

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
			this.@com.joint.gwt.client.ui.graph.JointGraph::redraw(Z)(true);
		}
	}-*/;

	/**
	 * Loads a graph based on the root member and its children
	 * 
	 * @param rootBean the root member
	 * @param rectCalculator the rect calculator for width and height
	 *            calculation of each member
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void load(T rootBean, RectCalculator<T> rectCalculator) {
		/*Clear the graph*/
		clear();
		//
		if (rootBean != null) {
			/*Load the new members*/
			load(rootBean, null, rectCalculator);
			/*Redraw the graph*/
			redrawJS();
			/*Scroll the graph relative to the root member*/
			scrollTo(rootMember);
		}
	}

	/**
	 * Loads the graph adding each element's children recursively
	 * 
	 * @author Douglas Matheus de Souza
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
	 * @param preserveScrollPosition true if should preserve the scroll position
	 *            after redraw the graph. If false, the graph will be positioned
	 *            at top-left 0
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void redraw(boolean preserveScrollPosition) {
		int[] scrollPosition = preserveScrollPosition ? getScrollPosition() : null;
		//
		redrawJS();
		//
		if (scrollPosition != null) {
			scrollTo(scrollPosition);
		}
	};

	/**
	 * Redraw the graph using the JointJS DirectedGraph layout
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native void redrawJS()/*-{
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
	 * 
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
	 * @author Douglas Matheus de Souza
	 */
	public void removeLeaf(JointMember<T> member) {
		if (!member.hasChildren()) {
			removeLeafJS(member);
			member.removeFromParent();
			members.remove(member.getJS());
		}
		/*Selected member is null*/
		this.selectedMember = null;
		/*if is excluding the rootMember, sets the root to null*/
		if (member == rootMember) {
			this.rootMember = null;
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

	/**
	 * Return the root member
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public JointMember<T> getRootMember() {
		return rootMember;
	}

	/**
	 * Return the selected member
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public JointMember<T> getSelectedMember() {
		return selectedMember;
	}

	/**
	 * Return true if the graph do not have any members
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public boolean isEmpty() {
		return rootMember == null;
	}

	/**
	 * Center the graph content
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void center()/*-{
		var paperScrollerJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		paperScrollerJS.center();
	}-*/;

	/**
	 * Center the graph content relative to xy position
	 * 
	 * @param x horizontal position
	 * @param y vertical position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void center(int x, int y)/*-{
		var paperScrollerJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		paperScrollerJS.center(x, y);
	}-*/;

	/**
	 * Center the graph content relative to xy position
	 * 
	 * @param xy horizontal and vertical position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void center(int[] xy) {
		center(xy[0], xy[1]);
	}

	/**
	 * Center the graph content relative to the xy position of the element
	 * 
	 * @param relativeTo the element that contains the xy position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void center(JointElement relativeTo) {
		center(relativeTo.getXY());
	}

	/**
	 * Scroll the graph to xy position
	 * 
	 * @param x horizontal position
	 * @param y vertical position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void scrollTo(int x, int y)/*-{
		var paperScrollerJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		paperScrollerJS.scrollLeft = x;
		paperScrollerJS.scrollTop = y;
	}-*/;

	/**
	 * Scroll the graph to xy position
	 * 
	 * @param xy horizontal and vertical position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void scrollTo(int[] xy) {
		scrollTo(xy[0], xy[1]);
	}

	/**
	 * Scroll the graph to the xy position of the element
	 * 
	 * @param relativeTo the element that contains the xy position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void scrollTo(JointElement relativeTo) {
		scrollTo(relativeTo.getXY());
	}

	/**
	 * Return the graph scroll position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native int[] getScrollPosition()/*-{
		var paperScrollerJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		return [paperScrollerJS.scrollLeft, paperScrollerJS.scrollTop];
	}-*/;
}