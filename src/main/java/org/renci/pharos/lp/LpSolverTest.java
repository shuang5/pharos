package org.renci.pharos.lp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import net.sf.javailp.Linear;
import net.sf.javailp.OptType;
import net.sf.javailp.Problem;
import net.sf.javailp.Result;
import net.sf.javailp.Solver;
import net.sf.javailp.SolverFactory;
import net.sf.javailp.SolverFactoryLpSolve;
public class LpSolverTest {
	private final static Logger logger = Logger.getLogger(LpSolverTest.class.getName());
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;
	
	static SolverFactory factory = new SolverFactoryLpSolve(); // use lp_solve
	{
	  factory.setParameter(Solver.VERBOSE, 0); 
	  factory.setParameter(Solver.TIMEOUT, 10000); // set timeout to 10000 seconds
	}
	public static void init() throws SecurityException, IOException{
		 Logger l = Logger.getLogger("");
		 l.setLevel(Level.FINE);
		 fileTxt = new FileHandler("./data/logging.txt");
		 formatterTxt = new SimpleFormatter();
		 fileTxt.setFormatter(formatterTxt);
		 logger.addHandler(fileTxt);


		 
	}
	{
	    try {
			LpSolverTest.init();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void test1(){
		Problem problem = new Problem();

		Linear linear = new Linear();
		linear.add(143, "x");
		linear.add(60, "y");

		problem.setObjective(linear, OptType.MAX);

		linear = new Linear();
		linear.add(120, "x");
		linear.add(210, "y");

		problem.add(linear, "<=", 15000);

		linear = new Linear();
		linear.add(110, "x");
		linear.add(30, "y");

		problem.add(linear, "<=", 4000);

		linear = new Linear();
		linear.add(1, "x");
		linear.add(1, "y");

		problem.add(linear, "<=", 75);

		//problem.setVarType("x", Integer.class);
		//problem.setVarType("y", Integer.class);

		Solver solver = factory.get(); // you should use this solver only once for one problem
		Result result = solver.solve(problem);

		System.out.println(result);

		/**
		* Extend the problem with x <= 16 and solve it again
		*/
		problem.setVarUpperBound("x", 16);

		solver = factory.get();
		result = solver.solve(problem);

		System.out.println(result);
	}
	public static void test2() throws FileNotFoundException, IOException{

		  logger.log(Level.INFO, "setting source to A, destination to F");
		  MCFlowProblem problem=new MCFlowProblem(new SSSDFlowProblem(new ILP (new LpProblem())));
		  problem.addLabelConstraints();
		  problem.addCapacityConstraints();
		  problem.addRequestConstraints();
		  problem.addFlowConservationConstraints();
		  //objective
		  logger.log(Level.INFO,"setting objective function");
		  ArrayList<Linear> ports=((FlowAssignmentVariables8D)problem.variables()).getPorts();
		  Linear linear=new Linear();
			for(Linear p:ports){
				String v=FlowAssignmentVariables8D.getRoutingVariable((String) p.get(0).getVariable());
				problem.problem().setVarType(v,Boolean.class);
				linear.add(1,v);
				p.add(-1*p.size(), v);
				problem.problem().add(p,"<=",0);
			}
		  problem.setObjective(linear, OBJ.MIN);
		  
		  Solver solver = factory.get(); // you should use this solver only once for one problem
		  logger.log(Level.INFO,"Solving...");
		  Result result = problem.solve(solver);
		  System.out.println(problem.problem());
		  ArrayList<String> res=new ArrayList<String>();
		  if(result!=null){
			  for(String s:problem.getProviderVariables()){
				  if(result.getPrimalValue(s).intValue()>0) 
					  res.add(s+"="+result.getPrimalValue(s));
			  }
			  logger.log(Level.INFO,res.toString()+"\nObjective:"+String.valueOf(result.getObjective()));
		  }
		  else logger.log(Level.INFO,"no feasible soltuion found!");
	}
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	test2();
	 /* 
    try {
      // Create a problem with 4 variables and 0 constraints
      LpSolve solver = LpSolve.makeLp(0, 4);

      // add constraints
      solver.strAddConstraint("3 2 2 1", LpSolve.LE, 4);
      solver.strAddConstraint("0 4 3 1", LpSolve.GE, 3);

      // set objective function
      solver.strSetObjFn("2 3 -2 3");

      // solve the problem
      solver.solve();

      // print solution
      System.out.println("Value of objective function: " + solver.getObjective());
      double[] var = solver.getPtrVariables();
      for (int i = 0; i < var.length; i++) {
        System.out.println("Value of var[" + i + "] = " + var[i]);
      }

      // delete the problem and free memory
      solver.deleteLp();
    }
    catch (LpSolveException e) {
       e.printStackTrace();
    }
    */
  }

}
