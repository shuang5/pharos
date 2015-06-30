package org.renci.pharos.lp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.renci.pharos.gui.Objectives;

import com.google.common.collect.Sets;
import com.sun.tools.javac.util.Pair;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

class PortUsageHeuristic extends Heuristic {
	private HashSet<String> edges=Sets.newHashSet();
	PortUsageHeuristic(List<Pair<String,String>> l,HashMap<Pair<String,String>,Integer> h){
		super(Objectives.PortUsage,l,h);
	}
	@Override
	public void setEdgeCost(Edge e, int cost, boolean fictitious) {
		GraphHelper.setCost(e, fictitious, cost);
	}

	@Override
	public void updateEdgeCost(Graph g, List<Vertex> p) {
		GraphHelper.updateCost(g, p, 1);
	}

	@Override
	public void updateObjective(Edge e) {
		if(!edges.contains(e.getProperty("Original"))){
			edges.add((String) e.getProperty("Original"));
		}
	}

	@Override
	public String getObjective() {
		return String.valueOf(edges.size());
	}

}
