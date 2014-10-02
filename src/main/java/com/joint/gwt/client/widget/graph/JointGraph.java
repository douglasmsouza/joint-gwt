package com.joint.gwt.client.widget.graph;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.joint.gwt.client.widget.graph.member.JointMember;
import com.joint.gwt.client.widget.graph.member.JointMemberListener;
import com.joint.gwt.client.widget.graph.paper.JointPaperOptions;

/**
 * An implementation of joint.dia.Graph of JointJS library
 * 
 * @author Douglas Matheus de Souza em 01/10/2014
 */
public class JointGraph extends Composite {

	private Element graphElement;

	public JointGraph(final JointPaperOptions paperOptions, final JointMember rootMember, final JointMemberListener memberListener) {
		initWidget(new HTML());

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				initGraphElement(getElement().getId(), paperOptions, rootMember, memberListener);
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
	 * @param memberListener <b>null</b> you don't want to handle events
	 * @author Douglas Matheus de Souza
	 */
	private native void initGraphElement(String containerId, JointPaperOptions paperOptions, JointMember rootMember,
			JointMemberListener memberListener)/*-{
		var graph = new $wnd.joint.dia.Graph;
		this.@com.joint.gwt.client.widget.graph.JointGraph::graphElement = graph;
		//
		paperOptions["model"] = graph;
		paperOptions["el"] = $doc.getElementById(containerId);
		//
		var paper = new $wnd.joint.dia.Paper(paperOptions);
		var graphInstance = this.@com.joint.gwt.client.widget.graph.JointGraph::getInstance()();
		if (memberListener != null) {
			function isMember(el){
				return el instanceof $wnd.joint.shapes.org.Member;
			}
			//
			paper.on('cell:pointerdown',function(cellView, evt, x, y) {
				if(isMember(cellView.model)){
					memberListener.@com.joint.gwt.client.widget.graph.member.JointMemberListener::onPointerDown(Lcom/joint/gwt/client/widget/graph/JointGraph;Lcom/joint/gwt/client/widget/graph/member/JointMember;II)(graphInstance, cellView.model, x, y);
				}
			});
			paper.on('cell:pointermove',function(cellView, evt, x, y) {
				if(isMember(cellView.model)){
					memberListener.@com.joint.gwt.client.widget.graph.member.JointMemberListener::onPointerMove(Lcom/joint/gwt/client/widget/graph/JointGraph;Lcom/joint/gwt/client/widget/graph/member/JointMember;II)(graphInstance, cellView.model, x, y);
				}
			});
			paper.on('cell:pointerup',function(cellView, evt) {
				if(isMember(cellView.model)){
					memberListener.@com.joint.gwt.client.widget.graph.member.JointMemberListener::onPointerUp(Lcom/joint/gwt/client/widget/graph/JointGraph;Lcom/joint/gwt/client/widget/graph/member/JointMember;)(graphInstance, cellView.model);
				}
			});
			paper.on('cell:pointerdblclick',function(cellView, evt, x, y) {
				if(isMember(cellView.model)){
					memberListener.@com.joint.gwt.client.widget.graph.member.JointMemberListener::onDblClick(Lcom/joint/gwt/client/widget/graph/JointGraph;Lcom/joint/gwt/client/widget/graph/member/JointMember;II)(graphInstance, cellView.model, x, y);
				}
			});
			paper.on('cell:pointerclick',function(cellView, evt, x, y) {
				if(isMember(cellView.model)){
					memberListener.@com.joint.gwt.client.widget.graph.member.JointMemberListener::onClick(Lcom/joint/gwt/client/widget/graph/JointGraph;Lcom/joint/gwt/client/widget/graph/member/JointMember;II)(graphInstance, cellView.model, x, y);
				}
			});
		}
		//
		this.@com.joint.gwt.client.widget.graph.JointGraph::addMember(Lcom/joint/gwt/client/widget/graph/member/JointMember;Lcom/joint/gwt/client/widget/graph/member/JointMember;)(rootMember, null);
	}-*/;

	/**
	 * Add a new member to the graph
	 * 
	 * @param newMember the new member
	 * @param parentMember parent member to link
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void addMember(JointMember newMember, JointMember parentMember)/*-{
		var graph = this.@com.joint.gwt.client.widget.graph.JointGraph::graphElement;
		graph.addCell(newMember);
		//
		if (parentMember != null) {
			var link = @com.joint.gwt.client.widget.graph.link.JointLink::createLink(Lcom/google/gwt/dom/client/Element;Lcom/google/gwt/dom/client/Element;)(parentMember, newMember)
			graph.addCell(link);
		}
		//
		this.@com.joint.gwt.client.widget.graph.JointGraph::redraw()();
	}-*/;

	/**
	 * Redraw the graph and recalculate the positions of its members
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native Element redraw()/*-{
		var graph = this.@com.joint.gwt.client.widget.graph.JointGraph::graphElement;
		$wnd.joint.layout.DirectedGraph.layout(graph, {
			setLinkVertices : true,
			nodeSep : 50,
			edgeSep : 80,
			rankDir : "TB"
		});
	}-*/;

	private JointGraph getInstance() {
		return this;
	}
}
