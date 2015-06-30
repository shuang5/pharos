package org.renci.pharos.lp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.renci.pharos.gui.DetailControl;
import org.renci.pharos.gui.ProviderGraph;

import net.sf.javailp.Result;

import com.google.common.base.Preconditions;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

public class OptimizationResult implements DetailControl {
	private String objective;
	private ArrayList<String> nzValues=new ArrayList<String>();
	private Result result;
	private Graph pg;
	private Graph rq;
	private PProblem problem;
	public OptimizationResult(){}
	public OptimizationResult(PProblem p){problem=p;}
	OptimizationResult(Graph p, Graph r, PProblem pp){
		problem=pp;
		pg=p;
		rq=r;
	}
	private Graph toGraph1(){
		Graph g=new TinkerGraph();
		for(Edge re: rq.getEdges()){
			Vertex rv_in=re.getVertex(Direction.IN);
			Vertex pv_in=GraphHelper.getVertex(pg, rv_in.getProperty("ID"));
			Vertex vin=GraphHelper.copyVertexProperties(g, pv_in);
			Vertex rv_out=re.getVertex(Direction.OUT);
			Vertex pv_out=GraphHelper.getVertex(pg, rv_out.getProperty("ID"));
			Vertex vout=GraphHelper.copyVertexProperties(g, pv_out);
			
			Edge to=g.addEdge(null,vout,vin,re.getLabel());;
			to.setProperty("Virtual", "Yes");
			to.setProperty("ID",re.getProperty("ID"));
			to.setProperty("LinkType", "N/A");
		}
		return g;
	}
	private Graph toGraph2() {
		// TODO Auto-generated method stub
		return null;
	}
	private void save2file(Graph graph,File fileName){
		fileName=Preconditions.checkNotNull(fileName);
		 try {
    		 ProviderGraph.write(graph, fileName);
    		 graph.shutdown();
         } catch (IOException ioe) {
             ioe.printStackTrace();
             graph.shutdown();
         }
		
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public ArrayList<String> getNzValues() {
		return nzValues;
	}
	public void setNzValues(ArrayList<String> nzValues) {
		this.nzValues = nzValues;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public PProblem getProblem() {
		return problem;
	}
	public void setProblem(PProblem problem) {
		this.problem = problem;
	}
	public void saveProblem(File file) throws IOException {
		problem.saveProblem(file);		
	}
	@Override
	public void save2file1(File fileName) {
		Graph g=toGraph1();
		save2file(g, fileName);	
	}
	@Override
	public void save2file2(File fileName) {
		Graph g=toGraph2();
		save2file(g, fileName);	
	}
	
}
