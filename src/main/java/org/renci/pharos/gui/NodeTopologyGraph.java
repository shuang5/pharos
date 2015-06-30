package org.renci.pharos.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.oupls.jung.GraphJung;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

class NodeTopologyGraph extends TopologyGraph
{
	
	public NodeTopologyGraph(GUIMain trgt) {
		super(trgt);
	}
	private NodeCircle getNode(String id){
		for(Drawings d:itemList){
			if(d.getShapeType()==ShapeType.NodeNode)
				if(((NodeCircle)d).getId().equals(id)) return (NodeCircle) d;
		}
		return null;
	}
	@Override
	public void openFromFile(File fileName) throws Exception{
		 fileName=Preconditions.checkNotNull(fileName);
		 try{
			 Graph graph=new TinkerGraph();
			 if( ProviderGraph.read(graph, fileName)==null){
				 JOptionPane.showMessageDialog(null, "File format is incorrect. Is this a provider topology file?");
				 return;
			 }
			 GraphJung<Graph> gj = new GraphJung<Graph>(graph);
			 Layout<Vertex, Edge> layout = new CircleLayout<Vertex, Edge>(gj);
			 layout.setSize(new Dimension(800, 600));
			 BasicVisualizationServer<Vertex, Edge> viz = new BasicVisualizationServer<Vertex, Edge>(layout);
			 viz.setPreferredSize(new Dimension(800, 600));
			 newTopo();
			 for (Vertex v: gj.getVertices()){
				 Circle n=new NodeCircle(v);
				 n.x1=(int)layout.transform(v).getX()-5;
				 n.y1=(int)layout.transform(v).getY()-5;
				 n.x2=n.x1+10;
				 n.y2=n.y1+10;
				 itemList.add(n);
				 
				 
			 }
			
			 for (Edge e: gj.getEdges()){
				 Line l=new NodeLine(e);
				 
				 l.x1=(int)layout.transform(e.getVertex(Direction.OUT)).getX();
				 l.y1=(int)layout.transform(e.getVertex(Direction.OUT)).getY();
				 l.x2=(int)layout.transform(e.getVertex(Direction.IN)).getX();
				 l.y2=(int)layout.transform(e.getVertex(Direction.IN)).getY();
				 NodeCircle n=getNode((String)e.getVertex(Direction.OUT).getProperty("ID"));
				 ((UndirectedLine)l).setNode1(n);
				 if(l.getShapeType()==ShapeType.NodeLine){
					 n.outLinks().add(l);
				 }
				 else if(l.getShapeType()==ShapeType.RequestNodeLine){
					 n.outLinks().add(l);
				 }	
				 String peers=(String)e.getProperty("Peers");
			  	 String[] tokens = peers.split("[ (,)]+");
			  	 String[] port1=tokens[1].split("[:]");
			  	 String[] port2=tokens[2].split("[:]");
	  	    	 if(n.getId().equals(port1[0]))
	  	    		 n.addLink(Integer.parseInt(port1[1]),l);
	  	    	 else
	  	    		n.addLink(Integer.parseInt(port2[1]),l);
				 n=getNode((String)e.getVertex(Direction.IN).getProperty("ID"));
				 ((UndirectedLine)l).setNode2(n);
				 if(l.getShapeType()==ShapeType.NodeLine){
					 n.inLinks().add(l);
				 }
				 else if(l.getShapeType()==ShapeType.RequestNodeLine){
					 n.inLinks().add(l);
				 }	
				 if(n.getId().equals(port1[0]))
	  	    		 n.addLink(l, Integer.parseInt(port1[1]));
	  	    	 else
	  	    		n.addLink(l, Integer.parseInt(port2[1]));
				 itemList.add(l);
			 }
			 
			 repaint();
			 //tg.getContentPane().add ( viz );
		 }
		 catch (RuntimeException ex){
			 JOptionPane.showMessageDialog(null, "File format is incorrect!");
		 }
	 }
	 public Graph saveProviderToGraph(){
		 Graph graph=new ProviderGraph();
		//process nodes first
 		 for (Drawings d : itemList){
 			 if(!d.configComplete()){
 				 JOptionPane.showMessageDialog(null, "Graph is not configured completely");
 			 }
 		 	 switch(d.getShapeType()){
     			 case PortNode:
     				 ((PortCircle)d).setBluePrintVertex(graph.addVertex(null));
     				 break;
     			 case NodeNode:
     				 ((NodeCircle)d).setBluePrintVertex(graph.addVertex(null));
     				 break;
     			 default:
     				 break;
 		 	 }
 		 }
 		 //then edges
 		 for (Drawings d : itemList){
		 	 switch(d.getShapeType()){
     			 case NodeLine:
     				 Circle lNode=((UndirectedLine)d).getNode1();
     				 Circle rNode=((UndirectedLine)d).getNode2();
     				 //label is used internally same as the id
     				 if(((NodeLine)d).getLabel()==null)((NodeLine)d).setLabel(((NodeLine)d).getId());
     				 try{
     					 //lines are bidirectional
	     				 Edge e1=graph.addEdge(null,lNode.getBluePrintVertex(),
	     						rNode.getBluePrintVertex(),((NodeLine)d).getLabel());
	     				 Edge e2=graph.addEdge(null,rNode.getBluePrintVertex(),
	     						lNode.getBluePrintVertex(),((NodeLine)d).getLabel());
	     				 d.setColor(Color.black);
	     				 //if Reverse==false, node1(x1,y1) is the outVertex
	     				 //e1.setProperty("Reverse", "false");
	     				 ((Line)d).setBluePrintEdge(e1,false);
	     				 //if Reverse==true, node1(x1,y1) is the in Vertex
	     				 //e2.setProperty("Reverse", "true");
	     				 ((Line)d).setBluePrintEdge(e2,true);
     				 }
     				 catch(Exception ex){
     					 d.setColor(new Color(255,0,0));
     					 repaint();
     					 JOptionPane.showMessageDialog(null, "Node1/Node2/label not set");
     				 }
     				 break;
     			 case PortLine:
     				 Circle startNode=((DirectedLine)d).getStartNode();
     				 Circle endNode=((DirectedLine)d).getEndNode();
     				 try{
	     				 Edge e=graph.addEdge(null,startNode.getBluePrintVertex(),
	     						endNode.getBluePrintVertex(),((PortLine)d).getLabel());
	     				 d.setColor(Color.black);
	     				 ((Line)d).setBluePrintEdge(e,false);
     				 }
     				 catch(NullPointerException ex){
     					d.setColor(new Color(255,0,0));
     					 repaint();
     					 JOptionPane.showMessageDialog(null, "Node1/Node2/label not set");
     				 }
     				 break;
     			 default:
     				 break;
     				
 			 }
 			
 		 }
 		return graph;
	 }
	 
	 public Graph saveRequestToGraph(){
		 Graph graph=new RequestGraph();
		//process nodes first
 		 for (Drawings d : itemList){
 		 	 switch(d.getShapeType()){
     			 case PortNode:
     				 ((PortCircle)d).setBluePrintVertex(graph.addVertex(null));
     				 break;
     			 case NodeNode:
     				 ((NodeCircle)d).setBluePrintVertex(graph.addVertex(null));
     				 break;
     			 default:
     				 break;
 		 	 }
 		 }
 		 //then edges
 		 for (Drawings d : itemList){
		 	 switch(d.getShapeType()){
     			 case RequestNodeLine:
     				 Circle lNode=((UndirectedCurvedDottedLine)d).getNode1();
     				 Circle rNode=((UndirectedCurvedDottedLine)d).getNode2();
     				 //label is used internally same as the id
     				 String label=((RequestNodeLine)d).getLabel();
     				 if(StringUtils.isEmpty(label))((RequestNodeLine)d).setLabel(((RequestNodeLine)d).getId());
     				 try{
     					 //lines are bidirectional
	     				 Edge e1=graph.addEdge(null,lNode.getBluePrintVertex(),
	     						rNode.getBluePrintVertex(),((RequestNodeLine)d).getLabel());
	     				 Edge e2=graph.addEdge(null,rNode.getBluePrintVertex(),
	     						lNode.getBluePrintVertex(),((RequestNodeLine)d).getLabel());
	     				 d.setColor(Color.black);
	     				 ((Line)d).setBluePrintEdge(e1,false);
	     				 ((Line)d).setBluePrintEdge(e2,true);
     				 }
     				 catch(NullPointerException ex){
     					d.setColor(new Color(255,0,0));
     					 repaint();
     					 JOptionPane.showMessageDialog(null, "Node1/Node2/label not set");
     				 }
     				 break;
     			 default:
     				 break;
     				
 			 }
 			
 		 }
 		 return graph;
	 }
	 @Override
	public void saveProviderTofile(File fileName){
		 fileName=Preconditions.checkNotNull(fileName);
		 Graph graph = saveProviderToGraph();
		 try {
     		 ProviderGraph.write(graph, fileName);
     		 graph.shutdown();
          } catch (IOException ioe) {
              ioe.printStackTrace();
              graph.shutdown();
          }
	 }
	@Override
	void saveRequestToFile(File fileName) {
		 fileName=Preconditions.checkNotNull(fileName);
		 Graph graph = null;
		 try {
	    	 graph=saveRequestToGraph();     		 
	    	 RequestGraph.write(graph, fileName);
     		 graph.shutdown();
          } catch (IOException ioe) {
              ioe.printStackTrace();
              graph.shutdown();
          }
		
	}
}


