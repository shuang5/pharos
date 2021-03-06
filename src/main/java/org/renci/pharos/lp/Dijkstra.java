package org.renci.pharos.lp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

class Dijkstra {

	private Graph graph=null; 
	@SuppressWarnings("unused")
	private final List<Vertex> nodes;
	private final List<Edge> edges;
	private Set<Vertex> visited;
	private Set<Vertex> unvisited;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;

	Dijkstra(Graph p) {
		graph=p;
		ArrayList<Vertex> arr_v=new ArrayList<Vertex>();
		Iterator<Vertex> v1_itr=p.getVertices().iterator();
		
		while(v1_itr.hasNext()){
			arr_v.add(v1_itr.next());
		}

		ArrayList<Edge> arr_e=new ArrayList<Edge>();
		Iterator<Edge> e1_itr=p.getEdges().iterator();
		while(e1_itr.hasNext()){
			arr_e.add(e1_itr.next());
		}
		this.nodes = arr_v;
		this.edges = arr_e;
	}

	private void execute(Vertex source) {
		visited = new HashSet<Vertex>();
		unvisited = new HashSet<Vertex>();
		distance = new HashMap<Vertex, Integer>();
		predecessors = new HashMap<Vertex, Vertex>();
		distance.put(source, 0);
		unvisited.add(source);
		while (unvisited.size() > 0) {
			Vertex node = getMinimum(unvisited);
			visited.add(node);
			unvisited.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target, getShortestDistance(node)
						+ getDistance(node, target));
				predecessors.put(target, node);
				unvisited.add(target);
			}
		}

	}

	private int getDistance(Vertex node, Vertex target) {
		for (Edge edge : edges) {
			if (edge.getVertex(Direction.OUT).equals(node)
					&& edge.getVertex(Direction.IN).equals(target)) {
				if(edge.getProperty("Cost")!=null)
					return edge.getProperty("Cost");
				else return 1;
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getVertex(Direction.OUT).equals(node)
					&& !isSettled(edge.getVertex(Direction.IN))) {
				neighbors.add(edge.getVertex(Direction.IN));
			}
		}
		return neighbors;
	}

	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertex vertex) {
		return visited.contains(vertex);
	}

	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	
	private LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
	
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
	
		Collections.reverse(path);
		return path;
	}
	private Vertex getVertex(String nodeId){
		Iterator<Vertex> v1_itr=graph.query().has("ID",Compare.EQUAL,nodeId).vertices().iterator();
		if(v1_itr.hasNext())
			return v1_itr.next();
		return null;
			
	}
	LinkedList<Vertex> getShortestPath(String source, String target){
		//System.out.println("Nodes refered: " + source+" and "+target );
		
	    Vertex source1=getVertex(source);
	    Vertex target1=getVertex(target);

		execute(source1);
	    LinkedList<Vertex> path = getPath(target1);
	    //System.out.println(path);
	    if(path==null)return null;
	    if(path.getLast()!=target1 || path.getFirst()!=source1)return null;
	    if(!GraphHelper.isAuxVertexWithLabelTranslation(path.peekFirst()))
	    	path.removeFirst();
	    if(!GraphHelper.isAuxVertexWithLabelTranslation(path.peekLast()))
	    	path.removeLast();
		return path;
	}
}