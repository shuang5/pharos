package org.renci.pharos.lp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

class GraphHelper {
	//Assumption: ID is unique 
	static Vertex getVertex(Graph graph, String nodeId){
		Iterator<Vertex> v1_itr=graph.query().has("ID",Compare.EQUAL,nodeId).vertices().iterator();
		if(v1_itr.hasNext())
			return v1_itr.next();
		return null;
			
	}
	static Vertex addAuxVertex(Graph graph, String nodeId,String original){
		if(getVertex(graph,nodeId)==null){
			Vertex v=graph.addVertex(null);
			v.setProperty("Original", original);
			v.setProperty("ID", nodeId);
			return v;
		}
		else return getVertex(graph,nodeId);
			
	}
	
	static Vertex addAuxVertexWithLabelTranslation(Graph graph, String nodeId){
		if(getVertex(graph,nodeId)==null){
			Vertex v=graph.addVertex(null);
			v.setProperty("Original", nodeId+"_");
			v.setProperty("ID", nodeId);
			return v;
		}
		else return getVertex(graph,nodeId);
			
	}
	static boolean isAuxVertexWithLabelTranslation(Vertex v){
		return v.getProperty("ID").equals(v.getProperty("Original")+"_") || 
				v.getProperty("ID").equals(v.getProperty("Original"));
	}
	private static Edge getEdge(Graph graph, String edgeId){
		Iterator<Edge> v1_itr=graph.query().has("ID",Compare.EQUAL,edgeId).edges().iterator();
		if(v1_itr.hasNext())
			return v1_itr.next();
		return null;
			
	}
	private static Edge addEdge(Graph graph, Vertex out, Vertex in){
		String edgeId=toEdgeId(getVertexID(out),getVertexID(in));
		if(getEdge(graph,edgeId)==null){
			Edge e=graph.addEdge(null,out,in,edgeId);
			e.setProperty("ID", edgeId);
			return e;
		}
		else return getEdge(graph,edgeId);
			
	}
	static Edge addAuxEdge(Graph graph, Vertex out,Vertex in){
		Edge e=addEdge(graph,out,in);
		String id1=out.getProperty("Original");
		String id2=in.getProperty("Original");
		e.setProperty("Original", toEdgeId(id1, id2));
		return e;
	}
	static Edge addAuxEdge(Graph graph, Vertex out,Vertex in, int cost){
		Edge e=addAuxEdge(graph,out,in);
		e.setProperty("Cost", cost);
		return e;
	}
	static String getVertexID(Vertex v){
		return v.getProperty("ID");
	}
	static void setVertexID(Vertex v, String id){
		v.setProperty("ID", id);
	}
	static String getEdgeID(Edge e){
		return e.getProperty("ID");
	}
	static void setEdgeID(Edge e, String id){
		e.setProperty("ID", id);
	}
	private static Edge getEdge(Graph g, Vertex out,Vertex in){
		for (Edge e: g.getEdges()){
			if(e.getVertex(Direction.OUT).getProperty("ID").equals(out.getProperty("ID")) 
					&& e.getVertex(Direction.IN).getProperty("ID").equals(in.getProperty("ID")))
				return e;
		}
		return null;
	}
	static String toEdgeId(String vertexIdOut,String vertexIdIn){
		return vertexIdOut+"_"+vertexIdIn;
	}
	static int getCost(Graph g,String id1,String id2){
		return getEdge(g, toEdgeId(id1,id2)).getProperty("Cost");
	}
	static Vertex copyVertexProperties(Graph g, Vertex from){
		if(getVertex(g,getVertexID(from))==null){
			Vertex to=g.addVertex(null);
			for(String s:from.getPropertyKeys()){
				to.setProperty(s, from.getProperty(s));
			}
			return to;
		}
		else {
			Vertex to=getVertex(g,getVertexID(from));
			return to;
		}
		
	}
	private static Edge copyEdgeProperties(Graph g, Edge from){
		if(getEdge(g,getEdgeID(from))==null){
			Vertex vin=getVertex(g,getVertexID(from.getVertex(Direction.IN)));
			Vertex vout=getVertex(g,getVertexID(from.getVertex(Direction.OUT)));
			Edge to=addEdge(g, vout, vin);
			for(String s:from.getPropertyKeys()){
				to.setProperty(s, from.getProperty(s));
			}
			return to;
		}
		else {
			Edge to=getEdge(g,getEdgeID(from));
			return to;
		}
		
	}
	static Graph makeCopy(Graph graph){
		Graph g=new TinkerGraph();
		for (Vertex v: graph.getVertices()){
			copyVertexProperties(g,v);
			for(Edge e:v.getEdges(Direction.IN)){
				copyVertexProperties(g, e.getVertex(Direction.OUT));
				copyEdgeProperties(g, e);				 
			}
		}
		return g;
	}
	private static void removeEdge(Graph g, Edge e){
		g.removeEdge(e);
	}
	static List<Edge> transformPath(Graph g,List<Vertex> p){
		List<Edge> e=new LinkedList<Edge>();
		Vertex prev=null;
		for(Vertex next:p){
			if(prev!=null){
				e.add(getEdge(g, prev, next));
			}
			prev=next;
		}
		return e;
	}
	static void removePath(Graph g,List<Vertex> p){
		Vertex prev=null;
		for(Vertex next:p){
			if(prev!=null){
				removeEdge(g,getEdge(g,prev,next));
			}
			prev=next;
		}
	}
	static void updateCost(Graph g,List<Vertex> p, int c){
		List<Edge> list=transformPath(g, p);
		for(Edge e:list){
			for(Edge ee:g.getEdges()){
				if(ee.getProperty("Original").equals(e.getProperty("Original"))){
					int cost=(int)ee.getProperty("Cost");
					if(ee.getProperty("Artificial").equals("Yes"))
						cost-=c;
					else cost-=2*c;
					ee.setProperty("Cost",cost);
				}
			}
		}		
	}
	static void setCost(Edge e,boolean ficitious, int cost){
		if(ficitious){
			e.setProperty("Cost", cost);
			e.setProperty("Artificial", "Yes");
		}
		else{
			e.setProperty("Cost", 2*cost);
			e.setProperty("Artificial", "No");
		}
	}
}
