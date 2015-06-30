package org.renci.pharos.lp;

import java.util.HashMap;

class NthDimIterator implements Filter{
	private int dim;
	private HashMap<String, Boolean> seenitb4;
	NthDimIterator(int n){
		dim=n;
		seenitb4=new HashMap<String, Boolean>();
	}
	@Override
	public boolean test(String s) {
		String[] tokens=s.split("[_]");
		String ss=tokens[dim];
		if(seenitb4.containsKey(ss))return false;
		else{
			seenitb4.put(ss, true);
			return true;
		}
	}
	
}