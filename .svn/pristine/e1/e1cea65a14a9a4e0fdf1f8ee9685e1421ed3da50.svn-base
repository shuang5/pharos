package org.renci.pharos.flow;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.common.collect.Range;
//a simple adaptor for test purpose
//e.g. 1-2/0-7 is converted to label 1-2 by stripping off the mask
public class FlowLabels extends FlowSpace{
	private ArrayList<Integer> labels=new ArrayList<Integer>();
	public FlowLabels(FlowSpace fs){		
		super();
		merge(fs);
		Iterator<FlowUnit> it=fs.getFlowsets().iterator();
		while(it.hasNext()){
			Range<Integer> r=it.next().getRange();
			for(int i=r.lowerEndpoint();i<=r.upperEndpoint();i++){
				labels.add(i);
			}
		}
	}
	
	public Iterator<Integer> getIterator(){
		return labels.iterator();
	}
	public int size(){
		return labels.size();
	}

	public boolean equals(FlowLabels other){
		if(other.size()!=this.size())return false;
		Iterator<Integer> oit=other.getIterator();
		Iterator<Integer> it=this.getIterator();
		while(it.hasNext()){
			if(oit.next()!=it.next())return false;
		}
		return true;
	}
}
