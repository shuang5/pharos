package org.renci.pharos.gui;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

public abstract class PharosGraph extends TinkerGraph{
	private static final long serialVersionUID = 1L;
	protected Vertex vertex;
	public static String getGraphType(Graph graph){
		for(Vertex v:graph.getVertices()){
			if(v.getProperty("ID").equals("Pharos Graph"))
				return v.getProperty("Graph Type");
		}
		return null;
	}
	public static boolean isProvider(Graph graph){
		return getGraphType(graph).equals("provider");
	}
	public static boolean isRequest(Graph graph){
		return getGraphType(graph).equals("request");
	}
	protected static void removePropertyVertex(Graph graph, String id){
		for(Vertex v:graph.getVertices()){
			if(v.getProperty("ID").equals(id))
				graph.removeVertex(v);
		}
	}
	
}
