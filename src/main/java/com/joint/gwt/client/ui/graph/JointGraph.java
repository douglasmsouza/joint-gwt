package com.joint.gwt.client.ui.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.joint.gwt.client.ui.element.JointElement;
import com.joint.gwt.client.ui.element.view.JointElementView;
import com.joint.gwt.client.ui.graph.link.JointLink;
import com.joint.gwt.client.ui.graph.link.JointLinkRouter;
import com.joint.gwt.client.ui.graph.loader.JointGraphLoader;
import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.client.ui.graph.member.JointMemberListener;
import com.joint.gwt.client.ui.graph.member.JointMemberListenerAdapter;
import com.joint.gwt.client.ui.graph.paper.JointPaperListener;
import com.joint.gwt.shared.Point;
import com.joint.gwt.shared.Position;
import com.joint.gwt.shared.Rect;
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
	private float zoomScale = 1;
	private String selectedColor = "#3498DB";
	private boolean allowChildrenAlignVertically = true;
	private JointLinkRouter defaultLinkRouter;

	public JointGraph(final JointGraphOptions graphOptions) {
		this(graphOptions, null);
	}

	public JointGraph(final JointGraphOptions graphOptions, final T rootMember) {
		SimplePanel contentPanel = new SimplePanel();
		contentPanel.setWidth("100%");
		contentPanel.setHeight("100%");
		initWidget(contentPanel);
		//
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				initGraphJS(getElement().getId(), graphOptions, rootMember);
				//
				addListener(new JointMemberListenerAdapter<T>() {
					public void onClick(JointGraph<T> graph, JointMember<T> member, Position graphPosition, Position pagePosition) {
						/*Update the members colors with its original color*/
						for (JointMember<T> m : graph) {
							m.setBackgroundColor(m.getOriginalBackgroundColor(), true);
						}
						//
						select(member);
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
	 * @param graphOptions options to create the graph's viewport
	 * @param rootMember the root member of the graph
	 * @author Douglas Matheus de Souza
	 */
	private native void initGraphJS(String containerId, JointGraphOptions graphOptions, T rootMember)/*-{
		//Creates the graph
		var graph = new $wnd.joint.dia.Graph;
		//Creates the paper
		graphOptions["model"] = graph;
		//graphOptions["el"] = paperScroller.el;
		var paper = new $wnd.joint.dia.Paper(graphOptions);
		//Creates the paperScroller
		var paperScroller = new $wnd.joint.ui.PaperScroller({
			autoResizePaper : graphOptions.autoResizePaper,
			paper : paper
		});
		//Sets the scroller options
		//paperScroller.options.paper = paper;
		paperScroller.$el.css({
			width : graphOptions.scrollerWidth,
			height : graphOptions.scrollerHeight
		});
		//
		if (graphOptions.allowSelection) {
			//Initialize the selection view
			var selection = new $wnd.Backbone.Collection;
			var selectionView = new $wnd.joint.ui.SelectionView({
				paper : paper,
				graph : graph,
				model : selection,
				boxContent : function() {
					return "";
				}
			});
			paper.on('blank:pointerdown', function(event) {
				if (event.ctrlKey) {
					//Select the content if CTRL key is pressed
					selectionView.startSelecting(event);
				} else {
					paperScroller.startPanning(event);
				}
			});
			paper.on('blank:pointerdblclick', selectionView.stopSelecting);
		} else {
			//Initiate panning when the user grabs the blank area of the paper.
			paper.on('blank:pointerdown', paperScroller.startPanning);
		}
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
			var graphPosition = @com.joint.gwt.shared.Position::create(II)(event.offsetX,event.offsetY);
			var pagePosition = @com.joint.gwt.shared.Position::create(II)(event.pageX,event.pageY);
			graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::fireMemberEvent(Ljava/lang/String;Lcom/joint/gwt/client/ui/graph/member/JointMember;Lcom/joint/gwt/shared/Position;Lcom/joint/gwt/shared/Position;Lcom/joint/gwt/client/ui/graph/member/JointMemberListener;)(event.type,javaMember,graphPosition,pagePosition,listener);
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
		var graphPosition = @com.joint.gwt.shared.Position::create(II)(event.offsetX,event.offsetY);
		var pagePosition = @com.joint.gwt.shared.Position::create(II)(event.pageX,event.pageY);
		graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::firePaperBlankAreaEvent(Ljava/lang/String;Lcom/joint/gwt/shared/Position;Lcom/joint/gwt/shared/Position;Lcom/joint/gwt/client/ui/graph/paper/JointPaperListener;)(event.type,graphPosition,pagePosition,listener);
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
	 * See {@link JointGraph#addMember(JointMember, JointMember, List, boolean, boolean)
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addMember(JointMember<T> newMember, JointMember<T> parentMember) {
		addMember(newMember, parentMember, null, true, true);
	}

/**
	 * See {@link JointGraph#addMember(JointMember, JointMember, List, boolean, boolean)
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addMember(JointMember<T> newMember, JointMember<T> parentMember, boolean autoLayout) {
		addMember(newMember, parentMember, null, true, autoLayout);
	}

/**
	 * See {@link JointGraph#addMember(JointMember, JointMember, List, boolean, boolean)
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addMember(JointMember<T> newMember, JointMember<T> parentMember, List<Point> linkVertices) {
		addMember(newMember, parentMember, linkVertices, true, false);
	}

	/**
	 * Add a new member to the graph
	 * 
	 * @param newMember the new member
	 * @param parentMember parent member to link
	 * @param linkVertices the existing points in the connection between the
	 *            newMember member and the parentMember
	 * @param associateParentAndChild true if should add the newMember to the
	 *            parentMember's children list
	 * @param autoLayout if should automatic calculate the graph's layout after
	 *            insert the new member
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private void addMember(JointMember<T> newMember, JointMember<T> parentMember, List<Point> linkVertices, boolean associateParentAndChild,
			boolean autoLayout) {
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
		addElementJS(newMember);
		// Draw the link in the graph
		if (parentMember != null) {
			JointLink<T> link = new JointLink<T>(parentMember, newMember, defaultLinkRouter);
			link.setVertices(linkVertices);
			addElementJS(link);
		}
		// Auto layout the graph
		if (autoLayout) {
			updateLayout(true);
		} else {
			// Brings the new member to front. If autoLayout, the auto layout
			// will bring the elements to front automatically
			newMember.toFront();
		}
	}

	private native void addElementJS(JointElement element)/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		var elementJS = element.@com.joint.gwt.client.ui.element.JointElement::getJS()();
		graph.addCell(elementJS);
	}-*/;

	/**
	 * Loads a graph based on the root member and its children
	 * 
	 * @param rootBean the root member
	 * @param jointGraphLoader a class to initialize the jointMembers
	 *            before add to the graph
	 * @param autoLayout if should automatic calculate the graph's layout
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void load(T rootBean, JointGraphLoader<T> jointGraphLoader) {
		/*Clear the graph*/
		clear();
		//
		if (rootBean != null) {
			/*Load the new members*/
			load(rootBean, null, jointGraphLoader);
			/*Redraw the graph*/
			if (jointGraphLoader.isAutoLayout()) {
				updateLayoutJS();
			}
			/*Scroll the graph relative to the root member or to the configured initial scroll position*/
			float[] initialScrollPosition = jointGraphLoader.getInitialScrollPosition(rootMember);
			if (initialScrollPosition != null) {
				scrollTo(initialScrollPosition);
			} else {
				scrollTo(rootMember);
			}
		}
	}

	/**
	 * Loads the graph adding each element's children recursively
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private void load(T member, JointMember<T> parentMemberJS, JointGraphLoader<T> jointGraphLoader) {
		if (jointGraphLoader == null) {
			throw new IllegalArgumentException("The jointGraphLoader cannnot be null.");
		}
		//
		if (jointGraphLoader.getRectCalculator() == null) {
			throw new IllegalArgumentException("The jointGraphLoader.getRectCalculator() cannnot return null.");
		}
		//
		/*Calculates the element rect*/
		Rect rect = jointGraphLoader.getRectCalculator().calculateRect(member);
		/*Creates the js element*/
		JointMember<T> memberJS = new JointMember<T>(member, rect);
		/*Initilalize the memberJS*/
		Point initialPosition = jointGraphLoader.getInitialPosition(memberJS);
		List<Point> linkVertices = jointGraphLoader.getLinkVertices(memberJS);
		if (initialPosition != null) {
			memberJS.setPosition(initialPosition.getX(), initialPosition.getY());
		}
		/*Adds the element into the graph*/
		addMember(memberJS, parentMemberJS, linkVertices, false, false);
		/*Adds the element's children into the graph*/
		for (T childBean : member.getChildren()) {
			load(childBean, memberJS, jointGraphLoader);
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
	public void updateLayout(boolean preserveScrollPosition) {
		float[] scrollPosition = preserveScrollPosition ? getScrollPosition() : null;
		//
		updateLayoutJS();
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
	private native void updateLayoutJS()/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		var thisInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		$wnd.joint.layout.DirectedGraph.layout(graph, {
			setLinkVertices : true,
			allowChildrenAlignVertically : thisInstance.@com.joint.gwt.client.ui.graph.JointGraph::allowChildrenAlignVertically,
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
			float[] scrollPosition = this.getScrollPosition();
			//
			this.graphScale = newGraphScale;
			scaleJS(graphScale);
			// Back the scroll to its original position before scale the graph
			scrollTo(scrollPosition);
		}
	};

	/**
	 * Scale the graph by its real size
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void scaleRealSize() {
		zoom(1 - graphScale);
	}

	private native void scaleJS(float scaleValue)/*-{
		var paper = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		paper.scale(scaleValue, scaleValue);
	}-*/;

	/**
	 * Zoom the graph by a float between 0 and 1 that representes the percentage
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void zoom(float zoomValue) {
		float newZoomScale = zoomScale + zoomValue;
		if (newZoomScale > 0) {
			this.zoomScale = newZoomScale;
			zoomJS(zoomValue);
		}
	}

	/**
	 * Zoom the graph by 0.1 (10%)
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void zoomIn() {
		zoom(0.1f);
	}

	/**
	 * Zoom the graph by -0.1 (-10%)
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void zoomOut() {
		zoom(-0.1f);
	}

	/**
	 * Zoom the graph by its real size
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void zoomRealSize() {
		zoom(1 - zoomScale);
	}

	private native void zoomJS(float zoom)/*-{
		var paperScroller = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		paperScroller.zoom(zoom);
	}-*/;

	/**
	 * 
	 * Fits the graph to its content
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void zoomToFit()/*-{
		var paperScroller = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		paperScroller.zoomToFit();
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
		zoomRealSize();
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
	public native void centerContent()/*-{
		var paperScrollerJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		paperScrollerJS.centerContent();
	}-*/;

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
	public native void center(float x, float y)/*-{
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
	public void center(float[] xy) {
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
		scrollTo(getCenterPosition(relativeTo));
	}

	/**
	 * Returns the center position relative to the element
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public float[] getCenterPosition(JointElement relativeTo) {
		float[] xy = relativeTo.getXY();
		//
		float x = xy[0] - (Window.getClientWidth() / 2);
		xy[0] = (x < 0) ? 0 : x;
		//
		/*float y = xy[1] - (Window.getClientHeight() / 2);
		xy[1] = (y < 0) ? 0 : y;*/
		//
		return xy;
	}

	/**
	 * Scroll the graph to xy position
	 * 
	 * @param x horizontal position
	 * @param y vertical position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void scrollTo(float x, float y)/*-{
		var paperScrollerJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		paperScrollerJS.el.scrollLeft = x;
		paperScrollerJS.el.scrollTop = y;
	}-*/;

	/**
	 * Scroll the graph to xy position
	 * 
	 * @param xy horizontal and vertical position
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void scrollTo(float[] xy) {
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
	public native float[] getScrollPosition()/*-{
		var paperScrollerJS = this.@com.joint.gwt.client.ui.graph.JointGraph::paperScrollerJS;
		return [paperScrollerJS.el.scrollLeft, paperScrollerJS.el.scrollTop];
	}-*/;

	/**
	 * Sets the background color for the selected member
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
		/*Sets the current selected member background color*/
		if (selectedMember != null) {
			selectedMember.setBackgroundColor(selectedColor, true);
		}
	}

	/**
	 * Select a member an scroll the graph view to the member's position
	 * 
	 * @param member the member to select
	 * @param scrollGraphView if should scroll the graph's view into the element
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void select(JointMember<T> member, boolean scrollGraphView) {
		select(member);
		//
		if (scrollGraphView) {
			scrollTo(member);
		}
	}

	/**
	 * Select a member an scroll the graph view to the member's position
	 * 
	 * @param member the member to select
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void select(JointMember<T> member) {
		this.selectedMember = member;
		/*Sets the new color*/
		member.setBackgroundColor(selectedColor, true);
	}

	/**
	 * Return the existing points in the connection between a member and its
	 * parent
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native Point[] getLinkVertices(JointMember<T> member) /*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		var memberJS = member.@com.joint.gwt.client.ui.graph.member.JointMember::getJS()();
		var points = [];
		//Searchs for the connection between the member and its parent 
		var connectedLinks = graph.getConnectedLinks(memberJS);
		for (var i = 0; i < connectedLinks.length; i++) {
			var el = connectedLinks[i];
			//When the member is the target, automatically the source is the parent
			if (memberJS.id == el.get('target').id) {
				var vertices = el.get('vertices');
				if (vertices) {
					for (var v = 0; v < vertices.length; v++) {
						var vertice = vertices[v];
						points[v] = @com.joint.gwt.shared.Point::create(FF)(vertice.x, vertice.y);
					}
				}
				//
				break;
			}
		}
		//
		return points;
	}-*/;

	/**
	 * True if can positioning the children vertically in the graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void setAllowChildrenAlignVertically(boolean allowChildrenAlignVertically) {
		this.allowChildrenAlignVertically = allowChildrenAlignVertically;
	}

	/**
	 * Sets the default link router for autolayout graph
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void setDefaultLinkRouter(JointLinkRouter defaultLinkRouter) {
		this.defaultLinkRouter = defaultLinkRouter;
	}
}