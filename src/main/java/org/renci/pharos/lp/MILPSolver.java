package org.renci.pharos.lp;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.javailp.Solver;
import net.sf.javailp.SolverFactory;
import net.sf.javailp.SolverFactoryLpSolve;



abstract class MILPSolver {
	protected PProblem problem;
	private SolverFactory factory = new SolverFactoryLpSolve(); // use lp_solve
	private final static Logger logger = Logger.getLogger(MILPSolver.class.getName());
	protected ArrayList<String> arr;
	//SolverFactory factory = new SolverFactoryGurobi(); // use gurobi
	{
		factory.setParameter(Solver.VERBOSE, 0); 
		factory.setParameter(Solver.TIMEOUT, 10000); // set timeout to 10000 seconds
		
	}
	MILPSolver(PProblem pp,ArrayList<String> ar){
		problem=pp;
		arr=ar;
		init();
	}
	OptimizationResult solve(){
		setObjective();
		Solver solver = factory.get(); // you should use this solver only once for one problem
		logger.log(Level.INFO,"Solving..."+problem.numberOfVariables()+" variables");
		final OptimizationResult or=new OptimizationResult(problem.getProviderTopo(),
				problem.getRequestTopo(), problem);
		or.setResult(problem.solve(solver));
		if(or.getResult()!=null){
			logger.log(Level.INFO,"Objective: "+String.valueOf(or.getResult().getObjective()));
			or.setObjective(String.valueOf(or.getResult().getObjective()));
			for(String s:arr){
				if(or.getResult().getPrimalValue(s).intValue()>0) 
					  or.getNzValues().add(s+"="+(or.getResult().getPrimalValue(s)).toString());
			}
		}
		else logger.log(Level.INFO,"no feasible soltuion found!");
		//Graph g=or.toGraph1();
		return or;
	}
	public abstract void init();
	public abstract void setObjective();

}
