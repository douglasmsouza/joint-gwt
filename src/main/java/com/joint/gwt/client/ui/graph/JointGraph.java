package com.joint.gwt.client.ui.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.joint.gwt.client.ui.graph.member.JointMember;
import com.joint.gwt.client.ui.graph.member.JointMemberListener;
import com.joint.gwt.client.ui.graph.paper.JointPaperOptions;

/**
 * An implementation of joint.dia.Graph of JointJS library
 * 
 * @author Douglas Matheus de Souza em 01/10/2014
 */
public class JointGraph extends Composite implements Iterable<JointMember> {

	private JavaScriptObject graphJS;
	private JavaScriptObject paperJS;
	private Map<JavaScriptObject, JointMember> members = new HashMap<JavaScriptObject, JointMember>();

	public JointGraph(final JointPaperOptions paperOptions) {
		this(paperOptions, null);
	}

	public JointGraph(final JointPaperOptions paperOptions, final JointMember rootMember) {
		initWidget(new HTML());

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
	private native void initGraphJS(String containerId, JointPaperOptions paperOptions, JointMember rootMember)/*-{
		var graph = new $wnd.joint.dia.Graph;
		this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS = graph;
		//
		paperOptions["model"] = graph;
		paperOptions["el"] = $doc.getElementById(containerId);
		//
		this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS = new $wnd.joint.dia.Paper(paperOptions);
		//
		if (rootMember != null) {
			this.@com.joint.gwt.client.ui.graph.JointGraph::addMember(Lcom/joint/gwt/client/ui/graph/member/JointMember;Lcom/joint/gwt/client/ui/graph/member/JointMember;)(rootMember);
		}
	}-*/;

	/**
	 * Add a listener to the graph
	 * 
	 * @param listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public void addListener(final JointMemberListener listener) {
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

	private JointMember getJointMemberFromJS(JavaScriptObject javaScriptObject) {
		return members.get(javaScriptObject);
	}

	/**
	 * Add a listener to the graph
	 * 
	 * @param listener
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private final native void addListenerRendered(JointMemberListener listener)/*-{
		var graphInstance = this.@com.joint.gwt.client.ui.graph.JointGraph::getInstance()();
		var paper = this.@com.joint.gwt.client.ui.graph.JointGraph::paperJS;
		//
		paper
				.on(
						'cell:pointerdown',
						function(cellView, evt, x, y) {
							if (@com.joint.gwt.client.ui.graph.JointGraph::isMember(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model)) {
								var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getJointMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model);
								listener.@com.joint.gwt.client.ui.graph.member.JointMemberListener::onPointerDown(Lcom/joint/gwt/client/ui/graph/JointGraph;Lcom/joint/gwt/client/ui/graph/member/JointMember;II)(graphInstance, javaMember, x, y);
							}
						});
		paper
				.on(
						'cell:pointermove',
						function(cellView, evt, x, y) {
							if (@com.joint.gwt.client.ui.graph.JointGraph::isMember(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model)) {
								var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getJointMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model);
								listener.@com.joint.gwt.client.ui.graph.member.JointMemberListener::onPointerMove(Lcom/joint/gwt/client/ui/graph/JointGraph;Lcom/joint/gwt/client/ui/graph/member/JointMember;II)(graphInstance, javaMember, x, y);
							}
						});
		paper
				.on(
						'cell:pointerup',
						function(cellView, evt) {
							if (@com.joint.gwt.client.ui.graph.JointGraph::isMember(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model)) {
								var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getJointMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model);
								listener.@com.joint.gwt.client.ui.graph.member.JointMemberListener::onPointerUp(Lcom/joint/gwt/client/ui/graph/JointGraph;Lcom/joint/gwt/client/ui/graph/member/JointMember;)(graphInstance, javaMember);
							}
						});
		paper
				.on(
						'cell:pointerdblclick',
						function(cellView, evt, x, y) {
							if (@com.joint.gwt.client.ui.graph.JointGraph::isMember(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model)) {
								var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getJointMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model);
								listener.@com.joint.gwt.client.ui.graph.member.JointMemberListener::onDblClick(Lcom/joint/gwt/client/ui/graph/JointGraph;Lcom/joint/gwt/client/ui/graph/member/JointMember;II)(graphInstance, javaMember, x, y);
							}
						});
		paper
				.on(
						'cell:pointerclick',
						function(cellView, evt, x, y) {
							if (@com.joint.gwt.client.ui.graph.JointGraph::isMember(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model)) {
								var javaMember = graphInstance.@com.joint.gwt.client.ui.graph.JointGraph::getJointMemberFromJS(Lcom/google/gwt/core/client/JavaScriptObject;)(cellView.model);
								listener.@com.joint.gwt.client.ui.graph.member.JointMemberListener::onClick(Lcom/joint/gwt/client/ui/graph/JointGraph;Lcom/joint/gwt/client/ui/graph/member/JointMember;II)(graphInstance, javaMember, x, y);
							}
						});
	}-*/;

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
	public void addMember(JointMember newMember, JointMember parentMember) {
		newMember.setParentMember(parentMember);
		this.members.put(newMember.getJS(), newMember);
		addMemberJS(newMember, parentMember);
	}

	private native void addMemberJS(JointMember newMember, JointMember parentMember)/*-{
		var graph = this.@com.joint.gwt.client.ui.graph.JointGraph::graphJS;
		var newMemberJS = newMember.@com.joint.gwt.client.ui.graph.member.JointMember::getJS()();
		graph.addCell(newMemberJS);
		//
		if (parentMember != null) {
			link = @com.joint.gwt.client.ui.graph.link.JointLink::createLink(Lcom/joint/gwt/client/ui/JointElement;Lcom/joint/gwt/client/ui/JointElement;)(parentMember,newMember);
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
			nodeSep : 50,
			edgeSep : 80,
			rankDir : "TB"
		});
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
	public Collection<JointMember> getMembers() {
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

	private JointGraph getInstance() {
		return this;
	}

	public Iterator<JointMember> iterator() {
		return members.values().iterator();
	}

}
