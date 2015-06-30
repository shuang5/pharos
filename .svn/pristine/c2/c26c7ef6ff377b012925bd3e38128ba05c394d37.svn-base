package org.renci.pharos.toyTopology;
import java.io.File;
import java.io.IOException;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.dex.DexGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLWriter;
//domain A has two nodes a and b, each node has two ports 1 and 2, each port is initially split into two flowspaces 
//in and out direction respectively.
//each node a and b also has function nodes describing what functionality is available, e.g., L1 switching, L2 switching, VLAN modification...
//
public class domain_A {
	public static void main(String[] args) {
		String dbfilename = "./data/doe_pharos_domain_A.dex";
		File dbfile = new File(dbfilename);
		if (dbfile.exists())dbfile.delete();
		Graph graph = new DexGraph(dbfilename);
		((DexGraph)graph).label.set("flowspace");
		Vertex v_A_a_1_in = graph.addVertex(null);
		Vertex v_A_a_1_out = graph.addVertex(null);
		Vertex v_A_a_2_in = graph.addVertex(null);
		Vertex v_A_a_2_out = graph.addVertex(null);
		Vertex v_A_b_1_in = graph.addVertex(null);
		Vertex v_A_b_1_out = graph.addVertex(null);
		Vertex v_A_b_2_in = graph.addVertex(null);
		Vertex v_A_b_2_out = graph.addVertex(null);
		//assert v_A_fsi_a.getProperty(StringFactory.LABEL).equals("flowspace");
		//assert v_A_fsi_b.getProperty(StringFactory.LABEL).equals("flowspace");
		((DexGraph)graph).createKeyIndex("domain", Vertex.class);
		((DexGraph)graph).createKeyIndex("type", Vertex.class);
		v_A_a_1_in.setProperty("domain", "A");
		v_A_a_1_out.setProperty("domain", "A");
		v_A_a_2_in.setProperty("domain", "A");
		v_A_a_2_out.setProperty("domain", "A");
		v_A_b_1_in.setProperty("domain", "A");
		v_A_b_1_out.setProperty("domain", "A");
		v_A_b_2_in.setProperty("domain", "A");
		v_A_b_2_out.setProperty("domain", "A");
		v_A_a_1_in.setProperty("type", "flowspace");
		v_A_a_1_out.setProperty("type", "flowspace");
		v_A_a_2_in.setProperty("type", "flowspace");
		v_A_a_2_out.setProperty("type", "flowspace");
		v_A_b_1_in.setProperty("type", "flowspace");
		v_A_b_1_out.setProperty("type", "flowspace");
		v_A_b_2_in.setProperty("type", "flowspace");
		v_A_b_2_out.setProperty("type", "flowspace");
		v_A_a_1_in.setProperty("DPID", "A1");
		v_A_a_1_out.setProperty("DPID", "A1");
		v_A_a_2_in.setProperty("DPID", "A1");
		v_A_a_2_out.setProperty("DPID", "A1");
		v_A_b_1_in.setProperty("DPID", "A2");
		v_A_b_1_out.setProperty("DPID", "A2");
		v_A_b_2_in.setProperty("DPID", "A2");
		v_A_b_2_out.setProperty("DPID", "A2");
		v_A_a_1_in.setProperty("Port", "1");
		v_A_a_1_out.setProperty("Port", "1");
		v_A_a_2_in.setProperty("Port", "2");
		v_A_a_2_out.setProperty("Port", "2");
		v_A_b_1_in.setProperty("Port", "1");
		v_A_b_1_out.setProperty("Port", "1");
		v_A_b_2_in.setProperty("Port", "2");
		v_A_b_2_out.setProperty("Port", "2");
		
		/*
		((DexGraph)graph).label.set("function");
		Vertex v_A_fn_a = graph.addVertex(null);
		//assert v_A_fn_a.getProperty(StringFactory.LABEL).equals("function");
		((DexGraph)graph).createKeyIndex("capability", Vertex.class);
		v_A_fn_a.setProperty("capability", "l2switch");
		v_A_fn_a.setProperty("type", "function");
		Vertex v_A_fn_aa = graph.addVertex(null);
		//assert v_A_fn_a.getProperty(StringFactory.LABEL).equals("function");
		v_A_fn_aa.setProperty("capability", "vlantranslation");
		v_A_fn_aa.setProperty("type", "function");
		
		Vertex v_A_fn_b = graph.addVertex(null);
		//assert v_A_fn_a.getProperty(StringFactory.LABEL).equals("function");
		v_A_fn_b.setProperty("capability", "l2switch");
		v_A_fn_b.setProperty("type", "function");
		Vertex v_A_fn_bb = graph.addVertex(null);
		//assert v_A_fn_a.getProperty(StringFactory.LABEL).equals("function");
		v_A_fn_bb.setProperty("capability", "vlantranslation");
		v_A_fn_bb.setProperty("type", "function");
		*/
		((DexGraph)graph).label.set("flowspace");
		Vertex v_B_a_1_in = graph.addVertex(null);
		v_B_a_1_in.setProperty("domain", "B");
		v_B_a_1_in.setProperty("type", "flowspace");
		v_B_a_1_in.setProperty("DPID", "1");
		v_B_a_1_in.setProperty("Port", "1");
		Vertex v_B_a_1_out = graph.addVertex(null);
		v_B_a_1_out.setProperty("domain", "B");
		v_B_a_1_out.setProperty("type", "flowspace");
		v_B_a_1_out.setProperty("DPID", "1");
		v_B_a_1_out.setProperty("Port", "1");
		
		
		//v_A_a_1_in  ----> v_A_a_2_out -----> v_A_b_1_in  ----> v_A_b_2_out ----> v_B_a_1_in
		//v_A_a_1_out <---- v_A_a_2_in  <----- v_A_b_1_out <---- v_A_b_2_in <-----v_B_a_1_out
		
		Edge e1=graph.addEdge(null, v_A_a_1_in, v_A_a_2_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2+":"+org.renci.pharos.gui.LinePropertyConstants.Capabilities.Vlan);
		e1=graph.addEdge(null, v_A_a_2_in, v_A_a_1_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2+":"+org.renci.pharos.gui.LinePropertyConstants.Capabilities.Vlan);
		
		graph.addEdge(null, v_A_a_2_out, v_A_b_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_A_b_1_out, v_A_a_2_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		
		e1=graph.addEdge(null, v_A_b_1_in, v_A_b_2_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		e1=graph.addEdge(null, v_A_b_2_in, v_A_b_1_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		
		
		graph.addEdge(null, v_A_b_2_out, v_B_a_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_B_a_1_out, v_A_b_2_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		
		
		//System.out.println(e.getVertex(Direction.OUT).getProperty("name") + "--" + e.getLabel() + "-->" + e.getVertex(Direction.IN).getProperty("name"));
		GraphMLWriter writer=new GraphMLWriter(graph);
		try {
			writer.outputGraph("./data/doe_pharos_domain_A.xml");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("file not found!");
		}
		graph.shutdown();
	}
}
