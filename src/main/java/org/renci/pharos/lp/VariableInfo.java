package org.renci.pharos.lp;

import java.util.HashMap;
import java.util.Map;


class VariableInfo <T>{
	private Map<Variable, T> varInfo=new HashMap<Variable, T>();
	void add(Variable var, T t){
		varInfo.put(var, t);
	}
	void remove(Variable var){
		varInfo.remove(var);
	}
	T get(Variable var){
		return varInfo.get(var);
	}
}
