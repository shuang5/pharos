package org.renci.pharos.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLWriter;

public class ProviderGraph extends PharosGraph{
	
	private static final long serialVersionUID = 1L;

	protected static void setGraphType(Vertex v) {
		v.setProperty("Graph Type", "provider");		
	}
	public static Graph read(Graph graph, File fileName) throws FileNotFoundException, IOException{
		GraphMLReader.inputGraph(graph, new FileInputStream(fileName)); 
		if(isProvider(graph)){
			PharosGraph.removePropertyVertex(graph, "Pharos Graph");
			return graph;
		}
		else return null;
	}
	public static void write(Graph graph, File fileName) throws IOException{
		 Vertex v=graph.addVertex(null);
		 v.setProperty("ID", "Pharos Graph");
		 setGraphType(v);
		 GraphMLWriter writer=new GraphMLWriter(graph);
 		 writer.outputGraph(fileName.getPath());
	}
}
