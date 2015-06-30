package org.renci.pharos.lp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.sf.javailp.Linear;

import com.google.common.collect.Maps;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.util.io.graphml.GraphMLReader;

class FlowVariables4D extends Variables implements Array4D{
	private HashMap<String,ArrayList<String>> edge2label;
	private HashMap<String,ArrayList<String>> label2edge;
	private HashMap<Mode,PharosGraphReader> factory=Maps.newHashMap();
	{
		factory.put(Mode.Provider,new ProviderGraphReader(this));
		factory.put(Mode.Request,new RequestGraphReader(this));
	}
	public FlowVariables4D(Graph graph, Mode mode){
		this();
		factory.get(mode).add(graph);
	}
// TODO Remove unused code found by UCDetector
// 	public Iterator<String> getNeighborIt(String from1, String to1, 
// 			String from2, String to2) {
// 		if(!from1.equals("*") && !to1.equals("*") && 
// 				!from2.equals("*") && !to2.equals("*")){
// 			return null;
// 		}
// 		else if(!from1.equals("*") && !to1.equals("*") && 
// 				from2.equals("*") && to2.equals("*")){
// 			return edge2label.get(from1+"_"+to1).iterator();
// 		}
// 		else if(!from2.equals("*") && !to2.equals("*") && 
// 				from1.equals("*") && to1.equals("*")){
// 			return label2edge.get(from2+"_"+to2).iterator();
// 		}
// 
// 		else if(from1.equals("*") && to1.equals("*") && 
// 				from2.equals("*") && to2.equals("*")){
// 			return this.iterator();
// 		}
// 		else return null;
// 	}

	public void add(NetworkTopology n,LabelPairs l){
		Iterator<String> nt_it=n.iterator();
		Iterator<String> lp_it=l.iterator();
		while(nt_it.hasNext()){
			String ntnext=nt_it.next();
			if(edge2label.containsKey(ntnext)){
				ArrayList<String> a=edge2label.get(ntnext);
				while(lp_it.hasNext()){
					String s=lp_it.next();
					if(!a.contains(s)){
						a.add(s);
					}
				}
			}
			else {
				ArrayList<String> arr=new ArrayList<String>();
				while(lp_it.hasNext()){
					arr.add(lp_it.next());
				}
				edge2label.put(ntnext, arr);
			}
		}
		lp_it=l.iterator();
		while(lp_it.hasNext()){
			String lpnext=lp_it.next();
			if(label2edge.containsKey(lpnext)){
				ArrayList<String> a=label2edge.get(lpnext);
				nt_it=n.iterator();
				while(nt_it.hasNext()){
					String s=nt_it.next();
					if(!a.contains(s)){
						a.add(s);
					}
				}
			}
			else {
				ArrayList<String> ar=new ArrayList<String>();
				nt_it=n.iterator();
				while(nt_it.hasNext()){
					ar.add(nt_it.next());
				}
				label2edge.put(lpnext, ar);
			}
		}
	}
	public FlowVariables4D(){
		edge2label=new HashMap<String,ArrayList<String>>();
		label2edge=new HashMap<String,ArrayList<String>>();
		setDimension(4);
	}

	
	private class FVIterator implements Iterator<String>{
		private Iterator<String> key_it;
		private Iterator<String> val_it;
		private Array2D arr1;
		private String key,val;
		private int state=0;
		private String f1,f2,t1,t2;
		private FVIterator(String from1,String to1,String from2,String to2){
			this.f1=from1;
			this.t1=to1;
			this.f2=from2;
			this.t2=to2;
			if(f1.equals("*") && t1.equals("*") && 
					f2.equals("*") && t2.equals("*")){
				key_it=edge2label.keySet().iterator();
				if(key_it==null)throw new  IllegalArgumentException("Iterator not available");
				state=1;
			}
			else {
				ArrayList<String> al=new ArrayList<String>();
				al.addAll(edge2label.keySet());
				arr1=new NetworkTopology(al);
				key_it=arr1.iterator(from1, to1);
				if(key_it==null)throw new  IllegalArgumentException("Iterator not available");
				state=2;
			}
		}
		public boolean hasNext() {		
			if(state==1){
				if(val_it==null)
					return key_it.hasNext();
				else if(val_it.hasNext())
					return true;
				else 
					return key_it.hasNext();
			}
			else if(state==2){
				if(val==null || val.equals("null")){
					if(val_it!=null){
						while(val_it.hasNext()){
							val=val_it.next();
							String delim="[_]";
							String[] v=val.split(delim);
							if(f2.equals("*") || f2.equals(v[0])){
								if(t2.equals("*") || t2.equals(v[1]))
									return true;
							}
						}
					}
					while(key_it.hasNext()){
						key=key_it.next();
						val_it=edge2label.get(key).iterator();
						while(val_it.hasNext()){
							val=val_it.next();
							String delim="[_]";
							String[] v=val.split(delim);
							if(f2.equals("*") || f2.equals(v[0])){
								if(t2.equals("*") || t2.equals(v[1]))
									return true;
							}
						}
					}
					return false;
				}
				else 
					return true;
			}
			return false;
		}

		public String next() {
			if(state==1 && hasNext()){
				if(key==null)
					key=key_it.next();
				if(val_it==null)
					val_it=edge2label.get(key).iterator();
				if(val_it.hasNext())
					return key+"_"+val_it.next();
				else {
					key=key_it.next();
					val_it=edge2label.get(key).iterator();
					return Variable.makeVariable(key, val_it.next());
				}
			}
			else if(state==2 && hasNext()){
				String v=val;
				val="null";
				return Variable.makeVariable(key, v);
			}
			return null;
		}

		@Override
		public void remove() {
			throw new IllegalStateException("dont remove!");
		}
		
	}
	@Override
	public Iterator<String> iterator() {
		return new RemoveDuplicatesFilterDecorator(new FVIterator("*","*","*","*"));
	}

	public Iterator<String> iterator(String from1, String to1, String from2,
			String to2) {
		if(from1.equals("+")||to1.equals("+")
				||from2.equals("+")||to2.equals("+"))
			throw new IllegalArgumentException("+ is not allowed");
		if(!from1.equals("*") && !to1.equals("*") && 
				!from2.equals("*") && !to2.equals("*")){
			if(edge2label.containsKey(from1+"_"+to1) && 
					edge2label.get(from1+"_"+to1).contains(from2+"_"+to2)){
				ArrayList<String> ar=new ArrayList<String>();
				ar.add(from1+"_"+to1+"_"+from2+"_"+to2);
				return ar.iterator();
			}
			else return null;
		}
		else 
			return new RemoveDuplicatesFilterDecorator(new FVIterator(from1,to1,from2,to2));
	}
	
	@Override
	public int getDimension() {
		return 4;
	}
	
	@Override
	public ArrayList<String> getAllVariables() {
		ArrayList<String> ar=new ArrayList<String>();
		Iterator<String> it1=this.iterator();
		while(it1.hasNext()){
			String r1=it1.next();
			ar.add(Variable.makeVariable(getName(),r1));
		}
		return ar;
	}
	
	@Override
	public String getVariable(String... s) {
		StringBuilder sb=new StringBuilder();
		sb.append(getName());
		for(String ss:s){
			sb.append("_").append(ss);
		}
		return sb.toString();
	}
	
	private class Sum implements LinearOperation{
		@Override
		public Linear operate(String[] sub) {
			Linear linear=new Linear();
			String[] s=sub.clone();
			for(int i=0;i<s.length;++i){
				if(s[i].equals("+"))s[i]="*";
			}
			Iterator<String> it1=iterator(s[0],s[1],s[2],s[3]);
			while(it1.hasNext()){
				String r=it1.next();
				linear.add(1,Variable.makeVariable(getName(),r));
			}
			return linear;
		}
	}
	public LinearOperation getSum(){
		return new Sum();
	}

	public ArrayList<Linear> generateLinears(String[] sub, LinearOperation lo) {
		ArrayList<Linear> linears=new ArrayList<Linear>();
		String[] s=sub.clone();
		ArrayList<Integer> plus=new ArrayList<Integer>();
		//replace "+" with "*" and save the positions
		int i=0;
		for(String ss:s){
			if(ss.equals("+")){
				plus.add(i);
				s[i]="*";
			}
			i++;
		}
		Linear linear=new Linear();
		Iterator<String> it1=iterator(s[0],s[1],s[2],s[3]);
		HashMap<String, Boolean> map=new HashMap<String,Boolean>();
		while(it1.hasNext()){
			String r=it1.next();	
			String[] t1=r.split("[_]");
			for(int j:plus){
				t1[j]="+";
			}
			//because we have wild cards,
			//t1 can be repetitive, use a HashMap<String, Boolean> to remove 
			//repetitive ones,
			//first, convert t1 to a String
			String mapstring = null;
			for(String t:t1){
				mapstring+=t;
			}
			if(!map.containsKey(mapstring)){
				map.put(mapstring, true);
				linear=lo.operate(t1);
				if(linear.size()>0)linears.add(linear);
			}			
			
		}
		return linears;
	}
	public static void main(String[] args){
		FlowVariables4D v,vr;
		Graph graph=new TinkerGraph();
		Graph graphr=new TinkerGraph();
		try{
		GraphMLReader.inputGraph(graph, new FileInputStream("/Users/shuang/Desktop/Pharos DOE/temp3"));
		GraphMLReader.inputGraph(graphr, new FileInputStream("/Users/shuang/Desktop/Pharos DOE/temp3-req"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		v=new FlowVariables4D(graph,Mode.Provider);
		vr=new FlowVariables4D(graphr,Mode.Request);
		System.out.println("tesing getVariable(A, E, new Label(1), new Label(4))");
		System.out.println(v.getVariable("A", "E", (new Label(1)).toString(), (new Label(4)).toString()));
		System.out.println("tesing getVariable(A, E, new Label(1), new Label(4)) finished");
		ArrayList<String> arr=v.getAllVariables();
		System.out.println("testing getAllVariables()");
		for(String s: arr){
			System.out.println(s);
		}
		System.out.println("testing getAllVariables() finished");
		System.out.println("testing sum(B,+,1,1)");
		String[] s={"B","+","1","1"};
		Linear l=v.getSum().operate(s);
		System.out.println(l);
		System.out.println("testing sum(B,+,1,1) finished");
		System.out.println("testing sum(B,+,5,+)");
		String[] s1={"B","+","5","+"};
		Linear l1=v.getSum().operate(s1);
		System.out.println(l1);
		System.out.println("testing sum(B,+,5,+) finished");
		System.out.println("testing sums(*,*,+,+)");
		String[] s2={"*","*","+","+"};
		ArrayList<Linear> arr1=v.generateLinears(s2, v.getSum());
		for(Linear li:arr1){
			System.out.println(li);
		}
		System.out.println("testing sums(*,*,+,+) finished");
		System.out.println("testing sum(A,E,+,+)");
		String[] s3={"A","E","+","+"};
		Linear l3=vr.getSum().operate(s3);
		System.out.println(l3);
		System.out.println("testing sum(A,E,+,+) finished");
	}
}