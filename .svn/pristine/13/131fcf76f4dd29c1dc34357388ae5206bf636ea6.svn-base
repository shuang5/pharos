package org.renci.pharos.lp;

import java.util.HashMap;
import java.util.List;

import org.renci.pharos.gui.Objectives;

import com.sun.tools.javac.util.Pair;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

class LabelUsageHeuristic extends Heuristic {
	private int objective=0;
	LabelUsageHeuristic(List<Pair<String,String>> l,HashMap<Pair<String,String>,Integer> h){
		super(Objectives.LabelUsage,l,h);
	}
	@Override
	public void setEdgeCost(Edge e, int cost, boolean fictitious) {
		GraphHelper.setCost(e, fictitious, 1);

	}

	@Override
	public void updateEdgeCost(Graph g, List<Vertex> p) {
		//do nothing

	}

	@Override
	public void updateObjective(Edge e) {
		objective+=(int)e.getProperty("Cost");

	}

	@Override
	public String getObjective() {
		return String.valueOf(objective/2);
	}

}
