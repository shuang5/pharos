package org.renci.pharos.lp;

import com.tinkerpop.blueprints.Graph;

interface PharosGraphReader {
	public FlowVariables4D add(Graph graph);
}
