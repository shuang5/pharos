package org.renci.pharos.lp;


import java.io.File;
import java.io.IOException;

import net.sf.javailp.Linear;
import net.sf.javailp.Problem;
import net.sf.javailp.Result;
import net.sf.javailp.Solver;

import com.tinkerpop.blueprints.Graph;

abstract class ProblemDecorator implements PProblem{
	PProblem pproblem;
	public Problem problem() {
		return pproblem.problem();
	}
	
	public void setObjective(Linear l, OBJ obj) {
		pproblem.setObjective(l, obj);
	}

	public Result solve(Solver solver) {
		return pproblem.solve(solver);
	}
	
	public Matrix demandMatrix() {
		return pproblem.demandMatrix();
	}
	public Graph getRequestTopo() {
		return pproblem.getRequestTopo();
	}
	public Graph getProviderTopo() {
		return pproblem.getProviderTopo();
	}
	public int numberOfVariables(){
		return pproblem.variables().size();
	}
	public void saveProblem(File file) throws IOException{
		pproblem.saveProblem(file);
	}
}