package org.renci.pharos.lp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.sun.tools.javac.util.Pair;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;


class NodeAdjacencyMatrix extends Matrix {
	//matrix(i,j) = EdgeDisc 
	//intenally we use hashmap<pair<i,j>,EdgeDisc>
	private HashMap<Pair<String,String>, EdgeDisc> matrix = Maps.newHashMap();
		
	NodeAdjacencyMatrix(Graph graph) {
		Iterable<Vertex> it=graph.getVertices();
		for (Vertex v: it){
			  nodeID2id.put((String)v.getProperty("ID"), Integer.parseInt((String)v.getId()));
			  Iterable<Edge> ite=v.getEdges(Direction.OUT);
			  for (Edge e: ite){
				  //System.out.println(e.getId());
				  edgeID2id.put((String)e.getProperty("ID"), Integer.parseInt((String)v.getId()));
				  String r1=(String)e.getProperty("Request1");
				  String r2=(String)e.getProperty("Request2");
				  String[] tokens1=r1.split("[:]");
				  String[] tokens2=r2.split("[:]");
				  int demand=Math.min(Integer.parseInt(tokens1[1]),Integer.parseInt(tokens2[1]));
				  Vertex vv=e.getVertex(Direction.IN);
				  //System.out.println(v.getId()+":"+vv.getId());
				  Pair<String,String> edge= new Pair<String,String> ((String)v.getProperty("ID"),(String)vv.getProperty("ID"));
				  EdgeDisc ed=new EdgeDisc((String) e.getProperty("ID"),demand);
				  matrix.put(edge, ed);
				  
			  }
			  
		  }
	}
	@Override
	public Iterator<Entry<Pair<String, String>, EdgeDisc>> getIterator(){
		return matrix.entrySet().iterator();
	}
	

}
