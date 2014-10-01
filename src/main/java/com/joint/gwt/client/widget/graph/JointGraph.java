package com.joint.gwt.client.widget.graph;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.joint.gwt.client.widget.graph.member.JointMember;
import com.joint.gwt.client.widget.graph.member.JointMemberOptions;
import com.joint.gwt.client.widget.graph.paper.JointPaperListener;
import com.joint.gwt.client.widget.graph.paper.JointPaperOptions;

public class JointGraph extends Composite {

	private Element graphElement;

	public JointGraph(final JointPaperOptions paperOptions, final JointPaperListener paperListener, final JointMemberOptions rootMemberOptions) {
		initWidget(new HTML());

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				initGraphElement(getElement().getId(), paperOptions, paperListener, rootMemberOptions);
			}
		});
	}

	/**
	 * Initialize and bind the graph into the container identified by the
	 * <b>containerId</b>
	 * 
	 * @param containerId DOM id of the graph container
	 * @param paperOptions options to create the graph's viewport
	 * @param paperListener <b>null</b> you don't want to handle events
	 * 
	 * @author Douglas Matheus de Souza
	 */
	private native void initGraphElement(String containerId, JointPaperOptions paperOptions, JointPaperListener paperListener,
			JointMemberOptions rootMemberOptions)/*-{
		var graph = new $wnd.joint.dia.Graph;
		this.@com.joint.gwt.client.widget.graph.JointGraph::graphElement = graph;
		//
		paperOptions["model"] = graph;
		paperOptions["el"] = $doc.getElementById(containerId);
		//
		var paper = new $wnd.joint.dia.Paper(paperOptions);
		var jointInstance = this.@com.joint.gwt.client.widget.graph.JointGraph::getInstance()();
		if (paperListener != null) {
			paper
					.on(
							'cell:pointerdblclick',
							function(cellView, evt, x, y) {
								paperListener.@com.joint.gwt.client.widget.graph.paper.JointPaperListener::onDblClick(Lcom/joint/gwt/client/widget/graph/JointGraph;Lcom/joint/gwt/client/widget/graph/member/JointMember;II)(jointInstance, cellView.model, x, y);
							});
		}
		//
		this.@com.joint.gwt.client.widget.graph.JointGraph::addMember(Lcom/joint/gwt/client/widget/graph/member/JointMemberOptions;Lcom/joint/gwt/client/widget/graph/member/JointMember;)(rootMemberOptions, null);
	}-*/;

	/**
	 * Add a new member to the graph
	 * 
	 * @param memberOptions new member options
	 * @param parentMember parent member to link
	 * 
	 * @author Douglas Matheus de Souza
	 */
	public native void addMember(JointMemberOptions memberOptions, JointMember parentMember)/*-{
		var graph = this.@com.joint.gwt.client.widget.graph.JointGraph::graphElement;
		var newMember = @com.joint.gwt.client.widget.graph.member.JointMember::createMember(Lcom/joint/gwt/client/widget/graph/member/JointMemberOptions;)(memberOptions);
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
