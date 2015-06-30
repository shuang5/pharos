package org.renci.pharos.lp;

import java.util.ArrayList;

import net.sf.javailp.Linear;

class PortUsageLPRelaxation extends PortUsageMILP {

	PortUsageLPRelaxation(PProblem pp, ArrayList<String> ar, ArrayList<Linear> p) {
		super(pp, ar, p);
	}

	@Override
	public void init() {
		((MCFlowProblem)problem).lprelaxinit();
		
	}
}
