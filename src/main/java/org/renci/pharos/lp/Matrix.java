package org.renci.pharos.lp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.sun.tools.javac.util.Pair;

abstract class Matrix {
	//externally ID is used to uniquely identify a node or an edge
	//internally id is an integer that Blueprints automatically assigns as the identifier
	//we need a quick way to find the id from ID
	protected HashMap<String,Integer> nodeID2id=Maps.newHashMap();
	protected HashMap<String,Integer> edgeID2id=Maps.newHashMap();
	public int nodeidfromStringID(String ID){
		return nodeID2id.get(ID);
	}
	public int edgeidfromStringID(String ID){
		return edgeID2id.get(ID);
	}
	public abstract Iterator<Entry<Pair<String, String>, EdgeDisc>> getIterator();
	
}