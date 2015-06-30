package org.renci.pharos.lp;

import java.util.ArrayList;
import java.util.Iterator;

import org.renci.pharos.flow.FlowPharosException;
import org.renci.pharos.flow.FlowSpace;
import org.renci.pharos.flow.FlowLabels;
import org.renci.pharos.flow.FlowUnit;
import org.renci.pharos.gui.NodePortProperties;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

class RequestGraphReader implements PharosGraphReader{
	/**
	 * 
	 */
	private final FlowVariables4D flowVariables4D;

	/**
	 * @param flowVariables4D
	 */
	RequestGraphReader(FlowVariables4D flowVariables4D) {
		this.flowVariables4D = flowVariables4D;
	}
/*
	@Override
	public FlowVariables4D add(Graph graph) {
		Iterable<Vertex> it=graph.getVertices();		
		for (Vertex v: it){
		    Iterable<Edge> ite=v.getEdges(Direction.OUT);
		    for (Edge e: ite){
		  	    ArrayList<String> labels= new ArrayList<String>();
		  	    //get labels from graph	  	    
		  	    Vertex vin=e.getVertex(Direction.IN);	
		  	    Vertex vout=e.getVertex(Direction.OUT);
		  	    ArrayList<NodePortProperties> arr_in=
		  	    		NodePortProperties.getNodePortProepertiesList((String)vin.getProperty("Ports"));
		  	    ArrayList<NodePortProperties> arr_out=
		  	    		NodePortProperties.getNodePortProepertiesList((String)vout.getProperty("Ports"));
		  	    for(NodePortProperties npin:arr_in){
		  	    	for(NodePortProperties npout:arr_out){
		  	    		try {
							FlowLabels lin=new FlowLabels(new FlowSpace(npin.getFlowSpace()));
							FlowLabels lout=new FlowLabels(new FlowSpace(npout.getFlowSpace()));
							
							Iterator<Integer> itout=lout.getIterator();				
							while (itout.hasNext()){
								Label l=new Label(itout.next());
								Iterator<Integer> itin=lin.getIterator();
								while(itin.hasNext()){
									Label ll=new Label(itin.next());
									labels.add((new SDPair<Label>(ll,l)).toString());
								}
							}				
						} catch (FlowPharosException e1) {
							e1.printStackTrace();
						}
		  	    	}
		  	    }
		  	    String edge=GraphHelper.toEdgeId((String)vin.getProperty("ID"),
		  	    		(String)vout.getProperty("ID"));
		  	    ArrayList<String> edges=new ArrayList<String>();
		  	    edges.add(edge);
		  	    this.flowVariables4D.add(new NetworkTopology(edges), new LabelPairs(labels));		  	  
		    }
		}			
  	    return this.flowVariables4D;
	}	
*/
	@Override
	public FlowVariables4D add(Graph graph) {
		Iterable<Vertex> it=graph.getVertices();		
		for (Vertex v: it){
		    Iterable<Edge> ite=v.getEdges(Direction.OUT);
		    for (Edge e: ite){
		    	ArrayList<String> labels= new ArrayList<String>();
		  	    //get labels from graph	  	    
		  	    Vertex vin=e.getVertex(Direction.IN);	
		  	    Vertex vout=e.getVertex(Direction.OUT);
		  	    ArrayList<NodePortProperties> arr_in=
		  	    		NodePortProperties.getNodePortProepertiesList((String)vin.getProperty("Ports"));
		  	    ArrayList<NodePortProperties> arr_out=
		  	    		NodePortProperties.getNodePortProepertiesList((String)vout.getProperty("Ports"));
		  	    for(NodePortProperties npin:arr_in){
		  	    	for(NodePortProperties npout:arr_out){
		  	    		try {
							FlowLabels lin=new FlowLabels(new FlowSpace(npin.getFlowSpace()));
							FlowLabels lout=new FlowLabels(new FlowSpace(npout.getFlowSpace()));

							
							Iterator<FlowUnit> itout=lout.fiterator();		
							while (itout.hasNext()){
								FlowUnit f=itout.next();
								Iterator<FlowUnit> itin=lin.fiterator();
								while(itin.hasNext()){
									String s=Variable.makeVariable(f.toString(), itin.next().toString());
									if(!labels.contains(s))labels.add(s);
								}
							}				
						} catch (FlowPharosException e1) {
							e1.printStackTrace();
						}
		  	    	}
		  	    }
		  	    String edge=GraphHelper.toEdgeId((String)vin.getProperty("ID"),
		  	    		(String)vout.getProperty("ID"));
		  	    ArrayList<String> edges=new ArrayList<String>();
		  	    edges.add(edge);
		  	    this.flowVariables4D.add(new NetworkTopology(edges), new LabelPairs(labels));		  	  
		    }
		}			
  	    return this.flowVariables4D;
	}
}