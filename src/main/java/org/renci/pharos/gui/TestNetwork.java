package org.renci.pharos.gui;

import java.io.File;
import java.io.IOException;

import org.renci.pharos.flow.FlowPharosException;
import org.renci.pharos.flow.Rule;
import org.renci.pharos.flow.Rules;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.dex.DexGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLWriter;

public class TestNetwork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dbfilename = "./data/test_network.dex";
		File dbfile = new File(dbfilename);
		if (dbfile.exists())dbfile.delete();
		Graph graph = new DexGraph(dbfilename);
		((DexGraph)graph).label.set("flowspace");
		Vertex v_B_a_1_in = graph.addVertex(null);
		Vertex v_B_a_1_out = graph.addVertex(null);
		Vertex v_B_a_2_in = graph.addVertex(null);
		Vertex v_B_a_2_out = graph.addVertex(null);
		Vertex v_B_a_3_in = graph.addVertex(null);
		Vertex v_B_a_3_out = graph.addVertex(null);
		Vertex v_B_b_1_in = graph.addVertex(null);
		Vertex v_B_b_1_out = graph.addVertex(null);
		Vertex v_B_b_2_in = graph.addVertex(null);
		Vertex v_B_b_2_out = graph.addVertex(null);
		Vertex v_B_c_1_in = graph.addVertex(null);
		Vertex v_B_c_1_out = graph.addVertex(null);
		Vertex v_B_c_2_in = graph.addVertex(null);
		Vertex v_B_c_2_out = graph.addVertex(null);
		Vertex v_B_d_1_in = graph.addVertex(null);
		Vertex v_B_d_1_out = graph.addVertex(null);
		Vertex v_B_d_2_in = graph.addVertex(null);
		Vertex v_B_d_2_out = graph.addVertex(null);
		Vertex v_B_d_3_in = graph.addVertex(null);
		Vertex v_B_d_3_out = graph.addVertex(null);
		Vertex v_B_d_4_in = graph.addVertex(null);
		Vertex v_B_d_4_out = graph.addVertex(null);
		//assert v_A_fsi_a.getProperty(StringFactory.LABEL).equals("flowspace");
		//assert v_A_fsi_b.getProperty(StringFactory.LABEL).equals("flowspace");
		((DexGraph)graph).createKeyIndex("domain", Vertex.class);
		((DexGraph)graph).createKeyIndex("type", Vertex.class);
		v_B_a_1_in.setProperty("domain", "B");
		v_B_a_1_out.setProperty("domain", "B");
		v_B_a_2_in.setProperty("domain", "B");
		v_B_a_2_out.setProperty("domain", "B");
		v_B_a_3_in.setProperty("domain", "B");
		v_B_a_3_out.setProperty("domain", "B");
		v_B_b_1_in.setProperty("domain", "B");
		v_B_b_1_out.setProperty("domain", "B");
		v_B_b_2_in.setProperty("domain", "B");
		v_B_b_2_out.setProperty("domain", "B");
		v_B_c_1_in.setProperty("domain", "B");
		v_B_c_1_out.setProperty("domain", "B");
		v_B_c_2_in.setProperty("domain", "B");
		v_B_c_2_out.setProperty("domain", "B");
		v_B_d_1_in.setProperty("domain", "B");
		v_B_d_1_out.setProperty("domain", "B");
		v_B_d_2_in.setProperty("domain", "B");
		v_B_d_2_out.setProperty("domain", "B");
		v_B_d_3_in.setProperty("domain", "B");
		v_B_d_3_out.setProperty("domain", "B");
		v_B_d_4_in.setProperty("domain", "B");
		v_B_d_4_out.setProperty("domain", "B");
		v_B_a_1_in.setProperty("type", "flowspace");
		v_B_a_1_out.setProperty("type", "flowspace");
		v_B_a_2_in.setProperty("type", "flowspace");
		v_B_a_2_out.setProperty("type", "flowspace");
		v_B_a_3_in.setProperty("type", "flowspace");
		v_B_a_3_out.setProperty("type", "flowspace");
		v_B_b_1_in.setProperty("type", "flowspace");
		v_B_b_1_out.setProperty("type", "flowspace");
		v_B_b_2_in.setProperty("type", "flowspace");
		v_B_b_2_out.setProperty("type", "flowspace");
		v_B_c_1_in.setProperty("type", "flowspace");
		v_B_c_1_out.setProperty("type", "flowspace");
		v_B_c_2_in.setProperty("type", "flowspace");
		v_B_c_2_out.setProperty("type", "flowspace");
		v_B_d_1_in.setProperty("type", "flowspace");
		v_B_d_1_out.setProperty("type", "flowspace");
		v_B_d_2_in.setProperty("type", "flowspace");
		v_B_d_2_out.setProperty("type", "flowspace");
		v_B_d_3_in.setProperty("type", "flowspace");
		v_B_d_3_out.setProperty("type", "flowspace");
		v_B_d_4_in.setProperty("type", "flowspace");
		v_B_d_4_out.setProperty("type", "flowspace");
		
		v_B_a_1_in.setProperty("DPID", "B1");
		v_B_a_1_out.setProperty("DPID", "B1");
		v_B_a_2_in.setProperty("DPID", "B1");
		v_B_a_2_out.setProperty("DPID", "B1");
		v_B_a_3_in.setProperty("DPID", "B1");
		v_B_a_3_out.setProperty("DPID", "B1");
		v_B_b_1_in.setProperty("DPID", "B2");
		v_B_b_1_out.setProperty("DPID", "B2");
		v_B_b_2_in.setProperty("DPID", "B2");
		v_B_b_2_out.setProperty("DPID", "B2");
		v_B_c_1_in.setProperty("DPID", "B3");
		v_B_c_1_out.setProperty("DPID", "B3");
		v_B_c_2_in.setProperty("DPID", "B3");
		v_B_c_2_out.setProperty("DPID", "B3");
		v_B_d_1_in.setProperty("DPID", "B4");
		v_B_d_1_out.setProperty("DPID", "B4");
		v_B_d_2_in.setProperty("DPID", "B4");
		v_B_d_2_out.setProperty("DPID", "B4");
		v_B_d_3_in.setProperty("DPID", "B4");
		v_B_d_3_out.setProperty("DPID", "B4");
		v_B_d_4_in.setProperty("DPID", "B4");
		v_B_d_4_out.setProperty("DPID", "B4");
		
		v_B_a_1_in.setProperty("Port", "1");
		v_B_a_1_out.setProperty("Port", "1");
		v_B_a_2_in.setProperty("Port", "2");
		v_B_a_2_out.setProperty("Port", "2");
		v_B_a_3_in.setProperty("Port", "3");
		v_B_a_3_out.setProperty("Port", "3");
		v_B_b_1_in.setProperty("Port", "1");
		v_B_b_1_out.setProperty("Port", "1");
		v_B_b_2_in.setProperty("Port", "2");
		v_B_b_2_out.setProperty("Port", "2");
		v_B_c_1_in.setProperty("Port", "1");
		v_B_c_1_out.setProperty("Port", "1");
		v_B_c_2_in.setProperty("Port", "2");
		v_B_c_2_out.setProperty("Port", "2");
		v_B_d_1_in.setProperty("Port", "1");
		v_B_d_1_out.setProperty("Port", "1");
		v_B_d_2_in.setProperty("Port", "2");
		v_B_d_2_out.setProperty("Port", "2");
		v_B_d_3_in.setProperty("Port", "3");
		v_B_d_3_out.setProperty("Port", "3");
		v_B_d_4_in.setProperty("Port", "4");
		v_B_d_4_out.setProperty("Port", "4");
		
		((DexGraph)graph).label.set("flowspace");
		Vertex v_C_a_1_in = graph.addVertex(null);
		v_C_a_1_in.setProperty("domain", "C");
		v_C_a_1_in.setProperty("type", "flowspace");
		v_C_a_1_in.setProperty("DPID", "C1");
		v_C_a_1_in.setProperty("Port", "1");
		Vertex v_C_a_1_out = graph.addVertex(null);
		v_C_a_1_out.setProperty("domain", "C");
		v_C_a_1_out.setProperty("type", "flowspace");
		v_C_a_1_out.setProperty("DPID", "C1");
		v_C_a_1_out.setProperty("Port", "1");
		Vertex v_A_b_2_in = graph.addVertex(null);
		v_A_b_2_in.setProperty("domain", "A");
		v_A_b_2_in.setProperty("type", "flowspace");
		v_A_b_2_in.setProperty("DPID", "A2");
		v_A_b_2_in.setProperty("Port", "2");
		Vertex v_A_b_2_out = graph.addVertex(null);
		v_A_b_2_out.setProperty("domain", "A");
		v_A_b_2_out.setProperty("type", "flowspace");
		v_A_b_2_out.setProperty("DPID", "A2");
		v_A_b_2_out.setProperty("Port", "2");
		Vertex v_D_a_1_in = graph.addVertex(null);
		v_D_a_1_in.setProperty("domain", "D");
		v_D_a_1_in.setProperty("type", "flowspace");
		v_D_a_1_in.setProperty("DPID", "D1");
		v_D_a_1_in.setProperty("Port", "1");
		Vertex v_D_a_1_out = graph.addVertex(null);
		v_D_a_1_out.setProperty("domain", "D");
		v_D_a_1_out.setProperty("type", "flowspace");
		v_D_a_1_out.setProperty("DPID", "D1");
		v_D_a_1_out.setProperty("Port", "1");
		//		  v_B_a_2----v_B_b_1--v_B_b_2----v_B_d_2 ---->v_B_d_1 -- v_C_a_1
		//	     /                                      
		//v_B_a_1                                        
	    //       \                                      
		//        v_B_a_3----v_B_c_1--v_B_c_2----v_B_d_3 ---->v_B_d_4 -- v_D_a_1
		Edge e1=graph.addEdge(null, v_B_a_1_in, v_B_a_2_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2+":"+org.renci.pharos.gui.LinePropertyConstants.Capabilities.Vlan);
		e1=graph.addEdge(null, v_B_a_2_in, v_B_a_1_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2+":"+org.renci.pharos.gui.LinePropertyConstants.Capabilities.Vlan);
		graph.addEdge(null, v_B_a_2_out, v_B_b_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_B_b_1_out, v_B_a_2_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		e1=graph.addEdge(null, v_B_b_1_in, v_B_b_2_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		e1=graph.addEdge(null, v_B_b_2_in, v_B_b_1_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		graph.addEdge(null, v_B_b_2_out, v_B_d_2_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_B_d_2_out, v_B_b_2_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		e1=graph.addEdge(null, v_B_d_2_in, v_B_d_1_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		e1=graph.addEdge(null, v_B_d_1_in, v_B_d_2_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		
		e1=graph.addEdge(null, v_B_a_1_in, v_B_a_3_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		e1=graph.addEdge(null, v_B_a_3_in, v_B_a_1_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		graph.addEdge(null, v_B_a_3_out, v_B_c_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_B_c_1_out, v_B_a_3_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		e1=graph.addEdge(null, v_B_c_1_in, v_B_c_2_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		e1=graph.addEdge(null, v_B_c_2_in, v_B_c_1_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		graph.addEdge(null, v_B_c_2_out, v_B_d_3_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_B_d_3_out, v_B_c_2_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		e1=graph.addEdge(null, v_B_d_3_in, v_B_d_4_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		e1=graph.addEdge(null, v_B_d_4_in, v_B_d_3_out, org.renci.pharos.gui.LinePropertyConstants.Label.can);
		e1.setProperty("Capabilities",org.renci.pharos.gui.LinePropertyConstants.Capabilities.L2);
		
		e1=graph.addEdge(null, v_A_b_2_out, v_B_a_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		
		try{
			Rules rules = new Rules();
			Rule r = new Rule();
			r.setCondition("1-4/10-14");
			r.setAction("11-14/16-20");
			rules.addRule(r);
			Rule r2 = new Rule();
			r2.setCondition("10-14/10-14");
			r2.setAction("20-24/16-20");
			rules.addRule(r2);
			Rule r1 = new Rule();
			r1.setCondition("10-16/16-20");
			r1.setAction("1-7/20-24");
			rules.addRuleTable(r1);
			
			e1.setProperty("Rules", rules.toString());
			String s=e1.getProperty("Rules");
			Rules rules2=new Rules(s);
			System.out.println(rules2.toString(2));
		}
		catch (FlowPharosException ex){
			System.out.println(ex);
		}
		
		graph.addEdge(null, v_B_a_1_out, v_A_b_2_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_B_d_1_out, v_C_a_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_C_a_1_out, v_B_d_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_B_d_4_out, v_D_a_1_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		graph.addEdge(null, v_D_a_1_out, v_B_d_4_in, org.renci.pharos.gui.LinePropertyConstants.Label.is);
		
		//System.out.println(e.getVertex(Direction.OUT).getProperty("name") + "--" + e.getLabel() + "-->" + e.getVertex(Direction.IN).getProperty("name"));
		GraphMLWriter writer=new GraphMLWriter(graph);
		try {
			writer.outputGraph("./data/test_network.xml");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("file not found!");
		}
		graph.shutdown();
	
	}

}
