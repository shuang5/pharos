package org.renci.pharos.lp;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.javailp.Linear;

import org.apache.commons.lang.ArrayUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

public class SSSDFlowProblem extends ProblemDecorator{
	private final static Logger logger = Logger.getLogger(LpSolverTest.class.getName());
	private String sourceid=null;
	private String destid=null;
	private HandlerFactory handlers=new HandlerFactory();
	private class HandlerFactory{
		private NodeConstraints getHandler(String id,Boolean labeltran){
			if(id.equals(sourceid))return new SourceNode(id);
			else if(id.equals(destid)) return new DestNode(id);
			else if(labeltran) return new IntermediateNodeWithLabelTran(id);
			else return new IntermediateNodeNoLabelTran(id);
		}
	}
	private interface NodeConstraints{
		void add(FlowAssignmentVariables8D fav);
	}
	private class SourceNode implements NodeConstraints{
		private String id;
		private SourceNode(String s){id=s;}
		@Override
		public void add(FlowAssignmentVariables8D fav) {
			 String[] s=new String[]{sourceid,destid,"*","*",id,"+","+","+"};
			  ArrayList<Linear> arr= 
					  fav.generateLinears(s, fav.getSub());
			  for(Linear l:arr){
				  if(l.size()>0)problem().add(l, "=", 0);
				  logger.log(Level.FINE, id+" is source");
				  logger.log(Level.FINE, l+" = 0");
			  }
		}
		
	}
	private class DestNode implements NodeConstraints{
		private String id;
		private DestNode(String s){id=s;}
		@Override
		public void add(FlowAssignmentVariables8D fav) {
			 String[] s=new String[]{sourceid,destid,"*","*","+",id,"+","+"};
			  ArrayList<Linear> arr= 
					  fav.generateLinears(s, fav.getSub());
			  for(Linear l:arr){
				  if(l.size()>0)problem().add(l, "=", 0);
				  logger.log(Level.FINE, id+" is destination");
				  logger.log(Level.FINE, l+" = 0");
			  }
		}
		
	}
	private class IntermediateNodeWithLabelTran implements NodeConstraints{
		private String id;
		private IntermediateNodeWithLabelTran(String s){id=s;}
		@Override
		public void add(FlowAssignmentVariables8D fav) {
			
		  Iterator<String> itt=fav.getRequest().iterator(sourceid, destid, "*", "*");
		  while(itt.hasNext()){
			  String req=itt.next();
			  String[] array=req.split("[_]");
			  //if the node can translate label, \sum_in=\sum_out
			  String[] prv={id,"+","+","+"};
			  String[] both1=(String[]) ArrayUtils.addAll(array, prv);
			  Linear l1=fav.getSum().operate(both1);
			  
			  String[] prv2={"+",id,"+","+"};
			  String[] both2=(String[]) ArrayUtils.addAll(array, prv2);
			  Linear l2=fav.getSum().operate(both2);
			  Linear ll=new Linear();
			  List<Object> va=l1.getVariables();
			  for(Object obj:va){
				  ll.add(1,obj);
			  }
			  va=l2.getVariables();
			  for(Object obj:va){
				  ll.add(-1,obj);
			  }
			  if(ll.size()>0)
				  problem().add(ll, "=", 0);
	    	  logger.log(Level.FINE, id+" has labeltranslation");
			  logger.log(Level.FINE, ll+" = 0");
			  
		  } 
		}
		
	}
	private class IntermediateNodeNoLabelTran implements NodeConstraints{
		private String id;
		private IntermediateNodeNoLabelTran(String s){id=s;}
		@Override
		public void add(FlowAssignmentVariables8D fav) {			
		    Iterator<String> itt=fav.getRequest().iterator(sourceid, destid, "*", "*");
		    while(itt.hasNext()){
			    String req=itt.next();
			    String[] array=req.split("[_]");		  
			    //if the node can NOT translate label, \sum_in=\sum_out for every label
			    Iterator<String> it1=fav.getProvider().iterator(id,"*","*","*");
			    Iterator<String> it2=fav.filteredIterator(it1, new NthDimIterator(2));
			    HashMap<Linear,Boolean> map=Maps.newHashMap();
			    while(it2.hasNext()){
			    	String it2next=it2.next();
			    	String label=fav.getProvider().getNthDim(it2next, 2);
			    	String[] prv={id,"+",label,"+"};
			    	String[] both1=(String[]) ArrayUtils.addAll(array, prv);
			    	Linear l1=fav.getSum().operate(both1);
			    	String[] prv2={"+",id,"+",label};
			    	String[] both2=(String[]) ArrayUtils.addAll(array, prv2);
			    	Linear l2=fav.getSum().operate(both2);
			    	Linear ll=new Linear();
			    	List<Object> va=l1.getVariables();
			    	for(Object obj:va){
			    		ll.add(1,obj);
			    	}
			    	va=l2.getVariables();
			    	for(Object obj:va){
			    		ll.add(-1,obj);
			    	}
			    	if(ll.size()>0&&!map.containsKey(ll)){
			    		map.put(ll, true);
			    		problem().add(ll, "=", 0);

			    		logger.log(Level.FINE, id+" has no labeltranslation");
			    		logger.log(Level.FINE, ll+" = 0");
			    	}
			    }					  
		    }			  
		}		
	}
	void addFlowConservationConstraints(){
		logger.log(Level.INFO,"adding flow constraints:");
		Graph pgraph=getProviderTopo();
		Iterable<Vertex> it=pgraph.getVertices();		
		  for (Vertex v: it){
			  String id=(String)v.getProperty("ID");
			  boolean labeltranslation=false;
			  if(((String)v.getProperty("LabelTranslation")).equals("yes"))
				  labeltranslation=true;
			  else labeltranslation=false;
			  FlowAssignmentVariables8D fav=(FlowAssignmentVariables8D)variables();
			  handlers.getHandler(id, labeltranslation).add(fav);
		  }
	}
	void addRequestConstraints(){
		logger.log(Level.INFO,"adding request constraints:");
		FlowAssignmentVariables8D fav=(FlowAssignmentVariables8D)variables();
		FlowVariables4D fv=fav.getRequest();
		if(!Strings.isNullOrEmpty(sourceid) && !Strings.isNullOrEmpty(destid)){
			String[] s=new String[]{sourceid,destid,"+","+"};
			Linear l=fv.getSum().operate(s);
			problem().add(l, "=", fav.demand(sourceid, destid));
			logger.log(Level.FINE, l+" = "+ fav.demand(sourceid, destid));
		}
	}
	void addLabelConstraints(){
		logger.log(Level.INFO,"adding label assignment constraints:");
		FlowAssignmentVariables8D fav=(FlowAssignmentVariables8D)variables();
		FlowVariables4D fv=fav.getRequest();
		String[] s=new String[]{sourceid,destid,"*","+"};
		ArrayList<Linear> arr=fv.generateLinears(s, fv.getSum());
		for(Linear l:arr){
			problem().add(l, "<=", 1);
			logger.log(Level.FINE, l+" <= "+ 1);
		}
	}
	private void addCapacityConstraints(){
		logger.log(Level.INFO,"adding capacity constraints:");
		FlowAssignmentVariables8D fav=(FlowAssignmentVariables8D)variables();
		String[] s=new String[]{sourceid,destid,"+","+","*","*","*","*"};
		ArrayList<Linear> arr=fav.generateLinears(s, fav.getSum());
		for(Linear l:arr){
			problem().add(l, "<=", 1);
			logger.log(Level.FINE,l+" <= "+ 1);
		}
	}
	public SSSDFlowProblem(PProblem p) {
		checkNotNull(p);
		pproblem=p;
	}
	public SSSDFlowProblem(PProblem p, String sid,String did) {
		pproblem=checkNotNull(p);
		sourceid=checkNotNull(sid);
		destid=checkNotNull(did);
	}
	public void setSource(String ID) {
		sourceid=ID;		
	}
	public void setDestination(String ID) {
		destid=ID;		
	}

	public Variables variables() {
		return pproblem.variables();
	}
	@Override
	public void init() {
		this.addLabelConstraints();
		this.addRequestConstraints();
		this.addCapacityConstraints();
		this.addFlowConservationConstraints();
		
	}
}