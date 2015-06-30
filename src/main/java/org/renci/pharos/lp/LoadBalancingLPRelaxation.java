package org.renci.pharos.lp;

import java.util.ArrayList;

import net.sf.javailp.Linear;


class LoadBalancingLPRelaxation extends LoadBalancingMILP{
	LoadBalancingLPRelaxation(PProblem pp, ArrayList<String> ar, ArrayList<Linear> p) {
		super(pp, ar,p);
	}

	@Override
	public void init() {
		((MCFlowProblem)problem).lprelaxinit();
		
	}
}
