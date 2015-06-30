package org.renci.pharos.lp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class NetworkTopology implements Array2D{
	private HashMap<String,ArrayList<String>> connectivity;
	public NetworkTopology(ArrayList<String> s){
		//from edge list to node adjacency 
		connectivity=new HashMap<String,ArrayList<String>>();
		for(String ss:s){
			String delims="[_]";
			String[] tokens=ss.split(delims);
			if(connectivity.containsKey(tokens[0])){
				connectivity.get(tokens[0]).add(tokens[1]);
			}
			else{
				ArrayList<String> al=new ArrayList<String>();
				al.add(tokens[1]);
				connectivity.put(tokens[0], al);
			}
		}
	}
// TODO Remove unused code found by UCDetector
// 	public Iterator<String> getNeighborIt(String from, String to) {
// 		if(from.equals("*") && to.equals("*")){
// 			return this.iterator();
// 		}
// 		else if(from.equals("*")){
// 			return connectivity.get(to).iterator();
// 		}
// 		else if(to.equals("*")){
// 			return connectivity.get(from).iterator();
// 		}
// 		return null;
// 	}

	private class Iterator2D implements Iterator<String>{
		private Iterator<String> key_it=connectivity.keySet().iterator();
		private Iterator<String> val_it;
		private String key;
		
		public boolean hasNext() {			
			if(val_it==null)return key_it.hasNext();
			else if(val_it.hasNext())return true;
			else if(key_it.hasNext())return true;
			else return false;
		}

		public String next() {
			while(hasNext()){
				if(key==null)
					key=key_it.next();
				if(val_it==null)
					val_it=connectivity.get(key).iterator();
				if(val_it.hasNext())
					return key+"_"+val_it.next();
				else {
					key=key_it.next();
					val_it=connectivity.get(key).iterator();
				}
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
		return new Iterator2D();
	}
	
	private class Iterator1D implements Iterator<String>{
		Iterator<String> it;
		int state=0;
		String val;
		public Iterator1D(String from,String to){
			if(from.equals("*") && !to.equals("*")){
				if(connectivity.containsKey(to))
					it=connectivity.get(to).iterator();
				state=1;
				val=to;
			}
			else if(to.equals("*") && !from.equals("*")){
				if(connectivity.containsKey(from))
					it=connectivity.get(from).iterator();
				state=2;
				val=from;
			}
			else throw new IllegalArgumentException();
		}
		public boolean hasNext() {	
			if(it==null)return false;
			else
				return it.hasNext();
		}

		public String next() {
			if(hasNext()){
				if(state==1)
					return it.next()+"_"+val;
				else 
					return val+"_"+it.next();
			}
			else 
				return null;
		}

		@Override
		public void remove() {
			throw new IllegalStateException("dont remove!");
		}
		
	}
	public Iterator<String> iterator(String from, String to) {
		if(from.equals("*") && to.equals("*"))
			return this.iterator();
		else if(!from.equals("*") && !to.equals("*")){
			if(connectivity.containsKey(from) && connectivity.get(from).contains(to)){
				ArrayList<String> ar=new ArrayList<String>();
				ar.add(from+"_"+to);
				return ar.iterator();
			}
			else return null;
		}
		else {
			return new Iterator1D(from,to);
		}
	}
	public int getDimension() {
		return 2;
	}
	
}